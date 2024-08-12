package br.com.mizaeldouglas.api.repositories.wallet;

import br.com.mizaeldouglas.api.domain.wallet.Wallet;
import org.springframework.data.repository.CrudRepository;

public interface WalletRepository extends CrudRepository<Wallet, Long> {
}
