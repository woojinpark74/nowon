package kr.nwlle.cmmn.vo;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.commons.lang3.StringUtils;

import kr.nwlle.cmmn.constant.Constant;

public class ResultVO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 2984861801589895345L;

    private String resultCd; // 작업결과코드 00.실패, 01.성공
    private String message; // 메시지코드
    private String messageDesc; // 메지지 설명
    private Object returnObj; // 리턴객체(모델, 리스트 등 리턴할 객체를 닮기 위한 오브젝트)

    public ResultVO() {
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

    /**
     * @param resultCd
     *            작업결과코드
     */
    public ResultVO(String resultCd) {
        this.resultCd = resultCd;
    }

    /**
     * @param resultCd
     *            작업결과코드
     * @param cd_message
     *            메시지코드
     */
    public ResultVO(String resultCd, String message) {
        this(resultCd);
        this.message = message;

        if (StringUtils.isNotBlank(message)) {
            this.messageDesc = message;
        }
    }

    /**
     * @param resultCd
     *            작업결과코드
     * @param cd_message
     *            메시지코드
     * @param returnObj
     *            리턴객체
     */
    public ResultVO(String resultCd, String cd_message, Object returnObj) {
        this(resultCd, cd_message);
        this.returnObj = returnObj;
    }

    /**
     * @param resultCd
     *            :작업결과코드
     * @param cd_message
     *            :메세지코드
     * @param messageDesc
     *            :메세지설명
     */
    public ResultVO(String resultCd, String cd_message, String messageDesc) {
        this(resultCd, cd_message);
        this.messageDesc = StringUtils.defaultString(messageDesc);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResultCd() {
        return resultCd;
    }

    public void setResultCd(String resultCd) {
        this.resultCd = resultCd;
    }

    public String getMessageDesc() {
        return messageDesc;
    }

    public void setMessageDesc(String messageDesc) {
        this.messageDesc = messageDesc;
    }

    public Object getReturnObj() {
        return returnObj;
    }

    public void setReturnObj(Object returnObj) {
        this.returnObj = returnObj;
    }

    public boolean isSuccess() {
        if (resultCd == null)
            return false;

        return Constant.SUCCESS.equals(resultCd);
    }

    public static ResultVO getInstanceFailed(String message) {
        return new ResultVO(Constant.FAILED, message);
    }

    public static ResultVO getInstanceSuccess(String message) {
        return new ResultVO(Constant.SUCCESS, message);
    }
}
