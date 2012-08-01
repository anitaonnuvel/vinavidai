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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Anita Onnuvel
 */
public abstract class AhpAbstractCommandObject implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    final static Logger LOGGER = LoggerFactory.getLogger( AhpAbstractCommandObject.class );

    /** ${formName} */
    private String mCommandName;
    private String mSubmitAction;
    private String mNextPage;
    /**
     * @return the commandName
     */
    public String getCommandName() {
        return mCommandName;
    }
    /**
     * @param pCommandName the commandName to set
     */
    public void setCommandName( String pCommandName ) {
        mCommandName = pCommandName;
    }
    /**
     * @return the submitAction
     */
    public String getSubmitAction() {
        return mSubmitAction;
    }
    /**
     * @param pSubmitAction the submitAction to set
     */
    public void setSubmitAction( String pSubmitAction ) {
        mSubmitAction = pSubmitAction;
    }
    /**
     * @return the nextPage
     */
    public String getNextPage() {
        return mNextPage;
    }
    /**
     * @param pNextPage the nextPage to set
     */
    public void setNextPage( String pNextPage ) {
        mNextPage = pNextPage;
    }

}
