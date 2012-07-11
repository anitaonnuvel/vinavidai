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
import java.util.Iterator;
import java.util.Map;

import javax.xml.XMLConstants;
import javax.xml.namespace.NamespaceContext;

/**
 * 
 * @author Anita Onnuvel
 *
 */
public class AhpNamespaceContext implements NamespaceContext {
   
   /** DOCUMENT ME! */
   private Map<String,String> mNamespaceContextMap = null;

   /**
    * DOCUMENT ME!
    */    
   public AhpNamespaceContext( Map<String,String> pNamespaceContextMap ){
       this.mNamespaceContextMap = pNamespaceContextMap;
       if ( this.mNamespaceContextMap == null ) {
           this.mNamespaceContextMap = new HashMap<String,String>();    
       }
       this.mNamespaceContextMap.put( "xml", XMLConstants.XML_NS_URI );
   }

   /**
    * DOCUMENT ME!
    */    
   public void addPrefixMapping( String pPrefix, String pNamespaceUri ){
       this.mNamespaceContextMap.put( pPrefix, pNamespaceUri );
   }
   
   /**
    * DOCUMENT ME!
    */
   public String getNamespaceURI(String pPrefix) {
       if ( pPrefix == null ) 
           throw new NullPointerException( "Prefix is null" );
       if ( this.mNamespaceContextMap.containsKey( pPrefix ) )
           return this.mNamespaceContextMap.get( pPrefix );
       else 
           return XMLConstants.NULL_NS_URI;
   }

   /**
    * DOCUMENT ME!
    */
   public String getPrefix( String pNamespaceUri ) {
       throw new UnsupportedOperationException();
   }

   /**
    * DOCUMENT ME!
    */
   public Iterator getPrefixes( String pNamespaceUri ) {
       throw new UnsupportedOperationException();
   }

}

