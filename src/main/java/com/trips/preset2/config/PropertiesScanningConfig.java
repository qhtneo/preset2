package com.trips.preset2.config;

import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationPropertiesScan(basePackages = {
        "com.trips.preset2.properties",
        "com.trips.preset2.util"
})
public class PropertiesScanningConfig {
}
