
package org.ahp.vinavidai.services.quiz;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.ahp.vinavidai.services.quiz package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetQuizInfoByNameRequest_QNAME = new QName("http://quiz.services.vinavidai.ahp.org/", "getQuizInfoByNameRequest");
    private final static QName _GetQuizInfoByNameResponse_QNAME = new QName("http://quiz.services.vinavidai.ahp.org/", "getQuizInfoByNameResponse");
    private final static QName _NoSuchQuiz_QNAME = new QName("http://quiz.services.vinavidai.ahp.org/", "NoSuchQuiz");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.ahp.vinavidai.services.quiz
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link NoSuchQuiz }
     * 
     */
    public NoSuchQuiz createNoSuchQuiz() {
        return new NoSuchQuiz();
    }

    /**
     * Create an instance of {@link GetQuizInfoByNameResponse }
     * 
     */
    public GetQuizInfoByNameResponse createGetQuizInfoByNameResponse() {
        return new GetQuizInfoByNameResponse();
    }

    /**
     * Create an instance of {@link GetQuizInfoByNameRequest }
     * 
     */
    public GetQuizInfoByNameRequest createGetQuizInfoByNameRequest() {
        return new GetQuizInfoByNameRequest();
    }

    /**
     * Create an instance of {@link Quiz }
     * 
     */
    public Quiz createQuiz() {
        return new Quiz();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetQuizInfoByNameRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://quiz.services.vinavidai.ahp.org/", name = "getQuizInfoByNameRequest")
    public JAXBElement<GetQuizInfoByNameRequest> createGetQuizInfoByNameRequest(GetQuizInfoByNameRequest value) {
        return new JAXBElement<GetQuizInfoByNameRequest>(_GetQuizInfoByNameRequest_QNAME, GetQuizInfoByNameRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetQuizInfoByNameResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://quiz.services.vinavidai.ahp.org/", name = "getQuizInfoByNameResponse")
    public JAXBElement<GetQuizInfoByNameResponse> createGetQuizInfoByNameResponse(GetQuizInfoByNameResponse value) {
        return new JAXBElement<GetQuizInfoByNameResponse>(_GetQuizInfoByNameResponse_QNAME, GetQuizInfoByNameResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NoSuchQuiz }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://quiz.services.vinavidai.ahp.org/", name = "NoSuchQuiz")
    public JAXBElement<NoSuchQuiz> createNoSuchQuiz(NoSuchQuiz value) {
        return new JAXBElement<NoSuchQuiz>(_NoSuchQuiz_QNAME, NoSuchQuiz.class, null, value);
    }

}
