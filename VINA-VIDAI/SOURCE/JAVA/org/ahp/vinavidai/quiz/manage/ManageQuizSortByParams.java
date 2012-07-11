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
package org.ahp.vinavidai.quiz.manage;

/**
 * 
 * @author Anita Onnuvel
 *
 */
public enum ManageQuizSortByParams {
	
	QuizName("Quiz Name"), 
	CreatedDate("Created Date");

	private String mManageQuizSortByParamsLiteral;

	private ManageQuizSortByParams(String pManageQuizSortByParamsLiteral) {
		this.mManageQuizSortByParamsLiteral = pManageQuizSortByParamsLiteral;
	}

	public String toString() {
		return this.mManageQuizSortByParamsLiteral;
	}

	/**
	 * Parse text into an element of this enumeration.
	 * 
	 */
	public static ManageQuizSortByParams getValueOf(String pTextLiteral) {
		for ( ManageQuizSortByParams lManageQuizSortByParams : ManageQuizSortByParams.values() ) {
			if ( lManageQuizSortByParams.toString().equals( pTextLiteral ) ) {
				return lManageQuizSortByParams;
			}
		}
		throw new IllegalArgumentException(	"Cannot be parsed into an enum element : '" + pTextLiteral + "'" );
	}

}
