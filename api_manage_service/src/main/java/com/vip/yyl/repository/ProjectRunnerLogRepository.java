package com.vip.yyl.repository;

import com.vip.yyl.domain.ProjectRunnerLog;
import org.springframework.data.mongodb.repository.MongoRepository;

@SuppressWarnings("unused")
public interface ProjectRunnerLogRepository extends MongoRepository<ProjectRunnerLog, String> {

}
