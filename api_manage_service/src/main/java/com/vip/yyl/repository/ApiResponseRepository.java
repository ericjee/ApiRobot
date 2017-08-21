
package com.vip.yyl.repository;

import com.vip.yyl.domain.ApiResponse;
import org.springframework.data.mongodb.repository.MongoRepository;

@SuppressWarnings("unused")
public interface ApiResponseRepository extends MongoRepository<ApiResponse, String> {

}
