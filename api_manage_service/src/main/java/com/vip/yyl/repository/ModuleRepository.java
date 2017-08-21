package com.vip.yyl.repository;

import com.vip.yyl.domain.Module;
import org.springframework.data.mongodb.repository.MongoRepository;

@SuppressWarnings("unused")
public interface ModuleRepository extends MongoRepository<Module, String> {

}
