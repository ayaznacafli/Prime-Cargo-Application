package com.prime.service.impl;

import com.prime.dto.CreateOrderDto;
import com.prime.dto.OrderFilterDto;
import com.prime.dto.OrderListUserDto;
import com.prime.exception.OrderNotDeleteException;
import com.prime.exception.OrderNotFoundException;
import com.prime.exception.OrderNotUpdateException;
import com.prime.model.CurrencyType;
import com.prime.model.Order;
import com.prime.model.OrderDateInfo;
import com.prime.model.StatusType;
import com.prime.repository.OrderDateInfoRepository;
import com.prime.repository.OrderRepository;
import com.prime.service.OrderDateInfoService;
import com.prime.service.OrderService;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderDateInfoService dateInfoService;

    @Override
    public long createOrder(long userId, CreateOrderDto orderDto) {
        Order order = Order.builder().userId(userId).build();
        orderDtoMapperOrder(orderDto,order);
        order.setOrderDateInfo(OrderDateInfo.builder().createDateUser(new Date()).build());
        order.setStatus(StatusType.NEW.getId());
        Order result = orderRepository.save(order);
        return result.getId();
    }

    @Override
    public void updateOrder(long orderId, CreateOrderDto orderDto) {
        Order order = orderRepository.findById(orderId).orElseThrow(() ->
                new OrderNotFoundException(orderId));
        orderDtoMapperOrder(orderDto,order);
        if (order.getStatus() <= 1) {
            System.out.println(dateInfoService.getOrderDateInfoByOrderId(orderId));
            order.setOrderDateInfo(OrderDateInfo.builder().updatedDateUser(new Date()).build());
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
            order.setDeletedStatus(1);
            order.setOrderDateInfo(OrderDateInfo.builder().deletedDateUser(new Date()).build());
            orderRepository.save(order);
        } else {
            throw new OrderNotDeleteException(orderId);
        }
    }

    @Override
    public List<OrderListUserDto> getOrderListWithUserId(long userId) {
        List<OrderListUserDto> dtoList = new LinkedList<>();
        Iterable<Order> orderList = orderRepository.findAllByUserId(userId);
        System.out.println(orderList);
        orderList.forEach(order -> {
                    OrderListUserDto dto = OrderListUserDto.builder()
                            .link(order.getLink())
                            .orderId(order.getId())
                            .status(getStatusById(order.getStatus()))
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
        if (order.getStatus() == 1) {
            order.setOperatorId(operatorId);
            order.setOrderDateInfo(OrderDateInfo.builder().chooseOperatorDate(new Date()).build());
            orderRepository.save(order);
        } else {
            throw new OrderNotUpdateException(orderId);
        }

    }

    @Override
    public Iterable<Order> getOrderListForOperator(OrderFilterDto orderFilterDto, Pageable pageable) {
        return orderRepository.findAllByDeletedStatusAndStatus(0,1);
    }

    private void orderDtoMapperOrder(CreateOrderDto dto, Order order) {
        order.setLink(dto.getLink());
        order.setPrice(dto.getPrice());
        order.setCount(dto.getCount());
        order.setCargoPrice(dto.getCargoPrice());
        order.setTotalPrice(getTotalPrice(dto));
        order.setCurrency(this.getCurrency(dto.getCurrencyType()));
        order.setDescriptionUser(dto.getDescriptionUser());
    }

    private CurrencyType getCurrency(String currency) {
        if (currency.equalsIgnoreCase(CurrencyType.AZN.getValue())) {
            return CurrencyType.AZN;
        } else if (currency.equalsIgnoreCase(CurrencyType.RUB.getValue())) {
            return CurrencyType.RUB;
        } else if (currency.equalsIgnoreCase(CurrencyType.RUB.getValue())) {
            return CurrencyType.TL;
        } else {
            return null;
        }
    }

    private double getTotalPrice(CreateOrderDto dto) {
        double price = dto.getPrice();
        int count = dto.getCount();
        double cargo = dto.getCargoPrice();
        return price * count + cargo;
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
