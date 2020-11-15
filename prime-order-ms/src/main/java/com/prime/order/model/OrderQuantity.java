package com.prime.order.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "order_quantity_info_tb")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderQuantity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long orderCode;
    private double orderWeight;
    private double orderWidth;
    private double orderHeigh;
    private double orderLength;
    private String foreignLocation;
    private String localLocation;
    private String barCode;
    private String categoryName;

    @OneToOne(mappedBy = "orderQuantity")
    private Order order;

}
