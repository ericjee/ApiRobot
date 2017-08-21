package com.vip.yyl.repository;

import com.vip.yyl.domain.Environment;
import org.springframework.data.mongodb.repository.MongoRepository;

@SuppressWarnings("unused")
public interface EnvironmentRepository extends MongoRepository<Environment, String> {

}
