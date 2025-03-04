package gads.web.financeOn.business.controller;

import gads.web.financeOn.business.services.WalletService;
import gads.web.financeOn.infrastructure.entity.UserEntity;
import gads.web.financeOn.infrastructure.entity.WalletEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/wallets")
public class WalletController {

    private final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @PostMapping("/{id}")
    public ResponseEntity<WalletEntity> save(@RequestBody WalletEntity wallet, @PathVariable UserEntity id){

        wallet = this.walletService.save(wallet, id);
        return ResponseEntity.status(HttpStatus.CREATED).body(wallet);
    }

}
