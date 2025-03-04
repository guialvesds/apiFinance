package gads.web.financeOn.infrastructure.repository;

import gads.web.financeOn.infrastructure.entity.WalletEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WalletRepository extends MongoRepository<WalletEntity, String> {
}
