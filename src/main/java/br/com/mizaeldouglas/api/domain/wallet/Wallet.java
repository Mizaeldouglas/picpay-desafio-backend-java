package br.com.mizaeldouglas.api.domain.wallet;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;

@Table("WALLETS")
public record Wallet(
        @Id Long id,
        String fullName,
        String email,
        Long cpf,
        String password,
        int type,
        BigDecimal balance
) {
    public Wallet debit(BigDecimal value) {
        return new Wallet(id, fullName, email, cpf, password, type, balance.subtract(value));
    }

    public Wallet credit(BigDecimal value) {
        return new Wallet(id, fullName, email, cpf, password, type, balance.add(value));
    }
}
