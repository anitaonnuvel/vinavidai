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

import static org.ahp.commons.constants.AhpConstants.DISPLAY;
import static org.ahp.commons.constants.AhpConstants.FORM_NAME;
import static org.ahp.commons.constants.AhpConstants.PROCESS;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Anita Onnuvel
 */
public abstract class AhpAbstractForm extends ActionForm {

    private static final long serialVersionUID = 1L;

    final static Logger LOGGER = LoggerFactory
            .getLogger( AhpAbstractForm.class );

    /** ${formName} */
    private String mFormName;
    /** ${form.processAction} */
    private String mProcessAction;
    /** ${form.displayAction} */
    private String mDisplayAction;
    private String mCurrentAction;
    private String mSubmitAction;
    private String mNextPage;

    @Override
    public void reset( ActionMapping pActionMapping,
            HttpServletRequest pHttpServletRequest ) {
        super.reset( pActionMapping, pHttpServletRequest );
        /**
         * The key that the form is stored under in session scope's map
         * ${form.<YourAccessor>}, useful mainly for passing it to javascript
         * functions for use by the DOM. With JQuery May not be very useful.
         */
        pHttpServletRequest.getSession().setAttribute( FORM_NAME, this );
        this.mSubmitAction = null;
        this.mFormName = pActionMapping.getName();
        this.mCurrentAction = pActionMapping.getPath();
        this.mDisplayAction = this.mCurrentAction.startsWith( "/" + DISPLAY ) ? this.mCurrentAction
                : this.mCurrentAction.replaceFirst( PROCESS, DISPLAY );
        this.mProcessAction = this.mDisplayAction.replaceFirst( DISPLAY,
                PROCESS );
        if ( isProcessAction( pActionMapping ) ) {
            resetForm( pActionMapping, pHttpServletRequest );
        }
    }

    /**
     * 
     * @param pActionMapping
     * @return
     */
    protected boolean isProcessAction( ActionMapping pActionMapping ) {
        return pActionMapping.getPath().startsWith( "/" + PROCESS );
    }

    /**
     * 
     * @param pActionMapping
     * @param pHttpServletRequest
     */
    protected abstract void resetForm( ActionMapping pActionMapping,
            HttpServletRequest pHttpServletRequest );

    @Override
    public ActionErrors validate( ActionMapping mapping,
            HttpServletRequest request ) {
        return new ActionErrors();
    }

    public String getFormName() {
        return mFormName;
    }

    public void setFormName( String pFormName ) {
        mFormName = pFormName;
    }

    public String getProcessAction() {
        return mProcessAction;
    }

    public void setProcessAction( String pProcessAction ) {
        mProcessAction = pProcessAction;
    }

    public String getDisplayAction() {
        return mDisplayAction;
    }

    public void setDisplayAction( String pDisplayAction ) {
        mDisplayAction = pDisplayAction;
    }

    public String getCurrentAction() {
        return mCurrentAction;
    }

    public void setCurrentAction( String pCurrentAction ) {
        mCurrentAction = pCurrentAction;
    }

    public String getSubmitAction() {
        return mSubmitAction;
    }

    public void setSubmitAction( String pSubmitAction ) {
        mSubmitAction = pSubmitAction;
    }

    public String getNextPage() {
        return mNextPage;
    }

    public void setNextPage( String pNextPage ) {
        mNextPage = pNextPage;
    }

    /**
     * 
     * @param pSubmitActionsEnum
     * @return
     */
    public boolean isSubmitAction( Enum<?> pSubmitActionsEnum ) {
        String lSubmitAction = StringUtils.trimToEmpty( this.getSubmitAction() );
        if ( lSubmitAction.equalsIgnoreCase( pSubmitActionsEnum.toString() ) ) {
            return true;
        }
        return false;
    }

    /**
     * 
     * @param pNavigateActionssEnum
     * @return
     */
    public boolean isNextPage( Enum<?> pNavigateActionsEnum ) {
        String lNextPage = StringUtils.trimToEmpty( this.getNextPage() );
        if ( lNextPage.equalsIgnoreCase( pNavigateActionsEnum.toString() ) ) {
            return true;
        }
        return false;
    }
}
