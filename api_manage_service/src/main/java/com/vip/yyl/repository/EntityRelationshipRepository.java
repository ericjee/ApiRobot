package com.vip.yyl.repository;

import com.vip.yyl.domain.EntityRelationship;
import org.springframework.data.mongodb.repository.MongoRepository;

@SuppressWarnings("unused")
public interface EntityRelationshipRepository extends MongoRepository<EntityRelationship, String> {

}
