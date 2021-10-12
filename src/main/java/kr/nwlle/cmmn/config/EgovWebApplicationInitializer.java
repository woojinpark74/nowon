package kr.nwlle.cmmn.config;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.mobile.device.DeviceResolverRequestFilter;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.multipart.support.MultipartFilter;

import kr.nwlle.cmmn.util.EgovProperties;
import lombok.extern.slf4j.Slf4j;

/**
 * EgovWebApplicationInitializer 클래스
 * <Notice>
 * 사용자 인증 권한처리를 분리(session, spring security) 하기 위해서 web.xml의 기능을
 * Servlet3.x WebApplicationInitializer 기능으로 처리
 * <Disclaimer>
 * N/A
 *
 * @author 장동한
 * @since 2016.06.23
 * @version 1.0
 * @see
 *
 *      <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일        수정자           수정내용
 *  -------      -------------  ----------------------
 *   2016.06.23  장동한           최초 생성
 *   2018.10.02  신용호           Facebook 관련 HiddenHttpMethodFilter 추가
 *   2018.10.26  신용호           EgovLoginPolicyFilter 추가 (IP접근처리)
 *   2018.12.03  신용호           springMultipartFilter,HTMLTagFilter 추가 (XSS방지처리)
 *   2020.07.01  진수진	      deviceResolverRequestFilter 추가
 *   2021.10.12  박우진          기본적인 filter나 listener는 web.xml로 옮기고, custum filter 만 추가
 *      </pre>
 */

@Slf4j
public class EgovWebApplicationInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {

        log.debug("onStartup.............");

        // -------------------------------------------------------------
        // Spring CharacterEncodingFilter 설정(I/F 결제)
        // -------------------------------------------------------------
        FilterRegistration.Dynamic characterIfChargeEncoding = servletContext.addFilter("IfChargeilter", new org.springframework.web.filter.CharacterEncodingFilter());
        characterIfChargeEncoding.setInitParameter("encoding", "euc-kr");
        characterIfChargeEncoding.setInitParameter("forceEncoding", "true");
        characterIfChargeEncoding.addMappingForUrlPatterns(null, false, "/intrfc/charge/return/*");
        characterIfChargeEncoding.addMappingForUrlPatterns(null, false, "/web/common/prsoncerti/resultPop");

        // -------------------------------------------------------------
        // Tomcat의 경우 allowCasualMultipartParsing="true" 추가
        // <Context docBase="" path="/" reloadable="true" allowCasualMultipartParsing="true">
        // -------------------------------------------------------------
        MultipartFilter springMultipartFilter = new MultipartFilter();
        springMultipartFilter.setMultipartResolverBeanName("multipartResolver");
        FilterRegistration.Dynamic multipartFilter = servletContext.addFilter("springMultipartFilter", springMultipartFilter);
        multipartFilter.addMappingForUrlPatterns(null, false, "/*");

     // @formatter:off
        if ("security".equals(EgovProperties.getProperty("Globals.Auth").trim())) {
            // -------------------------------------------------------------
            // springSecurityFilterChain 설정
            // -------------------------------------------------------------
            FilterRegistration.Dynamic springSecurityFilterChain = servletContext.addFilter("springSecurityFilterChain", new DelegatingFilterProxy());
            springSecurityFilterChain.addMappingForUrlPatterns(null, false, "*");

            // -------------------------------------------------------------
            // HttpSessionEventPublisher 설정
            // -------------------------------------------------------------
            servletContext.addListener(new org.springframework.security.web.session.HttpSessionEventPublisher());

            // -------------------------------------------------------------
            // EgovSpringSecurityLoginFilter 설정
            // -------------------------------------------------------------
            //TODO: /** */영역 활성필요
            /**
            FilterRegistration.Dynamic egovSpringSecurityLoginFilter = servletContext.addFilter("egovSpringSecurityLoginFilter", new EgovSpringSecurityLoginFilter());
            // 로그인 실패시 반활 될 URL설정
            egovSpringSecurityLoginFilter.setInitParameter("loginURL", Constant.ADMIN_ROOT + "/login");
            // 로그인 처리 URL설정
            egovSpringSecurityLoginFilter.setInitParameter("loginProcessURL", Constant.ADMIN_ROOT + "/actionLogin");
            // 처리 Url Pattern
            egovSpringSecurityLoginFilter.addMappingForUrlPatterns(null, false, Constant.ADMIN_ROOT + "/actionLogin");
            */
        } else if ("session".equals(EgovProperties.getProperty("Globals.Auth").trim())) {
            // -------------------------------------------------------------
            // EgovLoginPolicyFilter 설정
            // -------------------------------------------------------------
          //TODO: /** */영역 활성필요
            /**
            FilterRegistration.Dynamic egovLoginPolicyFilter = servletContext.addFilter("LoginPolicyFilter", new EgovLoginPolicyFilter());
            egovLoginPolicyFilter.addMappingForUrlPatterns(null, false, Constant.ADMIN_ROOT + "/login");
            */
        }

        // -------------------------------------------------------------
        // deviceResolverRequestFilter 설정
        // -------------------------------------------------------------
        FilterRegistration.Dynamic deviceResolverRequestFilter = servletContext.addFilter("deviceResolverRequestFilter", new DeviceResolverRequestFilter());
        deviceResolverRequestFilter.addMappingForUrlPatterns(null, false, "/*");

    }

}
