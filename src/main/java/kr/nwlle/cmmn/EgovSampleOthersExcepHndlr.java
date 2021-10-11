package kr.nwlle.cmmn;

import egovframework.rte.fdl.cmmn.exception.handler.ExceptionHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @Class Name : EgovSampleOthersExcepHndlr.java
 * @Description : 자세한 클래스 설명
 * @author woojinp@legitsys.co.kr
 * @since 2021. 10. 7.
 * @version 1.0
 * @see
 *      Copyright(c) 2021 HISCO. All rights reserved
 */
@Slf4j
public class EgovSampleOthersExcepHndlr implements ExceptionHandler {

    /**
     * @param exception
     * @param packageName
     * @see 개발프레임웍크 실행환경 개발팀
     */
    @Override
    public void occur(Exception exception, String packageName) {
        log.debug(" EgovServiceExceptionHandler run...............");
    }

}
