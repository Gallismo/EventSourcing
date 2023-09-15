package ru.almaz.OrderEventSourcing;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import ru.almaz.OrderEventSourcing.controllers.OrderController;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OrderControllerTests {
    private final MockMvc mockMvc;

    @Autowired
    public OrderControllerTests(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Test
    @Order(1)
    public void onEmptyDBShouldReturn404() throws Exception {
        this.mockMvc.perform(get("/1")).andDo(print()).andExpect(status().isNotFound());
    }

    @Test
    @Order(2)
    public void publishingAnyEventToNonExistOrderShouldReturn404() throws Exception {
        String json = "{\"employerId\": 1}";
        MockHttpServletRequestBuilder req = patch("/accept/1").contentType(MediaType.APPLICATION_JSON).content(json);

        this.mockMvc.perform(req).andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @Order(3)
    public void orderRegisterShouldReturn200() throws Exception {
        String json = "{\"clientId\": 1,\"employerId\": 1,\"productId\": 2,\"productPrice\": 5}";
        MockHttpServletRequestBuilder req = post("/").contentType(MediaType.APPLICATION_JSON).content(json);

        this.mockMvc.perform(req).andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @Order(4)
    public void publishingAcceptEventToExistingOrderShouldReturn200() throws Exception {
        String json = "{\"employerId\": 1}";
        MockHttpServletRequestBuilder req = patch("/accept/1").contentType(MediaType.APPLICATION_JSON).content(json);

        this.mockMvc.perform(req).andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @Order(5)
    public void publishingReadyEventToExistingOrderShouldReturn200() throws Exception {
        String json = "{\"employerId\": 1}";
        MockHttpServletRequestBuilder req = patch("/ready/1").contentType(MediaType.APPLICATION_JSON).content(json);

        this.mockMvc.perform(req).andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @Order(6)
    public void publishingGiveEventToExistingOrderShouldReturn200() throws Exception {
        String json = "{\"employerId\": 1}";
        MockHttpServletRequestBuilder req = patch("/give/1").contentType(MediaType.APPLICATION_JSON).content(json);

        this.mockMvc.perform(req).andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @Order(7)
    public void publishingAnyNewEventToExistingAndGivenOrderShouldReturn404() throws Exception {
        String json = "{\"employerId\": 1}";
        MockHttpServletRequestBuilder req = patch("/accept/1").contentType(MediaType.APPLICATION_JSON).content(json);

        this.mockMvc.perform(req).andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @Order(8)
    public void publishingAnyNewEventToExistingAndCanceledOrderShouldReturn404() throws Exception {
        String jsonRegister = "{\"clientId\": 1,\"employerId\": 1,\"productId\": 2,\"productPrice\": 5}";
        MockHttpServletRequestBuilder reqReg = post("/").contentType(MediaType.APPLICATION_JSON).content(jsonRegister);

        this.mockMvc.perform(reqReg).andDo(print())
                .andExpect(status().isOk());

        String jsonCancel = "{\"employerId\": 1, \"cancelReason\": \"Долго ждать\"}";
        MockHttpServletRequestBuilder reqCancel = patch("/cancel/2").contentType(MediaType.APPLICATION_JSON).content(jsonCancel);

        this.mockMvc.perform(reqCancel).andDo(print())
                .andExpect(status().isOk());

        String jsonAny = "{\"employerId\": 1}";
        MockHttpServletRequestBuilder req = patch("/accept/2").contentType(MediaType.APPLICATION_JSON).content(jsonAny);

        this.mockMvc.perform(req).andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @Order(9)
    public void anyNullFieldAtAnyRequestShouldReturn400() throws Exception {
        // Empty client id and product price fields
        String jsonRegister = "{\"employerId\": 1,\"productId\": 2}";
        MockHttpServletRequestBuilder reqReg = post("/").contentType(MediaType.APPLICATION_JSON).content(jsonRegister);

        this.mockMvc.perform(reqReg).andDo(print())
                .andExpect(status().isBadRequest());

        // Empty cancel reason field
        String jsonCancel = "{\"employerId\": 1}";
        MockHttpServletRequestBuilder reqCancel = patch("/cancel/1").contentType(MediaType.APPLICATION_JSON).content(jsonCancel);

        this.mockMvc.perform(reqCancel).andDo(print())
                .andExpect(status().isBadRequest());

        // Empty JSON body
        String jsonAny = "{}";
        MockHttpServletRequestBuilder req = patch("/accept/1").contentType(MediaType.APPLICATION_JSON).content(jsonAny);

        this.mockMvc.perform(req).andDo(print())
                .andExpect(status().isBadRequest());
    }
}
