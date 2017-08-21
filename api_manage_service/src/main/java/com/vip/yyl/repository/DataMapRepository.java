
package com.vip.yyl.repository;

import com.vip.yyl.domain.DataMap;
import org.springframework.data.mongodb.repository.MongoRepository;

@SuppressWarnings("unused")
public interface DataMapRepository extends MongoRepository<DataMap, String> {

}
