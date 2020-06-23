package com.prime.model;

import lombok.*;

import javax.persistence.*;

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
