package onlineshopapp.fashionstore.integration;


import onlineshopapp.fashionstore.model.Order;
import onlineshopapp.fashionstore.model.User;
import onlineshopapp.fashionstore.model.enumerations.OrderStatus;
import onlineshopapp.fashionstore.service.OrderService;
import onlineshopapp.fashionstore.service.UserService;
import onlineshopapp.fashionstore.web.controller.PostmanController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class PostmanIntegrationTest {

    private MockMvc mockMvc;

    @Mock
    private OrderService orderService;

    @Mock
    private UserService userService;

    @InjectMocks
    private PostmanController postmanController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(postmanController).build();
    }

    @Test
    public void testGetPostmanOrders() throws Exception {
        // Create a mock user
        User mockUser = new User();
        mockUser.setUsername("postmanUser");

        // Create a mock list of orders
        List<Order> mockOrders = new ArrayList<>();

        // Mock userService to return the mockUser when loadUserByUsername is called
        when(userService.loadUserByUsername("postmanUser")).thenReturn(mockUser);

        // Mock orderService to return the mockOrders when findOrdersByPostman is called
        when(orderService.findOrdersByPostman(mockUser)).thenReturn(mockOrders);

        // Perform the GET request to /postman/orders
        mockMvc.perform(get("/postman/orders")
                        .with(request -> {
                            request.setRemoteUser("postmanUser");
                            return request;
                        }))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("orders"))
                .andExpect(model().attributeExists("status"))
                .andExpect(view().name("postmanOrders"));

        // Verify that userService.loadUserByUsername and orderService.findOrdersByPostman were called
        verify(userService, times(1)).loadUserByUsername("postmanUser");
        verify(orderService, times(1)).findOrdersByPostman(mockUser);
    }



}
