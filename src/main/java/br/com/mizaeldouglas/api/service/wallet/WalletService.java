package br.com.mizaeldouglas.api.service.wallet;

import br.com.mizaeldouglas.api.domain.wallet.Wallet;
import br.com.mizaeldouglas.api.repositories.wallet.WalletRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WalletService {
    private final WalletRepository walletRepository;

    public WalletService(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    public void createWallet(Wallet wallet) {
        walletRepository.save(wallet);
    }

    public void updateWallet(Wallet wallet) {
        walletRepository.save(wallet);
    }

    public void deleteWallet(Long id) {
        walletRepository.deleteById(id);
    }

    public List<Wallet> list() {
        return walletRepository.findAll();
    }



}
