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

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ahp.commons.action.AhpAbstractDisplayAction;
import org.ahp.commons.util.AhpStringUtil;
import org.ahp.core.actions.AhpActionHelper;
import org.ahp.core.pojo.User;
import org.ahp.vinavidai.enums.NavigateActions;
import org.ahp.vinavidai.enums.SubmitActions;
import org.ahp.vinavidai.pojo.Category;
import org.ahp.vinavidai.pojo.SkillLevel;
import org.ahp.vinavidai.quiz.QuizService;
import org.ahp.vinavidai.quiz.form.CreateQuizForm;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Anita Onnuvel
 * 
 * @struts.action path="/DisplayCreateQuiz" name="CreateQuizForm"
 *                scope="session" validate="false"
 * 
 * @struts.action-forward name="DisplayCreateQuiz" path="/quiz/Quiz.jsp"
 * 
 * @struts.action-forward name="DisplayCreateQuestion"
 *                        path="/ProcessCreateQuestion.do" redirect="false"
 * 
 * @struts.action-forward name="DisplayCreateQuizConfirmation"
 *                        path="/quiz/CreateQuizConfirmation.jsp"
 * 
 * @spring.bean name="/DisplayCreateQuiz"
 * 
 * @spring.property name="quizService" ref="quizService"
 * 
 */
public class DisplayCreateQuiz extends AhpAbstractDisplayAction {

    final static Logger LOGGER = LoggerFactory
            .getLogger( DisplayCreateQuiz.class );

    private QuizService mQuizService;

    public void setQuizService( QuizService pQuizService ) {
        this.mQuizService = pQuizService;
    }

    @Override
    public ActionForward display( ActionMapping pActionMapping,
            ActionForm pActionForm, HttpServletRequest pHttpServletRequest,
            HttpServletResponse pHttpServletResponse ) {
        LOGGER.trace( "DisplayCreateQuiz :: starts" );
        User lLoggedInUser = AhpActionHelper
                .getLoggedInUser( pHttpServletRequest );
        CreateQuizForm lCreateQuizForm = ( CreateQuizForm ) pActionForm;
        String lNextPage = StringUtils.trimToEmpty( lCreateQuizForm
                .getNextPage() );
        ActionForward lActionForward = pActionMapping
                .findForward( NavigateActions.DisplayCreateQuiz.toString() );
        if ( lCreateQuizForm.isSubmitAction( SubmitActions.ADD_CATEGORY ) ) {
            Category lCategory = new Category();
            lCategory.setCategory( "" );
            lCreateQuizForm.getCategory().add( lCategory );
            return lActionForward;
        }
        if ( lCreateQuizForm.isSubmitAction( SubmitActions.DELETE_CATEGORY ) ) {
            int lDeleteCategoryIndex = lCreateQuizForm
                    .getHiddenDeleteCategoryIndex();
            lCreateQuizForm.getCategory().remove( lDeleteCategoryIndex );
            return lActionForward;
        }
        if ( lCreateQuizForm.isSubmitAction( SubmitActions.ADD_SKILL_LEVEL ) ) {
            SkillLevel lSkillLevel = new SkillLevel();
            lSkillLevel.setSkillLevel( "" );
            lCreateQuizForm.getSkillLevel().add( lSkillLevel );
            return lActionForward;
        }
        if ( lCreateQuizForm.isSubmitAction( SubmitActions.DELETE_SKILL_LEVEL ) ) {
            int lDeleteSkillLevelIndex = lCreateQuizForm
                    .getHiddenDeleteSkillLevelIndex();
            lCreateQuizForm.getSkillLevel().remove( lDeleteSkillLevelIndex );
            return lActionForward;
        }
        if ( NavigateActions.DisplayCreateQuiz.toString().equals( lNextPage ) ) {
            lCreateQuizForm.setCategoriesAutoComplete( AhpStringUtil
                    .listToQuotedString( this.mQuizService
                            .getAllCategoryNames( lLoggedInUser ) ) );
            lCreateQuizForm.setSkillLevelsAutoComplete( AhpStringUtil
                    .listToQuotedString( this.mQuizService
                            .getAllSkillLevelNames( lLoggedInUser ) ) );
            List<Category> lCategoryList = new ArrayList<Category>();
            lCategoryList.add( new Category() );
            lCreateQuizForm.setCategory( lCategoryList );
            List<SkillLevel> lSkillLevelList = new ArrayList<SkillLevel>();
            lSkillLevelList.add( new SkillLevel() );
            lCreateQuizForm.setSkillLevel( lSkillLevelList );
            lActionForward = pActionMapping
                    .findForward( NavigateActions.DisplayCreateQuiz.toString() );
        }
        if ( NavigateActions.DisplayCreateQuestion.toString()
                .equals( lNextPage ) ) {
            lActionForward = pActionMapping
                    .findForward( NavigateActions.DisplayCreateQuestion
                            .toString() );
        }
        if ( NavigateActions.DisplayCreateQuizConfirmation.toString().equals(
                lNextPage ) ) {
            lActionForward = pActionMapping
                    .findForward( NavigateActions.DisplayCreateQuizConfirmation
                            .toString() );
        }
        LOGGER.trace( "DisplayCreateQuiz :: ends" );
        return lActionForward;
    }
}
