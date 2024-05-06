package ch.selise.todo.controller;

import ch.selise.todo.dto.UserCreateDTO;
import ch.selise.todo.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Rhidoy
 * @created 14/07/2023
 * @package ch.selise.todo.controller
 */
@SpringBootTest
@AutoConfigureMockMvc
public class AdminControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;

    @Test
    void getUser() throws Exception {
        mockMvc.perform(get("/admin/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalRecords").isNotEmpty());
    }

    @Test
    void addUser() throws Exception {
        UserCreateDTO user = new UserCreateDTO();
        user.setEmail("example@gmail.com");
        user.setGender(User.Gender.MALE);
        user.setPassword("111111");
        user.setRepeatPassword("111111");
        user.setUsername("usertest");
        user.setFirstName("User");
        user.setLastName("Test");
        user.setDateOfBirth(OffsetDateTime.of(1993,11,7, 0,0,0,0, ZoneOffset.UTC));

        mockMvc.perform(post("/admin/users")
                        .content(objectMapper.writeValueAsString(user))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.username").value("usertest"));
    }
}
