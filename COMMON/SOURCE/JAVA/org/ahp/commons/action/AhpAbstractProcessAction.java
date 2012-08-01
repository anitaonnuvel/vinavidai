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
package org.ahp.commons.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author Anita Onnuvel
 * 
 */
public abstract class AhpAbstractProcessAction extends AhpAbstractAction {

    final static Logger LOGGER = LoggerFactory.getLogger( AhpAbstractProcessAction.class );

    @Override
    public ActionForward execute( ActionMapping pActionMapping, 
                                  ActionForm pActionForm,
                                  HttpServletRequest pHttpServletRequest,
                                  HttpServletResponse pHttpServletResponse ) throws Exception {
        ActionForward lProcessActionForward = process( pActionMapping, 
                                                       pActionForm,
                                                       pHttpServletRequest,
                                                       pHttpServletResponse );
        return lProcessActionForward;
    }

    /**
     * 
     * @param pActionMapping
     * @param pActionForm
     * @param pHttpServletRequest
     * @param pHttpServletResponse
     * @return
     */
    public abstract ActionForward process( ActionMapping pActionMapping, 
                                           ActionForm pActionForm,
                                           HttpServletRequest pHttpServletRequest, 
                                           HttpServletResponse pHttpServletResponse );

}
