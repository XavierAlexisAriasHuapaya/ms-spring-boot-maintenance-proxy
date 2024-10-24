package dev.arias.huapaya.ms_maintenance_proxy.streams;

import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;

import dev.arias.huapaya.ms_maintenance_proxy.models.Master;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class MasterPublisher {

    private final StreamBridge streamBridge;

    // ! kafka-console-consumer --bootstrap-server kafka:9092 --topic consumerReportMaintenance --from-beginning

    public void publisher(String master) {
        log.info("publisher");
        this.streamBridge.send("consumerMaster", master);
        this.streamBridge.send("consumerMaster-in-0", master);
        this.streamBridge.send("consumerMaster-out-0", master);
    }

    // ! Ver topics
    // ! kafka-console-consumer --bootstrap-server kafka:9092 --topic consumerMaintenance --from-beginning

    public Master publisherCb(String master) {
        log.info("publisherCb");
        this.streamBridge.send("consumerMaintenance", master);
        this.streamBridge.send("consumerMaintenance-in-0", master);
        this.streamBridge.send("consumerMaintenance-out-0", master);
        return Master.builder().build();
    }

}
