package per.muyi.bosai.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import per.muyi.bosai.model.BaseResponseModel;
import per.muyi.bosai.model.SessionModel;
import per.muyi.bosai.service.StorageService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author chen_muyi
 * @date 2023/8/25 15:50
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {
    private final static String LOGIN_PATH = "login";
    private final static String LOGIN_OUT_PATH = "loginOut";
    private final static String JSESSION = "jsession";
    private final static Integer UNLOING = 401;

    private StorageService storageService;

    public LoginInterceptor(StorageService storageService) {
        this.storageService = storageService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        if (Objects.equals(LOGIN_PATH,requestURI) || Objects.equals(LOGIN_OUT_PATH,requestURI) ){
               return true;
        }
        boolean isLogin = true;
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie:cookies){
            if (Objects.equals(cookie.getName(),JSESSION)){
                SessionModel session = storageService.getSession(cookie.getValue());
                if (Objects.isNull(session)){
                    isLogin = false;
                }
                break;
            }
        }
        if (isLogin){
            return true;
        }
        BaseResponseModel<Object> responseModel = new BaseResponseModel<>();
        responseModel.setErrorMessage("user is not logged，please login");
        response.getWriter().write(responseModel.toString());
        response.setStatus(UNLOING);
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
