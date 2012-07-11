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
package org.ahp.vinavidai.quiz.manage.form;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.ahp.vinavidai.commons.pagination.AhpAbstractPaginationForm;
import org.ahp.vinavidai.enums.SubmitActions;
import org.ahp.vinavidai.pojo.Quiz;
import org.ahp.vinavidai.validator.QuizValidator;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Anita Onnuvel
 * 
 * @struts.form name="ManageQuizForm"
 */
public class ManageQuizForm extends AhpAbstractPaginationForm {

	private static final long serialVersionUID = 1L;

	final static Logger LOGGER = LoggerFactory.getLogger(ManageQuizForm.class);

	@Override
	protected void resetForm(ActionMapping pActionMapping,
			HttpServletRequest pHttpServletRequest) {
	}

	@Override
	public ActionErrors validate(ActionMapping pActionMapping,
			HttpServletRequest pHttpServletRequest) {
		ActionErrors lActionErrors = new ActionErrors();
		if (!super.isSubmitAction(SubmitActions.EMPTY)
				&& !isSubmitAction(SubmitActions.GO)) {
			QuizValidator.validateManageQuizForm(this, lActionErrors);
		}
		return lActionErrors;
	}

	private List<Quiz> mQuizResults;
	private Long mSelectedQuizId;
	private Quiz mSelectedQuiz;

	//
	private boolean mDeleteQuiz;

	public List<Quiz> getQuizResults() {
		return mQuizResults;
	}

	public void setQuizResults(List<Quiz> pQuizResults) {
		mQuizResults = pQuizResults;
	}

	public Long getSelectedQuizId() {
		return mSelectedQuizId;
	}

	public void setSelectedQuizId(Long pSelectedQuizId) {
		mSelectedQuizId = pSelectedQuizId;
	}

	public Quiz getSelectedQuiz() {
		return mSelectedQuiz;
	}

	public void setSelectedQuiz(Quiz pViewQuiz) {
		mSelectedQuiz = pViewQuiz;
	}

	public boolean getDeleteQuiz() {
		return mDeleteQuiz;
	}

	public void setDeleteQuiz(boolean pDeleteQuiz) {
		mDeleteQuiz = pDeleteQuiz;
	}

}
