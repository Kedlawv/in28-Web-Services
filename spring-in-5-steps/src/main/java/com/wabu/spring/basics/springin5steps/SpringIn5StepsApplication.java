package com.wabu.spring.basics.springin5steps;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringIn5StepsApplication {

    public static void main(String[] args) {
        //Spring needs to know:
        //- What are the beans -> @Component
        //- What are the dependencies of a bean -> @Autowired
        //- Where to find the beans -> ComponentScan. No need in this case as the default package for ComponentScan
        //  is the package containing class with @SpringBootApplication

        //All beans are created and managed by the ApplicationContext


        ConfigurableApplicationContext applicationContext =
                SpringApplication.run(SpringIn5StepsApplication.class, args);

        BinarySearchImpl binarySearch = applicationContext.getBean(BinarySearchImpl.class);

        //Instead of creating a BinarySearchImpl instance we get the bean of that type from
        //  Spring's ApplicationContext and the required SortAlgorithm type argument is automatically supplied
        //  by Spring framework

        //BinarySearchImpl binarySearch = new BinarySearchImpl(new BubbleSort());

        int result = binarySearch.binarySearch(new int[]{23, 556, 3, 2, 7}, 2);
        System.out.println("result: " + result);
    }

}
