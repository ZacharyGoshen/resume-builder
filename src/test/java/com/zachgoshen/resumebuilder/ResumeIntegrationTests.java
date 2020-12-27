package com.zachgoshen.resumebuilder;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ResumeIntegrationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void getResumesReturnsValidResponse() throws Exception {
        assertThat(
                this.testRestTemplate.getForEntity(
                        "http://localhost:" + this.port + "/resumes",
                        String.class
                ).getStatusCode()
        ).isEqualTo(HttpStatus.OK);
    }

}
