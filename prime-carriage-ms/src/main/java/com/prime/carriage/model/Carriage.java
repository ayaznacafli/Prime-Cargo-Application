package com.prime.carriage.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "carriage_ms")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Carriage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String trackNumber;
    private String siteName;
    private String productCategory;

    private long userId;

    private int status;
    private int deletedStatus;

    private int count;
    private double amount;
    private double totalAmount;

    private String descriptionUser;
    private String orderCode;


    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER,optional = false)
    @JoinColumn(name = "date_info_id")
    private CarrigeDateInfo dateInfo;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER,optional = false)
    @JoinColumn(name = "quantity_info_id")
    private CarrigeQuantity quantity;

}

