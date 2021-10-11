package kr.nwlle.cmmn.advice;

import java.lang.reflect.Method;

import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.MethodBeforeAdvice;

import kr.nwlle.cmmn.util.SessionUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @Class Name : BeforeCommonSetterAdvice.java
 * @Description : 자세한 클래스 설명
 * @author woojinp@legitsys.co.kr
 * @since 2021. 10. 7.
 * @version 1.0
 * @see
 *      Copyright(c) 2021 HISCO. All rights reserved
 */

@Slf4j
public class BeforeCommonSetterAdvice implements MethodBeforeAdvice {

    private static final Logger log = LoggerFactory.getLogger(BeforeCommonSetterAdvice.class);

    // @Value("#{prop['channel']}")
    private String channel = "www";

    private static final String SET_PROPERTY_FOR_GET_LIST = "channel";
    private static final String[] GET_METHOD_PREFIX_LIST = { "get", "select", "retrieve", "list" };
    private static final String[] SET_PROPERTY_LIST = { "regId", "modId" };

    public BeforeCommonSetterAdvice() {
    }

    public void before(Method method, Object args[], Object target) throws Throwable {
        if (args == null || args.length < 1) { // arguments가 존재하지 않는 경우
            return;
        }

        if (isGetTypeMethod(method.getName())) { // get type인 경우 return
            for (int i = 0; i < args.length; i++) {
                setPropertyValue(args[i], SET_PROPERTY_FOR_GET_LIST, channel); // mapper에서 검색조건으로 이용
            }
            return;
        }

        String userId = (String) SessionUtil.getAttribute("userId");

        for (int i = 0; i < args.length; i++) {
            for (String propertyName : SET_PROPERTY_LIST) {
                setPropertyValue(args[i], propertyName, userId);
            }
        }
    }

    private void setPropertyValue(Object arg, String propertyName, String userId) {
        if (PropertyUtils.isWriteable(arg, propertyName)) { // setter가 존재하는 경우
            try {
                PropertyUtils.setProperty(arg, propertyName, userId);
                log.debug("set regrNo as {}", userId);
            } catch (Exception e) {
                log.error("argument type:errmsg = {}:{}" + Object.class.getName(), e.getMessage());
            }
        }
    }

    private boolean isGetTypeMethod(String methodName) {
        for (String prefix : GET_METHOD_PREFIX_LIST) {
            if (methodName.startsWith(prefix)) {
                return true;
            }
        }
        return false;
    }
}
