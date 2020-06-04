package com.qt;

import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.util.LinkedMultiValueMap;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class AcceptanceTestUtils {
    public static String extractDomainIdFromCreatedResourceAddress(WebTestClient.ResponseSpec responseSpec) {
        return Objects.requireNonNull(responseSpec.returnResult(String.class)
                .getResponseHeaders()
                .get("location")).get(0)
                .split("/.+/")[1];
    }

    public static Map<String, String> ConvertObjectToMap(Object target){
        try{
            Field[] fields = target.getClass().getDeclaredFields();
            Map<String, String> resultMap = new HashMap<>();
            for(int i = 0; i<=fields.length-1; i++){
                fields[i].setAccessible(true);
                resultMap.put(fields[i].getName(), fields[i].get(target).toString());
            }
            return resultMap;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
