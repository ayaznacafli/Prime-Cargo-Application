package com.prime.carriage.dto;

import lombok.Data;

@Data
public class CarriageListResponseDto {
    private Long id;
    private String trackNumber;
    private String fromCountryName;
    private String toCountryName;
    private String categoryName;
    private String currencyName;
    private double invoicePrice;
    private String storeName;
    private String description;
    private String status;
}
