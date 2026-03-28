package com.bbquantum.smartfarming.Service;

import com.bbquantum.smartfarming.Constants.ActionStatus;
import com.bbquantum.smartfarming.Constants.LogActions;
import com.bbquantum.smartfarming.Entity.SystemActivityLog;
import com.bbquantum.smartfarming.Repository.SystemActivityLogRepo;
import com.bbquantum.smartfarming.Utility.IdGenerationUtil;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ActivityLogService {

    private final SystemActivityLogRepo logRepo;

    private final IdGenerationUtil idGenerationUtil;

    public ActivityLogService(SystemActivityLogRepo logRepo, IdGenerationUtil idGenerationUtil) {
        this.logRepo = logRepo;
        this.idGenerationUtil = idGenerationUtil;
    }

    public void logActivity(LogActions actionType, String message, ActionStatus actionStatus) {
        try {
            SystemActivityLog log = new SystemActivityLog();

            log.setLogId(idGenerationUtil.generateEntityId("LOG"));
            log.setTimeOfActivity(LocalDateTime.now());
            log.setActionType(actionType);
            log.setMessage(message);
            log.setActionStatus(actionStatus);
            logRepo.save(log);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
