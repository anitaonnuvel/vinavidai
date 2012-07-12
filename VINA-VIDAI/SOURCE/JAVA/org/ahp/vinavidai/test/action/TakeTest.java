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
package org.ahp.vinavidai.test.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ahp.commons.action.AhpAbstractDisplayAction;
import org.ahp.vinavidai.pojo.Test;
import org.ahp.vinavidai.test.TestService;
import org.ahp.vinavidai.test.form.TakeTestForm;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Anita Onnuvel
 * 
 * @struts.action path="/TakeTest" name="TakeTestForm" scope="session"
 *                validate="false"
 * 
 * @struts.action-forward name="TakeTest" path="/test/TakeTest.jsp"
 * 
 * @spring.bean name="/TakeTest"
 * 
 * @spring.property name="testService" ref="testService"
 */
public class TakeTest extends AhpAbstractDisplayAction {

    final static Logger LOGGER = LoggerFactory.getLogger( TakeTest.class );

    private TestService mTestService;

    public void setTestService( TestService pTestService ) {
        this.mTestService = pTestService;
    }

    @Override
    public ActionForward display( ActionMapping pActionMapping,
            ActionForm pActionForm, HttpServletRequest pHttpServletRequest,
            HttpServletResponse pHttpServletResponse ) {
        TakeTestForm lTakeTestForm = ( TakeTestForm ) pActionForm;
        String lTestAccessKey = lTakeTestForm.getAccessKey();// ( String )
                                                             // pHttpServletRequest.getParameter(
                                                             // "accessKey"
                                                             // );
        if ( StringUtils.trimToNull( lTestAccessKey ) != null ) {
            Test lTest = this.mTestService.loadTest( lTestAccessKey );
            lTakeTestForm.setTakeTest( lTest );
        }
        return pActionMapping.findForward( "TakeTest" );
    }
}
