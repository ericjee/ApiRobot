package com.vip.yyl.repository;

import com.vip.yyl.domain.apis.custom.ScenarioDoc;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by king.yu on 2017/1/10.
 */
public interface ScenarioRepository extends MongoRepository<ScenarioDoc,String> {
}
