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
package org.ahp.filters;

import static org.ahp.core.constants.HttpSessionAttributeConstants.LOGGED_IN_USER;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.ahp.core.pojo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author Anita Onnuvel
 * 
 */
public class LoginFilter implements Filter {

    final static Logger LOGGER = LoggerFactory.getLogger( LoginFilter.class );

    @Override
    public void destroy() {
        LOGGER.trace( "Login Filter destroyed" );

    }

    @Override
    public void doFilter( ServletRequest pServletRequest, 
                          ServletResponse pServletResponse, 
                          FilterChain pFilterChain )
            throws IOException, ServletException {
        LOGGER.trace( "doFilter start" );
        HttpServletRequest lHttpServletRequest = ( HttpServletRequest ) pServletRequest;
        lHttpServletRequest.getRequestURI();
        if ( !lHttpServletRequest.getRequestURI().endsWith( "ProcessLogin.do" ) ) {
            if ( lHttpServletRequest.getSession() != null ) {
                User lLoggedInUser = ( User ) lHttpServletRequest.getSession().getAttribute( LOGGED_IN_USER );
                if ( lLoggedInUser == null ) {
                    LOGGER.trace( "User object null" );
                    lHttpServletRequest.getRequestDispatcher( "/ProcessLogin.do" ).forward( pServletRequest, pServletResponse );
                    return;
                }
            }
        }
        LOGGER.trace( "LoginFilter :: doFilter end1" );
        pFilterChain.doFilter( pServletRequest, pServletResponse );
        LOGGER.trace( "LoginFilter :: doFilter end2" );
    }

    @Override
    public void init( FilterConfig pFilterConfig ) throws ServletException {
        LOGGER.trace( "Login Filter initialized" );

    }

}
