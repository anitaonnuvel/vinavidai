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
 * @struts.action path="/ProcessEditQuestion" name="EditQuestionForm"
 *                input="/qQuiz/Question.jsp" scope="session" validate="true"
 * 
 * @struts.action-forward name="DisplayEditQuestion"
 *                        path="/DisplayEditQuestion.do" redirect="false"
 * 
 * @spring.bean name="/ProcessEditQuestion"
 * 
 * @spring.property name="quizService" ref="quizService"
 */
public class ProcessEditQuestion extends AhpAbstractProcessAction {

	final static Logger LOGGER = LoggerFactory
			.getLogger(ProcessEditQuestion.class);

	private QuizService mQuizService;

	public void setQuizService(QuizService pQuizService) {
		this.mQuizService = pQuizService;
	}

	@Override
	public ActionForward process(ActionMapping pActionMapping,
			ActionForm pActionForm, HttpServletRequest pHttpServletRequest,
			HttpServletResponse pHttpServletResponse) {
		User lLoggedInUser = AhpActionHelper
				.getLoggedInUser(pHttpServletRequest);
		EditQuestionForm lEditQuestionForm = (EditQuestionForm) pActionForm;
		if (!lEditQuestionForm.isSubmitAction(SubmitActions.EMPTY)) {
			if (lEditQuestionForm.isSubmitAction(SubmitActions.ADD_OPTION)
					|| lEditQuestionForm
							.isSubmitAction(SubmitActions.DELETE_OPTION)
					|| lEditQuestionForm.isSubmitAction(SubmitActions.RESET)) {
				lEditQuestionForm
						.setNextPage(NavigateActions.DisplayEditQuestion
								.toString());
			}
			if (lEditQuestionForm.isSubmitAction(SubmitActions.NEXT)) {
				lEditQuestionForm
						.setNextPage(NavigateActions.DisplayEditQuestion
								.toString());
				this.storeQuestion(lEditQuestionForm, lLoggedInUser);
			}
			if (lEditQuestionForm.isSubmitAction(SubmitActions.COMPLETE)) {
				lEditQuestionForm
						.setNextPage(NavigateActions.DisplayEditQuizCompleted
								.toString());
				pHttpServletRequest.getSession().setAttribute(
						QUIZ_UNDER_CREATION, null);
				this.storeQuestion(lEditQuestionForm, lLoggedInUser);
			}
			if (lEditQuestionForm.isSubmitAction(SubmitActions.CANCEL)) {
				lEditQuestionForm
						.setNextPage(NavigateActions.DisplayEditQuizCancelled
								.toString());
			}
		} else {
			lEditQuestionForm.setNextPage(NavigateActions.DisplayEditQuestion
					.toString());
		}
		return pActionMapping.findForward(NavigateActions.DisplayEditQuestion
				.toString());
	}

	/**
	 * 
	 * @param pEditQuestionForm
	 * @param pLoggedInUser
	 */
	private void storeQuestion(EditQuestionForm pEditQuestionForm,
			User pLoggedInUser) {
		Quiz lQuizUnderCreation = pEditQuestionForm.getQuiz();
		Question lQuestion = new Question();
		Audit lAudit = AhpBusinessDelegate.createAudit(pLoggedInUser);
		lQuestion.setAudit(lAudit);
		lQuestion.setQuiz(lQuizUnderCreation);
		lQuestion.setQuestionType(QuestionType.valueOf(pEditQuestionForm
				.getQuestionType()));
		lQuestion.setQuestionDescription(pEditQuestionForm
				.getQuestionDescription());
		lQuestion
				.setQuestionObjective(pEditQuestionForm.getQuestionObjective());
		if (lQuizUnderCreation.getQuestions() != null) {
			lQuestion.setQuestionOrder(pEditQuestionForm.getQuiz()
					.getQuestions().size() + 1);
		} else {
			lQuestion.setQuestionOrder(1);
		}
		Category lCategory = new Category();
		lCategory
				.setCategoryId(pEditQuestionForm.getSelectedQuestionCategory());
		lQuestion.setCategory(lCategory);

		SkillLevel lSkillLevel = new SkillLevel();
		lSkillLevel.setSkillLevelId(pEditQuestionForm
				.getSelectedQuestionSkillLevel());
		lQuestion.setSkillLevel(lSkillLevel);

		lQuestion.setQuestionPoints(pEditQuestionForm.getQuestionPoints());

		long lQuestionDuration = -1;
		if (StringUtils.isNotBlank(pEditQuestionForm
				.getResponseDurationInHours())) {
			lQuestionDuration += TimeUnit.MILLISECONDS.convert(Long
					.parseLong(pEditQuestionForm.getResponseDurationInHours()),
					TimeUnit.HOURS);
		}
		if (StringUtils.isNotBlank(pEditQuestionForm
				.getResponseDurationInMinutes())) {
			lQuestionDuration += TimeUnit.MILLISECONDS.convert(Long
					.parseLong(pEditQuestionForm.getResponseDurationInHours()),
					TimeUnit.MINUTES);
		}
		if (StringUtils.isNotBlank(pEditQuestionForm
				.getResponseDurationInSeconds())) {
			lQuestionDuration += TimeUnit.MILLISECONDS.convert(Long
					.parseLong(pEditQuestionForm.getResponseDurationInHours()),
					TimeUnit.SECONDS);
		}
		lQuestion.setQuestionDuration(lQuestionDuration);
		if (lQuestion.getQuestionType().equals(QuestionType.MultipleChoice)
				|| lQuestion.getQuestionType().equals(QuestionType.WordList)
				|| lQuestion.getQuestionType().equals(QuestionType.Matching)
				|| lQuestion.getQuestionType().equals(QuestionType.Ordering)) {
			for (Option lOption : pEditQuestionForm.getOptions()) {
				lOption.setQuestion(lQuestion);
				lOption.setAudit(lAudit);
			}
		}
		if (lQuestion.getQuestionType().equals(QuestionType.TrueOrFalse)
				|| lQuestion.getQuestionType().equals(QuestionType.Descriptive)) {
			Option lOption = pEditQuestionForm.getOptions().get(0);
			lOption.setQuestion(lQuestion);
			if (!StringUtils.isEmpty(lOption
					.getDescriptionQuestionMaximumSizeTypeStr())) {
				lOption.setDescriptionQuestionMaximumSizeType(DescriptionQuestionMaximumSizeType
						.valueOf(lOption
								.getDescriptionQuestionMaximumSizeTypeStr()));
			}
			lOption.setAudit(lAudit);
			List<Option> lOptions = new LinkedList<Option>();
			lOptions.add(lOption);
			pEditQuestionForm.setOptions(lOptions);
		}
		// Set Options in Question
		Set<Option> lOptions = new LinkedHashSet<Option>();
		for (Option lOption : pEditQuestionForm.getOptions()) {
			lOptions.add(lOption);
		}
		lQuestion.setOptions(lOptions);

		/*
		 * if ( lQuizUnderCreation.getQuestions() == null ) { Set<Question>
		 * lQuestions = new LinkedHashSet<Question>(); lQuestions.add( lQuestion
		 * ); // lQuizUnderCreation.setQuestions( lQuestions ); } else {
		 * lQuizUnderCreation.getQuestions().add( lQuestion ); }
		 */
		lQuizUnderCreation = this.mQuizService.updateQuiz(lQuizUnderCreation);
		pEditQuestionForm.setQuiz(lQuizUnderCreation);
	}
}
