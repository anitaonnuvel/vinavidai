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
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author Anita Onnuvel
 *
 */
public class AhpPagination implements Serializable {
    
    final static Logger LOGGER = LoggerFactory.getLogger( AhpPagination.class );
    
    private int mSelectedPage;
    private int mHiddenSelectedComboValue;
    private String mHiddenGoButton;
    private List<AhpPageRange> mComboPageRange;
    private String mOperation;
    private boolean mClickedPrevious;
    private boolean mClickedNext;
    private int mStartIndex;
    private int mEndIndex;
    private int mTotalRecords;
    private int mMaxRecordsPerPage;
    private boolean mShowNextPageFlag;
    private boolean mShowPreviousPageFlag;
    private String mSortByParam;
    private String mSortByOrder;
    private Set<String> mSortByParamSet;
    private Set<String> mSortByOrderSet;
    private int mTableColSpan;
    
	public Set<String> getSortByParamSet() {
		return mSortByParamSet;
	}
	public void setSortByParamSet( Set<String> pSortByParamSet ) {
		mSortByParamSet = pSortByParamSet;
	}
	public Set<String> getSortByOrderSet() {
		return mSortByOrderSet;
	}
	public void setSortByOrderSet( Set<String> pSortByOrderSet ) {
		mSortByOrderSet = pSortByOrderSet;
	}
	public int getTotalRecords() {
		return mTotalRecords;
	}
	public void setTotalRecords(int pTotalRecords) {
		mTotalRecords = pTotalRecords;
	}
	public String getHiddenGoButton() {
		return mHiddenGoButton;
	}
	public void setHiddenGoButton(String pHiddenGoButton) {
		mHiddenGoButton = pHiddenGoButton;
	}
	public int getMaxRecordsPerPage() {
		return mMaxRecordsPerPage;
	}
	public void setMaxRecordsPerPage( int pMaxRecordsPerPage ) {
		mMaxRecordsPerPage = pMaxRecordsPerPage;
	}
	public int getSelectedPage() {
		return mSelectedPage;
	}
	public void setSelectedPage(int pSelectedPage) {
		mSelectedPage = pSelectedPage;
	}
	public int getHiddenSelectedComboValue() {
		return mHiddenSelectedComboValue;
	}
	public void setHiddenSelectedComboValue(int pHiddenSelectedComboValue) {
		mHiddenSelectedComboValue = pHiddenSelectedComboValue;
	}
	public String getOperation() {
		return mOperation;
	}
	public void setOperation(String pOperation) {
		mOperation = pOperation;
	}
	public boolean getClickedPrevious() {
		return mClickedPrevious;
	}
	public void setClickedPrevious(boolean pClickedPrevious) {
		mClickedPrevious = pClickedPrevious;
	}
	public boolean getClickedNext() {
		return mClickedNext;
	}
	public void setClickedNext(boolean pClickedNext) {
		mClickedNext = pClickedNext;
	}
	public List<AhpPageRange> getComboPageRange() {
		return mComboPageRange;
	}
	public void setComboPageRange(List<AhpPageRange> pComboPageRange) {
		mComboPageRange = pComboPageRange;
	}
	public boolean isShowNextPageFlag() {
		return mShowNextPageFlag;
	}
	public void setShowNextPageFlag( boolean pShowNextPageFlag ) {
		mShowNextPageFlag = pShowNextPageFlag;
	}
	public boolean isShowPreviousPageFlag() {
		return mShowPreviousPageFlag;
	}
	public void setShowPreviousPageFlag( boolean pShowPreviousPageFlag ) {
		mShowPreviousPageFlag = pShowPreviousPageFlag;
	}
	public int getStartIndex() {
		return mStartIndex;
	}
	public void setStartIndex( int pStartIndex ) {
		mStartIndex = pStartIndex;
	}
	public int getEndIndex() {
		return mEndIndex;
	}
	public void setEndIndex( int pEndIndex ) {
		mEndIndex = pEndIndex;
	}
	public String getSortByParam() {
		return mSortByParam;
	}
	public void setSortByParam( String pSortByParam ) {
		mSortByParam = pSortByParam;
	}
	public String getSortByOrder() {
		return mSortByOrder;
	}
	public void setSortByOrder( String pSortByOrder ) {
		mSortByOrder = pSortByOrder;
	}
	public int getTableColSpan() {
		return mTableColSpan;
	}
	public void setTableColSpan(int pTableColSpan) {
		mTableColSpan = pTableColSpan;
	}    
    
}
