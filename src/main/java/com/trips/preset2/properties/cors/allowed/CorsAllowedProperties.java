package com.trips.preset2.properties.cors.allowed;

import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;

@ConfigurationPropertiesBinding
public record CorsAllowedProperties(
        String[] methods,
        String[] headers,
        String[] origins
) {
    public CorsAllowedProperties {
        if (methods == null || methods.length == 0) {
            methods = new String[] {"*"};
        }
        if (headers == null || headers.length == 0) {
            headers = new String[] {"*"};
        }
        if (origins == null) {
            origins = new String[] {};
        }
    }
}
