package com.prime.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
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

    @Temporal(TemporalType.TIMESTAMP)
    private Date createDateUser;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDateUser;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDateOperator;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createForeignWarehouse;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createLocalWarehouse;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createHandover;
    @Temporal(TemporalType.TIMESTAMP)
    private Date deletedDateUser;
    @Temporal(TemporalType.TIMESTAMP)
    private Date chooseOperatorDate;


 //   @JsonIgnore

    @OneToOne(mappedBy = "orderDateInfo")
    private Order order;

}
