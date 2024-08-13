package br.com.mizaeldouglas.api.controller.wallet;

import br.com.mizaeldouglas.api.domain.wallet.Wallet;
import br.com.mizaeldouglas.api.service.wallet.WalletService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/wallets" , produces = "application/json")
@Tag(name = "Wallets", description = "Wallets")
public class WalletController {

    private final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @GetMapping
    @ApiResponse(responseCode = "200", description = "List of wallets")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public List<Wallet> findAll() {
       return walletService.list();
    }

}
