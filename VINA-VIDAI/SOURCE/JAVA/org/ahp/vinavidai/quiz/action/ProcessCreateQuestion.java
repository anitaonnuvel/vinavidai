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

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ahp.commons.action.AhpAbstractProcessAction;
import org.ahp.core.actions.AhpActionHelper;
import org.ahp.core.businessdelegate.AhpBusinessDelegate;
import org.ahp.core.pojo.Audit;
import org.ahp.core.pojo.User;
import org.ahp.vinavidai.enums.DescriptionQuestionMaximumSizeType;
import org.ahp.vinavidai.enums.NavigateActions;
import org.ahp.vinavidai.enums.QuestionType;
import org.ahp.vinavidai.enums.SubmitActions;
import org.ahp.vinavidai.pojo.Category;
import org.ahp.vinavidai.pojo.Option;
import org.ahp.vinavidai.pojo.Question;
import org.ahp.vinavidai.pojo.Quiz;
import org.ahp.vinavidai.pojo.SkillLevel;
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
 * @struts.action 
 *     path="/ProcessCreateQuestion" 
 *     name="CreateQuestionForm"
 *     input="/quiz/Question.jsp" 
 *     scope="session" 
 *     validate="true"
 * 
 * @struts.action-forward 
 *     name="DisplayCreateQuestion"
 *     path="/DisplayCreateQuestion.do" 
 *     redirect="false"
 * 
 * @spring.bean 
 *     name="/ProcessCreateQuestion"
 * 
 * @spring.property 
 *     name="quizService" 
 *     ref="quizService"
 */
public class ProcessCreateQuestion extends AhpAbstractProcessAction {

    final static Logger LOGGER = LoggerFactory.getLogger( ProcessCreateQuestion.class );

    private QuizService mQuizService;

    public void setQuizService( QuizService pQuizService ) {
        this.mQuizService = pQuizService;
    }

    @Override
    public ActionForward process( ActionMapping pActionMapping, 
                                  ActionForm pActionForm,
                                  HttpServletRequest pHttpServletRequest, 
                                  HttpServletResponse pHttpServletResponse ) {
        User lLoggedInUser = AhpActionHelper.getLoggedInUser( pHttpServletRequest );
        CreateQuestionForm lCreateQuestionForm = ( CreateQuestionForm ) pActionForm;
        Quiz lQuiz = ( Quiz ) pHttpServletRequest.getAttribute( QUIZ_UNDER_CREATION );
        if ( !lCreateQuestionForm.isSubmitAction( SubmitActions.EMPTY ) ) {
            if ( lQuiz != null ) {
                lCreateQuestionForm.setQuiz( lQuiz );
                lCreateQuestionForm.setNextPage( NavigateActions.DisplayCreateQuestionStart.toString() );
            }
            if ( lCreateQuestionForm.isSubmitAction( SubmitActions.ADD_OPTION )
                    || lCreateQuestionForm.isSubmitAction( SubmitActions.DELETE_OPTION )
                    || lCreateQuestionForm.isSubmitAction( SubmitActions.RESET ) ) {
                lCreateQuestionForm.setNextPage( NavigateActions.DisplayCreateQuestion.toString() );
            }
            if ( lCreateQuestionForm.isSubmitAction( SubmitActions.NEXT ) ) {
                lCreateQuestionForm.setNextPage( NavigateActions.DisplayCreateQuestionNext.toString() );
                this.storeQuestion( lCreateQuestionForm, lLoggedInUser );
            }
            if ( lCreateQuestionForm.isSubmitAction( SubmitActions.COMPLETE ) ) {
                lCreateQuestionForm.setNextPage( NavigateActions.DisplayCreateQuizConfirmation.toString() );
                pHttpServletRequest.getSession().setAttribute( QUIZ_UNDER_CREATION, null );
                this.storeQuestion( lCreateQuestionForm, lLoggedInUser );
            }
            if ( lCreateQuestionForm.isSubmitAction( SubmitActions.CANCEL ) ) {
                lCreateQuestionForm.setNextPage( NavigateActions.DisplayCreateQuizCancelled.toString() );
            }
        } else {
            lCreateQuestionForm.setNextPage( NavigateActions.DisplayCreateQuestion.toString() );
        }
        return pActionMapping.findForward( NavigateActions.DisplayCreateQuestion.toString() );
    }

    /**
     * 
     * @param pCreateQuestionForm
     * @param pLoggedInUser
     */
    private void storeQuestion( CreateQuestionForm pCreateQuestionForm, User pLoggedInUser ) {
        Quiz lQuizUnderCreation = pCreateQuestionForm.getQuiz();
        Question lQuestion = new Question();
        Audit lAudit = AhpBusinessDelegate.createAudit( pLoggedInUser );
        lQuestion.setAudit( lAudit );
        lQuestion.setQuiz( lQuizUnderCreation );
        lQuestion.setQuestionType( QuestionType.valueOf( pCreateQuestionForm.getQuestionType() ) );
        lQuestion.setQuestionDescription( pCreateQuestionForm.getQuestionDescription() );
        lQuestion.setQuestionObjective( pCreateQuestionForm.getQuestionObjective() );
        if ( lQuizUnderCreation.getQuestions() != null ) {
            lQuestion.setQuestionOrder( pCreateQuestionForm.getQuiz().getQuestions().size() + 1 );
        } else {
            lQuestion.setQuestionOrder( 1 );
        }
        Category lCategory = new Category();
        lCategory.setCategoryId( pCreateQuestionForm.getSelectedQuestionCategory() );
        lQuestion.setCategory( lCategory );

        SkillLevel lSkillLevel = new SkillLevel();
        lSkillLevel.setSkillLevelId( pCreateQuestionForm.getSelectedQuestionSkillLevel() );
        lQuestion.setSkillLevel( lSkillLevel );

        lQuestion.setQuestionPoints( pCreateQuestionForm.getQuestionPoints() );

        long lQuestionDuration = -1;
        if ( StringUtils.isNotBlank( pCreateQuestionForm.getResponseDurationInHours() ) ) {
            lQuestionDuration += TimeUnit.MILLISECONDS.convert(
                    Long.parseLong( pCreateQuestionForm.getResponseDurationInHours() ), TimeUnit.HOURS );
        }
        if ( StringUtils.isNotBlank( pCreateQuestionForm.getResponseDurationInMinutes() ) ) {
            lQuestionDuration += TimeUnit.MILLISECONDS.convert(
                    Long.parseLong( pCreateQuestionForm.getResponseDurationInHours() ), TimeUnit.MINUTES );
        }
        if ( StringUtils.isNotBlank( pCreateQuestionForm.getResponseDurationInSeconds() ) ) {
            lQuestionDuration += TimeUnit.MILLISECONDS.convert(
                    Long.parseLong( pCreateQuestionForm.getResponseDurationInHours() ), TimeUnit.SECONDS );
        }
        lQuestion.setQuestionDuration( lQuestionDuration );
        if ( lQuestion.getQuestionType().equals( QuestionType.MultipleChoice )
                || lQuestion.getQuestionType().equals( QuestionType.WordList )
                || lQuestion.getQuestionType().equals( QuestionType.Matching )
                || lQuestion.getQuestionType().equals( QuestionType.Ordering ) ) {
            for ( Option lOption : pCreateQuestionForm.getOptions() ) {
                lOption.setQuestion( lQuestion );
                lOption.setAudit( lAudit );
            }
        }
        if ( lQuestion.getQuestionType().equals( QuestionType.TrueOrFalse )
                || lQuestion.getQuestionType().equals( QuestionType.Descriptive ) ) {
            Option lOption = pCreateQuestionForm.getOptions().get( 0 );
            lOption.setQuestion( lQuestion );
            if ( !StringUtils.isEmpty( lOption.getDescriptionQuestionMaximumSizeTypeStr() ) ) {
                lOption.setDescriptionQuestionMaximumSizeType( DescriptionQuestionMaximumSizeType.valueOf( lOption
                        .getDescriptionQuestionMaximumSizeTypeStr() ) );
            }
            lOption.setAudit( lAudit );
            List<Option> lOptions = new LinkedList<Option>();
            lOptions.add( lOption );
            pCreateQuestionForm.setOptions( lOptions );
        }
        if ( lQuestion.getQuestionType().equals( QuestionType.FillInTheBlank ) ) {
            String[] lQuestionDescriptionArray = lQuestion.getQuestionDescription().split( "\\s+" );
            List<Option> lFillInTheBlanksOptions = new LinkedList<Option>();
            for ( String lWordToken : lQuestionDescriptionArray ) {
                if ( lWordToken.startsWith( "$" ) && lWordToken.endsWith( "$" ) ) {
                    Option lOption = new Option();
                    lOption.setOptionDescription( lWordToken.substring( 1, lWordToken.length() - 1 ) );
                    lOption.setQuestion( lQuestion );
                    lOption.setAudit( lAudit );
                    lFillInTheBlanksOptions.add( lOption );
                }
            }
            pCreateQuestionForm.setOptions( lFillInTheBlanksOptions );
        }
        // Set Options in Question
        Set<Option> lOptions = new LinkedHashSet<Option>();
        for ( Option lOption : pCreateQuestionForm.getOptions() ) {
            lOptions.add( lOption );
        }
        lQuestion.setOptions( lOptions );

        if ( lQuizUnderCreation.getQuestions() == null ) {
            Set<Question> lQuestions = new LinkedHashSet<Question>();
            lQuestions.add( lQuestion );
            lQuizUnderCreation.setQuestions( lQuestions );
        } else {
            lQuizUnderCreation.getQuestions().add( lQuestion );
        }
        lQuizUnderCreation = this.mQuizService.updateQuiz( lQuizUnderCreation );
        pCreateQuestionForm.setQuiz( lQuizUnderCreation );
    }
}
