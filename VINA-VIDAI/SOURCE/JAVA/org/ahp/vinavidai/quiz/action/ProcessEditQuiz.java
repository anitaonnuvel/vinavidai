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

import static org.ahp.vinavidai.constants.HttpRequestAttributeConstants.QUIZ_UNDER_EDIT;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ahp.commons.action.AhpAbstractProcessAction;
import org.ahp.core.actions.AhpActionHelper;
import org.ahp.core.pojo.User;
import org.ahp.vinavidai.enums.NavigateActions;
import org.ahp.vinavidai.enums.SubmitActions;
import org.ahp.vinavidai.pojo.Category;
import org.ahp.vinavidai.pojo.Question;
import org.ahp.vinavidai.pojo.Quiz;
import org.ahp.vinavidai.pojo.SkillLevel;
import org.ahp.vinavidai.quiz.QuizService;
import org.ahp.vinavidai.quiz.form.EditQuizForm;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Anita Onnuvel
 * 
 * @struts.action path="/ProcessEditQuiz" name="EditQuizForm"
 *                input="/quiz/Quiz.jsp" scope="session" validate="true"
 * 
 * @struts.action-forward name="DisplayEditQuiz" path="/DisplayEditQuiz.do"
 *                        redirect="false"
 * 
 * @spring.bean name="/ProcessEditQuiz"
 * 
 * @spring.property name="quizService" ref="quizService"
 * 
 */
public class ProcessEditQuiz extends AhpAbstractProcessAction {

    final static Logger LOGGER = LoggerFactory
            .getLogger( ProcessEditQuiz.class );

    private QuizService mQuizService;

    public void setQuizService( QuizService pQuizService ) {
        this.mQuizService = pQuizService;
    }

    @Override
    public ActionForward process( ActionMapping pActionMapping,
            ActionForm pActionForm, HttpServletRequest pHttpServletRequest,
            HttpServletResponse pHttpServletResponse ) {
        User lLoggedInUser = AhpActionHelper
                .getLoggedInUser( pHttpServletRequest );
        EditQuizForm lEditQuizForm = ( EditQuizForm ) pActionForm;
        Quiz lQuizUnderEdit = ( Quiz ) pHttpServletRequest
                .getAttribute( QUIZ_UNDER_EDIT );
        if ( lQuizUnderEdit == null ) {
            if ( lEditQuizForm.isSubmitAction( SubmitActions.SAVE_CHANGES ) ) {
                Quiz lUpdatedQuiz = this.updateQuiz( lEditQuizForm,
                        lLoggedInUser );
                lEditQuizForm.setQuizUnderEdit( lUpdatedQuiz );
                lEditQuizForm.setNextPage( NavigateActions.EditQuestions
                        .toString() );
            } else {
                lEditQuizForm.setNextPage( NavigateActions.DisplayEditQuiz
                        .toString() );
            }
        } else {
            lEditQuizForm.setQuizUnderEdit( lQuizUnderEdit );
            lEditQuizForm.setNextPage( NavigateActions.DisplayEditQuiz
                    .toString() );
        }

        return pActionMapping.findForward( NavigateActions.DisplayEditQuiz
                .toString() );
    }

    /**
     * 
     * @param pEditQuizForm
     * @return
     */
    private Quiz updateQuiz( EditQuizForm pEditQuizForm, User pLoggedInUser ) {
        Quiz lQuizUnderEdit = pEditQuizForm.getQuizUnderEdit(); // this.mQuizService.loadQuiz(
                                                                // pEditQuizForm.getQuizUnderEdit().getQuizId(),
                                                                // true );
        // Quiz lQuiz = pEditQuizForm.getQuizUnderEdit();
        lQuizUnderEdit.setQuizName( pEditQuizForm.getQuizName() );
        lQuizUnderEdit.setQuizDescription( pEditQuizForm.getQuizDescription() );
        Set<Category> categoryList = new HashSet<Category>();
        categoryList.addAll( pEditQuizForm.getCategory() );
        lQuizUnderEdit.setCategories( categoryList );
        Set<SkillLevel> skillList = new HashSet<SkillLevel>();
        skillList.addAll( pEditQuizForm.getSkillLevel() );
        lQuizUnderEdit.setSkillLevels( skillList );

        lQuizUnderEdit.getAudit().setLastUpdatedBy( pLoggedInUser.getUserId() );
        lQuizUnderEdit.getAudit().setLastUpdatedDate(
                new Date( System.currentTimeMillis() ) );
        if ( pEditQuizForm.getDeleteCategoryIdSet().size() != 0
                || pEditQuizForm.getDeleteSkillLevelIdSet().size() != 0 ) {
            Category lDefaultCategory = this.mQuizService
                    .getDefaultCategory( lQuizUnderEdit );
            SkillLevel lDefaultSkillLevel = this.mQuizService
                    .getDefaultSkillLevel( lQuizUnderEdit );
            for ( Question lQuestion : lQuizUnderEdit.getQuestions() ) {
                if ( pEditQuizForm.getDeleteCategoryIdSet().contains(
                        lQuestion.getCategory().getCategoryId() ) )
                    ;
                {
                    lQuestion.setCategory( lDefaultCategory );
                }
                if ( pEditQuizForm.getDeleteSkillLevelIdSet().contains(
                        lQuestion.getSkillLevel().getSkillLevelId() ) ) {
                    lQuestion.setSkillLevel( lDefaultSkillLevel );
                }
            }
        }
        if ( true )
            this.mQuizService.updateQuiz( lQuizUnderEdit );
        LOGGER.debug( "Edited Quiz with Id: " + lQuizUnderEdit.getQuizId() );
        return lQuizUnderEdit;
    }

}