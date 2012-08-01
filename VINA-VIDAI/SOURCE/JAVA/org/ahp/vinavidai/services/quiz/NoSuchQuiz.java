
package org.ahp.vinavidai.services.quiz;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for NoSuchQuiz complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="NoSuchQuiz">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="quizNameName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NoSuchQuiz", propOrder = {
    "quizNameName"
})
public class NoSuchQuiz {

    @XmlElement(required = true, nillable = true)
    protected String quizNameName;

    /**
     * Gets the value of the quizNameName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQuizNameName() {
        return quizNameName;
    }

    /**
     * Sets the value of the quizNameName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQuizNameName(String value) {
        this.quizNameName = value;
    }

}
