package com.prime.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "order_ms")
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
    private Long orderCode;
    private int deletedStatus;
    private int status;

    private String categoryName;

    private double orderWeight;
    private double orderWidth;
    private double orderHeigh;
    private double orderLength;
    private String foreignLocation;
    private String localLocation;
    private String barCode;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER,optional = false)
    @JoinColumn(name="date_info_id")
    private OrderDateInfo orderDateInfo;

}
