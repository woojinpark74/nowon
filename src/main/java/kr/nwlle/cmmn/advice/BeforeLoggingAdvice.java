package kr.nwlle.cmmn.advice;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.aop.MethodBeforeAdvice;

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
public class BeforeLoggingAdvice implements MethodBeforeAdvice {

    public BeforeLoggingAdvice() {
    }

    public void before(Method method, Object args[], Object target) throws Throwable {

        String className = target.getClass().getInterfaces()[0].getName();

        if (className.indexOf("Mapper") < 0) {
            className = target.getClass().getName();
        }

        StringBuilder logSb = new StringBuilder(String.format("%s.%s Arguments\n", className, method.getName()));

        if (log.isDebugEnabled()) {
            if (args != null && args.length > 0) {
                for (int i = 0; i < args.length; i++) {
                    if (args[i] instanceof java.util.HashMap) {
                        logSb.append(String.format("Argument[%s] : %s\n", i, args[i]));
                    } else {
                        logSb.append(String.format("Argument[%s] : %s\n", i, toString(args[i])));
                    }
                }
            }

            log.debug("{}", logSb.toString());
        }
    }

    private String toString(Object object) {
        List<String> blankList = new ArrayList<String>();
        List<String> notBlankList = new ArrayList<String>();

        PropertyDescriptor[] pds = PropertyUtils.getPropertyDescriptors(object);
        for (PropertyDescriptor pd : pds) {
            try {
                String property = pd.getName();
                Object propertyValue = PropertyUtils.getSimpleProperty(object, property);

                String tmpStr = String.format("%s", propertyValue);

                if (StringUtils.isBlank(tmpStr) || "null".equals(tmpStr)) {
                    blankList.add(String.format("%s=%s", property, propertyValue));
                } else {
                    notBlankList.add(String.format("%s=%s", property, propertyValue));
                }

            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }

        Collections.sort(blankList);
        Collections.sort(notBlankList);

        // log.debug("blanList = {}", blankList);
        // log.debug("notBlankList = {}", notBlankList);

        String blank = blankList.toString().replaceAll("\r\n|\n", "");
        String notBlank = notBlankList.toString().replaceAll("[,]", ",\n\t");

        return String.format("%s\n\tBlankValues%s", notBlank, blank);
    }
}
