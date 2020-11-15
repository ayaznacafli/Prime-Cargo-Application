package com.prime.carriage.dto;

import lombok.Data;

import java.io.File;

@Data
public class CarriageRequestDto {

    private String trackNumber;
    private int fromCountryId;
    private int toCountryId;
    private int categoryId;
    private int currencyId;
    private double invoicePrice;
    private String storeName;
    private String description;
    private File invoiceFile;

}
