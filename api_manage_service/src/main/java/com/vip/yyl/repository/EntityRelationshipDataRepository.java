package com.vip.yyl.repository;

import com.vip.yyl.domain.EntityRelationshipData;
import org.springframework.data.mongodb.repository.MongoRepository;

@SuppressWarnings("unused")
public interface EntityRelationshipDataRepository extends MongoRepository<EntityRelationshipData, String> {

}
