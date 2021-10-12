package kr.nwlle.cmmn.config;

/**
 * CommandMap 객체에 담은 resolver
 * 
 * @author 진수진
 * @since 2020.07.01
 * @version 1.0, 2020.07.01
 *          ------------------------------------------------------------------------
 *          작성자 일자 내용
 *          ------------------------------------------------------------------------
 *          진수진 2020.07.01 최초작성
 */
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import kr.nwlle.cmmn.vo.CommandMap;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CommandMapArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        // return CommandMap.class.isAssignableFrom(parameter.getParameterType());
        return parameter.getParameterType().equals(CommandMap.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

        log.debug("resolveArgeument..................");

        CommandMap commandMap = new CommandMap();
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();

        // 세션 가져오기
        // commandMap.setSessionInfo((UserSession) SessionUtil.getAttribute(Constant.USER_SESSION);

        commandMap.setIp(request.getRemoteAddr());
        String userAgent = request.getHeader("user-agent");
        if (StringUtils.isNotEmpty(userAgent) && userAgent.indexOf("(") > -1 && userAgent.indexOf(")") > -1) {
            userAgent = userAgent.substring(userAgent.indexOf("(") + 1, userAgent.indexOf(")"));
        }
        commandMap.setUserAgent(userAgent);

        Enumeration<?> enumeration = request.getParameterNames();
        while (enumeration.hasMoreElements()) {
            String key = (String) enumeration.nextElement();
            String[] values = request.getParameterValues(key);
            if (values != null) {
                if (key.equals("pageIndex") && values[0].equals("")) {
                    commandMap.put("pageIndex", "1");
                } else {
                    commandMap.put(key, (values.length > 1) ? values : values[0]);
                }
            }
        }

        /*
         * if (request.getAttribute("SELECTED_MENU_OBJ") != null) {
         * commandMap.setSelectedMenu((MenuManageVO) request.getAttribute("SELECTED_MENU_OBJ"));
         * }
         */

        String reqUrl = request.getRequestURL().toString();
        String queryString = request.getQueryString();
        if (queryString != null) {
            reqUrl += "?" + queryString;
        }

        commandMap.setTrgetUrl(reqUrl);

        log.debug("resolveArgeument..................{}", commandMap);

        return commandMap;

    }
}
