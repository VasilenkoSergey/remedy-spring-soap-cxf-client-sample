package io.vasilenko.remedy.spring.soap.cxfclient.sample;

import com.bmc.arsys.api.Value;
import com.bmc.arsys.pluginsvr.plugins.ARFilterAPIPlugin;
import com.bmc.arsys.pluginsvr.plugins.ARPluginContext;
import com.bmc.thirdparty.org.slf4j.Logger;
import com.bmc.thirdparty.org.slf4j.LoggerFactory;
import io.vasilenko.remedy.spring.soap.cxfclient.sample.ws.WsClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@ComponentScan
public class SpringSoapCxfClientSamplePlugin extends ARFilterAPIPlugin {

    private final Logger log = LoggerFactory.getLogger(SpringSoapCxfClientSamplePlugin.class);

    private AnnotationConfigApplicationContext applicationContext;
    private WsClient wsClient;

    @Autowired
    public void setWsClient(WsClient wsClient) {
        this.wsClient = wsClient;
    }

    @Override
    public void initialize(ARPluginContext context) {
        applicationContext = new AnnotationConfigApplicationContext(SpringSoapCxfClientSamplePlugin.class);
        applicationContext.getAutowireCapableBeanFactory().autowireBean(this);
        log.info("initialized");
    }

    @Override
    public List<Value> filterAPICall(ARPluginContext arPluginContext, List<Value> inputValues) {
        int a = inputValues.get(0).getIntValue();
        int b = inputValues.get(1).getIntValue();
        int sum = wsClient.sum(a, b);
        log.info("sum: {}", sum);
        List<Value> outputValues = new ArrayList<>();
        outputValues.add(new Value(sum));
        return outputValues;
    }

    @Override
    public void terminate(ARPluginContext context) {
        applicationContext.close();
    }

    public static void main(String[] args) {
        SpringSoapCxfClientSamplePlugin plugin = new SpringSoapCxfClientSamplePlugin();
        plugin.initialize(new ARPluginContext());
        List<Value> values = new ArrayList<>();
        values.add(new Value(3));
        values.add(new Value(3));
        plugin.filterAPICall(new ARPluginContext(), values);
    }
}
