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
import org.ahp.vinavidai.quiz.QuizService;
import org.ahp.vinavidai.quiz.form.CreateQuestionForm;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Anita Onnuvel
 * 
 * @struts.action path="/DisplayCreateQuestion" name="CreateQuestionForm"
 *                scope="session" validate="false"
 * 
 * @struts.action-forward name="DisplayCreateQuestionConfirmation"
 *                        path="/quiz/CreateQuizConfirmation.jsp"
 * 
 * @struts.action-forward name="DisplayCreateQuestion" path="/quiz/Question.jsp"
 * 
 * @struts.action-forward name="DisplayQuizConsole"
 *                        path="/ProcessQuizConsole.do" redirect="true"
 * 
 * @spring.bean name="/DisplayCreateQuestion"
 * 
 * @spring.property name="quizService" ref="quizService"
 */
public class DisplayCreateQuestion extends AhpAbstractDisplayAction {

    final static Logger LOGGER = LoggerFactory.getLogger( ProcessCreateQuestion.class );

    private QuizService mQuizService;

    public void setQuizService( QuizService pQuizService ) {
        this.mQuizService = pQuizService;
    }

    @Override
    public ActionForward display( ActionMapping pActionMapping, ActionForm pActionForm,
            HttpServletRequest pHttpServletRequest, HttpServletResponse pHttpServletResponse ) {
        User lLoggedInUser = AhpActionHelper.getLoggedInUser( pHttpServletRequest );
        CreateQuestionForm lCreateQuestionForm = ( CreateQuestionForm ) pActionForm;
        String lNextPage = StringUtils.trimToEmpty( lCreateQuestionForm.getNextPage() );
        ActionForward lActionForward = pActionMapping.findForward( NavigateActions.DisplayCreateQuestion.toString() );
        if ( NavigateActions.DisplayCreateQuestion.toString().equals( lNextPage )
                || NavigateActions.DisplayCreateQuestionStart.toString().equals( lNextPage )
                || NavigateActions.DisplayCreateQuestionNext.toString().equals( lNextPage ) ) {
            if ( NavigateActions.DisplayCreateQuestionStart.toString().equals( lNextPage ) ) {
                this.setDefaultValues( lCreateQuestionForm );
                pHttpServletRequest.getSession().removeAttribute( QUIZ_UNDER_CREATION );
            }
            if ( NavigateActions.DisplayCreateQuestionNext.toString().equals( lNextPage ) ) {
                this.setDefaultValues( lCreateQuestionForm );
            }
            if ( lCreateQuestionForm.isSubmitAction( SubmitActions.ADD_OPTION ) ) {
                Option lOption = new Option();
                lOption.setOptionDescription( "" );
                lCreateQuestionForm.getOptions().add( lOption );
            }
            if ( lCreateQuestionForm.isSubmitAction( SubmitActions.DELETE_OPTION ) ) {
                int lDeleteIndex = Integer.parseInt( lCreateQuestionForm.getHiddenDeleteOptionIndex() );
                if ( lCreateQuestionForm.getOptions().size() > lDeleteIndex )
                    lCreateQuestionForm.getOptions().remove( lDeleteIndex );
                while ( lCreateQuestionForm.getOptions().size() < 3 ) {
                    Option lOption = new Option();
                    lOption.setOptionDescription( "" );
                    lCreateQuestionForm.getOptions().add( lOption );
                }
            }
            if ( lCreateQuestionForm.isSubmitAction( SubmitActions.RESET ) ) {
                this.setDefaultValues( lCreateQuestionForm );
            }
        }
        if ( NavigateActions.DisplayCreateQuizConfirmation.toString().equals( lNextPage ) ) {
            lActionForward = pActionMapping.findForward( NavigateActions.DisplayCreateQuestionConfirmation.toString() );
        }
        if ( NavigateActions.DisplayCreateQuizCancelled.toString().equals( lNextPage ) ) {
            lActionForward = pActionMapping.findForward( NavigateActions.DisplayQuizConsole.toString() );
        }
        return lActionForward;
    }

    /**
     * 
     * @return
     */
    private void setDefaultValues( CreateQuestionForm lCreateQuestionForm ) {
        lCreateQuestionForm.setMaximumSizeTypeDisplaySet( EnumWrapper
                .enumerateDescriptionQuestionMaximumSizeDisplayStyle() );
        lCreateQuestionForm.setHoursDisplaySet( AhpTimeUtil.getHourInDay() );
        lCreateQuestionForm.setMinutesDisplaySet( AhpTimeUtil.getMinutes() );
        lCreateQuestionForm.setSecondsDisplaySet( AhpTimeUtil.getSeconds() );
        List<Option> lDefaultOptions = new LinkedList<Option>();
        while ( lDefaultOptions.size() < 3 ) {
            Option lOption = new Option();
            lOption.setOptionDescription( "" );
            lDefaultOptions.add( lOption );
        }
        lCreateQuestionForm.setOptions( lDefaultOptions );
        lCreateQuestionForm.setQuestionDescription( null );
        lCreateQuestionForm.setQuestionObjective( null );
        lCreateQuestionForm.setQuestionPoints( 1 );
        lCreateQuestionForm.setSelectedQuestionCategory( 0 );
        lCreateQuestionForm.setSelectedQuestionSkillLevel( 0 );
        lCreateQuestionForm.setResponseDurationInHours( null );
        lCreateQuestionForm.setResponseDurationInMinutes( null );
        lCreateQuestionForm.setResponseDurationInSeconds( null );
    }
}
