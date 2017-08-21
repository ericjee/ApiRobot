
package com.vip.yyl.repository;

import com.vip.yyl.domain.ActivityLog;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

//@SuppressWarnings("unused")
//public interface ActivityLogRepository extends MongoRepository<ActivityLog, String> {
//
//}
//@SuppressWarnings("unused")
@Service
public interface ActivityLogRepository extends MongoRepository<ActivityLog,String> {

}
