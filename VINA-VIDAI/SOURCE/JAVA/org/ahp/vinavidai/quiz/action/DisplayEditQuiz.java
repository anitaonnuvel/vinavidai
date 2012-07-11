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
import org.ahp.core.actions.AhpActionHelper;
import org.ahp.core.businessdelegate.AhpBusinessDelegate;
import org.ahp.core.pojo.User;
import org.ahp.vinavidai.enums.NavigateActions;
import org.ahp.vinavidai.enums.SubmitActions;
import org.ahp.vinavidai.pojo.Category;
import org.ahp.vinavidai.pojo.Quiz;
import org.ahp.vinavidai.pojo.SkillLevel;
import org.ahp.vinavidai.quiz.QuizService;
import org.ahp.vinavidai.quiz.form.EditQuizForm;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Anita Onnuvel
 * 
 * @struts.action path="/DisplayEditQuiz" name="EditQuizForm" scope="session"
 *                validate="false"
 * 
 * @struts.action-forward name="DisplayEditQuiz" path="/quiz/Quiz.jsp"
 * 
 * @struts.action-forward name="DisplayEditQuestion"
 *                        path="/ProcessEditQuestion.do" redirect="true"
 * 
 * @spring.bean name="/DisplayEditQuiz"
 * 
 * @spring.property name="quizService" ref="quizService"
 * 
 */
public class DisplayEditQuiz extends AhpAbstractDisplayAction {

	final static Logger LOGGER = LoggerFactory.getLogger(DisplayEditQuiz.class);

	private QuizService mQuizService;

	public void setQuizService(QuizService pQuizService) {
		this.mQuizService = pQuizService;
	}

	@Override
	public ActionForward display(ActionMapping pActionMapping,
			ActionForm pActionForm, HttpServletRequest pHttpServletRequest,
			HttpServletResponse pHttpServletResponse) {
		User lLoggedInUser = AhpActionHelper
				.getLoggedInUser(pHttpServletRequest);
		EditQuizForm lEditQuizForm = (EditQuizForm) pActionForm;
		String lNextPage = StringUtils.trimToEmpty(lEditQuizForm.getNextPage());
		ActionForward lActionForward = pActionMapping
				.findForward(NavigateActions.DisplayEditQuiz.toString());
		if (NavigateActions.DisplayEditQuiz.toString().equals(lNextPage)) {
			// if ( lEditQuizForm.isSubmitAction( SubmitActions.EMPTY ) )
			// {

			// }
			if (lEditQuizForm.isSubmitAction(SubmitActions.ADD_CATEGORY)) {
				Category lCategory = new Category();
				lCategory.setCategory("");
				lCategory.setQuiz(lEditQuizForm.getQuizUnderEdit());
				lCategory.setAudit(AhpBusinessDelegate
						.createAudit(lLoggedInUser));
				lEditQuizForm.getCategory().add(lCategory);
			} else if (lEditQuizForm
					.isSubmitAction(SubmitActions.DELETE_CATEGORY)) {
				int lDeleteIndex = lEditQuizForm.getHiddenDeleteCategoryIndex();
				if (lDeleteIndex != -1) {
					if (lEditQuizForm.getCategory().size() > lDeleteIndex) {
						lEditQuizForm.getDeleteCategoryIdSet().add(
								new Long(lDeleteIndex));
						lEditQuizForm.getCategory().remove(lDeleteIndex);
					}
				}
			} else if (lEditQuizForm
					.isSubmitAction(SubmitActions.ADD_SKILL_LEVEL)) {
				SkillLevel lSkillLevel = new SkillLevel();
				lSkillLevel.setSkillLevel("");
				lSkillLevel.setQuiz(lEditQuizForm.getQuizUnderEdit());
				lSkillLevel.setAudit(AhpBusinessDelegate
						.createAudit(lLoggedInUser));
				lEditQuizForm.getSkillLevel().add(lSkillLevel);
			} else if (lEditQuizForm
					.isSubmitAction(SubmitActions.DELETE_SKILL_LEVEL)) {
				int lDeleteIndex = lEditQuizForm
						.getHiddenDeleteSkillLevelIndex();
				if (lDeleteIndex != -1) {
					if (lEditQuizForm.getSkillLevel().size() > lDeleteIndex) {
						lEditQuizForm.getDeleteSkillLevelIdSet().add(
								new Long(lDeleteIndex));
						lEditQuizForm.getSkillLevel().remove(lDeleteIndex);
					}
				}
			} else if (lEditQuizForm.isSubmitAction(SubmitActions.SAVE_CHANGES)) {

			} else {
				Quiz lManagedQuizUnderEdit = this.mQuizService.loadQuiz(
						lEditQuizForm.getQuizUnderEdit().getQuizId(), true);
				lEditQuizForm.setQuizName(lManagedQuizUnderEdit.getQuizName());
				lEditQuizForm.setQuizDescription(lManagedQuizUnderEdit
						.getQuizDescription());
				List<Category> categoryList = new ArrayList<Category>();
				categoryList.addAll(lManagedQuizUnderEdit.getCategories());
				lEditQuizForm.setCategory(categoryList);
				List<SkillLevel> skillLevelList = new ArrayList<SkillLevel>();
				skillLevelList.addAll(lManagedQuizUnderEdit.getSkillLevels());
				lEditQuizForm.setSkillLevel(skillLevelList);
			}
			lActionForward = pActionMapping
					.findForward(NavigateActions.DisplayEditQuiz.toString());
		}
		if (NavigateActions.DisplayEditQuestion.toString().equals(lNextPage)) {
			lActionForward = pActionMapping
					.findForward(NavigateActions.DisplayEditQuestion.toString());
		}
		return lActionForward;
	}
}
