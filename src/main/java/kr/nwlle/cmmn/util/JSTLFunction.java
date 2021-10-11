package kr.nwlle.cmmn.util;

import java.io.File;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import kr.nwlle.common.service.ComCodeService;
import kr.nwlle.common.vo.ComCodeVO;

public class JSTLFunction {

    /**
     * ApplicationContext 빈 가져오기
     * 
     * @param bean
     * @return
     */
    private static Object getBean(String bean) {
        return SpringApplicationContext.getBean(bean);
    }

    /**
     * 코드명을 반환합니다.
     * 
     * @param group
     * @param id
     * @return
     */
    public static String getCodeName(String group, String id) {
        ComCodeService comCodeService = (ComCodeService) getBean("comCodeService");
        ComCodeVO code = comCodeService.getCode(group, id);

        if (code == null)
            return id;

        return code.getCdNm();
    }

    /**
     * 설명을 반환합니다
     * 
     * @param group
     * @param id
     * @return
     */
    public static String getCodeEtc(String group, String id) {
        ComCodeService comCodeService = (ComCodeService) getBean("comCodeService");
        ComCodeVO code = comCodeService.getCode(group, id);

        if (code == null)
            return id;

        return code.getEtc();
    }

    public static String getNvlCodeName(String group, String id, String defaultStr) {

        if (StringUtils.isEmpty(group) || StringUtils.isEmpty(id)) {
            return defaultStr;
        }
        ComCodeService comCodeService = (ComCodeService) getBean("comCodeService");
        String returnValue = comCodeService.getCodeName(group, id);

        // codeValue 와 returnValue 가 같다는 의미는
        // code name이 null 이란 의미이다
        if (StringUtils.isEmpty(returnValue) || id.equals(returnValue))
            return defaultStr;

        return returnValue;
    }

    /**
     * 코드목록을 반환합니다.
     * 
     * @param code_group
     * @return
     */
    public static List<ComCodeVO> getCodeList(String code_group) {
        ComCodeService comCodeService = (ComCodeService) getBean("comCodeService");
        return comCodeService.getCodeList(code_group);
    }

    /**
     * 코드그룹명을 받아 해당 코드리스트를 select box 안의 option 으로 변환합니다.
     * 
     * @param code_group
     *            코드그룹명
     * @param defaultText
     *            기본텍스트 (EMPTY: 디폴트 없음)
     * @param selectValue
     *            선택 되어진 텍스트 ( 만약 선택 값이 존재한다면)
     * @param type
     *            = 1:NM(Default) , 2:ID.NM ,3:ETC
     * @return
     */
    public static String makeOptions(String code_group, String defaultText, String selectValue, String type) {
        ComCodeService comCodeService = (ComCodeService) getBean("comCodeService");

        ComCodeVO codeInfo = new ComCodeVO();
        codeInfo.setGrpCd(code_group);

        List<ComCodeVO> list = comCodeService.getCodeList(codeInfo);

        StringBuffer data = new StringBuffer();

        if (!"EMPTY".equals(defaultText)) {
            data.append("<option value=''>" + defaultText + "</option>");
        }

        if (list == null)
            return data.toString();

        boolean isALL = false;
        if (type != null && type.indexOf("ALL_") == 0) {
            isALL = true;
            type = type.substring(4);
        }

        for (ComCodeVO ci : list) {

            if (!isALL && "N".equals(ci.getUseYn()))
                continue;

            String val = ci.getCd();
            if (!StringUtils.isEmpty(ci.getCd()) && ci.getCd().equals(selectValue)) {
                val += "' selected='selected";
            } else if ("DEFAULT".equals(selectValue)) {
                if (!StringUtils.isEmpty(ci.getEtc()) && ci.getEtc().indexOf("DEFAULT") != -1)
                    val += "' selected='selected";
            }

            // logger.debug("dbCd:selVal:optionVal = {}:{}:{}", ci.getCd(), selectValue, val);

            if (type.equals("ID.NM"))
                data.append("<option value='" + val + "'>" + ci.getCd() + "." + ci.getCdNm() + "</option>");
            else if (type.equals("ETC"))
                data.append("<option value='" + val + "'>" + ci.getEtc() + "</option>");
            else
                data.append("<option value='" + val + "'>" + ci.getCdNm() + "</option>");
        }

        return data.toString();

    }

    public static String makeMultipleOptions(String code_group, String selectValue, String type) {
        ComCodeService comCodeService = (ComCodeService) getBean("comCodeService");

        ComCodeVO codeInfo = new ComCodeVO();
        codeInfo.setGrpCd(code_group);

        List<ComCodeVO> list = comCodeService.getCodeList(codeInfo);

        StringBuffer data = new StringBuffer();

        data.append("<option data-divider='true'></option>");

        if (list == null)
            return data.toString();

        boolean isALL = false;
        if (type != null && type.indexOf("ALL_") == 0) {
            isALL = true;
            type = type.substring(4);
        }

        for (ComCodeVO ci : list) {

            if (!isALL && "N".equals(ci.getUseYn()))
                continue;

            String val = ci.getCd();
            if (!StringUtils.isEmpty(ci.getCd()) && ci.getCd().equals(selectValue)) {
                val += "' selected='selected";
            } else if ("DEFAULT".equals(selectValue)) {
                if (!StringUtils.isEmpty(ci.getEtc()) && ci.getEtc().indexOf("DEFAULT") != -1)
                    val += "' selected='selected";
            }

            if (type.equals("ID.NM"))
                data.append("<option value='" + val + "'>" + ci.getCd() + "." + ci.getCdNm() + "</option>");
            else
                data.append("<option value='" + val + "'>" + ci.getCdNm() + "</option>");
        }

        return data.toString();

    }

    /**
     * 코드그룹과 코드이름으로 옵션을 만듭니다.
     * 
     * @param code_group
     * @param defaultText
     * @param selectName
     * @param type
     * @return
     */
    public static String makeOptionsName(String code_group, String defaultText, String selectName, String type) {
        ComCodeService comCodeService = (ComCodeService) getBean("comCodeService");

        ComCodeVO codeInfo = new ComCodeVO();
        codeInfo.setGrpCd(code_group);

        List<ComCodeVO> list = comCodeService.getCodeList(codeInfo);

        StringBuffer data = new StringBuffer();

        data.append("<option value=''>" + defaultText + "</option>");
        /* data.append("<option data-divider='true'></option>"); */

        if (list == null)
            return data.toString();

        boolean isALL = false;
        if (type != null && type.indexOf("ALL_") == 0) {
            isALL = true;
            type = type.substring(4);
        }

        for (ComCodeVO ci : list) {

            if (!isALL && "N".equals(ci.getUseYn()))
                continue;

            String val = ci.getCdNm();
            if (!StringUtils.isEmpty(ci.getCdNm()) && ci.getCdNm().equals(selectName)) {
                val += "' selected='selected";
            } else if ("DEFAULT".equals(selectName)) {
                if (!StringUtils.isEmpty(ci.getEtc()) && ci.getEtc().indexOf("DEFAULT") != -1)
                    val += "' selected='selected";
            }

            if (type.equals("ID.NM"))
                data.append("<option value='" + val + "'>" + ci.getCd() + "." + ci.getCdNm() + "</option>");
            else if (type.equals("ETC"))
                data.append("<option value='" + val + "'>" + ci.getEtc() + "</option>");
            else
                data.append("<option value='" + val + "'>" + ci.getCdNm() + "</option>");
        }

        return data.toString();

    }

    // Funciton Overloading
    public static String makeOptions(String code_group, String defaultText, String selectValue) {
        return makeOptions(code_group, defaultText, selectValue, "NM");
    }

    public static String makeMultipleOptions(String code_group, String selectValue) {
        return makeMultipleOptions(code_group, selectValue, "NM");
    }

    public static String makeRadioAndCheckBoxs(String code_group, String name, String type, String checkValues,
            int number, String event, String mode) {

        ComCodeService comCodeService = (ComCodeService) getBean("comCodeService");

        ComCodeVO codeInfo = new ComCodeVO();
        codeInfo.setGrpCd(code_group);

        List<ComCodeVO> list = comCodeService.getCodeList(codeInfo);
        StringBuffer data = new StringBuffer();
        String[] values = checkValues.split(",");
        int cnt = 1;

        if (list == null)
            return data.toString();

        if (!StringUtils.isEmpty(event)) {
            event = "onclick='" + event + "'";
        }

        for (ComCodeVO ci : list) {

            if ("N".equals(ci.getUseYn()))
                continue;

            String val = ci.getCd();

            for (String checkValue : values) {
                if (!StringUtils.isEmpty(ci.getCd()) && ci.getCd().equals(checkValue)) {
                    val += "' checked='checked";
                }
            }
            if (number > 0) {
                data.append("<div class='" + type + "'><label>");
            } else {
                data.append("<label class='" + type + "-inline'>");

            }

            data.append("<input type='" + type + "' name='" + name + "' id='" + name + cnt + "' value='" + val + "' "
                    + event + ">");

            if (mode.equals("etc")) {
                data.append(ci.getEtc() + "&nbsp;");
            } else {
                data.append(ci.getCdNm() + "&nbsp;");
            }
            if (number > 0) {
                data.append("</label></div>");
            } else {
                data.append("</label>");

            }

            cnt++;
        }

        return data.toString();
    }

    // Funciton Overloading
    public static String makeRadioAndCheckBoxs(String code_group, String name, String type, String checkValues,
            int number, String event) {
        return makeRadioAndCheckBoxs(code_group, name, type, checkValues, number, event, "");
    }

    // Funciton Overloading
    public static String makeRadioAndCheckBoxs(String code_group, String name, String type, String checkValues,
            int number) {
        return makeRadioAndCheckBoxs(code_group, name, type, checkValues, number, "");
    }

    /**
     * 코드그룹명을 받아 해당 코드리스트를 select box 안의 option 으로 변환합니다.
     * 
     * @param code_group
     *            코드그룹명
     * @param defaultText
     *            기본텍스트
     * @param selectValue
     *            선택 되어진 텍스트 ( 만약 선택 값이 존재한다면)
     * @param id
     *            사용자 ID
     * @return
     */
    public static String makeOptionsEmp(String code_group, String defaultText, String selectValue, String id) {

        ComCodeService comCodeService = (ComCodeService) getBean("comCodeService");

        ComCodeVO codeInfo = new ComCodeVO();
        codeInfo.setGrpCd(code_group);

        List<ComCodeVO> list = comCodeService.getCodeList(codeInfo);

        StringBuffer data = new StringBuffer();

        data.append("<option value=''>" + defaultText + "</option>");
        data.append("<option data-divider='true'></option>");

        if (list == null)
            return data.toString();

        for (ComCodeVO ci : list) {

            if ("N".equals(ci.getUseYn()))
                continue;

            String val = ci.getCd();
            if (!StringUtils.isEmpty(ci.getCd()) && ci.getCd().equals(selectValue)) {
                val += "' selected='selected";
            } else if ("DEFAULT".equals(selectValue)) {
                if (!StringUtils.isEmpty(ci.getEtc()) && ci.getEtc().indexOf("DEFAULT") != -1)
                    val += "' selected='selected";
            }

            if (id.equals(ci.getEtc()))
                data.append("<option value='" + val + "'>" + ci.getCdNm() + "</option>");
        }

        return data.toString();

    }

    /**
     * 날짜선택 박스 생성
     * 
     * @param date
     *            : jsp에 뿌려줄 날짜선택 메뉴
     * @param from
     *            : jsp에 세팅해줄 from input의 네임값
     * @param to
     *            : : jsp에 세팅해줄 to input의 네임값
     * @return
     */
    public static String quickYmdFromTo(String date, String from, String to) {

        String[] dataArray = date.split(",");
        StringBuffer data = new StringBuffer();

        data.append("<div class=\"dropdown\">\n");
        data.append("<button id=\"selectDate\" type=\"button\" class=\"btn btn-default\" data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">날짜<span class=\"caret\"></span></button>\n");
        data.append("<ul id=\"dateMenu\" class=\"dropdown-menu\" aria-labelledby=\"selectDate\" style=\"min-width:0px;\">\n");

        // 배열의 길이만큼 for문
        for (int i = 0; i < dataArray.length; i++) {

            if ("T".equals(dataArray[i]))
                data.append("<li onclick=\"getDate('T', '" + from + "', '" + to + "');\"><a href='#'>오늘</a></li>\n");
            else if ("Y".equals(dataArray[i]))
                data.append("<li onclick=\"getDate('Y', '" + from + "', '" + to + "');\"><a href='#'>어제</a></li>\n");
            else if ("TM".equals(dataArray[i]))
                data.append("<li onclick=\"getDate('TM', '" + from + "', '" + to + "');\"><a href='#'>내일</a></li>\n");
            else if ("W".equals(dataArray[i]))
                data.append("<li onclick=\"getDate('W', '" + from + "', '" + to + "');\"><a href='#'>금주</a></li>\n");
            else if ("LW".equals(dataArray[i]))
                data.append("<li onclick=\"getDate('LW', '" + from + "', '" + to + "');\"><a href='#'>전주</a></li>\n");
            else if ("M".equals(dataArray[i]))
                data.append("<li onclick=\"getDate('M', '" + from + "', '" + to + "');\"><a href='#'>당월</a></li>\n");
            else if ("LM".equals(dataArray[i]))
                data.append("<li onclick=\"getDate('LM', '" + from + "', '" + to + "');\"><a href='#'>전월</a></li>\n");
        }

        data.append("</ul>\n");
        data.append("</div>\n");

        return data.toString();
    }

    public static String quickYmd(String date, String ymd) {
        return quickYmdFromTo(date, ymd, "");
    }

    /**
     * 이메일 을 구분하여 지정된 자리를 리턴한다.
     * 
     * @param email
     * @param idx
     * @return
     */
    public static String getEmailPart(String email, int idx) {
        String returnValue = "";

        if (email == null)
            return "";

        try {
            String[] arrTmp = email.split("@");
            returnValue = arrTmp[idx - 1];
        } catch (Exception e) {
            returnValue = "";
        }

        return returnValue;
    }

    /**
     * url 경로에서 파일 이름 추출
     * 
     * @param content
     * @return String
     */
    public static String getFileNameFromUrl(String content) {

        String fileName = new File(content).getName();
        return fileName;
    }

}
