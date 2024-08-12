package br.com.mizaeldouglas.api.controller.transaction;


import br.com.mizaeldouglas.api.domain.transaction.Transaction;
import br.com.mizaeldouglas.api.service.transaction.TransactionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("transactions")
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public Transaction create(@RequestBody Transaction transaction) {
        return transactionService.create(transaction);
    }

    @GetMapping
    public List<Transaction> list() {
        return transactionService.list();
    }
}
