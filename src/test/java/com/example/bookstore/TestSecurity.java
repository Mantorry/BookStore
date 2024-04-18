package com.example.bookstore;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@DisplayName(value = "Тесты доступа")
class TestSecurity {

    @Autowired private WebApplicationContext context;
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }


    //Тесты с неавторизованными пользователями

    @Test
    @DisplayName(value = "Неавторизованный пользователь на главную (OK)")
    @WithAnonymousUser
    void Anonim_to_Main_isOK() throws Exception {
        mockMvc.perform(get("/")).andExpect(status().isOk());
    }

    @Test
    @DisplayName(value = "Неавторизованный пользователь на страницу входа (OK)")
    @WithAnonymousUser
    void Anonim_to_Login_isOK() throws Exception {
        mockMvc.perform(get("/Login")).andExpect(status().isOk());
    }

    @Test
    @DisplayName(value = "Неавторизованный пользователь на страницу регистрации (OK)")
    @WithAnonymousUser
    void Anonim_to_Registration_isOK() throws Exception {
        mockMvc.perform(get("/Registration")).andExpect(status().isOk());
    }

    @Test
    @DisplayName(value = "Неавторизованный пользователь на страницу книг (Redirection)")
    @WithAnonymousUser
    void Anonim_to_Book_isRedirection() throws Exception {
        mockMvc.perform(get("/Book")).andExpect(status().is3xxRedirection());
    }

    @Test
    @DisplayName(value = "Неавторизованный пользователь на страницу покупателей (Redirection)")
    @WithAnonymousUser
    void Anonim_to_Customer_isRedirection() throws Exception {
        mockMvc.perform(get("/Customer")).andExpect(status().is3xxRedirection());
    }

    @Test
    @DisplayName(value = "Неавторизованный пользователь на страницу продавцов (Redirection)")
    @WithAnonymousUser
    void Anonim_to_Sale_isRedirection() throws Exception {
        mockMvc.perform(get("/Sale")).andExpect(status().is3xxRedirection());
    }

    @Test
    @DisplayName(value = "Неавторизованный пользователь на страницу заказов (Redirection)")
    @WithAnonymousUser
    void Anonim_to_Delivery_isRedirection() throws Exception {
        mockMvc.perform(get("/Delivery")).andExpect(status().is3xxRedirection());
    }



    //Тесты с авторизованными пользователями


    @Test
    @DisplayName(value = "Авторизованный пользователь на главную (OK)")
    @WithMockUser
    void User_to_Main_isOK() throws Exception {
        mockMvc.perform(get("/")).andExpect(status().isOk());
    }

    @Test
    @DisplayName(value = "Авторизованный пользователь на страницу книг (OK)")
    @WithMockUser
    void User_to_Book_isOk() throws Exception {
        mockMvc.perform(get("/Book")).andExpect(status().isOk());
    }

    @Test
    @DisplayName(value = "Авторизованный пользователь на страницу покупателей (OK)")
    @WithMockUser
    void User_to_Customer_isOk() throws Exception {
        mockMvc.perform(get("/Customer")).andExpect(status().isOk());
    }

    @Test
    @DisplayName(value = "Авторизованный пользователь на страницу продавцов (OK)")
    @WithMockUser
    void User_to_Sale_isOk() throws Exception {
        mockMvc.perform(get("/Sale")).andExpect(status().isOk());
    }

    @Test
    @DisplayName(value = "Авторизованный пользователь на страницу заказов (OK)")
    @WithMockUser
    void User_to_Delivery_isOk() throws Exception {
        mockMvc.perform(get("/Delivery")).andExpect(status().isOk());
    }

    @Test
    @DisplayName(value = "Авторизованный пользователь на страницу входа (Forbidden)")
    @WithMockUser
    void User_to_Login_isOk() throws Exception {
        mockMvc.perform(get("/Login")).andExpect(status().isForbidden());
    }

    @Test
    @DisplayName(value = "Авторизованный пользователь на страницу регистрации (Forbidden)")
    @WithMockUser
    void User_to_Registration_isOk() throws Exception {
        mockMvc.perform(get("/Registration")).andExpect(status().isForbidden());
    }





}