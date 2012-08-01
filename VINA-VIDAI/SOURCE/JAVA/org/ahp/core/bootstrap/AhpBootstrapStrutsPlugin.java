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
package org.ahp.core.bootstrap;

import javax.servlet.ServletException;

import org.ahp.commons.exceptions.AhpRuntimeException;
import org.ahp.core.managers.IAhpBootstrapManager;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.PlugIn;
import org.apache.struts.config.ModuleConfig;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * 
 * @author Anita Onnuvel
 * 
 */
public class AhpBootstrapStrutsPlugin implements PlugIn {

    private final String DEFAULT_AHP_BOOTSTRAP_MANAGER_BEAN_ID = "ahpBootstrapManager";
    private String mAhpConfigurationLocation;
    private String mAhpBootstrapManagerBeanId;

    /**
     * 
     * @return
     */
    public String getAhpConfigurationLocation() {
        return mAhpConfigurationLocation;
    }

    /**
     * 
     * @param pAhpConfigurationLocation
     */
    public void setAhpConfigurationLocation( String pAhpConfigurationLocation ) {
        mAhpConfigurationLocation = pAhpConfigurationLocation;
    }

    /**
     * 
     * @return
     */
    public String getAhpBootstrapManagerBeanId() {
        return mAhpBootstrapManagerBeanId;
    }

    /**
     * 
     * @param pAhpBootstrapManagerBeanId
     */
    public void setAhpBootstrapManagerBeanId( String pAhpBootstrapManagerBeanId ) {
        mAhpBootstrapManagerBeanId = pAhpBootstrapManagerBeanId;
    }

    /**
     * 
     */
    public void init( ActionServlet pActionServlet, ModuleConfig pModuleConfig ) throws ServletException {
        System.out.println( "Initializing AhpBootstrapStrutsPlugin" );
        try {
            String lRealPath = pActionServlet.getServletContext().getRealPath( "/" );
            System.out.println( "ConfigurationFiles - " + this.getAhpConfigurationLocation() );
            WebApplicationContext lWebApplicationContext = WebApplicationContextUtils
                    .getWebApplicationContext( pActionServlet.getServletContext() );
            String lBeanName = StringUtils.trimToEmpty( this.getAhpBootstrapManagerBeanId() );
            if ( lBeanName == null ) {
                lBeanName = DEFAULT_AHP_BOOTSTRAP_MANAGER_BEAN_ID;
            }
            IAhpBootstrapManager lAhpBootstrapManager = ( IAhpBootstrapManager ) lWebApplicationContext
                    .getBean( lBeanName );
            if ( lAhpBootstrapManager == null )
                lAhpBootstrapManager = lWebApplicationContext.getBean( IAhpBootstrapManager.class );
            lAhpBootstrapManager.bootstrap( this.getAhpConfigurationLocation().split( "," ), lRealPath );
        } catch ( AhpRuntimeException exAhpRuntime ) {
            throw exAhpRuntime;
        } catch ( Exception ex ) {
            throw new AhpRuntimeException( "AHP.001.0001", ex );
        }
    }

    /**
     * 
     */
    public void destroy() {
        System.out.println( "Destroying AhpBootstrapStrutsPlugin" );
    }

}