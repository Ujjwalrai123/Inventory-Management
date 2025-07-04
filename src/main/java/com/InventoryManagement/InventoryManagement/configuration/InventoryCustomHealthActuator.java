package com.InventoryManagement.InventoryManagement.configuration;

import com.InventoryManagement.InventoryManagement.model.response.InventoryHealthInfo;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;

@Component
@Endpoint(id="inventory")
public class InventoryCustomHealthActuator {
    private final Instant startTimer=Instant.now();
    @ReadOperation
    public InventoryHealthInfo getCustomTask(){
        long upTime= Duration.between(startTimer,Instant.now()).getSeconds();
        return new InventoryHealthInfo(100,upTime,"Healthy");
    }
}
