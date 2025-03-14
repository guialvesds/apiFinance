package gads.web.financeOn.business.services;

import gads.web.financeOn.business.exceptions.BusinessException;
import gads.web.financeOn.infrastructure.dto.WalletDTO;
import gads.web.financeOn.infrastructure.entity.UserEntity;
import gads.web.financeOn.infrastructure.entity.WalletEntity;
import gads.web.financeOn.infrastructure.repository.UserRepository;
import gads.web.financeOn.infrastructure.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WalletService {

    @Autowired
    private WalletRepository walletRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    public WalletService(WalletRepository walletRepository, UserRepository userRepository) {

        this.walletRepository = walletRepository;
        this.userRepository = userRepository;
    }

    // Aqui converto WalletEntity para WalletDTO
    private WalletDTO toDTO(WalletEntity walletEntity) {
        WalletDTO walletDTO = new WalletDTO();

        walletDTO.setId(walletEntity.getId());
        walletDTO.setName(walletEntity.getName());
        walletDTO.setBalance(walletEntity.getBalance());
        walletDTO.setCreatedAt(walletEntity.getCreatedAt());

        if(walletEntity.getUser() != null) {
            walletDTO.setUserId(walletEntity.getUser().getId());
            walletDTO.setUserPrimaryName(walletEntity.getUser().getPrimaryName());
            walletDTO.setUserSecondName(walletEntity.getUser().getSecondName());
            walletDTO.setUserEmail(walletEntity.getUser().getEmail());

        }


        return walletDTO;
    }

    // Agora converto WalletDTO para WalletEntity

    private WalletEntity toEntity(WalletDTO walletDTO){
        WalletEntity walletEntity = new WalletEntity();

        walletEntity.setId(walletDTO.getId());
        walletEntity.setName(walletDTO.getName());
        walletEntity.setBalance(walletDTO.getBalance());
        walletEntity.setCreatedAt(walletDTO.getCreatedAt());
        // Aqui não passo o userID pois irei buscar no UserEntity no repo

        return walletEntity;

    }

    public List<WalletDTO> findAll(){
        return walletRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public WalletDTO findById(String id){
      WalletEntity walletEntity =  this.walletRepository.findById(id).orElseThrow(() -> new BusinessException("Wallet not found"));

        return toDTO(walletEntity);
    }

    public WalletEntity save1(WalletEntity wallet, UserEntity userId){
        wallet.setUser(userId);
        return walletRepository.save(wallet);
    }

    public WalletDTO save(WalletDTO walletDTO, String userId) {
        WalletEntity walletEntity = toEntity(walletDTO);

        // Aqui busca o UserEntity pelo userId
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new BusinessException("User not found"));

        walletEntity.setUser(userEntity);

        WalletEntity savedWallet = walletRepository.save(walletEntity);

        return toDTO(savedWallet);
    }

    public void delete(String userId){
        this.walletRepository.deleteById(userId);
    }


}
