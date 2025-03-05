package gads.web.financeOn.business.controller;

import gads.web.financeOn.business.services.WalletService;
import gads.web.financeOn.infrastructure.entity.UserEntity;
import gads.web.financeOn.infrastructure.entity.WalletEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/wallets")
public class WalletController {

    private final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @GetMapping
    public ResponseEntity<List<WalletEntity>> getAllWallets(){
        var wallets = this.walletService.findAll();
        return ResponseEntity.ok(wallets);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WalletEntity> findById(@PathVariable String id){
        var wallet = this.walletService.findById(id);
        return ResponseEntity.ok(wallet);
    }

    @PostMapping("/{id}")
    public ResponseEntity<WalletEntity> save(@RequestBody WalletEntity wallet, @PathVariable UserEntity id){

        wallet = this.walletService.save(wallet, id);
        return ResponseEntity.status(HttpStatus.CREATED).body(wallet);
    }

    // Build controi o objeto
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(String id) {
        this.walletService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
