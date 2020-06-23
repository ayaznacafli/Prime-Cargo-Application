package com.prime;

import com.prime.model.Order;
import com.prime.model.OrderDateInfo;
import com.prime.repository.OrderDateInfoRepository;
import com.prime.repository.OrderRepository;
import com.prime.service.OrderService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext contex = SpringApplication.run(DemoApplication.class, args);

  //      OrderRepository orderRepository = contex.getBean(OrderRepository.class);
  //      Iterable<Order> orders = orderRepository.findAll();
  //      System.out.println(orders);

    }

}
