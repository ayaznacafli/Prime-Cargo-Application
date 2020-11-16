package com.prime.carriage.controller;

import com.prime.carriage.dto.CarriageListResponseDto;
import com.prime.carriage.dto.CarriageRequestDto;
import com.prime.carriage.dto.CarriageResponseDto;
import com.prime.common.search.SearchSpecification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.Arrays;
import java.util.List;

@Slf4j
@RestController
@Profile("dev")
@RequestMapping("/carriage/")
public class CarriageController {

    private static final String NEW = "NEW";
    private static final String PENDING = "PENDING";
    private static final String DESCRIPTION = "This is description";
    private static final String TRACK_NUMBER = "124342DK";
    private static final String FROM_COUNTRY = "Turkey";
    private static final String TO_COUNTRY = "Azerbaijan";
    private static final String STORE_NAME = "Defacto";
    private static final String USER_ID_NAME = "userId";


    @PostMapping("/create/{userId}/")
    public ResponseEntity<CarriageResponseDto> createCarriage(@RequestBody CarriageRequestDto dto,
                                                      @PathVariable(name = USER_ID_NAME) Long userId) {
        log.trace("Create carriage request");
        CarriageResponseDto responseDto = new CarriageResponseDto();
        responseDto.setId(1L);
        responseDto.setFromCountryId(dto.getFromCountryId());
        responseDto.setToCountryId(dto.getToCountryId());
        responseDto.setDescription(dto.getDescription());
        responseDto.setCategoryId(dto.getCategoryId());
        responseDto.setCurrencyId(dto.getCurrencyId());
        responseDto.setInvoicePrice(dto.getInvoicePrice());
        responseDto.setTrackNumber(dto.getTrackNumber());
        responseDto.setStoreName(dto.getStoreName());
        responseDto.setStatus(NEW);
        responseDto.setInvoiceFile(dto.getInvoiceFile());
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @PutMapping("/update/{userId}/{carriageId}/")
    public ResponseEntity<CarriageResponseDto> updateCarriage(@RequestBody CarriageRequestDto dto,
                                                              @PathVariable(name = USER_ID_NAME) Long userId,
                                                              @PathVariable(name = "carriageId") Long carriageId) {
        log.trace("Update carriage request");
        CarriageResponseDto responseDto = new CarriageResponseDto();
        responseDto.setId(1L);
        responseDto.setFromCountryId(1);
        responseDto.setToCountryId(2);
        responseDto.setDescription(DESCRIPTION);
        responseDto.setCurrencyId(3);
        responseDto.setCurrencyId(1);
        responseDto.setInvoicePrice(20.9);
        responseDto.setTrackNumber(TRACK_NUMBER);
        responseDto.setStoreName(STORE_NAME);
        responseDto.setStatus(NEW);
        responseDto.setInvoiceFile(new File("image.jpg"));
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @DeleteMapping("/delete/{carriageId}")
    public ResponseEntity<Long> deleteCarriageById(@PathVariable(name = "carriageId") Long id) {
        log.trace("Delete carriage request");
        return ResponseEntity.status(HttpStatus.OK).body(id);
    }

    @GetMapping("/{userId}/{carriageId}")
    public ResponseEntity<CarriageResponseDto> getCarriageById(@PathVariable(name = USER_ID_NAME) Long userId,
                                                               @PathVariable(name = "carriageId") Long carriageId) {
        log.trace("Get carriage request by id");
        CarriageResponseDto responseDto = new CarriageResponseDto();
        responseDto.setId(1L);
        responseDto.setFromCountryId(1);
        responseDto.setToCountryId(2);
        responseDto.setDescription(DESCRIPTION);
        responseDto.setCurrencyId(3);
        responseDto.setCurrencyId(1);
        responseDto.setInvoicePrice(20.9);
        responseDto.setTrackNumber(TRACK_NUMBER);
        responseDto.setStoreName(STORE_NAME);
        responseDto.setStatus(NEW);
        responseDto.setInvoiceFile(new File("image.jpg"));
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @GetMapping("/list/{userId}")
    public ResponseEntity<List<CarriageListResponseDto>> getCarriageList(@PathVariable(name = USER_ID_NAME)
                                                                                     Long userId) {
        log.trace("Get carriage list request by UserId");
        CarriageListResponseDto dto1 = new CarriageListResponseDto();
        dto1.setId(1L);
        dto1.setFromCountryName(FROM_COUNTRY);
        dto1.setToCountryName(TO_COUNTRY);
        dto1.setDescription(DESCRIPTION);
        dto1.setCategoryName("Electronics");
        dto1.setCurrencyName("TL");
        dto1.setInvoicePrice(20.9);
        dto1.setTrackNumber(TRACK_NUMBER);
        dto1.setStoreName(STORE_NAME);
        dto1.setStatus(NEW);

        CarriageListResponseDto dto2 = new CarriageListResponseDto();
        dto2.setId(2L);
        dto2.setFromCountryName(FROM_COUNTRY);
        dto2.setToCountryName(TO_COUNTRY);
        dto2.setDescription(DESCRIPTION);
        dto2.setCategoryName("Clothes");
        dto2.setCurrencyName("TL");
        dto2.setInvoicePrice(29.9);
        dto2.setTrackNumber("12442AS");
        dto2.setStoreName(STORE_NAME);
        dto2.setStatus(PENDING);
        return ResponseEntity.status(HttpStatus.OK).body(Arrays.asList(dto1,dto2));
    }

    @PostMapping("/search")
    public Slice<CarriageListResponseDto> search(@RequestBody SearchSpecification<CarriageListResponseDto> dto,
                                                 @RequestBody Pageable pageable) {
        log.trace("Search carriage request");
        CarriageListResponseDto dto1 = new CarriageListResponseDto();
        dto1.setId(1L);
        dto1.setFromCountryName(FROM_COUNTRY);
        dto1.setToCountryName(TO_COUNTRY);
        dto1.setDescription(DESCRIPTION);
        dto1.setCategoryName("Electronics");
        dto1.setCurrencyName("TL");
        dto1.setInvoicePrice(20.9);
        dto1.setTrackNumber(TRACK_NUMBER);
        dto1.setStoreName(STORE_NAME);
        dto1.setStatus(NEW);

        CarriageListResponseDto dto2 = new CarriageListResponseDto();
        dto2.setId(2L);
        dto2.setFromCountryName(FROM_COUNTRY);
        dto2.setToCountryName(TO_COUNTRY);
        dto2.setDescription(DESCRIPTION);
        dto2.setCategoryName("Clothes");
        dto2.setCurrencyName("TL");
        dto2.setInvoicePrice(29.9);
        dto2.setTrackNumber("12442AS");
        dto2.setStoreName(STORE_NAME);
        dto2.setStatus(PENDING);
        return new PageImpl<>(Arrays.asList(dto1,dto2));
    }
}
