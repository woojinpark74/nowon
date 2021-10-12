package kr.nwlle.cmmn.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class CommonUtil {
    /**
     * <pre>
     * profile 설정 정보를 대문자로 반환
     * </pre>
     * 
     * @return
     */
    public static String getProfile() {
        String profile = StringUtils.defaultString(System.getenv("spring.profiles.myactive"), System.getProperty("spring.profiles.myactive"));

        if (StringUtils.isNotEmpty(profile)) {
            profile = profile.toUpperCase();
        }

        return profile;
    }

    /**
     * @return "maria,security,active"
     */
    public static String getProfiles() {
        String profile = StringUtils.defaultString(System.getenv("spring.profiles.active"), System.getProperty("spring.profiles.active"));

        if (StringUtils.isNotEmpty(profile)) {
            profile = profile.toUpperCase();
        }

        return profile;
    }

    /**
     * @return {"maria", "security", "active"}
     */
    public static List<String> getProfileList() {
        String profile = StringUtils.defaultString(System.getenv("spring.profiles.active"), System.getProperty("spring.profiles.active"));

        if (StringUtils.isNotEmpty(profile)) {
            profile = profile.toUpperCase();
            return Arrays.asList(profile.split("[,]"));
        }

        return Collections.EMPTY_LIST;
    }

}
