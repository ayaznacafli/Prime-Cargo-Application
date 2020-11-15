package com.prime.order.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Entity
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



    @OneToOne(mappedBy = "orderDateInfo")
    private Order order;

}
