package com.example.spring_6_rest_mvc_rsettemplate.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder(toBuilder = true)
public class JuiceDTO {
    private UUID id;
    private Integer version;

    @NotNull
    @NotBlank
    private String name;
    private JuiceStyle juiceStyle;
    private String upc;
    private Integer quantityOnHand;
    private BigDecimal price;
    private LocalDateTime createdDate;
    private  LocalDateTime updateDate;
}
