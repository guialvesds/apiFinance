package gads.web.financeOn.infrastructure.repository;

import gads.web.financeOn.infrastructure.entity.AddressEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AddressRepository extends MongoRepository<AddressEntity, String> {
}
