package kr.nwlle.cmmn.util;

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
        String profile = StringUtils.defaultString(System.getenv("spring.profiles.active"), System.getProperty("spring.profiles.active"));

        if (StringUtils.isNotEmpty(profile)) {
            profile = profile.toUpperCase();
        }

        return profile;
    }

}
