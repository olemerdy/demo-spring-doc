package org.lafeuille.demo;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class BaseContractVerifierTest {
    @BeforeEach
    public void setup(@Autowired MockMvc mockMvc) {
        RestAssuredMockMvc.mockMvc(mockMvc);
    }
}
