package gads.web.financeOn.infrastructure.repository;

import gads.web.financeOn.infrastructure.entity.WalletEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface WalletRepository extends MongoRepository<WalletEntity, String> {
    Optional<WalletEntity> findByUserIdAndName(String user, String name);
}
