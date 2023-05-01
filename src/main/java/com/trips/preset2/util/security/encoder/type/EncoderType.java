package com.trips.preset2.util.security.encoder.type;

import com.trips.preset2.util.security.encoder.PasswordEncoderFactory;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Objects;

public enum EncoderType {
    BCRYPT("{bcrypt}"),
    PBKDF2("{pbkdf2}"),
    SCRYPT("{scrypt}");

    public final String PREFIX;

    EncoderType(String prefix) {
        assert Arrays.stream(values())
                .noneMatch(item -> Objects.equals(item.PREFIX, prefix))
                : "인코더 유형의 Prefix는 중복이 없도록 작성.";

        PREFIX = prefix;
    }

    public static EncoderType findByPrefixExpression(String bracketExpression) {
        return Arrays.stream(values())
                .filter((encoderType) -> Objects.equals(encoderType.PREFIX, bracketExpression))
                .findAny()
                .orElseThrow(() -> new IllegalStateException(
                        MessageFormat.format(
                                "잘못된 비밀번호 인코더 표현식이 제공됨. {bcrypt} 등의 양식으로 제공하여야 함. 제공된 내용은: {0}, 허용된 목록: {1}",
                                bracketExpression,
                                Arrays.stream(values()).map(item -> item.PREFIX)
                        )
                ));
    }
}
