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
package org.ahp.commons.xml.validation;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Node;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;


/**
 * @author Anita Onnuvel
 * 
 * The Class AhpSchemaValidator.
 */
public class AhpSchemaValidator {
	
	/** The Constant LOGGER. */
	final static Logger LOGGER = LoggerFactory.getLogger( AhpSchemaValidator.class );
	
    /** The Constant W3C_SCHEMA. */
    public static final String W3C_SCHEMA = "http://www.w3.org/2001/XMLSchema";
    
    /** The m schema location. */
    private String mSchemaLocation;
    
    /** The m xml file. */
    private String mXmlFile;
    
    /** The m xml document. */
    private Node mXmlDocument;
    
    /** The m input stream. */
    private InputStream mInputStream;
    
    /** The m output stream. */
    private OutputStream mOutputStream;
    
    /** The m error handler. */
    private ErrorHandler mErrorHandler;
    
    /**
     * Sets the schema location.
     *
     * @param pSchemaLocation the new schema location
     */
    public final void setSchemaLocation( String pSchemaLocation ){
        this.mSchemaLocation = pSchemaLocation;
    }

    /**
     * Sets the xml file location.
     *
     * @param pXmlFileLocation the new xml file location
     */
    public final void setXmlFileLocation( String pXmlFileLocation ){
        this.mXmlFile = pXmlFileLocation;
    }

    /**
     * Sets the xml file input stream.
     *
     * @param pInputStream the new xml file input stream
     */
    public final void setXmlFileInputStream( InputStream pInputStream ){
        this.mInputStream = pInputStream;
    }

    /**
     * Sets the xml file output stream.
     *
     * @param pOutputStream the new xml file output stream
     */
    public final void setXmlFileOutputStream( OutputStream pOutputStream ){
        this.mOutputStream = pOutputStream;
    }

    /**
     * Sets the xml document.
     *
     * @param pXmlDocument the new xml document
     */
    public final void setXmlDocument( Node pXmlDocument ){
        this.mXmlDocument = pXmlDocument;
    }

    /**
     * Sets the error handler.
     *
     * @param pErrorHandler the new error handler
     */
    public final void setErrorHandler( ErrorHandler pErrorHandler ){
        this.mErrorHandler = pErrorHandler;
    }
    
    /**
     * Validate with w3 c schema.
     *
     * @return true, if successful
     */
    public boolean validateWithW3CSchema(){
        try { 
            SchemaFactory lSchemaFactory = SchemaFactory.newInstance( W3C_SCHEMA );
            Schema lSchema = lSchemaFactory.newSchema( new File( this.mSchemaLocation ) );
            Validator lValidator = lSchema.newValidator();
            Source lSource = null;
            if ( this.mXmlFile != null ) {
                lSource = new StreamSource( new File( this.mXmlFile ) );
                LOGGER.debug( "Using XmlFile Source" );
            }
            if ( this.mInputStream != null ) {
                lSource = new StreamSource( mInputStream );
                LOGGER.debug( "Using InputStream Source" );
            }
            if ( this.mXmlDocument != null ) {
                lSource = new StreamSource( new File( this.mXmlFile ) );
                LOGGER.debug( "Using DOM Source" );
            }
            if ( this.mOutputStream != null ) {
                lSource = new StreamSource( new File( this.mXmlFile ) );
            }
            ErrorHandler lErrorHandler = new AhpDefaultErrorHandler();
            if ( this.mErrorHandler != null ) {
                lErrorHandler = this.mErrorHandler;
            }                
            lValidator.setErrorHandler( lErrorHandler );
            lValidator.validate( lSource );
            return true;
        } catch ( SAXException exSAX ) {
            LOGGER.error( "ValidationFailed", exSAX );
        } catch ( IOException exIO ) {
        	LOGGER.error( "ValidationFailed", exIO );
		}  
        return false;
    }
}
