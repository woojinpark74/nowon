package kr.nwlle.common.service;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import kr.nwlle.cmmn.constant.Constant;
import kr.nwlle.cmmn.vo.ResultVO;
import kr.nwlle.common.mapper.ComCodeMapper;
import kr.nwlle.common.vo.ComCodeVO;

@Service
public class ComCodeService {

    @Autowired
    private ComCodeMapper comCodeMapper;

    @Cacheable(value = "CacheCode", key = "#param.grpCd + #param.cd")
    public ComCodeVO getCode(ComCodeVO param) {
        return comCodeMapper.getComCode(param);
    }

    @Cacheable("CacheCode")
    public ComCodeVO getCode(String group, String id) {
        ComCodeVO param = new ComCodeVO();
        param.setGrpCd(group);
        param.setCd(id);

        return comCodeMapper.getComCode(param);
    }

    public String getCodeName(String group, String id) {

        if (StringUtils.isEmpty(group) || StringUtils.isEmpty(id)) {
            return id;
        }

        ComCodeVO ci = this.getCode(group, id);

        if (ci == null)
            return id;

        return StringUtils.defaultString(ci.getCdNm(), id);
    }

    public String getNvlCodeName(String group, String id, String defaultStr) {

        if (StringUtils.isEmpty(group) || StringUtils.isEmpty(id)) {
            return defaultStr;
        }

        String returnValue = getCodeName(group, id);

        // codeValue 와 returnValue 가 같다는 의미는
        // code name이 null 이란 의미이다
        if (StringUtils.isEmpty(returnValue) || id.equals(returnValue))
            return defaultStr;

        return returnValue;
    }

    @Cacheable(value = "CacheListCode", key = "#param.grpCd")
    public List<ComCodeVO> getCodeList(ComCodeVO param) {
        return comCodeMapper.getComCodeList(param);
    }

    @Cacheable(value = "CacheListCode")
    public List<ComCodeVO> getCodeList(String group) {
        ComCodeVO param = new ComCodeVO();
        param.setGrpCd(group);

        return comCodeMapper.getComCodeList(param);
    }

    public ResultVO mergeCode(ComCodeVO codeVO) {

        if (StringUtils.isEmpty(codeVO.getGrpCd())) {
            return new ResultVO(Constant.FAILED, "코드그룹이 입력되지 않았습니다.");
        }

        if (!"Y".equals(codeVO.getGrpCdYn()) && StringUtils.isEmpty(codeVO.getCd())) {
            return new ResultVO(Constant.FAILED, "코드ID가 입력되지 않았습니다.");
        }

        if (1 != comCodeMapper.mergeComCode(codeVO)) {
            return new ResultVO(Constant.FAILED, "처리에 실패하였습니다.");
        }

        return new ResultVO(Constant.SUCCESS, "정상 처리하였습니다.");
    }

    public ResultVO deleteCode(ComCodeVO codeVO) {

        if (StringUtils.isEmpty(codeVO.getGrpCd())) {
            return new ResultVO(Constant.FAILED, "코드그룹이 입력되지 않았습니다.");
        }

        if (!"Y".equals(codeVO.getGrpCdYn()) && StringUtils.isEmpty(codeVO.getCd())) {
            return new ResultVO(Constant.FAILED, "코드ID가 입력되지 않았습니다.");
        }

        if (1 != comCodeMapper.deleteComCode(codeVO)) {
            return new ResultVO(Constant.FAILED, "처리에 실패하였습니다.");
        }

        return new ResultVO(Constant.SUCCESS, "정상 처리하였습니다.");
    }

    /*
     * @CacheEvict(value = { "CacheCode", "CacheListCode" }, allEntries = true)
     * public ResultVO clearCacheCode() {
     * logger.info("Cache clear : [CacheCode,CacheListCode]");
     * Constant.CRZ_IP_ADDRESS = this.setCrzIpAddress();
     * return new ResultVO(Constant.SUCCESS, "정상 처리하였습니다.");
     * }
     */

    /*
     * public String[] setCrzIpAddress() {
     * ComCodeVO param = new ComCodeVO();
     * param.setGrpCd("_CONF_IP_ADDRESS");
     * List<ComCodeVO> list = this.getCodeList(param);
     * String[] ipArray = new String[list.size()];
     * for (int i = 0; i < list.size(); i++) {
     * ipArray[i] = list.get(i).getCd();
     * }
     * return ipArray;
     * }
     */

    public ComCodeVO getMaxCode(ComCodeVO codeVO) {
        return comCodeMapper.getMaxComCode(codeVO);
    }

    public ResultVO modifyListCodeSeq(List<ComCodeVO> codeVO) {
        for (ComCodeVO cv : codeVO) {
            if (1 != comCodeMapper.updateSortNo(cv)) {
                return new ResultVO(Constant.FAILED, "처리에 실패하였습니다.");
            }
        }
        return new ResultVO(Constant.SUCCESS, "정상 처리하였습니다.");
    }

    public HashMap<String, ComCodeVO> getCodeMapInfo(String grpCd) {
        HashMap<String, ComCodeVO> codeMap = new HashMap<String, ComCodeVO>();

        ComCodeVO codeVO = new ComCodeVO();
        codeVO.setGrpCd(grpCd);
        List<ComCodeVO> codeList = comCodeMapper.getComCodeList(codeVO);

        for (ComCodeVO info : codeList) {
            codeMap.put(info.getCd(), info);
        }

        return codeMap;
    }

    public String getCodeEtc(String group, String id) {

        if (StringUtils.isEmpty(group) || StringUtils.isEmpty(id)) {
            return id;
        }

        ComCodeVO ci = this.getCode(group, id);

        if (ci == null)
            return id;

        return StringUtils.defaultString(ci.getEtc(), id);
    }

}
