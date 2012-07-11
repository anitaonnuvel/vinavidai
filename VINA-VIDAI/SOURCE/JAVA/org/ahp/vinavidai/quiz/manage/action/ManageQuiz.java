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
package org.ahp.vinavidai.quiz.manage.action;

import static org.ahp.vinavidai.constants.HttpRequestAttributeConstants.QUIZ_UNDER_DELETE;
import static org.ahp.vinavidai.constants.HttpRequestAttributeConstants.QUIZ_UNDER_EDIT;
import static org.ahp.vinavidai.constants.HttpRequestAttributeConstants.QUIZ_UNDER_PUBLISH;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ahp.commons.constants.AhpConstants;
import org.ahp.core.pojo.User;
import org.ahp.vinavidai.commons.pagination.AhpAbstractPaginationAction;
import org.ahp.vinavidai.commons.pagination.AhpAbstractPaginationForm;
import org.ahp.vinavidai.enums.EnumWrapper;
import org.ahp.vinavidai.enums.NavigateActions;
import org.ahp.vinavidai.enums.SortOrderAscDesc;
import org.ahp.vinavidai.enums.SubmitActions;
import org.ahp.vinavidai.pojo.Quiz;
import org.ahp.vinavidai.quiz.QuizSearchService;
import org.ahp.vinavidai.quiz.manage.ManageQuizSearchCriteria;
import org.ahp.vinavidai.quiz.manage.ManageQuizSearchResults;
import org.ahp.vinavidai.quiz.manage.ManageQuizSortByParams;
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
 * @struts.action path="/ManageQuiz" name="ManageQuizForm"
 *                input="/quiz/manage/ManageQuiz.jsp" scope="session"
 *                validate="true"
 * 
 * @struts.action-forward name="DisplayManageQuiz"
 *                        path="/quiz/manage/ManageQuiz.jsp"
 * 
 * @struts.action-forward name="DisplayViewQuiz" path="/DisplayViewQuiz.do"
 * 
 * @struts.action-forward name="DisplayPublishQuiz"
 *                        path="/ProcessPublishQuiz.do"
 * 
 * @struts.action-forward name="DisplayDeleteQuiz" path="/ProcessDeleteQuiz.do"
 * 
 * @struts.action-forward name="DisplayEditQuiz" path="/ProcessEditQuiz.do"
 * 
 * @spring.bean name="/ManageQuiz"
 * 
 * @spring.property name="quizSearchService" ref="quizSearchService"
 * 
 */
public class ManageQuiz extends AhpAbstractPaginationAction {

	final static Logger LOGGER = LoggerFactory.getLogger(ManageQuiz.class);

	private QuizSearchService mQuizSearchService;

	public void setQuizSearchService(QuizSearchService pQuizSearchService) {
		this.mQuizSearchService = pQuizSearchService;
	}

	@Override
	public ActionForward paginate(ActionMapping pActionMapping,
			ActionForm pActionForm, HttpServletRequest pHttpServletRequest,
			HttpServletResponse pHttpServletResponse) {
		ManageQuizForm lManageQuizForm = (ManageQuizForm) pActionForm;
		String lHiddenEditOperation = StringUtils.trimToEmpty(lManageQuizForm
				.getHiddenEditOperation());
		if (lHiddenEditOperation.equals(SubmitActions.VIEW_QUIZ.getValue())) {
			return pActionMapping.findForward(NavigateActions.DisplayViewQuiz
					.toString());
		}
		if (lManageQuizForm.isSubmitAction(SubmitActions.DELETE)) {
			Quiz lQuizUnderEdit = null;
			for (Quiz lQuiz : lManageQuizForm.getQuizResults()) {
				if (lQuiz.getQuizId().longValue() == lManageQuizForm
						.getSelectedQuizId()) {
					lQuizUnderEdit = lQuiz;
					break;
				}
			}
			pHttpServletRequest.setAttribute(QUIZ_UNDER_DELETE, lQuizUnderEdit);
			return pActionMapping.findForward(NavigateActions.DisplayEditQuiz
					.toString());
		}
		if (lManageQuizForm.isSubmitAction(SubmitActions.PUBLISH)) {
			Quiz lQuizUnderPublish = null;
			for (Quiz lQuiz : lManageQuizForm.getQuizResults()) {
				if (lQuiz.getQuizId().longValue() == lManageQuizForm
						.getSelectedQuizId()) {
					lQuizUnderPublish = lQuiz;
					break;
				}
			}
			pHttpServletRequest.setAttribute(QUIZ_UNDER_PUBLISH,
					lQuizUnderPublish);
			return pActionMapping
					.findForward(NavigateActions.DisplayPublishQuiz.toString());
		}
		if (lManageQuizForm.isSubmitAction(SubmitActions.EDIT)) {
			Quiz lQuizUnderEdit = null;
			for (Quiz lQuiz : lManageQuizForm.getQuizResults()) {
				if (lQuiz.getQuizId().longValue() == lManageQuizForm
						.getSelectedQuizId()) {
					lQuizUnderEdit = lQuiz;
					break;
				}
			}
			pHttpServletRequest.setAttribute(QUIZ_UNDER_EDIT, lQuizUnderEdit);
			return pActionMapping.findForward(NavigateActions.DisplayEditQuiz
					.toString());
		}
		ManageQuizSearchResults lManageQuizSearchResults = this.mQuizSearchService
				.searchQuiz((ManageQuizSearchCriteria) lManageQuizForm
						.getPaginationSearchCriteria());
		lManageQuizForm.getPaginationData().setTotalRecords(
				lManageQuizSearchResults.getTotalRecords());
		lManageQuizForm.setQuizResults(lManageQuizSearchResults
				.getQuizResults());
		// Load Next/Previous flags
		loadPageFlags(lManageQuizForm);

		int lSelectedPage = lManageQuizForm.getPaginationData()
				.getSelectedPage();
		if (lSelectedPage == 0) {
			lManageQuizForm.getPaginationData().setSelectedPage(1);
		}
		// Pagination List
		lManageQuizForm.getPaginationData().setComboPageRange(
				super.loadComboPageRange(lManageQuizForm));
		return pActionMapping.findForward(NavigateActions.DisplayManageQuiz
				.toString());
	}

	@Override
	public void populatePaginationSearchCriteria(
			AhpAbstractPaginationForm pAhpAbstractPaginationForm,
			User pLoggedInUser, boolean pDefault) {
		ManageQuizForm lManageQuizForm = (ManageQuizForm) pAhpAbstractPaginationForm;
		lManageQuizForm.getPaginationData().setTableColSpan(4);
		ManageQuizSearchCriteria lManageQuizSearchCriteria = null;
		if (lManageQuizForm.getPaginationSearchCriteria() != null) {
			lManageQuizSearchCriteria = (ManageQuizSearchCriteria) lManageQuizForm
					.getPaginationSearchCriteria();
		} else {
			lManageQuizSearchCriteria = new ManageQuizSearchCriteria();
			lManageQuizForm
					.setPaginationSearchCriteria(lManageQuizSearchCriteria);
		}
		if (pDefault) {
			// DB
			lManageQuizSearchCriteria
					.setOrderByParam(ManageQuizSortByParams.QuizName);
			lManageQuizSearchCriteria.setOrderBy(SortOrderAscDesc.Descending);
			// UI
			lManageQuizForm.getPaginationData().setSortByParam(
					ManageQuizSortByParams.QuizName.toString());
			lManageQuizForm.getPaginationData().setSortByParamSet(
					EnumWrapper.enumerateManageQuizSortByParams());
			lManageQuizForm.getPaginationData().setSortByOrder(
					SortOrderAscDesc.Descending.toString());
			lManageQuizForm.getPaginationData().setSortByOrderSet(
					EnumWrapper.enumerateSortOrderAscDesc());

			// Set Pagination Data DB
			lManageQuizSearchCriteria.setStartIndex(1);
			lManageQuizSearchCriteria.setEndIndex(lManageQuizForm
					.getPaginationData().getMaxRecordsPerPage());
			lManageQuizSearchCriteria.setUser(pLoggedInUser);
			// Set Pagination Data UI
			lManageQuizForm.getPaginationData().setMaxRecordsPerPage(
					AhpConstants.MAX_RECORDS_ON_ONE_PAGE);
			lManageQuizForm.getPaginationData().setStartIndex(1);
			lManageQuizForm.getPaginationData().setEndIndex(
					lManageQuizForm.getPaginationData().getMaxRecordsPerPage());
		} else {
			lManageQuizSearchCriteria.setOrderByParam(ManageQuizSortByParams
					.getValueOf(lManageQuizForm.getPaginationData()
							.getSortByParam()));
			lManageQuizSearchCriteria.setOrderBy(SortOrderAscDesc
					.valueOf(lManageQuizForm.getPaginationData()
							.getSortByOrder()));
			lManageQuizSearchCriteria.setStartIndex(lManageQuizForm
					.getPaginationData().getStartIndex());
			lManageQuizSearchCriteria.setEndIndex(lManageQuizForm
					.getPaginationData().getEndIndex());
		}
	}

}