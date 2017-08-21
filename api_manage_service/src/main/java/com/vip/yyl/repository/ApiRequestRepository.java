
package com.vip.yyl.repository;

import com.vip.yyl.domain.ApiRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

@SuppressWarnings("unused")
public interface ApiRequestRepository extends MongoRepository<ApiRequest, String> {

    List<ApiRequest> findDistinctRfRequestByApiUrl();

}
