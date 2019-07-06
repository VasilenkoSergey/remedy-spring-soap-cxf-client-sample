package io.vasilenko.remedy.spring.soap.cxfclient.sample.ws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tempuri.CalculatorSoap;

@Component
public class WsClient {

    private final CalculatorSoap calculator;

    @Autowired
    public WsClient(CalculatorSoap calculator) {
        this.calculator = calculator;
    }

    public int sum(int a, int b) {
        return calculator.add(a, b);
    }
}
