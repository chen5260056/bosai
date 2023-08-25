package per.muyi.bosai.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import per.muyi.bosai.model.BaseResponseModel;
import per.muyi.bosai.model.SessionModel;
import per.muyi.bosai.model.SessionResponse;
import per.muyi.bosai.service.StorageService;

/**
 * @author chen_muyi
 * @date 2023/8/25 15:46
 */
@RestController
public class SessionController {
    private StorageService storageService;

    public SessionController(StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping("session/{sessionId}")
    public BaseResponseModel<SessionResponse> getSessionId(@PathVariable String sessionId){
        SessionModel session = storageService.getSession(sessionId);
        SessionResponse sessionResponse = new SessionResponse();
        sessionResponse.setSessionId(session.getSessionId());
        return sessionResponse;
    }
}
