/*
 * Copyright 2012 Anita Onnuvel
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.ahp.vinavidai.test.controller;

import javax.servlet.http.HttpServletRequest;

import org.ahp.vinavidai.pojo.Test;
import org.ahp.vinavidai.test.TestService;
import org.ahp.vinavidai.test.command.TestInfoCommand;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * @author Anita Onnuvel
 * 
 * 
 */
@Controller
@RequestMapping( "/TestInfo" )
@SessionAttributes( "testInfo" )
public class TestInfo {

    final static Logger LOGGER = LoggerFactory.getLogger( TestInfo.class );

    @Autowired
    private TestService mTestService;

    public void setTestService( TestService pTestService ) {
        this.mTestService = pTestService;
    }

    @RequestMapping( method = RequestMethod.GET )
    public String displayTestInfo( HttpServletRequest pHttpServletRequest, ModelMap pModelMap ) {
        TestInfoCommand lTestInfoCommand = new TestInfoCommand();
        String lTestAccessKey = ( String )pHttpServletRequest.getParameter( "accessKey" );
        if ( StringUtils.trimToNull( lTestAccessKey ) != null ) {
            Test lTest = this.mTestService.loadTest( lTestAccessKey );
            lTestInfoCommand.setAccessKey( lTestAccessKey );
            lTestInfoCommand.setTest( lTest );
        }
        pModelMap.put( "testInfo", lTestInfoCommand );
        return "/test/TestInfo.jsp";
    }
    
    @RequestMapping( method = RequestMethod.POST, params="submitAction=Begin Test" )
    public String beginTest( @ModelAttribute( "testInfo" ) TestInfoCommand pTestInfoCommand ) {
        System.out.println( "beginTest" );
        return "redirect:TakeTest.ahp";
    }
}
