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
package org.ahp.commons.form;

import java.io.Serializable;

/**
 * 
 * @author Anita Onnuvel
 *
 */
public class AhpPageRange implements Serializable {
	
	public AhpPageRange(String pPageIndex,String pPageRange ){
		this.mPageIndex = pPageIndex;
		this.mPageRange = pPageRange;
	}
	
	private String mPageRange;
	private String mPageIndex;
	public String getPageRange() {
		return mPageRange;
	}
	public void setPageRange( String pPageRange ) {
		mPageRange = pPageRange;
	}
	public String getPageIndex() {
		return mPageIndex;
	}
	public void setPageIndex( String pPageIndex ) {
		mPageIndex = pPageIndex;
	}
       
}
