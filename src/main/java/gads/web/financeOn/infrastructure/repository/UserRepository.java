package gads.web.financeOn.infrastructure.repository;

import gads.web.financeOn.infrastructure.entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<UserEntity, String> {

}
