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
package org.ahp.vinavidai.quiz;

import org.ahp.vinavidai.dao.IQuizSearchDao;
import org.ahp.vinavidai.quiz.manage.ManageQuizSearchCriteria;
import org.ahp.vinavidai.quiz.manage.ManageQuizSearchResults;

/**
 * 
 * @author Anita Onnuvel
 * 
 * @spring.bean id="quizSearchService"
 * 
 * @spring.property name="quizSearchDao" ref="quizSearchDao"
 */
public class QuizSearchService {

	private IQuizSearchDao mQuizSearchDao;

	public void setQuizSearchDao(IQuizSearchDao pQuizSearchDao) {
		mQuizSearchDao = pQuizSearchDao;
	}

	public ManageQuizSearchResults searchQuiz(
			ManageQuizSearchCriteria pManageQuizSearchCriteria) {
		return mQuizSearchDao.searchQuiz(pManageQuizSearchCriteria);
	}

}
