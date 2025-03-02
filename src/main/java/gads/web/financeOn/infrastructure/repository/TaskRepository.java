package gads.web.financeOn.infrastructure.repository;

import gads.web.financeOn.infrastructure.entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<UserEntity, String> {

}
