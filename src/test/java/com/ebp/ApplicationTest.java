package com.ebp;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
@SpringBootTest
@AutoConfigureMockMvc
public class ApplicationTest {
    @Resource
    private PasswordEncoder passwordEncoder;
    @Resource
    private MockMvc mockMvc;
    @Test
    public void passwordEncoderTest() {
        System.out.println(passwordEncoder.encode("a"));
        System.out.println(passwordEncoder.encode("s"));
        System.out.println(passwordEncoder.encode("d"));
    }
    @Test
    public void loginTest() throws Exception {
        mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .post("/auth/login")
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .param("username", "a")
                                .param("password", "a")
                )
                // .andExpect(MockMvcResultMatchers.status().isFound())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }
}