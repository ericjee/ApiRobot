package com.vip.yyl.repository;

import com.vip.yyl.domain.Assertion;
import org.springframework.data.mongodb.repository.MongoRepository;

@SuppressWarnings("unused")
public interface AssertionRepository extends MongoRepository<Assertion, String> {

}



