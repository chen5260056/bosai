package per.muyi.bosai.service;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import per.muyi.bosai.model.SessionModel;
import per.muyi.bosai.queue.SessionDelay;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author chen_muyi
 * @date 2023/8/25 14:32
 */
@Service
@ConditionalOnProperty(name = "storage",havingValue = "memory", matchIfMissing = true)
public class LocalStorageServiceImpl implements StorageService {
    private ConcurrentHashMap sessionMap = new ConcurrentHashMap();

    @Override
    public void savaSession(SessionModel sessionModel) {

    }

    @Override
    public SessionModel getSession(String sessionId) {
        return null;
    }


}
