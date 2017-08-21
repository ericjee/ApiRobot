
package com.vip.yyl.repository;

import com.vip.yyl.domain.Star;
import org.springframework.data.mongodb.repository.MongoRepository;

@SuppressWarnings("unused")
public interface StarRepository extends MongoRepository<Star, String> {

}
