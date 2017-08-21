package com.vip.yyl.service;

import com.vip.yyl.repository.ActivityLogRepository;
import com.vip.yyl.domain.ActivityLog;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by king.yu on 2016/12/16.
 */
@Service
public class ActivityLogImpl implements ActivityLogService {
    @Inject
    private ActivityLogRepository activityLogRepository;

    @Override
    public ActivityLog save(ActivityLog activityLog) {
        return activityLogRepository.save(activityLog);
    }

    @Override
    public void delete(ActivityLog activityLog) {
        activityLogRepository.delete(activityLog);
    }

    @Override
    public ActivityLog findOne(String id) {
        return activityLogRepository.findOne(id);
    }

    @Override
    public List<ActivityLog> findAll() {
        return activityLogRepository.findAll();
    }
}
