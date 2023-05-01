package com.trips.preset2.properties.cors;

import com.trips.preset2.properties.cors.allowed.CorsAllowedProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

@ConfigurationProperties("app.security.cors")
@ConfigurationPropertiesBinding
// Record: 생성자(AllArgsConstructor), getter + 불변성, toString, implements Serializable, ...
public record CorsProperties(
        String[] exposedHeaders,
        @NestedConfigurationProperty CorsAllowedProperties allowed
) {
    // 특수한 형태의 생성자(다른 생성자에 끼는 느낌.)
    // 이 안에서는 this.속성 이런 거 안 씀.
    public CorsProperties {
        if (exposedHeaders == null || exposedHeaders.length == 0) {
            exposedHeaders = new String[] {"*"};
        }
    }
}