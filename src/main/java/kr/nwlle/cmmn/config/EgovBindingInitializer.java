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
package kr.nwlle.cmmn.config;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

import lombok.extern.slf4j.Slf4j;

/**
 * @Class Name : EgovBindingInitializer.java
 * @Description : 자세한 클래스 설명
 * @author woojinp@legitsys.co.kr
 * @since 2021. 10. 12.
 * @version 1.0
 * @see
 *      Copyright(c) 2021 HISCO. All rights reserved
 */
@Slf4j
public class EgovBindingInitializer implements WebBindingInitializer {

    /**
     * initBinder
     * 
     * @param binder
     * @param request
     * @see 개발프레임웍크 실행환경 개발팀
     */
    @Override
    public void initBinder(WebDataBinder binder, WebRequest request) {
        log.debug("initBinder...................");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(false));
    }

}
