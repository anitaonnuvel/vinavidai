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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ahp.commons.action.AhpAbstractProcessAction;
import org.ahp.core.actions.AhpActionHelper;
import org.ahp.core.pojo.User;
import org.ahp.vinavidai.enums.NavigateActions;
import org.ahp.vinavidai.enums.PaginationOperations;
import org.ahp.vinavidai.enums.SubmitActions;
import org.ahp.vinavidai.quiz.QuizService;
import org.ahp.vinavidai.quiz.manage.form.ManageQuizForm;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Anita Onnuvel
 * 
 * @struts.action path="/ProcessDeleteQuiz" name="ManageQuizForm"
 *                input="/quiz/manage/ViewDeleteQuiz.jsp" scope="session"
 *                validate="false"
 * 
 * @struts.action-forward name="DisplayDeleteQuiz" path="/DisplayDeleteQuiz.do"
 *                        redirect="false"
 * 
 * @spring.bean name="/ProcessDeleteQuiz"
 * 
 * @spring.property name="quizService" ref="quizService"
 * 
 */
public class ProcessDeleteQuiz extends AhpAbstractProcessAction {

	final static Logger LOGGER = LoggerFactory
			.getLogger(ProcessDeleteQuiz.class);

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
		ManageQuizForm lDeleteQuizForm = (ManageQuizForm) pActionForm;
		String lSubmitAction = StringUtils.trimToEmpty(lDeleteQuizForm
				.getSubmitAction());
		if (lDeleteQuizForm.isSubmitAction(SubmitActions.RETURN_TO_MANAGE_QUIZ)) {
			lDeleteQuizForm.getPaginationData().setOperation(
					PaginationOperations.ComboSubmit.toString());
			lDeleteQuizForm.getPaginationData().setHiddenSelectedComboValue(
					lDeleteQuizForm.getPaginationData().getSelectedPage());
			lDeleteQuizForm.setNextPage(NavigateActions.DisplayManageQuiz
					.toString());
		} else if (lDeleteQuizForm.isSubmitAction(SubmitActions.CONFIRM_DELETE)) {
			this.deleteQuiz(lDeleteQuizForm, lLoggedInUser);
			lDeleteQuizForm.setNextPage(NavigateActions.DisplayManageQuiz
					.toString());
		} else {
			lDeleteQuizForm.setNextPage(NavigateActions.DisplayDeleteQuiz
					.toString());
		}
		return pActionMapping.findForward(NavigateActions.DisplayDeleteQuiz
				.toString());
	}

	/**
	 * 
	 * @param pDeleteQuizForm
	 * @return
	 */
	private void deleteQuiz(ManageQuizForm pDeleteQuizForm, User pLoggedInUser) {
		// this.mQuizService.createQuizGroups( lQuizGroups );
	}

}