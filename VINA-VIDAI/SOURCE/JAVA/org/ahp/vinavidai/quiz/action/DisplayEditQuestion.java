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

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ahp.commons.action.AhpAbstractDisplayAction;
import org.ahp.commons.util.AhpTimeUtil;
import org.ahp.core.actions.AhpActionHelper;
import org.ahp.core.pojo.User;
import org.ahp.vinavidai.enums.EnumWrapper;
import org.ahp.vinavidai.enums.NavigateActions;
import org.ahp.vinavidai.enums.SubmitActions;
import org.ahp.vinavidai.pojo.Option;
import org.ahp.vinavidai.pojo.Quiz;
import org.ahp.vinavidai.quiz.QuizService;
import org.ahp.vinavidai.quiz.form.EditQuestionForm;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Anita Onnuvel
 * 
 * @struts.action path="/DisplayEditQuestion" name="EditQuestionForm"
 *                scope="session" validate="false"
 * 
 * @struts.action-forward name="DisplayEditQuestionConfirmation"
 *                        path="/quiz/EditQuizConfirmation.jsp"
 * 
 * @struts.action-forward name="DisplayEditQuestion" path="/quiz/Question.jsp"
 * 
 * @struts.action-forward name="DisplayQuizConsole"
 *                        path="/ProcessQuizConsole.do" redirect="true"
 * 
 * @spring.bean name="/DisplayEditQuestion"
 * 
 * @spring.property name="quizService" ref="quizService"
 */
public class DisplayEditQuestion extends AhpAbstractDisplayAction {

    final static Logger LOGGER = LoggerFactory
            .getLogger( ProcessEditQuestion.class );

    private QuizService mQuizService;

    public void setQuizService( QuizService pQuizService ) {
        this.mQuizService = pQuizService;
    }

    @Override
    public ActionForward display( ActionMapping pActionMapping,
            ActionForm pActionForm, HttpServletRequest pHttpServletRequest,
            HttpServletResponse pHttpServletResponse ) {
        User lLoggedInUser = AhpActionHelper
                .getLoggedInUser( pHttpServletRequest );
        EditQuestionForm lEditQuestionForm = ( EditQuestionForm ) pActionForm;
        String lNextPage = StringUtils.trimToEmpty( lEditQuestionForm
                .getNextPage() );
        ActionForward lActionForward = pActionMapping
                .findForward( NavigateActions.DisplayEditQuestion.toString() );
        if ( NavigateActions.DisplayEditQuestion.toString().equals( lNextPage )
                || NavigateActions.DisplayEditQuestionNext.toString().equals(
                        lNextPage ) ) {
            if ( lEditQuestionForm.isSubmitAction( SubmitActions.EMPTY ) ) {
                Quiz lQuiz = ( Quiz ) pHttpServletRequest.getSession()
                        .getAttribute( QUIZ_UNDER_CREATION );
                if ( lQuiz != null )
                    lEditQuestionForm.setQuiz( lQuiz );
                this.setDefaultValues( lEditQuestionForm );
                pHttpServletRequest.getSession().removeAttribute(
                        QUIZ_UNDER_CREATION );
            }
            if ( lEditQuestionForm.isSubmitAction( SubmitActions.NEXT ) ) {
                Quiz lQuiz = ( Quiz ) pHttpServletRequest.getSession()
                        .getAttribute( QUIZ_UNDER_CREATION );
                if ( lQuiz != null )
                    lEditQuestionForm.setQuiz( lQuiz );
                this.setDefaultValues( lEditQuestionForm );
                pHttpServletRequest.getSession().removeAttribute(
                        QUIZ_UNDER_CREATION );
            }
            if ( lEditQuestionForm.isSubmitAction( SubmitActions.ADD_OPTION ) ) {
                Option lOption = new Option();
                lOption.setOptionDescription( "" );
                lEditQuestionForm.getOptions().add( lOption );
            }
            if ( lEditQuestionForm.isSubmitAction( SubmitActions.DELETE_OPTION ) ) {
                int lDeleteIndex = Integer.parseInt( lEditQuestionForm
                        .getHiddenDeleteOptionIndex() );
                if ( lEditQuestionForm.getOptions().size() > lDeleteIndex )
                    lEditQuestionForm.getOptions().remove( lDeleteIndex );
                while ( lEditQuestionForm.getOptions().size() < 3 ) {
                    Option lOption = new Option();
                    lOption.setOptionDescription( "" );
                    lEditQuestionForm.getOptions().add( lOption );
                }
            }
            if ( lEditQuestionForm.isSubmitAction( SubmitActions.RESET ) ) {
                this.setDefaultValues( lEditQuestionForm );
            }
        }
        if ( NavigateActions.DisplayEditQuiz.toString().equals( lNextPage ) ) {

        }
        if ( NavigateActions.DisplayEditQuizConfirmation.toString().equals(
                lNextPage ) ) {
            lActionForward = pActionMapping
                    .findForward( NavigateActions.DisplayEditQuestionConfirmation
                            .toString() );
        }
        if ( NavigateActions.DisplayEditQuizCancelled.toString().equals(
                lNextPage ) ) {
            lActionForward = pActionMapping
                    .findForward( NavigateActions.DisplayQuizConsole.toString() );
        }
        return lActionForward;
    }

    /**
     * 
     * @return
     */
    private void setDefaultValues( EditQuestionForm lEditQuestionForm ) {
        lEditQuestionForm.setMaximumSizeTypeDisplaySet( EnumWrapper
                .enumerateDescriptionQuestionMaximumSizeDisplayStyle() );
        lEditQuestionForm.setHoursDisplaySet( AhpTimeUtil.getHourInDay() );
        lEditQuestionForm.setMinutesDisplaySet( AhpTimeUtil.getMinutes() );
        lEditQuestionForm.setSecondsDisplaySet( AhpTimeUtil.getSeconds() );
        List<Option> lDefaultOptions = new LinkedList<Option>();
        while ( lDefaultOptions.size() < 3 ) {
            Option lOption = new Option();
            lOption.setOptionDescription( "" );
            lDefaultOptions.add( lOption );
        }
        lEditQuestionForm.setOptions( lDefaultOptions );
        lEditQuestionForm.setQuestionDescription( null );
        lEditQuestionForm.setQuestionObjective( null );
        lEditQuestionForm.setQuestionPoints( 1 );
        lEditQuestionForm.setSelectedQuestionCategory( 0 );
        lEditQuestionForm.setSelectedQuestionSkillLevel( 0 );
        lEditQuestionForm.setResponseDurationInHours( null );
        lEditQuestionForm.setResponseDurationInMinutes( null );
        lEditQuestionForm.setResponseDurationInSeconds( null );
    }
}
