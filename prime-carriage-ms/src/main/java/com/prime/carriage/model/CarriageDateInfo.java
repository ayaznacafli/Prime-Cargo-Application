package com.prime.carriage.model;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
public class CarriageDateInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime createDateUser;
    private LocalDateTime updatedDateUser;
    private LocalDateTime createForeignWarehouse;
    private LocalDateTime createLocalWarehouse;
    private LocalDateTime createHandover;
    private LocalDateTime deletedDateUser;

    @OneToOne(mappedBy = "dateInfo")
    private Carriage carriage;
}
