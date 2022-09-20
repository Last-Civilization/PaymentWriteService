package com.lastcivilization.paymentwriteservice.utils;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@TestConfiguration
class WireMockConfiguartion {

    @Bean(initMethod = "start", destroyMethod = "stop")
    public WireMockServer wireMockServer(){
        WireMockServer wireMockServer = new WireMockServer(9561);
        mockUser(wireMockServer);
        return wireMockServer;
    }

    private void mockUser(WireMockServer wireMockServer){
        wireMockServer.stubFor(get(WireMock.urlEqualTo("/users/1"))
                .willReturn(aResponse()
                        .withStatus(OK.value())
                        .withHeader("Content-Type", APPLICATION_JSON_VALUE)
                        .withBody("{ \"keycloakId\":\"b28f7e5a-2622-4721-9369-5f0c899effc9\",\"login\":\"test\",\"email\":\"kwolny31@gmail.com\"," +
                                "\"stats\":0,\"equipment\":0,\"account\":1}")));
        wireMockServer.stubFor(get(WireMock.urlEqualTo("/users/3"))
                .willReturn(aResponse()
                        .withStatus(OK.value())
                        .withHeader("Content-Type", APPLICATION_JSON_VALUE)
                        .withBody("{ \"keycloakId\":\"b28f7e5a-2622-4721-9369-5f0c899effc9\",\"login\":\"test\",\"email\":\"kwolny31@gmail.com\"," +
                                "\"stats\":0,\"equipment\":0,\"account\":3}")));
        wireMockServer.stubFor(get(WireMock.urlEqualTo("/users/2"))
                .willReturn(aResponse()
                        .withStatus(NOT_FOUND.value())));
    }
}
