package per.muyi.bosai.service;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import per.muyi.bosai.model.SessionModel;

import java.util.concurrent.TimeUnit;

/**
 * @author chen_muyi
 * @date 2023/8/25 14:34
 */
@Service
@ConditionalOnProperty(name = "storage",havingValue = "redis", matchIfMissing = false)
public class RedisStorageServiceImpl implements StorageService {
    private RedisTemplate redisTemplate;

    public RedisStorageServiceImpl(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }


    @Override
    public void savaSession(SessionModel sessionModel) {
        redisTemplate.opsForValue().set(sessionModel.getSessionId(),sessionModel,10, TimeUnit.SECONDS);
    }

    @Override
    public SessionModel getSession(String sessionId) {
        return (SessionModel) redisTemplate.opsForValue().get(sessionId);
    }
}

