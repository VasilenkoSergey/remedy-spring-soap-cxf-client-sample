package io.vasilenko.remedy.spring.soap.cxfclient.sample.config;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.tempuri.CalculatorSoap;

@Configuration
public class SoapConfig {

    @Value("${soap.address}")
    private String address;

    @Bean
    public CalculatorSoap calculatorSoap() {
        JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();
        jaxWsProxyFactoryBean.setServiceClass(CalculatorSoap.class);
        jaxWsProxyFactoryBean.setAddress(address);

        return (CalculatorSoap) jaxWsProxyFactoryBean.create();
    }
}
