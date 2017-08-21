package com.vip.yyl.repository;

import com.vip.yyl.domain.JobJenkins;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Spring Data MongoDB repository for the JobJenkins entity.
 */
@SuppressWarnings("unused")
public interface JobJenkinsRepository extends MongoRepository<JobJenkins,String> {

}
