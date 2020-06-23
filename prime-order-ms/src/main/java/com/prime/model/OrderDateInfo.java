package com.prime.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "order_date_info_tb")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDateInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private LocalDateTime createDateUser;
    private LocalDateTime updatedDateUser;
    private LocalDateTime createDateOperator;
    private LocalDateTime createForeignWarehouse;
    private LocalDateTime createLocalWarehouse;
    private LocalDateTime createHandover;
    private LocalDateTime deletedDateUser;
    private LocalDateTime chooseOperatorDate;


 //   @JsonIgnore

    @OneToOne(mappedBy = "orderDateInfo")
    private Order order;

}
