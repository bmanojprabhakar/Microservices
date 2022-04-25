package com.backspace.currencyconversionservice.modal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyConversion {
    private Integer id;
    private String from;
    private String to;
    private Double quantity;
    private Double conversionMultiple;
    private Double totalCalculatedAmount;
    private String environment;
}
