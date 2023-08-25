package per.muyi.bosai.service;

import per.muyi.bosai.model.SessionModel;

/**
 * @author chen_muyi
 * @date 2023/8/25 14:31
 */
public interface StorageService {
    void savaSession(SessionModel sessionModel);
    SessionModel getSession(String sessionId);

}
