package com.trips.preset2.util.security.encoder;

import com.trips.preset2.util.security.encoder.properties.PasswordEncoderProperties;
import com.trips.preset2.util.security.encoder.type.EncoderType;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public final class PasswordEncoderFactory {
    private final PasswordEncoderProperties properties;

    public PasswordEncoder defaultEncoder() {
        return getEncoder(properties.defaultEncoder());
    }

    public PasswordEncoder getEncoder(@NonNull EncoderType encoder) {
        return switch(encoder) {
            case BCRYPT -> createBcrypt();
            case SCRYPT -> createScrypt();
            case PBKDF2 -> createPbkdf2();
        };
    }

    public PasswordEncoder getEncoderWithBracketExpression(@NonNull String encoderExpression) {
        EncoderType encoderType = EncoderType.findByPrefixExpression(encoderExpression);
        return getEncoder(encoderType);
    }

    private BCryptPasswordEncoder createBcrypt() {
        int costFactor = properties.bcrypt().costFactor();
        return new BCryptPasswordEncoder(costFactor);
    }

    private SCryptPasswordEncoder createScrypt() {
        int cpuCost = properties.scrypt().cpuCost();
        int memoryCost = properties.scrypt().memoryCost();
        int parallelism = properties.scrypt().parallelism();
        int keyLength = properties.scrypt().keyLength();
        int saltLength = properties.scrypt().saltLength();

        return new SCryptPasswordEncoder(
                cpuCost,
                memoryCost,
                parallelism,
                keyLength,
                saltLength);
    }

    private Pbkdf2PasswordEncoder createPbkdf2() {
        String secretKey = properties.pbkdf2().secret();
        Integer saltLength = properties.pbkdf2().saltLength();
        Integer costFactor = properties.pbkdf2().costFactor();
        SecretKeyFactoryAlgorithm algorithm = properties.pbkdf2().secretKeyFactoryAlgorithm();

        Pbkdf2PasswordEncoder encoder = new Pbkdf2PasswordEncoder(secretKey, saltLength, costFactor);
        encoder.setAlgorithm(algorithm);
        return encoder;
    }
}