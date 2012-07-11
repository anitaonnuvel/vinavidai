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
package org.ahp.vinavidai.quiz.form;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

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
 * @struts.form name="EditQuizForm"
 */
public class EditQuizForm extends QuizForm {

	private static final long serialVersionUID = 1L;

	final static Logger LOGGER = LoggerFactory.getLogger(EditQuizForm.class);
	private Quiz mQuizUnderEdit;

	@Override
	public ActionErrors validate(ActionMapping pActionMapping,
			HttpServletRequest pHttpServletRequest) {
		ActionErrors lActionErrors = new ActionErrors();
		if (super.isSubmitAction(SubmitActions.NEXT)) {
			QuizValidator.validateEditQuizForm(this, lActionErrors);
		}
		return lActionErrors;
	}

	@Override
	public String getQuizOperationType() {
		return "Edit";
	}

	private Set<Long> mDeleteCategoryIdSet = new LinkedHashSet<Long>();
	private Set<Long> mDeleteSkillLevelIdSet = new LinkedHashSet<Long>();

	public Set<Long> getDeleteCategoryIdSet() {
		return mDeleteCategoryIdSet;
	}

	public void setDeleteCategoryIdSet(Set<Long> pDeleteCategoryIdSet) {
		mDeleteCategoryIdSet = pDeleteCategoryIdSet;
	}

	public Set<Long> getDeleteSkillLevelIdSet() {
		return mDeleteSkillLevelIdSet;
	}

	public void setDeleteSkillLevelIdSet(Set<Long> pDeleteSkillLevelIdSet) {
		mDeleteSkillLevelIdSet = pDeleteSkillLevelIdSet;
	}

	public Quiz getQuizUnderEdit() {
		return mQuizUnderEdit;
	}

	public void setQuizUnderEdit(Quiz pQuizUnderEdit) {
		mQuizUnderEdit = pQuizUnderEdit;
	}
}
