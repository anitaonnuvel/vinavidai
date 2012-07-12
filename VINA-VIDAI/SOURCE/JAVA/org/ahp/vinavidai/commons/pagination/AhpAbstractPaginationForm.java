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

import org.ahp.commons.form.AhpAbstractForm;
import org.ahp.commons.form.AhpPagination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author Anita Onnuvel
 * 
 */
public abstract class AhpAbstractPaginationForm extends AhpAbstractForm {

    private static final long serialVersionUID = 1L;

    final static Logger LOGGER = LoggerFactory
            .getLogger( AhpAbstractPaginationForm.class );

    private AhpPagination mPaginationData = new AhpPagination();
    private AhpPaginationSearchCriteria mPaginationSearchCriteria;
    private long mHiddenSelectedItemId;
    private String mHiddenEditOperation;

    public AhpPagination getPaginationData() {
        return mPaginationData;
    }

    public void setPaginationData( AhpPagination pPaginationData ) {
        mPaginationData = pPaginationData;
    }

    public AhpPaginationSearchCriteria getPaginationSearchCriteria() {
        return mPaginationSearchCriteria;
    }

    public void setPaginationSearchCriteria(
            AhpPaginationSearchCriteria pPaginationSearchCriteria ) {
        mPaginationSearchCriteria = pPaginationSearchCriteria;
    }

    public long getHiddenSelectedItemId() {
        return mHiddenSelectedItemId;
    }

    public void setHiddenSelectedItemId( long pHiddenSelectedItemId ) {
        mHiddenSelectedItemId = pHiddenSelectedItemId;
    }

    public String getHiddenEditOperation() {
        return mHiddenEditOperation;
    }

    public void setHiddenEditOperation( String pHiddenEditOperation ) {
        mHiddenEditOperation = pHiddenEditOperation;
    }

}
