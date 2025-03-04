package gads.web.financeOn.business.services;

import gads.web.financeOn.infrastructure.entity.UserEntity;
import gads.web.financeOn.infrastructure.entity.WalletEntity;
import gads.web.financeOn.infrastructure.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WalletService {

    private WalletRepository walletRepository;

    @Autowired
    public WalletService(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    public WalletEntity save(WalletEntity wallet, UserEntity userId){
        wallet.setUser(userId);
        return walletRepository.save(wallet);
    }

}
