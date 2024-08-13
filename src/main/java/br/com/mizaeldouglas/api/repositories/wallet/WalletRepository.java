package br.com.mizaeldouglas.api.repositories.wallet;

import br.com.mizaeldouglas.api.domain.wallet.Wallet;
import org.springframework.data.repository.ListCrudRepository;

public interface WalletRepository extends ListCrudRepository<Wallet, Long> {
}
