package kr.nwlle.cmmn.config;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.lang3.StringUtils;

import kr.nwlle.cmmn.util.EgovProperties;
import lombok.extern.slf4j.Slf4j;

/**
 * @Class Name : ProfileActiveSetContextListener.java
 * @Description : 자세한 클래스 설명
 * @author woojinp@legitsys.co.kr
 * @since 2021. 10. 12.
 * @version 1.0
 * @see
 *      Copyright(c) 2021 HISCO. All rights reserved
 */

@Slf4j
public class ProfileActiveSetContextListener implements ServletContextListener {

    public ProfileActiveSetContextListener() {
        setProfile();
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        if (StringUtils.isNotBlank(System.getProperty("spring.profiles.active"))) {
            try {
                System.clearProperty("spring.profiles.active");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void contextInitialized(ServletContextEvent event) {
        if (StringUtils.isBlank(System.getProperty("spring.profiles.active"))) {
            setProfile();
        }
    }

    private void setProfile() {
        String dbType = EgovProperties.getProperty("Globals.DbType");
        String auth = EgovProperties.getProperty("Globals.Auth");
        String mode = EgovProperties.getProperty("Globals.Mode");

        try {
            if (StringUtils.isBlank(System.getProperty("spring.profiles.myactive"))) {
                log.info("not setting spring.profiles.myactive");
                System.setProperty("spring.profiles.myactive", "loc");
            }

            System.setProperty("spring.profiles.active", dbType + "," + auth + "," + mode);

            log.info("spring.profiles.myactive:spring.profiles.myactive = {}:{}", System.getProperty("spring.profiles.myactive"), System.getProperty("spring.profiles.active"));

        } catch (IllegalArgumentException e) {
            log.error(e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

}
