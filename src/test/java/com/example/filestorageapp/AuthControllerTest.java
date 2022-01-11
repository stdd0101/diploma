package com.example.filestorageapp;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void loginReturnsUnauthorizedTest() throws Exception {
        this.mockMvc.perform(get("/cloud/login")
                .with(user("someuser").password("somepassword")))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void loginReturnsAuthenticatedTest() throws Exception {

        this.mockMvc.perform(post("/cloud/login")
                .with(user("stdd01@gmail.com").password("stzv78@yandex.ru")))
                .andExpect(authenticated());
    }

    @Test
    public void logoutReturnUnauthenticated() throws Exception {
        this.mockMvc.perform(get("/cloud/logout"))
                .andExpect(unauthenticated());
    }

    @Test
    public void securityRouteReturnsUnauthorizedTest() throws Exception {
        this.mockMvc.perform(get("/cloud/files"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(username = "stdd01@gmail.com", password = "stzv78@yandex.ru", roles = "USER")
    public void authRefreshTokenReturnsOk() throws Exception {
        this.mockMvc.perform(post("/cloud/auth_token/refresh"))
                .andExpect(status().isOk());
    }

}
