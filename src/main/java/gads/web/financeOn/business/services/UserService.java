package gads.web.financeOn.business.services;

import gads.web.financeOn.infrastructure.enums.PerfilUserStatus;
import gads.web.financeOn.infrastructure.entity.UserEntity;
import gads.web.financeOn.infrastructure.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import gads.web.financeOn.business.exceptions.BusinessException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

  public UserService (UserRepository userRepository) {
      this.userRepository = userRepository;
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
              user.setCreatedAt(LocalDateTime.now());
              user.setUpdatedAt(LocalDateTime.now());
              user.setPerfil(PerfilUserStatus.BASIC);
              return userRepository.save(user);
          } else {
              throw ex;
          }
      }
  }

  public UserEntity update(UserEntity user){
      var userEntity = this.findId(user.getId());
      BeanUtils.copyProperties(user, userEntity, "id"); //Copia tudo que vem da minha propierade para o banco
      return  this.userRepository.save(userEntity);
  }

  public void delete(String userId){
       this.userRepository.deleteById(userId);
  }

}
