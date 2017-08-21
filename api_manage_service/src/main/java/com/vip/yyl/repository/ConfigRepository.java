
package com.vip.yyl.repository;

import com.vip.yyl.domain.Config;
import org.springframework.data.mongodb.repository.MongoRepository;

@SuppressWarnings("unused")
public interface ConfigRepository extends MongoRepository<Config, String> {

}
