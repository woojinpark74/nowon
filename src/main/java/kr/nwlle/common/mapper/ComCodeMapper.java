package kr.nwlle.common.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.nwlle.common.vo.ComCodeVO;

@Repository
public interface ComCodeMapper {

    /**
     * code_group , code_value 를 파라메터로 하여 CodeVO 객체 1개를 반환합니다.
     */
    ComCodeVO getComCode(ComCodeVO param);

    /**
     * 해당 파라미터로 조회된 결과 CodeVO객체 List를 반환합니다.
     */
    List<ComCodeVO> getComCodeList2(ComCodeVO param);

    /**
     * codeForm 파라메터로 CodeVO객체 List를 반환합니다.
     */
    List<ComCodeVO> getComCodeList(ComCodeVO param);

    /**
     * 코드정보를 등록/수정합니다.
     */
    int mergeComCode(ComCodeVO param);

    /**
     * 코드정보를 삭제합니다.
     */
    int deleteComCode(ComCodeVO param);

    /**
     * 코드그룹의 seq, value의 최대값을 가져옴
     */
    ComCodeVO getMaxComCode(ComCodeVO param);

    /**
     * 코드 순서변경
     */
    int updateSortNo(ComCodeVO param);

    /**
     * 호출전 트랜젝션의 auto increment 번호 조회 예) insert 작업 후 호출
     */
    int getLastInsertId();
}
