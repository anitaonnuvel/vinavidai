/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at

 * http://www.apache.org/licenses/LICENSE-2.0

 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.ahp.vinavidai.test.controller;

import java.util.ArrayList;
import java.util.List;

import org.ahp.vinavidai.pojo.Question;
import org.ahp.vinavidai.test.command.TakeTestCommand;
import org.ahp.vinavidai.test.command.TestInfoCommand;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Anita Onnuvel
 *
 */
@Controller
@RequestMapping( "/TakeTest" )
@SessionAttributes( {"takeTest", "testInfo"} )
public class TakeTest {
    
    @RequestMapping ( method = RequestMethod.POST, params = "submitAction=Pause Test" )
    public ModelAndView pauseTest( @ModelAttribute( "takeTest" ) TakeTestCommand pTakeTestCommand ){
        ModelMap lModelMap = new ModelMap();
        return new ModelAndView( "/test/TakeTest.jsp", "testModel", lModelMap );
    }

    @RequestMapping ( method = RequestMethod.POST, params = "submitAction=Cancel Test" )
    public ModelAndView cancelTest( @ModelAttribute( "takeTest" ) TakeTestCommand pTakeTestCommand ){
        ModelMap lModelMap = new ModelMap();
        return new ModelAndView( "/test/TakeTest.jsp", "testModel", lModelMap );
    }

    @RequestMapping ( method = RequestMethod.POST, params = "submitAction=Next Question" )
    public ModelAndView nextQuestion( @ModelAttribute( "takeTest" ) TakeTestCommand pTakeTestCommand ){
        System.out.println( "hi" );        
        int lCurrentQuestionNumber = pTakeTestCommand.getCurrentQuestionNumber() + 1;
        Question lCurrentQuestion = pTakeTestCommand.getQuestionList().get( lCurrentQuestionNumber );
        ModelMap lModelMap = new ModelMap();
        return new ModelAndView( "/test/TakeTest.jsp", "testModel", lModelMap );
    }

    @RequestMapping ( method = RequestMethod.POST, params = "submitAction=Complete Test" )
    public ModelAndView completeTest( @ModelAttribute( "takeTest" ) TakeTestCommand pTakeTestCommand ){
        ModelMap lModelMap = new ModelMap();
        return new ModelAndView( "/test/TakeTest.jsp", "testModel", lModelMap );
    }

    @RequestMapping ( method = RequestMethod.GET )
    public String showQuestion( ModelMap pModelMap, @ModelAttribute( "testInfo" ) TestInfoCommand pTestInfoCommand ){
        TakeTestCommand lTakeTestCommand = new TakeTestCommand();
        lTakeTestCommand.setTest( pTestInfoCommand.getTest() );
        List<Question> lQuestionList = new ArrayList<Question>( pTestInfoCommand.getTest().getQuiz().getQuestions() );
        lTakeTestCommand.setQuestionList( lQuestionList );
        lTakeTestCommand.setCurrentQuestionNumber( 1 );
        lTakeTestCommand.setTotalQuestions( lQuestionList.size() );
        lTakeTestCommand.setCurrentQuestion( lQuestionList.get( lTakeTestCommand.getCurrentQuestionNumber() - 1 ) );
        pModelMap.put( "takeTest", lTakeTestCommand );
        return "/test/TakeTest.jsp";
    }
}
