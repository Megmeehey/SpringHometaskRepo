package epam.spring;

import lombok.extern.java.Log;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@Log
public class Runner {
    /**
     * Main method.
     */
    public static void main(String[] args) {
        log.info("Initializing Spring context.");
        
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("main.xml");
        
        log.info("Spring context initialized.");
    }

}