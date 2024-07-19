package org.edupro.webapi.util;

import lombok.extern.slf4j.Slf4j;
import org.edupro.webapi.constant.Constant;
import org.edupro.webapi.exception.EduProApiException;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;

@Slf4j
public class CommonUtil {

    public static String getUUID(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public String getAlphaNumericString(int n) {

        // length is bounded by 256 Character
        byte[] array = new byte[256];
        new Random().nextBytes(array);

        String randomString
                = new String(array, Charset.forName("UTF-8"));

        // Create a StringBuffer to store the result
        StringBuffer r = new StringBuffer();

        // remove all spacial char
        String AlphaNumericString
                = randomString
                .replaceAll("[^A-Za-z0-9]", "");

        // Append first 20 alphanumeric characters
        // from the generated random String into the result
        for (int k = 0; k < AlphaNumericString.length(); k++) {

            if (Character.isLetter(AlphaNumericString.charAt(k))
                    && (n > 0)
                    || Character.isDigit(AlphaNumericString.charAt(k))
                    && (n > 0)) {

                r.append(AlphaNumericString.charAt(k));
                n--;
            }
        }

        // return the resultant string
        return r.toString();
    }

    public String getTransactionId() {
        return (String) Objects.requireNonNull(RequestContextHolder.getRequestAttributes()).getAttribute(Constant.TRANSACTION_ID, RequestAttributes.SCOPE_REQUEST);
    }

    public static String encodeImageToBase64(MultipartFile image) {
        try {
            return Base64.getEncoder().encodeToString(image.getBytes());
        } catch (IOException e) {
            log.error("Failed to encode image to Base64 format: {}", e.getMessage());
            throw new EduProApiException("Failed to encode image to Base64 format", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public static String toString(LocalDate date, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return date.format(formatter);
    }
}
