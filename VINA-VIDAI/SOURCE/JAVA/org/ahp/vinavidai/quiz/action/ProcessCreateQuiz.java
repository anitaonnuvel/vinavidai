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
package org.ahp.vinavidai.quiz.action;

import static org.ahp.vinavidai.constants.HttpSessionAttributeConstants.QUIZ_UNDER_CREATION;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ahp.commons.action.AhpAbstractProcessAction;
import org.ahp.core.actions.AhpActionHelper;
import org.ahp.core.businessdelegate.AhpBusinessDelegate;
import org.ahp.core.pojo.Audit;
import org.ahp.core.pojo.User;
import org.ahp.vinavidai.enums.NavigateActions;
import org.ahp.vinavidai.enums.Status;
import org.ahp.vinavidai.enums.SubmitActions;
import org.ahp.vinavidai.pojo.Category;
import org.ahp.vinavidai.pojo.Quiz;
import org.ahp.vinavidai.pojo.SkillLevel;
import org.ahp.vinavidai.quiz.QuizService;
import org.ahp.vinavidai.quiz.form.CreateQuizForm;
import org.ahp.vinavidai.quiz.form.QuizForm;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Anita Onnuvel
 * 
 * @struts.action path="/ProcessCreateQuiz" name="CreateQuizForm"
 *                input="/quiz/Quiz.jsp" scope="session" validate="true"
 * 
 * @struts.action-forward name="DisplayCreateQuiz" path="/DisplayCreateQuiz.do"
 *                        redirect="false"
 * 
 * @spring.bean name="/ProcessCreateQuiz"
 * 
 * @spring.property name="quizService" ref="quizService"
 * 
 */
public class ProcessCreateQuiz extends AhpAbstractProcessAction {

    final static Logger LOGGER = LoggerFactory
            .getLogger( ProcessCreateQuiz.class );

    private QuizService mQuizService;

    public void setQuizService( QuizService pQuizService ) {
        this.mQuizService = pQuizService;
    }

    @Override
    public ActionForward process( ActionMapping pActionMapping,
            ActionForm pActionForm, HttpServletRequest pHttpServletRequest,
            HttpServletResponse pHttpServletResponse ) {
        LOGGER.trace( "ProcessCreateQuiz :: starts" );
        User lLoggedInUser = AhpActionHelper
                .getLoggedInUser( pHttpServletRequest );
        CreateQuizForm lCreateQuizForm = ( CreateQuizForm ) pActionForm;
        if ( lCreateQuizForm.isSubmitAction( SubmitActions.SAVE ) ) {
            lCreateQuizForm.setNextPage( NavigateActions.DisplayCreateQuestion
                    .toString() );
            Quiz lQuiz = this.storeQuiz( lCreateQuizForm, lLoggedInUser );
            pHttpServletRequest.getSession().setAttribute( QUIZ_UNDER_CREATION,
                    lQuiz );
            lCreateQuizForm
                    .setNextPage( NavigateActions.DisplayCreateQuizConfirmation
                            .toString() );
        } else if ( lCreateQuizForm
                .isSubmitAction( SubmitActions.SAVE_AND_ADD_QUESTIONS ) ) {
            Quiz lStoredQuiz = this.storeQuiz( lCreateQuizForm, lLoggedInUser );
            pHttpServletRequest.setAttribute( QUIZ_UNDER_CREATION, lStoredQuiz );
            lCreateQuizForm.setNextPage( NavigateActions.DisplayCreateQuestion
                    .toString() );
        } else {
            lCreateQuizForm.setNextPage( NavigateActions.DisplayCreateQuiz
                    .toString() );
        }
        LOGGER.trace( "ProcessCreateQuiz :: ends" );
        return pActionMapping.findForward( NavigateActions.DisplayCreateQuiz
                .toString() );
    }

    /**
     * 
     * @param pCreateQuizForm
     * @return
     */
    private Quiz storeQuiz( QuizForm pCreateQuizForm, User pLoggedInUser ) {
        Audit lAudit = AhpBusinessDelegate.createAudit( pLoggedInUser );
        Quiz lQuiz = new Quiz();
        lQuiz.setQuizName( pCreateQuizForm.getQuizName() );
        lQuiz.setQuizDescription( pCreateQuizForm.getQuizDescription() );
        lQuiz.setQuizStatus( Status.Enabled );
        lQuiz.setAudit( lAudit );

        Set<Category> lCategorySet = new HashSet<Category>();
        if ( pCreateQuizForm.getCategory() != null ) {
            lCategorySet.addAll( lCategorySet );
        }
        for ( Category lCategory : lCategorySet ) {
            lCategory.setAudit( lAudit );
            lCategory.setQuiz( lQuiz );
        }
        Category lCategory = new Category();
        lCategory.setCategory( "Default" );
        lCategory.setAudit( lAudit );
        lCategory.setQuiz( lQuiz );
        lCategorySet.add( lCategory );
        lQuiz.setCategories( lCategorySet );

        Set<SkillLevel> lSkillLevelSet = new HashSet<SkillLevel>();
        if ( pCreateQuizForm.getSkillLevel() != null ) {
            lSkillLevelSet.addAll( pCreateQuizForm.getSkillLevel() );
        }
        for ( SkillLevel lSkillLevel : lSkillLevelSet ) {
            lSkillLevel.setAudit( lAudit );
            lSkillLevel.setQuiz( lQuiz );
        }
        SkillLevel lSkillLevel = new SkillLevel();
        lSkillLevel.setSkillLevel( "Default" );
        lSkillLevel.setAudit( lAudit );
        lSkillLevel.setQuiz( lQuiz );
        lSkillLevelSet.add( lSkillLevel );
        lQuiz.setSkillLevels( lSkillLevelSet );

        this.mQuizService.createQuiz( lQuiz );

        LOGGER.info( "Created Quiz : " + lQuiz.getQuizName() + " with Id: "
                + lQuiz.getQuizId() );
        return lQuiz;
    }

}