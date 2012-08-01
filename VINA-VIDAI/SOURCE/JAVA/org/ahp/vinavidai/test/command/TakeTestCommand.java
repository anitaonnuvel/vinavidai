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
package org.ahp.vinavidai.test.command;

import java.util.List;

import org.ahp.commons.form.AhpAbstractCommandObject;
import org.ahp.vinavidai.pojo.Question;
import org.ahp.vinavidai.pojo.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Anita Onnuvel
 * 
 * 
 */
public class TakeTestCommand extends AhpAbstractCommandObject {

    private static final long serialVersionUID = 1L;

    final static Logger LOGGER = LoggerFactory.getLogger( TakeTestCommand.class );

    private Test mTest;
    private Question mCurrentQuestion;
    private int mCurrentQuestionNumber;
    private int mTotalQuestions;
    private int mNumberOfCorrectOptions;
    private List<Question> mQuestionList;

    /**
     * @return the questionList
     */
    public List<Question> getQuestionList() {
        return mQuestionList;
    }

    /**
     * @param pQuestionList the questionList to set
     */
    public void setQuestionList( List<Question> pQuestionList ) {
        mQuestionList = pQuestionList;
    }

    public Test getTest() {
        return mTest;
    }

    public void setTest( Test pTest ) {
        mTest = pTest;
    }

    /**
     * @return the currentQuestion
     */
    public Question getCurrentQuestion() {
        return mCurrentQuestion;
    }

    /**
     * @param pCurrentQuestion the currentQuestion to set
     */
    public void setCurrentQuestion( Question pCurrentQuestion ) {
        mCurrentQuestion = pCurrentQuestion;
    }

    /**
     * @return the currentQuestionNumber
     */
    public int getCurrentQuestionNumber() {
        return mCurrentQuestionNumber;
    }

    /**
     * @param pCurrentQuestionNumber the currentQuestionNumber to set
     */
    public void setCurrentQuestionNumber( int pCurrentQuestionNumber ) {
        mCurrentQuestionNumber = pCurrentQuestionNumber;
    }

    /**
     * @return the totalQuestions
     */
    public int getTotalQuestions() {
        return mTotalQuestions;
    }

    /**
     * @return the numberOfCorrectOptions
     */
    public int getNumberOfCorrectOptions() {
        return mNumberOfCorrectOptions;
    }

    /**
     * @param pNumberOfCorrectOptions the numberOfCorrectOptions to set
     */
    public void setNumberOfCorrectOptions( int pNumberOfCorrectOptions ) {
        mNumberOfCorrectOptions = pNumberOfCorrectOptions;
    }

    /**
     * @param pTotalQuestions the totalQuestions to set
     */
    public void setTotalQuestions( int pTotalQuestions ) {
        mTotalQuestions = pTotalQuestions;
    }

    
}
