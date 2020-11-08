package com.prime;

import com.prime.repository.AuthorityRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


@SpringBootApplication
public class UserApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext contex = SpringApplication.run(UserApplication.class, args);
        AuthorityRepository repository = contex.getBean(AuthorityRepository.class);


    }
}

