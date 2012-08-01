
package org.ahp.vinavidai.services.quiz;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for quiz complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="quiz">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="quizId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="quizName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="quizDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "quiz", propOrder = {
    "quizId",
    "quizName",
    "quizDescription"
})
public class Quiz {

    protected int quizId;
    protected String quizName;
    protected String quizDescription;

    /**
     * Gets the value of the quizId property.
     * 
     */
    public int getQuizId() {
        return quizId;
    }

    /**
     * Sets the value of the quizId property.
     * 
     */
    public void setQuizId(int value) {
        this.quizId = value;
    }

    /**
     * Gets the value of the quizName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQuizName() {
        return quizName;
    }

    /**
     * Sets the value of the quizName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQuizName(String value) {
        this.quizName = value;
    }

    /**
     * Gets the value of the quizDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQuizDescription() {
        return quizDescription;
    }

    /**
     * Sets the value of the quizDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQuizDescription(String value) {
        this.quizDescription = value;
    }

}
