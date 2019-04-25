package com.rbsn.schedule.common.support.key;

import com.rbsn.schedule.common.util.PropertiesUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * ID管理
 *
 * @author Jiang
 * @date 2018/4/21
 */
@Component("IdManager")
public class IdManagerImpl implements IdManager{

    private static Logger logger = LoggerFactory.getLogger(IdManagerImpl.class);

    private SnowflakeIdWorker snowflakeIdWorker;

    public IdManagerImpl() throws Exception {
        String workerId = PropertiesUtil.getString("worker.id");
        String dataCenterId = PropertiesUtil.getString("data.center.id");
        logger.info("{workerId='{}', dataCenterId='{}'}",workerId,dataCenterId);
        long workerIdLong;
        if(StringUtils.isBlank(workerId)){
            workerIdLong = 0L;
        }else {
            workerIdLong = Long.valueOf(workerId);
        }
        long dataCenterIdLong;
        if(StringUtils.isBlank(dataCenterId)){
            dataCenterIdLong = 0L;
        }else {
            dataCenterIdLong = Long.valueOf(dataCenterId);
        }
        if(workerIdLong<0||workerIdLong>31){
            throw new Exception("workerI必须在0-31之间");
        }
        if(dataCenterIdLong<0||dataCenterIdLong>31){
            throw new Exception("dataCenterId必须在0-31之间");
        }
        snowflakeIdWorker = new SnowflakeIdWorker(workerIdLong,dataCenterIdLong);
    }

    @Override
    public String genetateId(){
        return String.valueOf(snowflakeIdWorker.nextId());
    }
}
