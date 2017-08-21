
package com.vip.yyl.repository;

import com.vip.yyl.domain.parameters.HeaderParameter;
import org.springframework.data.mongodb.repository.MongoRepository;

@SuppressWarnings("unused")
public interface HttpRequestHeaderRepository extends MongoRepository<HeaderParameter, String> {

}
