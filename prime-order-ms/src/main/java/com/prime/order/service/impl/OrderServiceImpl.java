package com.prime.order.service.impl;

import com.prime.order.dto.CreateOrderDto;
import com.prime.order.dto.OrderListOperatorDTO;
import com.prime.order.dto.OrderListUserDto;
import com.prime.order.exception.OrderNotDeleteException;
import com.prime.order.exception.OrderNotFoundException;
import com.prime.order.exception.OrderNotUpdateException;
import com.prime.order.model.CurrencyType;
import com.prime.order.model.Order;
import com.prime.order.model.OrderDateInfo;
import com.prime.order.model.OrderQuantity;
import com.prime.order.model.StatusType;
import com.prime.order.repository.OrderRepository;
import com.prime.order.service.OrderDateInfoService;
import com.prime.order.service.OrderQuantityService;
import com.prime.order.service.OrderService;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@PropertySource("classpath:currency.properties")
public class OrderServiceImpl implements OrderService {

    @Value("${currency.rub}")
    private double rub;
    @Value("${currency.usd}")
    private double usd;
    @Value("${currency.tl}")
    private double tl;
    @Value("${currency.azn}")
    private double azn;
    @Value("${currency.eur}")
    private double eur;

    private final OrderRepository orderRepository;
    private final OrderDateInfoService infoService;
    private final OrderQuantityService quantityService;

    @Override
    public long createOrder(long userId, CreateOrderDto orderDto) {
        Order order = Order.builder().userId(userId).build();
        orderDtoMapperOrder(orderDto,order);
        order.setOrderDateInfo(OrderDateInfo.builder().createDateUser(LocalDateTime.now()).build());
        order.setStatus(StatusType.NEW.getId());
        order.setTotalPrice(this.getTotalPrice(order));
        order.setOrderQuantity(new OrderQuantity());
        Order result = orderRepository.save(order);
        return result.getId();
    }

    @Override
    public void updateOrder(long orderId, CreateOrderDto orderDto) {
        Order order = orderRepository.findById(orderId).orElseThrow(() ->
                new OrderNotFoundException(orderId));
        orderDtoMapperOrder(orderDto,order);
        final int status = 1;
        if (status <= status) {
            OrderDateInfo info = infoService.getOrderDateInfoById(order.getOrderDateInfo().getId());
            OrderQuantity quantity = quantityService.getOrderQuantityById(order.getOrderQuantity().getId());
            info.setUpdatedDateUser(LocalDateTime.now());
            order.setOrderDateInfo(info);
            order.setOrderQuantity(quantity);
            order.setTotalPrice(this.getTotalPrice(order));
            orderRepository.save(order);
        } else {
            throw new OrderNotUpdateException(orderId);
        }
    }

    @Override
    public void deleteOrder(long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() ->
                new OrderNotFoundException(orderId));
        if (order.getStatus() == 0) {
            OrderDateInfo info = infoService.getOrderDateInfoById(order.getOrderDateInfo().getId());
            OrderQuantity quantity = quantityService.getOrderQuantityById(order.getOrderQuantity().getId());
            info.setDeletedDateUser(LocalDateTime.now());
            order.setOrderDateInfo(info);
            order.setOrderQuantity(quantity);
            order.setDeletedStatus(1);
            orderRepository.save(order);
        } else {
            throw new OrderNotDeleteException(orderId);
        }
    }

    @Override
    public List<OrderListUserDto> getOrderListWithUserId(long userId) {
        List<OrderListUserDto> dtoList = new LinkedList<>();
        Iterable<Order> orderList = orderRepository.findAllByUserId(userId);
        orderList.forEach(order -> {
            OrderListUserDto dto = OrderListUserDto.builder()
                    .link(order.getLink())
                    .orderId(order.getId())
                    .status(getStatusById(order.getStatus()).getMessage())
                    .carriageOrder(order.getCarriageOrder())
                    .totalPrice(order.getTotalPrice())
                    .count(order.getCount())
                    .createDate(order.getOrderDateInfo().getCreateDateUser())
                    .build();
            dtoList.add(dto);
        });
        return dtoList;
    }

    @Override
    public void chooseOrder(long orderId, long operatorId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() ->
                new OrderNotFoundException(orderId));
        boolean status = order.getStatus() == 1;
        if (status) {
            order.setOperatorId(operatorId);
            OrderDateInfo info = infoService.getOrderDateInfoById(order.getOrderDateInfo().getId());
            OrderQuantity quantity = quantityService.getOrderQuantityById(order.getOrderQuantity().getId());
            info.setChooseOperatorDate(LocalDateTime.now());
            order.setOrderDateInfo(info);
            order.setOrderQuantity(quantity);
            orderRepository.save(order);
        } else {
            throw new OrderNotUpdateException(orderId);
        }

    }

    @Override
    public List<OrderListOperatorDTO> getOrderListForOperator() {
        List<OrderListOperatorDTO> dtoList = new LinkedList<>();
        Iterable<Order> iterable = orderRepository.findAllByDeletedStatusAndStatus(0,1);
        iterable.forEach(order -> {
            OrderListOperatorDTO dto = OrderListOperatorDTO.builder()
                    .id(order.getId())
                    .currency(order.getCurrency().getValue())
                    .cargoPrice(order.getCargoPrice())
                    .carriageOrder(order.getCarriageOrder())
                    .count(order.getCount())
                    .descriptionOperator(order.getDescriptionOperator())
                    .descriptionUser(order.getDescriptionUser())
                    .link(order.getLink())
                    .price(order.getPrice())
                    .status(getStatusById(order.getStatus()).getMessage())
                    .totalPrice(order.getTotalPrice())
                    .userId(order.getUserId())
                    .build();
            dtoList.add(dto); });

        return dtoList;
    }

    private void orderDtoMapperOrder(CreateOrderDto dto, Order order) {
        order.setLink(dto.getLink());
        order.setPrice(dto.getPrice());
        order.setCount(dto.getCount());
        order.setCargoPrice(dto.getCargoPrice());
        order.setCurrency(this.getCurrency(dto.getCurrencyType()));
        order.setDescriptionUser(dto.getDescriptionUser());
    }

    private CurrencyType getCurrency(String currency) {
        if (currency.equalsIgnoreCase(CurrencyType.AZN.getValue())) {
            return CurrencyType.AZN;
        } else if (currency.equalsIgnoreCase(CurrencyType.RUB.getValue())) {
            return CurrencyType.RUB;
        } else if (currency.equalsIgnoreCase(CurrencyType.TL.getValue())) {
            return CurrencyType.TL;
        } else if (currency.equalsIgnoreCase(CurrencyType.EUR.getValue())) {
            return CurrencyType.EUR;
        } else if (currency.equalsIgnoreCase(CurrencyType.USD.getValue())) {
            return CurrencyType.USD;
        } else {
            return null;
        }
    }

    private double getTotalPrice(Order order) {
        if (order.getCurrency().getValue().equalsIgnoreCase("rub")) {
            return mathPrice(order,rub);
        } else if (order.getCurrency().getValue().equalsIgnoreCase("usd")) {
            return mathPrice(order,usd);
        } else if (order.getCurrency().getValue().equalsIgnoreCase("eur")) {
            return mathPrice(order,eur);
        } else if (order.getCurrency().getValue().equalsIgnoreCase("tl")) {
            return mathPrice(order,tl);
        } else {
            return mathPrice(order,azn);
        }
    }

    private double mathPrice(Order order, double countryCurriency) {
        double price = order.getPrice();
        int count = order.getCount();
        double cargo = order.getCargoPrice();
        return (price * count + cargo) * countryCurriency;
    }

    private StatusType getStatusById(int statusId) {
        switch (statusId) {
            case 0: return StatusType.NEW;
            case 1: return StatusType.PENDING;
            case 2: return StatusType.APPROVED;
            case 3: return StatusType.FOREIGN;
            case 4: return StatusType.SEND;
            case 5: return StatusType.LOCAL;
            case 6: return StatusType.COMPETED;
            default: return null;
        }
    }

}
