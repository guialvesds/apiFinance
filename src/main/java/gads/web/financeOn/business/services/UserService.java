package gads.web.financeOn.business.services;

import gads.web.financeOn.infrastructure.enums.PerfilUserStatus;
import gads.web.financeOn.infrastructure.entity.UserEntity;
import gads.web.financeOn.infrastructure.repository.UserRepository;
import gads.web.financeOn.business.exceptions.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private  UserRepository userRepository;
    @Autowired
    private  PasswordEncoder passwordEncoder;

    @Autowired
  public UserService (UserRepository userRepository, PasswordEncoder passwordEncoder) {
      this.userRepository = userRepository;
      this.passwordEncoder = passwordEncoder;
  }

  public List<UserEntity> findAll(){
     return this.userRepository.findAll();
  }

  public UserEntity findId(String userId){
      return this.userRepository.findById(userId).orElseThrow(() -> new BusinessException("User not found"));
  }

  public UserEntity findEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new BusinessException("User not found"));
    }

  public UserEntity save(UserEntity user){

      try {
          this.findEmail(user.getEmail());
          throw new BusinessException("Email already exists");

      } catch (BusinessException ex) {
          // Se a exceção for "User not found", significa que o email não existe
          if ("User not found".equals(ex.getMessage())) {
              user.setUpdatedAt(LocalDateTime.now());
              user.setPerfil(PerfilUserStatus.BASIC);
              user.setPassword(passwordEncoder.encode(user.getPassword()));
              return userRepository.save(user);
          } else {
              throw ex;
          }
      }
  }

    public UserEntity update(UserEntity user) {
        UserEntity userEntity = findId(user.getId());
        userEntity.setUpdatedAt(LocalDateTime.now());

        if (user.getPrimaryName() != null) {
            userEntity.setPrimaryName(user.getPrimaryName());
        }
        if (user.getSecondName() != null) {
            userEntity.setSecondName(user.getSecondName());
        }
        if (user.getEmail() != null) {
            userEntity.setEmail(user.getEmail());
        }
        if (user.getPassword() != null) {
            userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        if(user.getPerfil() != null) {
         userEntity.setPerfil(user.getPerfil());
        }
        return userRepository.save(userEntity);
    }

  public void delete(String userId){
       this.userRepository.deleteById(userId);
  }

}
