package com.prime.order.model;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@ToString
@Table(name = "order_ms")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings({"PMD.TooManyFields"})
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String link;
    @Enumerated(EnumType.STRING)
    private CurrencyType currency;
    private double price;
    private double cargoPrice;
    private int count;
    private double totalPrice;
    private double carriageOrder;

    private String descriptionUser;
    private String descriptionOperator;

    private long userId;
    private long operatorId;
    private int deletedStatus;
    private int status;



    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER,optional = false)
    @JoinColumn(name = "date_info_id")
    private OrderDateInfo orderDateInfo;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER,optional = false)
    @JoinColumn(name = "order_quantity_id")
    private OrderQuantity orderQuantity;


}
