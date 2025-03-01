package gads.web.financeOn.business.services;

import gads.web.financeOn.infrastructure.entity.UserEntity;
import gads.web.financeOn.infrastructure.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

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
      return this.userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
  }

  public UserEntity save(UserEntity user){
      return this.userRepository.save(user);
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
