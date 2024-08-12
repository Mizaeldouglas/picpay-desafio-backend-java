package br.com.mizaeldouglas.api.service.transaction;

import br.com.mizaeldouglas.api.domain.transaction.Transaction;
import br.com.mizaeldouglas.api.enums.WalletType;
import br.com.mizaeldouglas.api.exceptions.transaction.InvalidTransactionException;
import br.com.mizaeldouglas.api.repositories.transaction.TransactionRepository;
import br.com.mizaeldouglas.api.repositories.wallet.WalletRepository;
import br.com.mizaeldouglas.api.service.authorization.AuthorizerService;
import br.com.mizaeldouglas.api.service.notification.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TransactionService {
    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionService.class);

    private final TransactionRepository transactionRepository;
    private final WalletRepository walletRepository;
    private final AuthorizerService authorizerService;
    private final NotificationService notificationService;

    public TransactionService(TransactionRepository transactionRepository, WalletRepository walletRepository, AuthorizerService authorizerService, NotificationService notificationService) {
        this.transactionRepository = transactionRepository;
        this.walletRepository = walletRepository;
        this.authorizerService = authorizerService;
        this.notificationService = notificationService;
    }

    @Transactional
    public Transaction create(Transaction transaction) {
        validate(transaction);
        var newTransaction = transactionRepository.save(transaction);
        var walletPayer = walletRepository.findById(transaction.payer()).orElseThrow(() -> new InvalidTransactionException("Payer wallet not found"));
        var walletPayee = walletRepository.findById(transaction.payee()).orElseThrow(() -> new InvalidTransactionException("Payee wallet not found"));
        walletRepository.save(walletPayer.debit(transaction.value()));
        walletRepository.save(walletPayee.credit(transaction.value()));
        authorizerService.authorize(transaction);
        notificationService.notify(transaction);

        return newTransaction;
    }

    private void validate(Transaction transaction) {
        LOGGER.info("validating transaction {}...", transaction);
        if (transaction.payer() == null || transaction.payee() == null) {
            throw new InvalidTransactionException("Payer and Payee IDs must not be null");
        }
        walletRepository.findById(transaction.payee())
                .map(payee -> walletRepository.findById(transaction.payer())
                        .map(payer -> payer.type() == WalletType.COMUM.getValue() &&
                                payer.balance().compareTo(transaction.value()) >= 0 &&
                                !payer.id().equals(transaction.payee()) ? true : null)
                        .orElseThrow(() -> new InvalidTransactionException(
                                "Invalid transaction - " + transaction)))
                .orElseThrow(() -> new InvalidTransactionException(
                        "Invalid transaction - " + transaction));
    }

    public List<Transaction> list() {
        return transactionRepository.findAll();
    }
}