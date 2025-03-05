package gads.web.financeOn.business.services;

import gads.web.financeOn.business.exceptions.BusinessException;
import gads.web.financeOn.infrastructure.entity.UserEntity;
import gads.web.financeOn.infrastructure.entity.WalletEntity;
import gads.web.financeOn.infrastructure.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class WalletService {

    private WalletRepository walletRepository;

    @Autowired
    public WalletService(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    public List<WalletEntity> findAll(){
        return walletRepository.findAll();
    }

    public WalletEntity findById(String id){
        return this.walletRepository.findById(id).orElseThrow(() -> new BusinessException("Wallet not found"));
    }

    public WalletEntity save(WalletEntity wallet, UserEntity userId){
        wallet.setUser(userId);
        return walletRepository.save(wallet);
    }

    public void delete(String userId){
        this.walletRepository.deleteById(userId);
    }


}
