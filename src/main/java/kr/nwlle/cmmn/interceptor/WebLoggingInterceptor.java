package kr.nwlle.cmmn.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.mobile.device.Device;
import org.springframework.mobile.device.DeviceUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import kr.nwlle.cmmn.constant.Constant;
import lombok.extern.slf4j.Slf4j;

/**
 * @Class Name : EgovWebLogInterceptor.java
 * @Description : 웹로그 생성을 위한 인터셉터 클래스
 * @Modification Information
 *               수정일 수정자 수정내용 ------- ------- ------------------- 2009. 3. 9.
 *               이삼섭 최초생성 2011. 7. 1. 이기하 패키지 분리(sym.log -> sym.log.wlg) 2020.
 *               7. 1. 진수진 메뉴코드 추가
 * @author 공통 서비스 개발팀 이삼섭
 * @since 2009. 3. 9.
 * @version
 * @see
 */

@Slf4j
public class WebLoggingInterceptor extends HandlerInterceptorAdapter {

    // @Resource(name = "EgovWebLogService")
    // private EgovWebLogService webLogService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        log.debug("WebLoggingInterceptor :: preHandle");

        String strReqURL = request.getRequestURI();

        // 모바일 여부 체크
        Device device = (Device) request.getAttribute("currentDevice");
        if (device == null) {
            device = DeviceUtils.getCurrentDevice(request);
        } else {
            request.setAttribute("IS_MOBILE", device.isMobile());
            // log.debug("=============currentDevice=====>" + device.isMobile());
        }

        log.debug("Constant.ADMIN_ROOT = " + Constant.ADMIN_ROOT);
        log.debug("Constant.USER_ROOT  = " + Constant.USER_ROOT);
        log.debug("strReqURL         = " + strReqURL);

        return true;

    }

    /**
     * 웹 로그정보를 생성한다.
     *
     * @param HttpServletRequest
     *            request, HttpServletResponse response, Object
     *            handler
     * @return
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modeAndView) throws Exception {

        // WebLog webLog = new WebLog();
        String strReqURL = request.getRequestURI();
        String uniqId = "";
        String menuNo = String.valueOf(request.getAttribute("SELECTED_MENU_NO"));

        // log.debug("웹 로그정보 저장 => URL : {} MENU_NO : {} " + strReqURL + " " + menuNo);

        // 사용자도 관리자도 아닌 경우 로그를 남기지 않는다
        if (!strReqURL.startsWith(Constant.ADMIN_ROOT) && !strReqURL.startsWith(Constant.USER_ROOT)) {
            return;
        }

        if ("/web/member/logout".equals(strReqURL)) {
            return;
        } else {

            /*
             * if (strReqURL.startsWith(Constant.ADMIN_ROOT)) {
             * LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
             * uniqId = (user == null || user.getId() == null) ? "" : user.getId();
             * } else {
             * HttpSession session = request.getSession();
             * UserSession userSession = (UserSession) session.getAttribute(Constant.USER_SESSION);
             * LoginVO user = userSession.getUserInfo();
             * InetAddress inetAddress = InetAddress.getLocalHost();
             * String strIpAdress = inetAddress.getHostAddress();
             * uniqId = (user == null || user.getUniqId() == null) ? "" : user.getId();
             * if (strReqURL.indexOf("Ajax") < 0) {
             * if (request.getQueryString() != null && !request.getQueryString().equals("")) {
             * strReqURL += "?" + request.getQueryString();
             * }
             * if (strReqURL.length() > 200) {
             * strReqURL = strReqURL.substring(0, 200);
             * }
             * webLog.setUrl(strReqURL);
             * webLog.setRqesterId(uniqId);
             * // webLog.setRqesterIp(request.getRemoteAddr());
             * webLog.setRqesterIp(strIpAdress);
             * webLog.setMenuNo(menuNo);
             * webLogService.logInsertWebLog(webLog);
             * }
             * }
             */

        }

    }
}
