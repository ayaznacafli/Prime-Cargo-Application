package com.prime.model;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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

    @Temporal(TemporalType.TIMESTAMP)
    private Date createDateUser;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDateUser;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createForeignWarehouse;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createLocalWarehouse;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createHandover;
    @Temporal(TemporalType.TIMESTAMP)
    private Date deletedDateUser;


    private double orderWeight;
    private double orderWidth;
    private double orderHeigh;
    private double orderLength;
}

