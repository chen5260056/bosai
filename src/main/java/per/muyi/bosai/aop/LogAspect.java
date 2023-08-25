package per.muyi.bosai.aop;


import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.InputStreamSource;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import per.muyi.bosai.model.BaseResponseModel;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Aspect
@Slf4j
@Configuration
public class LogAspect {
    private static final Gson gson = new Gson().newBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

    public LogAspect() {
    }

    @Pointcut("@within(org.springframework.web.bind.annotation.RestController)")
    public void pointCut() {
    }

    @Around("pointCut()")
    public Object proceed(ProceedingJoinPoint point) {
        Object result = null;
        String requestId = UUID.randomUUID().toString().replaceAll("-", "");
        recordRequestInfo(requestId, point);
        try {
            result = point.proceed();
        } catch (Throwable throwable) {
            result = exceptionHandler(point,throwable);
        } finally {
            recordResponseInfo(requestId, result);
        }
        return result;
    }

    private Object exceptionHandler(ProceedingJoinPoint point, Throwable throwable) {
        throwable.printStackTrace();
        MethodSignature signature = (MethodSignature) point.getSignature();
        Class<?> returnType = signature.getMethod().getReturnType();
        Object result = null;
        try {
            result = returnType.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (result instanceof BaseResponseModel){
            String errorMessage = throwable.getMessage();
            if (StringUtils.isEmpty(errorMessage)){
                errorMessage = "未知异常，请联系开发";
            }
            ((BaseResponseModel) result).setErrorMessage(errorMessage);
        }
        return result;
    }

    private HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
    }


    private void recordRequestInfo(String requestId, ProceedingJoinPoint point) {
        HttpServletRequest request = getRequest();
        List args = Optional.ofNullable(point.getArgs())
                .map((s) -> (List) Stream.of(s).map(this::getArgs)
                        .collect(Collectors.toList())).orElse(null);
        log.info("requestId:{},method:{}, url:{}, host:{},args:{}", requestId, request.getMethod(), request.getRequestURI(), request.getRemoteHost(), gson.toJson(args));
    }

    private void recordResponseInfo(String requestId, Object result) {
        log.info("responseId:{}, result:{}", requestId, gson.toJson(result));
    }

    private Object getArgs(Object arg) {
        if (arg instanceof ServletRequest) {
            return "HttpServletRequest";
        } else if (arg instanceof ServletResponse) {
            return "HttpServletResponse";
        } else {
            return arg instanceof InputStreamSource ? "InputStreamSource" : arg;
        }
    }
}
