package com.shoppingCart;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.io.UnsupportedEncodingException;

@Component
public class TestRequestUtils {

    private static final Logger log = LogManager.getLogger(TestRequestUtils.class);
    @Autowired(required = false)
    private MockMvc mockMvc;
    @Autowired(required = false)
    private ObjectMapper objectMapper;

    public static String getResultBody(ResultActions result) throws UnsupportedEncodingException {
        return result.andReturn().getResponse().getContentAsString();
    }

    public ResultActions request(MockHttpServletRequestBuilder mockHttpServletRequestBuilder,
                                 Object body) throws Exception {
        return request(mockHttpServletRequestBuilder, body, null);
    }

    public ResultActions request(MockHttpServletRequestBuilder mockHttpServletRequestBuilder)
            throws Exception {
        return mockMvc.perform(mockHttpServletRequestBuilder);
    }

    public ResultActions request(MockHttpServletRequestBuilder mockHttpServletRequestBuilder,
                                 Object body, String authToken) throws Exception {
        if (body != null) {
            String parsedBody =
                    (body instanceof String) ? (String) body : objectMapper.writeValueAsString(body);
            log.debug("Requesting with body {}", parsedBody);
            mockHttpServletRequestBuilder.
                    content(parsedBody).
                    contentType(MediaType.APPLICATION_JSON);
            if (authToken != null) {
                mockHttpServletRequestBuilder.header(HttpHeaders.AUTHORIZATION, authToken);
            }
        }
        return request(mockHttpServletRequestBuilder);
    }

    public <T> T readValue(String s, Class<T> type) throws JsonProcessingException {
        return objectMapper.readValue(s, type);
    }


    public <T> T getBodyContentAsObject(ResultActions result, Class<T> type)
            throws UnsupportedEncodingException, JsonProcessingException {
        String responseBody = result.andReturn().getResponse().getContentAsString();
        return readValue(responseBody, type);
    }

    public <T> T toList(ResultActions result, TypeReference<T> valueTypeRef)
            throws UnsupportedEncodingException, JsonProcessingException {
        return objectMapper.readValue(result.andReturn().getResponse().getContentAsString(),
                valueTypeRef);
    }

}
