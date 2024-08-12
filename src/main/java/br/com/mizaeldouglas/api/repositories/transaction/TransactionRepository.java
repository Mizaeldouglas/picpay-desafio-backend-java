package br.com.mizaeldouglas.api.repositories.transaction;

import br.com.mizaeldouglas.api.domain.transaction.Transaction;
import org.springframework.data.repository.ListCrudRepository;

public interface TransactionRepository extends ListCrudRepository<Transaction, Long> {

}
