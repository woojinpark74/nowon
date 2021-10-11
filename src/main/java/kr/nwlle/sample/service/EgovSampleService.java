/*
 * Copyright 2008-2009 the original author or authors.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package kr.nwlle.sample.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import kr.nwlle.sample.mapper.SampleMapper;
import kr.nwlle.sample.vo.SampleDefaultVO;
import kr.nwlle.sample.vo.SampleVO;
import lombok.extern.slf4j.Slf4j;

/**
 * @Class Name : EgovSampleServiceImpl.java
 * @Description : Sample Business Implement Class
 * @Modification Information
 * @
 *   @ 수정일 수정자 수정내용
 *   @ --------- --------- -------------------------------
 *   @ 2009.03.16 최초생성
 * @author 개발프레임웍크 실행환경 개발팀
 * @since 2009. 03.16
 * @version 1.0
 * @see
 *      Copyright (C) by MOPAS All right reserved.
 */

@Slf4j
@Service("sampleService")
public class EgovSampleService extends EgovAbstractServiceImpl {
    // TODO: 더이상 interface 구조를 사용하지 않습니다. @Service 로 대체합니다.
    // public class EgovSampleServiceImpl extends EgovAbstractServiceImpl implements EgovSampleService {

    // TODO: 더이상 이런식으로 log를 선언하여 사용하지 않습니다. @Sl4jf로 대체
    // private static final log log = logFactory.getlog(EgovSampleService.class);

    /** SampleDAO */
    // TODO ibatis 형식은 더이상 사용하지 않습니다.
    // @Resource(name = "sampleDAO")
    // private SampleDAO sampleDAO;

    // TODO mybatis 사용
    @Resource(name = "sampleMapper")
    private SampleMapper sampleMapper;

    /** ID Generation */
    @Resource(name = "egovIdGnrService")
    private EgovIdGnrService egovIdGnrService;

    /**
     * 글을 등록한다.
     * 
     * @param vo
     *            - 등록할 정보가 담긴 SampleVO
     * @return 등록 결과
     * @exception Exception
     */
    // @Override
    // TODO: 더이상 @Override 필요없습니다.
    public String insertSample(SampleVO vo) throws Exception {
        log.debug(vo.toString());

        /** ID Generation Service */
        String id = egovIdGnrService.getNextStringId();
        vo.setId(id);
        log.debug(vo.toString());

        sampleMapper.insertSample(vo);
        return id;
    }

    /**
     * 글을 수정한다.
     * 
     * @param vo
     *            - 수정할 정보가 담긴 SampleVO
     * @return void형
     * @exception Exception
     */
    public void updateSample(SampleVO vo) throws Exception {
        sampleMapper.updateSample(vo);
    }

    /**
     * 글을 삭제한다.
     * 
     * @param vo
     *            - 삭제할 정보가 담긴 SampleVO
     * @return void형
     * @exception Exception
     */
    public void deleteSample(SampleVO vo) throws Exception {
        sampleMapper.deleteSample(vo);
    }

    /**
     * 글을 조회한다.
     * 
     * @param vo
     *            - 조회할 정보가 담긴 SampleVO
     * @return 조회한 글
     * @exception Exception
     */
    public SampleVO selectSample(SampleVO vo) throws Exception {
        SampleVO resultVO = sampleMapper.selectSample(vo);
        if (resultVO == null)
            throw processException("info.nodata.msg");
        return resultVO;
    }

    /**
     * 글 목록을 조회한다.
     * 
     * @param searchVO
     *            - 조회할 정보가 담긴 VO
     * @return 글 목록
     * @exception Exception
     */
    public List<?> selectSampleList(SampleDefaultVO searchVO) throws Exception {
        return sampleMapper.selectSampleList(searchVO);
    }

    /**
     * 글 총 갯수를 조회한다.
     * @param searchVO - 조회할 정보가 담긴 VO
     * @return 글 총 갯수
     * @exception
     */
    public int selectSampleListTotCnt(SampleDefaultVO searchVO) {
        return sampleMapper.selectSampleListTotCnt(searchVO);
    }

}
