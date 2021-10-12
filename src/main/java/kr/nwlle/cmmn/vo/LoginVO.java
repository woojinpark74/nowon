package kr.nwlle.cmmn.vo;

import java.io.Serializable;
import java.sql.Date;

import lombok.Data;

/**
 * @Class Name : LoginVO.java
 * @Description : Login VO class
 * @Modification Information
 * @
 *   @ 수정일 수정자 수정내용
 *   @ ------- -------- ---------------------------
 *   @ 2009.03.03 박지욱 최초 생성
 *   @ 2020.08.01 진수진 과학관에 맞춰 수정
 * @author 공통서비스 개발팀 박지욱
 * @since 2009.03.03
 * @version 1.0
 * @see
 */
// public class LoginVO extends ComDefaultVO implements Serializable {
@Data
public class LoginVO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -8274004534207618049L;

    /** 아이디 */
    private String id;
    /** 이름 */
    private String name;
    /** 핸드폰 */
    private String ihidNum;
    /** 이메일주소 */
    private String email;
    /** 비밀번호 */
    private String password;
    /** 비밀번호 힌트 */
    private String passwordHint;
    /** 비밀번호 정답 */
    private String passwordCnsr;
    /** 사용자구분 */
    private String userSe;
    /** 조직(부서)ID */
    private String orgnztId;
    /** 조직(부서)명 */
    private String orgnztNm;
    /** 고유아이디 */
    private String uniqId;
    /** 로그인 후 이동할 페이지 */
    private String url;
    /** 사용자 IP정보 */
    private String ip;
    /** GPKI인증 DN */
    private String dn;
    /** 권한명 */
    private String authorNm;

    /** SNS ID */
    private String snsId;

    /** SNS EMAIL */
    private String snsEmail;

    /** SNS 구분 */
    private String snsKind;

    /** DB 암호화 코드 */
    private String dbEncKey;

    /** 연간회원여부 */
    private String yearYn;

    /** 연간회원종료일 */
    private String anlmbEdate;

    /** 특별회원여부 */
    private String specialYn;

    /** 본인인증 키 */
    private String hpcertno;

    /** 회원 상태 */
    private String status;

    /** 탈퇴 또는 휴면 전환일 */
    private Date outDate;

    /** 회원구분 */
    private String memGbn;
    /** 비밀모드 */
    private String secretMode;

    /** 성별 */
    private String gender;

    /** 권한그룹 코드 */
    private int groupId;

    /** 생년월일 **/
    private String birthDate;

    /** 주소 (우편번호) **/
    private String homeZipNo;

    /** 주소1 **/
    private String homeAddr1;

    /** 주소2 **/
    private String homeAddr2;

    /** 컨텐츠 바로 수정 가능 여부 **/
    private String contentsYn;

    private String piAuthkey;

    private String piAuthtype;

    private String piPAuthtype;

    public boolean isMember() {
        if (this.uniqId == null || this.uniqId.equals("") || this.uniqId.equals("00000000")) {
            return false;
        } else {
            return true;
        }
    }

    public String getThisObjAllValue() {

        String strAllValue = "";

        strAllValue = strAllValue + "\n" + "id=" + id + "\n";
        strAllValue = strAllValue + "name=" + name + "\n";
        strAllValue = strAllValue + "ihidNum=" + ihidNum + "\n";
        strAllValue = strAllValue + "email=" + email + "\n";
        strAllValue = strAllValue + "password=" + password + "\n";
        strAllValue = strAllValue + "passwordHint=" + passwordHint + "\n";
        strAllValue = strAllValue + "passwordCnsr=" + passwordCnsr + "\n";
        strAllValue = strAllValue + "userSe=" + userSe + "\n";
        strAllValue = strAllValue + "orgnztId=" + orgnztId + "\n";
        strAllValue = strAllValue + "orgnztNm=" + orgnztNm + "\n";
        strAllValue = strAllValue + "uniqId=" + uniqId + "\n";
        strAllValue = strAllValue + "url=" + url + "\n";
        strAllValue = strAllValue + "ip=" + ip + "\n";
        strAllValue = strAllValue + "dn=" + dn + "\n";
        strAllValue = strAllValue + "authorNm=" + authorNm + "\n";
        strAllValue = strAllValue + "snsId=" + snsId + "\n";
        strAllValue = strAllValue + "snsEmail=" + snsEmail + "\n";
        strAllValue = strAllValue + "snsKind=" + snsKind + "\n";
        strAllValue = strAllValue + "dbEncKey=" + dbEncKey + "\n";
        strAllValue = strAllValue + "yearYn=" + yearYn + "\n";
        strAllValue = strAllValue + "anlmbEdate=" + anlmbEdate + "\n";
        strAllValue = strAllValue + "specialYn=" + specialYn + "\n";
        strAllValue = strAllValue + "hpcertno=" + hpcertno + "\n";
        strAllValue = strAllValue + "status=" + status + "\n";
        strAllValue = strAllValue + "memGbn=" + memGbn + "\n";
        strAllValue = strAllValue + "secretMode=" + secretMode + "\n";
        strAllValue = strAllValue + "gender=" + gender + "\n";
        strAllValue = strAllValue + "birthDate=" + birthDate + "\n";
        strAllValue = strAllValue + "homeZipNo=" + homeZipNo + "\n";
        strAllValue = strAllValue + "homeAddr1=" + homeAddr1 + "\n";
        strAllValue = strAllValue + "homeAddr2=" + homeAddr2 + "\n";
        strAllValue = strAllValue + "contentsYn=" + contentsYn + "\n";

        return strAllValue;
    }

}
