package com.example.library.global.utils;

import static org.springframework.util.StringUtils.hasText;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.zip.GZIPOutputStream;
import lombok.experimental.UtilityClass;
import org.apache.tomcat.util.codec.binary.Base64;

@UtilityClass
public class CommonUtils {

    /**
     * 문자열을 byte 변환 후 base64 형식으로 인코딩
     * @param str
     * @return
     */
    public String gzipCompress(String str) {

        if(hasText(str)) return "";

        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {

            try(GZIPOutputStream gzipOutputStream = new GZIPOutputStream(byteArrayOutputStream)) {
                gzipOutputStream.write(str.getBytes(StandardCharsets.UTF_8));
            } catch(IOException e) {
                return String.format("Gzip Compress Fail : [%s] : %s", e.getMessage(), str);
            }

            return Base64.encodeBase64String(byteArrayOutputStream.toByteArray());

        } catch (IOException e) {
            return String.format("Gzip Compress Fail : [%s] : %s", e.getMessage(), str);
        }
    }
}
