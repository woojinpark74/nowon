package kr.nwlle.common.vo;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class ComCodeVO implements Serializable {
    private static final long serialVersionUID = 420764962933391812L;

    private String grpCd; // 공통코드그룹코드
    private String cd; // 공통코드
    private String cdNm; // 코드이름
    private String etc; // 기타
    private int sortNo; // 정렬순서
    private String useYn; // 사용여부
    private String regId; // 최초ID
    private String regDt; // 최초시간
    private String modId; // 최종ID
    private String modDt; // 최종시간

    private String grpCdYn = "N"; // 그룹코드row만입력, 혹은 update시 사용

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

    public String getGrpCd() {
        return grpCd;
    }

    public void setGrpCd(String grpCd) {
        this.grpCd = grpCd;
    }

    public String getCd() {
        return "Y".equals(grpCdYn) ? " " : cd;
    }

    public void setCd(String cd) {
        this.cd = cd;
    }

    public String getCdNm() {
        return cdNm;
    }

    public void setCdNm(String cdNm) {
        this.cdNm = cdNm;
    }

    public String getEtc() {
        return etc;
    }

    public void setEtc(String etc) {
        this.etc = etc;
    }

    public int getSortNo() {
        return sortNo;
    }

    public void setSortNo(int sortNo) {
        this.sortNo = sortNo;
    }

    public String getUseYn() {
        return useYn;
    }

    public void setUseYn(String useYn) {
        this.useYn = useYn;
    }

    public String getRegId() {
        return regId;
    }

    public void setRegId(String regId) {
        this.regId = regId;
    }

    public String getRegDt() {
        return regDt;
    }

    public void setRegDt(String regDt) {
        this.regDt = regDt;
    }

    public String getModId() {
        return modId;
    }

    public void setModId(String modId) {
        this.modId = modId;
    }

    public String getModDt() {
        return modDt;
    }

    public void setModDt(String modDt) {
        this.modDt = modDt;
    }

    public String getGrpCdYn() {
        return grpCdYn;
    }

    public void setGrpCdYn(String grpCdYn) {
        this.grpCdYn = grpCdYn;
    }

}
