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

import javax.xml.namespace.NamespaceContext;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import javax.xml.xpath.XPathFunctionResolver;
import javax.xml.xpath.XPathVariableResolver;

import org.ahp.commons.exceptions.AhpRuntimeException;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @author Anita Onnuvel
 * 
 *         The Class AhpXPathUtil.
 * 
 */
public final class AhpXPathUtil {

    /** The m x path factory. */
    private XPathFactory mXPathFactory;

    /** The m x path. */
    private XPath mXPath;

    /** The m namespace context. */
    private NamespaceContext mNamespaceContext;

    /** The m x path variable resolver. */
    private XPathVariableResolver mXPathVariableResolver;

    /** The m x path function resolver. */
    private XPathFunctionResolver mXPathFunctionResolver;

    /**
     * Instantiates a new ahp x path util.
     */
    public AhpXPathUtil() {
        mXPathFactory = XPathFactory.newInstance();
        this.mXPath = createXPath();
    }

    /**
     * Creates the x path.
     * 
     * @return the x path
     */
    public XPath createXPath() {
        XPath lXPath = this.mXPathFactory.newXPath();
        if ( this.mNamespaceContext == null ) {
            this.mNamespaceContext = new AhpNamespaceContext( null );
        }
        if ( this.mXPathVariableResolver != null ) {
            lXPath.setXPathVariableResolver( this.mXPathVariableResolver );
        }
        if ( this.mXPathFunctionResolver != null ) {
            lXPath.setXPathFunctionResolver( this.mXPathFunctionResolver );
        }
        lXPath.setNamespaceContext( this.mNamespaceContext );
        return lXPath;
    }

    /**
     * Sets the namespace context.
     * 
     * @param pNamespaceContext
     *            the new namespace context
     */
    public void setNamespaceContext( NamespaceContext pNamespaceContext ) {
        this.mNamespaceContext = pNamespaceContext;
    }

    /**
     * Sets the x path variable resolver.
     * 
     * @param pXPathVariableResolver
     *            the new x path variable resolver
     */
    public void setXPathVariableResolver( XPathVariableResolver pXPathVariableResolver ) {
        this.mXPathVariableResolver = pXPathVariableResolver;
    }

    /**
     * Sets the x path function resolver.
     * 
     * @param pXPathFunctionResolver
     *            the new x path function resolver
     */
    public void setXPathFunctionResolver( XPathFunctionResolver pXPathFunctionResolver ) {
        this.mXPathFunctionResolver = pXPathFunctionResolver;
    }

    /**
     * Gets the as string.
     * 
     * @param pDocument
     *            the document
     * @param pExpression
     *            the expression
     * @return the as string
     */
    public String getAsString( Node pDocument, String pExpression ) {
        String lStringValue;
        try {
            lStringValue = ( String ) this.mXPath.evaluate( pExpression, pDocument, XPathConstants.STRING );
        } catch ( XPathExpressionException exXPathExpression ) {
            throw new AhpRuntimeException( "" );
        }
        return lStringValue;
    }

    /**
     * Gets the as boolean.
     * 
     * @param pDocument
     *            the document
     * @param pExpression
     *            the expression
     * @return the as boolean
     */
    public Boolean getAsBoolean( Node pDocument, String pExpression ) {
        Boolean lBooleanValue;
        try {
            lBooleanValue = ( Boolean ) this.mXPath.evaluate( pExpression, pDocument, XPathConstants.BOOLEAN );
        } catch ( XPathExpressionException exXPathExpression ) {
            throw new AhpRuntimeException( "" );
        }
        return lBooleanValue;
    }

    /**
     * Gets the as node.
     * 
     * @param pDocument
     *            the document
     * @param pExpression
     *            the expression
     * @return the as node
     */
    public Node getAsNode( Node pDocument, String pExpression ) {
        Node lEvaluatedNode;
        try {
            lEvaluatedNode = ( Node ) this.mXPath.evaluate( pExpression, pDocument, XPathConstants.NODE );
        } catch ( XPathExpressionException exXPathExpression ) {
            throw new AhpRuntimeException( "" );
        }
        return lEvaluatedNode;
    }

    /**
     * Gets the as node list.
     * 
     * @param pDocument
     *            the document
     * @param pExpression
     *            the expression
     * @return the as node list
     */
    public NodeList getAsNodeList( Node pDocument, String pExpression ) {
        NodeList lEvaluatedNodeList;
        try {
            lEvaluatedNodeList = ( NodeList ) this.mXPath.evaluate( pExpression, pDocument, XPathConstants.NODESET );
        } catch ( XPathExpressionException exXPathExpression ) {
            throw new AhpRuntimeException( "" );
        }
        return lEvaluatedNodeList;
    }

    /**
     * Gets the as integer.
     * 
     * @param pDocument
     *            the document
     * @param pExpression
     *            the expression
     * @return the as integer
     */
    public Integer getAsInteger( Node pDocument, String pExpression ) {
        Integer lIntegerValue;
        try {
            Double lDoubleValue = ( Double ) this.mXPath.evaluate( pExpression, pDocument, XPathConstants.NUMBER );
            lIntegerValue = new Integer( lDoubleValue.intValue() );
        } catch ( XPathExpressionException exXPathExpression ) {
            throw new AhpRuntimeException( "" );
        }
        return lIntegerValue;
    }

    /**
     * Gets the as long.
     * 
     * @param pDocument
     *            the document
     * @param pExpression
     *            the expression
     * @return the as long
     */
    public Long getAsLong( Node pDocument, String pExpression ) {
        Long lLongValue;
        try {
            Double lDoubleValue = ( Double ) this.mXPath.evaluate( pExpression, pDocument, XPathConstants.NUMBER );
            lLongValue = new Long( lDoubleValue.longValue() );
        } catch ( XPathExpressionException exXPathExpression ) {
            throw new AhpRuntimeException( "" );
        }
        return lLongValue;
    }

    /**
     * Compile x path expression.
     * 
     * @param pExpression
     *            the expression
     * @return the x path expression
     */
    public XPathExpression compileXPathExpression( String pExpression ) {
        XPathExpression lXPathExpression;
        try {
            lXPathExpression = this.mXPath.compile( pExpression );
        } catch ( XPathExpressionException exXPathExpression ) {
            throw new AhpRuntimeException( "" );
        }
        return lXPathExpression;
    }

    /**
     * Gets the as string.
     * 
     * @param pDocument
     *            the document
     * @param pXPathExpression
     *            the x path expression
     * @return the as string
     */
    public String getAsString( Node pDocument, XPathExpression pXPathExpression ) {
        String lStringValue;
        try {
            lStringValue = ( String ) pXPathExpression.evaluate( pDocument, XPathConstants.STRING );
        } catch ( XPathExpressionException exXPathExpression ) {
            throw new AhpRuntimeException( "" );
        }
        return lStringValue;

    }

    /**
     * Gets the as boolean.
     * 
     * @param pDocument
     *            the document
     * @param pXPathExpression
     *            the x path expression
     * @return the as boolean
     */
    public Boolean getAsBoolean( Node pDocument, XPathExpression pXPathExpression ) {
        Boolean lBooleanValue;
        try {
            lBooleanValue = ( Boolean ) pXPathExpression.evaluate( pDocument, XPathConstants.BOOLEAN );
        } catch ( XPathExpressionException exXPathExpression ) {
            throw new AhpRuntimeException( "" );
        }
        return lBooleanValue;

    }

    /**
     * Gets the as node.
     * 
     * @param pDocument
     *            the document
     * @param pXPathExpression
     *            the x path expression
     * @return the as node
     */
    public Node getAsNode( Node pDocument, XPathExpression pXPathExpression ) {
        Node lEvaluatedNode;
        try {
            lEvaluatedNode = ( Node ) pXPathExpression.evaluate( pDocument, XPathConstants.NODE );
        } catch ( XPathExpressionException exXPathExpression ) {
            throw new AhpRuntimeException( "" );
        }
        return lEvaluatedNode;
    }

    /**
     * Gets the as node list.
     * 
     * @param pDocument
     *            the document
     * @param pXPathExpression
     *            the x path expression
     * @return the as node list
     */
    public NodeList getAsNodeList( Node pDocument, XPathExpression pXPathExpression ) {
        NodeList lEvaluatedNodeList;
        try {
            lEvaluatedNodeList = ( NodeList ) pXPathExpression.evaluate( pDocument, XPathConstants.NODESET );
        } catch ( XPathExpressionException exXPathExpression ) {
            throw new AhpRuntimeException( "" );
        }
        return lEvaluatedNodeList;
    }

    /**
     * Gets the as integer.
     * 
     * @param pDocument
     *            the document
     * @param pXPathExpression
     *            the x path expression
     * @return the as integer
     */
    public Integer getAsInteger( Node pDocument, XPathExpression pXPathExpression ) {
        Integer lIntegerValue;
        try {
            Double lDoubleValue = ( Double ) pXPathExpression.evaluate( pDocument, XPathConstants.NUMBER );
            lIntegerValue = new Integer( lDoubleValue.intValue() );
        } catch ( XPathExpressionException exXPathExpression ) {
            throw new AhpRuntimeException( "" );
        }
        return lIntegerValue;
    }

    /**
     * Gets the as long.
     * 
     * @param pDocument
     *            the document
     * @param pXPathExpression
     *            the x path expression
     * @return the as long
     */
    public Long getAsLong( Node pDocument, XPathExpression pXPathExpression ) {
        Long lLongValue;
        try {
            Double lDoubleValue = ( Double ) pXPathExpression.evaluate( pDocument, XPathConstants.NUMBER );
            lLongValue = new Long( lDoubleValue.longValue() );
        } catch ( XPathExpressionException exXPathExpression ) {
            throw new AhpRuntimeException( "" );
        }
        return lLongValue;
    }

}