package com.vip.yyl.repository;

import com.vip.yyl.domain.apis.custom.ApiDoc;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by king.yu on 2017/1/10.
 */
public interface ApiDocRepository extends MongoRepository<ApiDoc,String> {

}
