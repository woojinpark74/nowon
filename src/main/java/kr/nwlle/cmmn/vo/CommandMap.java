package kr.nwlle.cmmn.vo;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import lombok.Data;

/**
 * 파라미터를 담는 객체
 * 
 * @author 진수진
 * @since 2020.07.01
 * @version 1.0, 2020.07.01
 *          ------------------------------------------------------------------------
 *          작성자 일자 내용
 *          ------------------------------------------------------------------------
 *          진수진 2020.07.01 최초작성
 */

@Data
public class CommandMap {

    /* 파라미터 정보 */
    private final Map<String, Object> param;
    /* 페이징 정보 */
    private PaginationInfo paginationInfo;
    /* 관리자 로그인 정보 */
    // private LoginVO adminUser;
    /* 사용자 로그인 정보 */
    // private LoginVO userInfo;
    /* 접속 아이피 */
    private String ip;
    /* 접속 device */
    private String userAgent;
    /* 작업 url */
    private String trgetUrl;
    /* 다음 idkey */
    private String nextId;

    /* 접속 메뉴 정보 */
    // private MenuManageVO selectedMenu;

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public CommandMap() {
        param = new HashMap();

        paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(1);

        paginationInfo.setRecordCountPerPage(10);
        paginationInfo.setPageSize(10);
    }

    /**
     * 세션정보를 초기화
     *
     * @param UserSession
     * @return
     */
    // @formatter:off
    /*
    public void setSessionInfo(UserSession sessionInfo) {
        if (sessionInfo == null) {
            this.userInfo = null;
        } else {
            this.userInfo = sessionInfo.getUserInfo();
        }
    }*/
    // @formatter:on

    /**
     * parameter map 에 값 리턴
     *
     * @param key
     * @return Object
     */
    public Object get(String key) {
        return param.get(key);
    }

    /**
     * parameter map 의 값을 String으로 리턴
     *
     * @param key
     * @return String
     */
    public String getString(String key) {
        if (param.get(key) == null) {
            return "";
        } else {
            return String.valueOf(param.get(key));
        }
    }

    /**
     * parameter map 의 값을 UTF-8 로 인코딩한 String으로 리턴
     *
     * @param key
     * @return String
     */
    public String getStringEncode(String key) {
        if (param.get(key) == null) {
            return "";
        } else {
            try {
                return java.net.URLEncoder.encode(String.valueOf(param.get(key)), "UTF-8");
            } catch (Exception e) {
                return String.valueOf(param.get(key));
            }
        }
    }

    /**
     * parameter map 에 값 설정
     *
     * @param key
     * @param value
     * @return
     */
    public void put(String key, Object value) {
        param.put(key, value);

        if (key.equals("pageIndex")) {
            paginationInfo.setCurrentPageNo(Integer.parseInt(String.valueOf(value)));
        }
    }

    /**
     * parameter map 에 값을 제거한 후 반환
     *
     * @param key
     * @return Object
     */
    public Object remove(String key) {
        return param.remove(key);
    }

    /**
     * parameter map 에 key가 있는지 체크
     *
     * @param key
     * @return boolean
     */
    public boolean containsKey(String key) {
        return param.containsKey(key);
    }

    /**
     * parameter map 에 값이 있는지 체크
     *
     * @param value
     * @return boolean
     */
    public boolean containsValue(Object value) {
        return param.containsValue(value);
    }

    /**
     * parameter map clear
     *
     * @param
     * @return
     */
    public void clear() {
        param.clear();
    }

    /**
     * parameter map 의 EntrySet 반환
     *
     * @param
     * @return Set<Entry<String, Object>>
     */
    public Set<Entry<String, Object>> entrySet() {
        return param.entrySet();
    }

    /**
     * parameter map 의 KeySet 반환
     *
     * @param
     * @return Set<String>
     */
    public Set<String> keySet() {
        return param.keySet();
    }

    /**
     * parameter map 의 empty 여부 체크
     *
     * @param
     * @return boolean
     */
    public boolean isEmpty() {
        return param.isEmpty();
    }

    /**
     * parameter map 에 값 담기
     *
     * @param Map
     * @return
     */
    public void putParam(Map<? extends String, ? extends Object> m) {
        param.putAll(m);
    }

    /**
     * parameter map 객체 반환
     *
     * @param
     * @return Map
     */
    public Map<String, Object> getParam() {
        return param;
    }

    /**
     * parameter map 의 키값을 uri 파라미터 형식으로 반환
     *
     * @param
     * @return String
     */
    public String getQueryAll() {
        UriComponentsBuilder builder = UriComponentsBuilder.newInstance();
        Set<String> set = param.keySet();

        try {
            for (String key : set) {
                builder.queryParam(key, URLEncoder.encode(String.valueOf(param.get(key)), "UTF-8"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        UriComponents uriComponents = builder.build();
        String url = uriComponents.toUriString();

        return url;
    }

    /**
     * parameter map 의 키값을 uri 파라미터 형식으로 반환 (제외할 파라미터 추가)
     *
     * @param excludeParam
     * @return String
     */
    public String getSearchQuery(String excludeParam) {
        String tempParam = excludeParam;
        UriComponentsBuilder builder = UriComponentsBuilder.newInstance();
        Set<String> set = param.keySet();

        try {
            tempParam = "," + tempParam + ",";

            for (String key : set) {
                if (tempParam.equals("") || tempParam.indexOf("," + key + ",") < 0)
                    builder.queryParam(key, URLEncoder.encode(String.valueOf(param.get(key)), "UTF-8"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        UriComponents uriComponents = builder.build();
        String url = uriComponents.toUriString();

        return url;
    }

    /**
     * parameter map 의 키값을 uri 파라미터 형식으로 반환
     *
     * @param excludeParam
     * @return String
     */
    public String getQuery() {
        String url = getQueryAll();

        return url.startsWith("?") ? "&" + url.substring(1) : url;
    }

}