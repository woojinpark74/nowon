package kr.nwlle.cmmn.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import lombok.extern.slf4j.Slf4j;

/**
 * Class Name : EgovProperties.java
 * Description : properties값들을 파일로부터 읽어와 Globals클래스의 정적변수로 로드시켜주는 클래스로
 * 문자열 정보 기준으로 사용할 전역변수를 시스템 재시작으로 반영할 수 있도록 한다.
 * Modification Information
 * 수정일 수정자 수정내용
 * ---------- -------- ---------------------------
 * 2009.01.19 박지욱 최초 생성
 * 2011.07.20 서준식 Globals파일의 상대경로를 읽은 메서드 추가
 * 2014.10.13 이기하 Globals.properties 값이 null일 경우 오류처리
 * 2019.04.26 신용호 RELATIVE_PATH_PREFIX Path 적용 방식 개선
 * 
 * @author 공통 서비스 개발팀 박지욱
 * @since 2009. 01. 19
 * @version 1.0
 * @see
 */

@Slf4j
public class EgovProperties {

    // 파일구분자
    final static String FILE_SEPARATOR = System.getProperty("file.separator");

    // @formatter:off
	//프로퍼티 파일의 물리적 위치
	public static final String RELATIVE_PATH_PREFIX = EgovProperties.class.getResource("") == null ? "" : EgovProperties.class.getResource("").getPath().substring(0, EgovProperties.class.getResource("").getPath().lastIndexOf("kr"));
	public static final String GLOBALS_PROPERTIES_FILE = RELATIVE_PATH_PREFIX + "property" + FILE_SEPARATOR + "globals_" + System.getProperty("spring.profiles.myactive") + ".properties";
	// @formatter:on

    /**
     * 인자로 주어진 문자열을 Key값으로 하는 상대경로 프로퍼티 값을 절대경로로 반환한다(Globals.java 전용)
     * 
     * @param keyName
     *            String
     * @return String
     */
    public static String getPathProperty(String keyName) {
        String value = "";

        try (FileInputStream fis = new FileInputStream(EgovWebUtil.filePathBlackList(GLOBALS_PROPERTIES_FILE))) {
            Properties props = new Properties();
            props.load(new BufferedInputStream(fis));
            value = props.getProperty(keyName);
            value = (value == null) ? "" : value.trim();// KISA 보안약점 조치 (2018-10-29, 윤창원)
            value = RELATIVE_PATH_PREFIX + "egovProps" + System.getProperty("file.separator") + value;
        } catch (FileNotFoundException e) {
            log.error(e.toString());
        } catch (IOException e) {
            log.error(e.toString());
        }

        return value;
    }

    /**
     * 인자로 주어진 문자열을 Key값으로 하는 프로퍼티 값을 반환한다(Globals.java 전용)
     * 
     * @param keyName
     *            String
     * @return String
     */
    public static String getProperty(String keyName) {
        String value = "";
        log.debug("getProperty : {} = {}", GLOBALS_PROPERTIES_FILE, keyName);

        try (FileInputStream fis = new FileInputStream(EgovWebUtil.filePathBlackList(GLOBALS_PROPERTIES_FILE))) {
            Properties props = new Properties();
            props.load(new BufferedInputStream(fis));
            if (props.getProperty(keyName) == null) {
                return "";
            }
            value = props.getProperty(keyName).trim();
        } catch (FileNotFoundException e) {
            log.error(e.toString());
        } catch (IOException e) {
            log.error(e.toString());
        }

        return value;
    }

    /**
     * 주어진 파일에서 인자로 주어진 문자열을 Key값으로 하는 프로퍼티 상대 경로값을 절대 경로값으로 반환한다
     * 
     * @param fileName
     *            String
     * @param key
     *            String
     * @return String
     */
    public static String getPathProperty(String fileName, String key) {
        try (FileInputStream fis = new FileInputStream(EgovWebUtil.filePathBlackList(fileName))) {
            Properties props = new Properties();
            props.load(new BufferedInputStream(fis));

            String value = props.getProperty(key);
            return RELATIVE_PATH_PREFIX + "egovProps" + System.getProperty("file.separator") + value;
        } catch (FileNotFoundException e) {
            log.error(e.toString());
        } catch (IOException e) {
            log.error(e.toString());
        }
        return "";
    }

    /**
     * 주어진 파일에서 인자로 주어진 문자열을 Key값으로 하는 프로퍼티 값을 반환한다
     * 
     * @param fileName
     *            String
     * @param key
     *            String
     * @return String
     */
    public static String getProperty(String fileName, String key) {

        try (FileInputStream fis = new FileInputStream(EgovWebUtil.filePathBlackList(fileName))) {
            Properties props = new Properties();
            props.load(new BufferedInputStream(fis));
            return props.getProperty(key);
        } catch (FileNotFoundException e) {
            log.error(e.toString());
        } catch (IOException e) {
            log.error(e.toString());
        }

        return "";
    }

    /**
     * 주어진 프로파일의 내용을 파싱하여 (key-value) 형태의 구조체 배열을 반환한다.
     * 
     * @param property
     *            String
     * @return ArrayList
     */
    public static ArrayList<Map<String, String>> loadPropertyFile(String property) {

        // key - value 형태로 된 배열 결과
        ArrayList<Map<String, String>> keyList = new ArrayList<Map<String, String>>();

        String src = property.replace('\\', File.separatorChar).replace('/', File.separatorChar);
        try (FileInputStream fis = new FileInputStream(src)) {
            File srcFile = new File(EgovWebUtil.filePathBlackList(src));

            if (srcFile.exists()) {
                Properties props = new Properties();
                props.load(new BufferedInputStream(fis));
                fis.close();

                Enumeration<?> plist = props.propertyNames();
                if (plist != null) {
                    while (plist.hasMoreElements()) {
                        Map<String, String> map = new HashMap<String, String>();
                        String key = (String) plist.nextElement();
                        map.put(key, props.getProperty(key));
                        keyList.add(map);
                    }
                }
            }
        } catch (IOException ex) {
            log.error(ex.toString());
        }

        return keyList;
    }
}
