package kr.nwlle.cmmn.constant;

import java.util.Arrays;
import java.util.List;

public class Constant {
    public static final List<String> LOGGING_PROFILES = Arrays.asList("LOC", "DEV", "STG");

    public final static String SECURITY_ROLE_ADMIN = "ROLE_ADMIN";
    public final static String SECURITY_ROLE_USER = "ROLE_USER";

    public final static String SUCCESS = "00"; // 작업결과 성공
    public final static String FAILED = "99"; // 작업결과 실패

    // 오류 메시지
    public final static String INTERNAL_SERVER_ERROR = "일시적으로 문제가 발생하였습니다. 잠시 후 다시 시도해 주세요.";
    public final static String INVALID_ACCESS = "비정상적인 접근입니다.";

    // 가상계좌결과코드
    public final static String VBANK_RESULT_CODE_입금성공 = "4110";

    public void init() throws Exception {
    }
}
