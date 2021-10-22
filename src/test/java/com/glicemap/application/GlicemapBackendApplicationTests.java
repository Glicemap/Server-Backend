package com.glicemap.application;

import com.glicemap.repository.MedicRepository;
import com.glicemap.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class GlicemapBackendApplicationTests {

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private MedicRepository medicRepository;

    @Test
    void contextLoads() {
    }

}
