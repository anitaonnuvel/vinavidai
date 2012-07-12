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
package org.ahp.commons.xml.xpath;

import java.util.HashMap;
import java.util.Map;

import javax.xml.namespace.QName;
import javax.xml.xpath.XPathVariableResolver;

/**
 * 
 * @author Anita Onnuvel
 * 
 */
public class AhpXPathVariableResolver implements XPathVariableResolver {

    /** DOCUMENT ME! */
    private Map<QName, Object> mVariables = new HashMap<QName, Object>();

    /**
     * DOCUMENT ME!
     */
    public void addVariable( String pNamespaceURI, String pLocalName,
            Object pValue ) {
        addVariable( new QName( pNamespaceURI, pLocalName ), pValue );
    }

    /**
     * DOCUMENT ME!
     */
    public void addVariable( QName pQName, Object pValue ) {
        this.mVariables.put( pQName, pValue );
    }

    /**
     * DOCUMENT ME!
     */
    public Object resolveVariable( QName pQName ) {
        return this.mVariables.get( pQName );
    }
}
