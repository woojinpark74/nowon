package kr.nwlle.cmmn.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

public class SessionUtil {

    private String Authority;
    private String ID;

    public SessionUtil() {
    };

    public SessionUtil(HttpServletRequest request) {

        // UserService userService = (UserService) SpringApplicationContext.getBean("userService");

        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null)
            return;

        for (GrantedAuthority authority : authentication.getAuthorities()) {
            this.Authority = authority.toString();
        }

        this.ID = authentication.getName();
    }

    public String getUserId() {
        return this.ID;
    }

    public String getAuthority() {
        return this.Authority;
    }

    /**
     * attribute 값을 가져 오기 위한 method
     *
     * @param String
     *            attribute key name
     * @return Object attribute obj
     */
    public static Object getAttribute(String name) {
        return (Object) RequestContextHolder.getRequestAttributes().getAttribute(name, RequestAttributes.SCOPE_SESSION);
    }

    /**
     * attribute 설정 method
     *
     * @param String
     *            attribute key name
     * @param Object
     *            attribute obj
     * @return void
     */
    public static void setAttribute(String name, Object object) {
        RequestContextHolder.getRequestAttributes().setAttribute(name, object, RequestAttributes.SCOPE_SESSION);
    }

    /**
     * 설정한 attribute 삭제
     *
     * @param String
     *            attribute key name
     * @return void
     */
    public static void removeAttribute(String name) throws Exception {
        RequestContextHolder.getRequestAttributes().removeAttribute(name, RequestAttributes.SCOPE_SESSION);
    }

    /**
     * session id
     *
     * @param void
     * @return String SessionId 값
     */
    public static String getSessionId() throws Exception {
        return RequestContextHolder.getRequestAttributes().getSessionId();
    }

}
