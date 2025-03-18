package gads.web.financeOn.infrastructure.repository;

import gads.web.financeOn.infrastructure.entity.TransactionEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TransactonRepository extends MongoRepository<TransactionEntity, String> {
}
