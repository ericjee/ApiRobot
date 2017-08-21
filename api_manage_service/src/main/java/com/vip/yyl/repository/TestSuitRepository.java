package com.vip.yyl.repository;

import com.vip.yyl.domain.TestSuit;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Spring Data MongoDB repository for the TestSuit entity.
 */
@SuppressWarnings("unused")
public interface TestSuitRepository extends MongoRepository<TestSuit,String> {

}
