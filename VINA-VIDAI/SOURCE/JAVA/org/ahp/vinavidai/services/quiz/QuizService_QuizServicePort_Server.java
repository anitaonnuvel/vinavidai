
package org.ahp.vinavidai.services.quiz;

import javax.xml.ws.Endpoint;

/**
 * This class was generated by Apache CXF 2.6.1
 * 2012-07-30T16:34:35.412-05:00
 * Generated source version: 2.6.1
 * 
 */
 
public class QuizService_QuizServicePort_Server{

    protected QuizService_QuizServicePort_Server() throws java.lang.Exception {
        System.out.println("Starting Server");
        Object implementor = new QuizServiceImpl();
        String address = "http://localhost:8080/vinavidai/services/QuizService";
        Endpoint.publish(address, implementor);
    }
    
    public static void main(String args[]) throws java.lang.Exception { 
        new QuizService_QuizServicePort_Server();
        System.out.println("Server ready..."); 
        
        Thread.sleep(5 * 60 * 1000); 
        System.out.println("Server exiting");
        System.exit(0);
    }
}
