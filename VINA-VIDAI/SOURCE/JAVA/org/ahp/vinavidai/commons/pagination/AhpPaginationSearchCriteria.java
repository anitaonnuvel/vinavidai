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
package org.ahp.vinavidai.commons.pagination;

import org.ahp.core.pojo.User;
import org.ahp.vinavidai.enums.SortOrderAscDesc;

/**
 * 
 * @author Anita Onnuvel
 *
 */
public class AhpPaginationSearchCriteria {

	private User mUser;
	private int mStartIndex;
	private int mEndIndex;
	private SortOrderAscDesc mOrderBy;

	public int getStartIndex() {
		return mStartIndex;
	}

	public void setStartIndex(int pStartIndex) {
		mStartIndex = pStartIndex;
	}

	public int getEndIndex() {
		return mEndIndex;
	}

	public void setEndIndex(int pEndIndex) {
		mEndIndex = pEndIndex;
	}

	public SortOrderAscDesc getOrderBy() {
		return mOrderBy;
	}

	public void setOrderBy(SortOrderAscDesc pOrderBy) {
		mOrderBy = pOrderBy;
	}

	public User getUser() {
		return mUser;
	}

	public void setUser(User pUser) {
		mUser = pUser;
	}

}
