package com.vip.yyl.service;

import com.vip.yyl.domain.ActivityLog;

import java.util.List;

/**
 * Created by king.yu on 2016/12/16.
 */

public interface ActivityLogService {

    ActivityLog save(ActivityLog activityLog);

    void delete(ActivityLog activityLog);

    ActivityLog findOne(String id);

    List<ActivityLog> findAll();
}
