package com.sevilla.usermanager.users;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    void signUp() throws Exception {
        MvcResult result = mvc.perform(post("/signup").contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"name\": \"TEST\",\n" +
                        "    \"email\": \"test1@mail.com\",\n" +
                        "    \"password\": \"Pa#12345678\",\n" +
                        "    \"phones\": [" +
                        "      {\n" +
                        "        \"number\": \"1234567\",\n" +
                        "        \"citycode\": \"1\",\n" +
                        "        \"contrycode\": \"593\"\n" +
                        "      }\n" +
                        "    ]\n" +
                        "}"))
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        log.info(content);
    }

    @Test
    void getUser() throws Exception {
        MvcResult result = mvc.perform(get("/user/1").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        String content = result.getResponse().getContentAsString();

        log.info(content);
    }

    @Test
    void getUsers() throws Exception {
        MvcResult result = mvc.perform(get("/users").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        String content = result.getResponse().getContentAsString();

        log.info(content);
    }

    @Test
    void getUserByEmail() throws Exception {
        MvcResult result = mvc.perform(post("/get_user_by_email").contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                         " \"email\": \"test1@mail.com\"\n" +
                         "}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        String content = result.getResponse().getContentAsString();

        log.info(content);
    }

    @Test
    void updateUser() throws Exception {
        MvcResult result = mvc.perform(put("/updateuser").contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"id\": \"111\",\n" +
                        "    \"name\": \"TEST2\",\n" +
                        "    \"email\": \"test2@mail.com\",\n" +
                        "    \"password\": \"Cb#12345678\"\n" +
                        "}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        String content = result.getResponse().getContentAsString();

        log.info(content);
    }

    @Test
    void deleteUser() throws Exception {
        MvcResult result = mvc.perform(delete("/user/\"111\"").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        String content = result.getResponse().getContentAsString();

        log.info(content);
    }
}
