package com.backspace.currencyconversionservice.controller;

import com.backspace.currencyconversionservice.modal.CurrencyConversion;
import com.backspace.currencyconversionservice.modal.CurrencyExchangeProxy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class CurrencyConversionController {
    @Autowired
    private CurrencyExchangeProxy proxy;

    @GetMapping("/currency-conversion/{from}/to/{to}/{quantity}")
    public CurrencyConversion calculateCurrencyValue(
            @PathVariable String from,
            @PathVariable String to,
            @PathVariable Integer quantity
    ) {
        log.info("Invoking currency conversion service {} to {}", from, to);
        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("from", from);
        uriVariables.put("to", to);
        ResponseEntity<CurrencyConversion> responseEntity = new RestTemplate()
                .getForEntity("http://localhost:8000/currency-exchange/{from}/to/{to}", CurrencyConversion.class,
                uriVariables);
        CurrencyConversion currencyConversion = responseEntity.getBody();
        assert currencyConversion != null;
        return new CurrencyConversion(1001, currencyConversion.getFrom(),
                currencyConversion.getTo(), (double) quantity,
                currencyConversion.getConversionMultiple(),
                currencyConversion.getConversionMultiple()*quantity,
                currencyConversion.getEnvironment());
    }

    @GetMapping("/currency-conversion/{from}/to/{to}/{quantity}/feign")
    public CurrencyConversion calculateCurrencyValueFromFeign(
            @PathVariable String from,
            @PathVariable String to,
            @PathVariable Integer quantity
    ) {
        log.info("Invoking currency conversion feign service {} to {}", from, to);
        CurrencyConversion currencyConversion = proxy.retrieveExchangeValue(from,to);
        assert currencyConversion != null;
        return new CurrencyConversion(1001, currencyConversion.getFrom(),
                currencyConversion.getTo(), (double) quantity,
                currencyConversion.getConversionMultiple(),
                currencyConversion.getConversionMultiple()*quantity,
                currencyConversion.getEnvironment()+" from feign");
    }
}
