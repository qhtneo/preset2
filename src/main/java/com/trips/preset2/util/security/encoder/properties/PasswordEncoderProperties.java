package com.trips.preset2.util.security.encoder.properties;

import com.trips.preset2.util.security.encoder.properties.bcrypt.BcryptProperties;
import com.trips.preset2.util.security.encoder.properties.pbkdf2.Pbkdf2Properties;
import com.trips.preset2.util.security.encoder.properties.scrypt.ScryptProperties;
import com.trips.preset2.util.security.encoder.type.EncoderType;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

@ConfigurationProperties("app.security.password")
@ConfigurationPropertiesBinding
public record PasswordEncoderProperties(
        EncoderType defaultEncoder,
        @NestedConfigurationProperty BcryptProperties bcrypt,
        @NestedConfigurationProperty ScryptProperties scrypt,
        @NestedConfigurationProperty Pbkdf2Properties pbkdf2
) {
    public PasswordEncoderProperties {
        if (defaultEncoder == null) defaultEncoder = EncoderType.BCRYPT;
    }
}
