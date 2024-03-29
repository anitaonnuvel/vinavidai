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
package org.ahp.commons.xml.dom;

import java.io.InputStream;
import java.io.StringWriter;

import org.ahp.commons.exceptions.AhpRuntimeException;
import org.ahp.commons.util.AhpResourceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSInput;
import org.w3c.dom.ls.LSOutput;
import org.w3c.dom.ls.LSParser;
import org.w3c.dom.ls.LSSerializer;

/**
 * 
 * @author Anita Onnuvel
 * 
 */
public final class AhpDomLevel3LS {

    static final Logger LOGGER = LoggerFactory.getLogger( AhpDomLevel3LS.class );

    static final String ERROR_HANDLER = "error-handler";
    static final String FORMAT_PRETTY_PRINT = "format-pretty-print";
    static final String LS_IMPLEMENTATION = "LS";
    static final String LS_OUTPUT_ENCODING = "UTF-8";

    static {
        // System.setProperty(DOMImplementationRegistry.PROPERTY,
        // "org.apache.xerces.dom.DOMXSImplementationSourceImpl");
    }

    /**
     * 
     * @param pXmlPath
     * @return
     */
    public static Node loadNodeFromUri( String pXmlResourceUri ) {
        try {
            if ( AhpResourceUtil.isClassPathResource( pXmlResourceUri ) ) {
                pXmlResourceUri = AhpResourceUtil.getClassPathResourceAsFileResource( pXmlResourceUri );
            }
            DOMImplementationRegistry lDOMImplementationRegistry = DOMImplementationRegistry.newInstance();
            DOMImplementationLS lDOMImplementationLS = ( DOMImplementationLS ) lDOMImplementationRegistry
                    .getDOMImplementation( LS_IMPLEMENTATION );
            LSParser lLSParser = lDOMImplementationLS.createLSParser( DOMImplementationLS.MODE_SYNCHRONOUS, null );
            lLSParser.getDomConfig().setParameter( ERROR_HANDLER, new AhpSilentDomErrorHandler() );
            Document lDocument = lLSParser.parseURI( pXmlResourceUri );
            return ( Node ) lDocument;
        } catch ( ClassCastException | ClassNotFoundException | InstantiationException | IllegalAccessException ex ) {
            throw new AhpRuntimeException( "", ex );
        }
    }

    /**
     * 
     * @param pXmlPath
     * @return
     */
    public static Node loadNodeFromStream( InputStream pXmlInputStream ) {
        try {
            DOMImplementationRegistry lDOMImplementationRegistry = DOMImplementationRegistry.newInstance();
            DOMImplementationLS lDOMImplementationLS = ( DOMImplementationLS ) lDOMImplementationRegistry
                    .getDOMImplementation( LS_IMPLEMENTATION );
            LSParser lLSParser = lDOMImplementationLS.createLSParser( DOMImplementationLS.MODE_SYNCHRONOUS, null );
            lLSParser.getDomConfig().setParameter( ERROR_HANDLER, new AhpSilentDomErrorHandler() );
            LSInput lLSInput = lDOMImplementationLS.createLSInput();
            lLSInput.setByteStream( pXmlInputStream );
            Document lDocument = lLSParser.parse( lLSInput );
            return ( Node ) lDocument;
        } catch ( ClassCastException | ClassNotFoundException | InstantiationException | IllegalAccessException ex ) {
            throw new AhpRuntimeException( "", ex );
        }
    }

    /**
     * 
     * @param pXmlPath
     * @return
     */
    public static Node loadNodeFromString( String pXmlString ) {
        try {
            DOMImplementationRegistry lDOMImplementationRegistry = DOMImplementationRegistry.newInstance();
            DOMImplementationLS lDOMImplementationLS = ( DOMImplementationLS ) lDOMImplementationRegistry
                    .getDOMImplementation( LS_IMPLEMENTATION );
            LSParser lLSParser = lDOMImplementationLS.createLSParser( DOMImplementationLS.MODE_SYNCHRONOUS, null );
            lLSParser.getDomConfig().setParameter( ERROR_HANDLER, new AhpSilentDomErrorHandler() );
            LSInput lLSInput = lDOMImplementationLS.createLSInput();
            lLSInput.setStringData( pXmlString );
            Document lDocument = lLSParser.parse( lLSInput );
            return ( Node ) lDocument;
        } catch ( ClassCastException | ClassNotFoundException | InstantiationException | IllegalAccessException ex ) {
            throw new AhpRuntimeException( "", ex );
        }
    }

    /**
     * 
     * @param pNode
     * @return
     */
    public static String saveNodeToString( Node pNode ) {
        try {
            DOMImplementationRegistry lDOMImplementationRegistry = DOMImplementationRegistry.newInstance();
            DOMImplementationLS lDOMImplementationLS = ( DOMImplementationLS ) lDOMImplementationRegistry
                    .getDOMImplementation( LS_IMPLEMENTATION );
            LSSerializer lLSSerializer = lDOMImplementationLS.createLSSerializer();
            if ( lLSSerializer.getDomConfig().canSetParameter( FORMAT_PRETTY_PRINT, Boolean.TRUE ) ) {
                lLSSerializer.getDomConfig().setParameter( FORMAT_PRETTY_PRINT, Boolean.TRUE );
            }
            LSOutput lLSOutput = lDOMImplementationLS.createLSOutput();
            lLSOutput.setEncoding( LS_OUTPUT_ENCODING );
            StringWriter lStringWriter = new StringWriter();
            lLSOutput.setCharacterStream( lStringWriter );
            lLSSerializer.write( pNode, lLSOutput );
            return lStringWriter.toString();
        } catch ( ClassCastException | ClassNotFoundException | InstantiationException | IllegalAccessException ex ) {
            throw new AhpRuntimeException( "", ex );
        }
    }

    /**
     * 
     * @param pNode
     * @param pSystemId
     */
    public static void saveNodeToFile( Node pNode, String pSystemId ) {
        try {
            DOMImplementationRegistry lDOMImplementationRegistry = DOMImplementationRegistry.newInstance();
            DOMImplementationLS lDOMImplementationLS = ( DOMImplementationLS ) lDOMImplementationRegistry
                    .getDOMImplementation( LS_IMPLEMENTATION );
            LSSerializer lLSSerializer = lDOMImplementationLS.createLSSerializer();
            if ( lLSSerializer.getDomConfig().canSetParameter( FORMAT_PRETTY_PRINT, Boolean.TRUE ) ) {
                lLSSerializer.getDomConfig().setParameter( FORMAT_PRETTY_PRINT, Boolean.TRUE );
            }
            LSOutput lLSOutput = lDOMImplementationLS.createLSOutput();
            lLSOutput.setSystemId( pSystemId );
            lLSSerializer.write( pNode, lLSOutput );
        } catch ( ClassCastException | ClassNotFoundException | InstantiationException | IllegalAccessException ex ) {
            throw new AhpRuntimeException( "", ex );
        }
    }
}