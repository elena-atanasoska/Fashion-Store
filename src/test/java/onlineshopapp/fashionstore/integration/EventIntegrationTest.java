package onlineshopapp.fashionstore.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import onlineshopapp.fashionstore.model.Event;
import onlineshopapp.fashionstore.model.User;
import onlineshopapp.fashionstore.model.enumerations.Role;
import onlineshopapp.fashionstore.service.EventService;
import onlineshopapp.fashionstore.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
public class EventIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private EventService eventService;

    @Autowired
    private ObjectMapper objectMapper;

    private static boolean dataInitialized = false;
    private static User admin;
    private static User user;
    private static Event event1;
    private static Event event2;
    public static List<Event> events;
    public static Date startDate;
    public static Date endDate;
    public static String start;
    public static String end;
    public static SimpleDateFormat inputDateFormat;
    public static LocalDateTime startDateTime;
    public static LocalDateTime endDateTime;
    public static List<Event> eventsInRange;

    @BeforeEach
    public void setup() throws ParseException {
        initData();
    }

    public void initData() throws ParseException {
        if (!dataInitialized) {
            // Mock user and event creation
            admin = userService.register("admin", "admin", "admin", "admin", Role.ROLE_ADMIN, "adminadmin@gmail.com");
            user = userService.register("user", "user", "user", "user", Role.ROLE_USER, "useruser@gmail.com");
            Mockito.when(userService.register("admin", "admin", "admin", "admin", Role.ROLE_ADMIN, "adminadmin@gmail.com")).thenReturn(admin);
            Mockito.when(userService.register("user", "user", "user", "user", Role.ROLE_USER, "useruser@gmail.com")).thenReturn(user);

            start = "2023-09-10";
            end = "2024-09-10";
            inputDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            event1 = new Event("test", "test", LocalDateTime.ofInstant(inputDateFormat.parse(start).toInstant(), ZoneId.systemDefault()), LocalDateTime.ofInstant(inputDateFormat.parse(end).toInstant(), ZoneId.systemDefault()), user);
            event1.setId(1L);

            // Mock eventService
            Mockito.when(eventService.save(Mockito.any())).thenReturn(event1);
            Mockito.when(eventService.findAll()).thenReturn(new ArrayList<>());

            dataInitialized = true;
        }
    }

    @Test
    @WithMockUser(username = "admin", roles = { "ADMIN" })
    public void testAllEvents() throws Exception {
        // Mock the eventService to return events
        List<Event> events = new ArrayList<>();
        events.add(event1);
        Mockito.when(eventService.findAll()).thenReturn(events);

        MockHttpServletRequestBuilder eventRequest = MockMvcRequestBuilders.get("/allevents");

        this.mockMvc.perform(eventRequest)
                .andExpect(status().isOk());
    }


    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void testAddEvent() throws Exception {
        // Mock the eventService to return the saved event
        Mockito.when(eventService.save(Mockito.any())).thenReturn(event1);

        MockHttpServletRequestBuilder eventRequest = MockMvcRequestBuilders.post("/event")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(event1));

        this.mockMvc.perform(eventRequest)
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", roles = { "ADMIN" })
    public void testUpdateEvent() throws Exception {
        // Mock the eventService to return the updated event
        Mockito.when(eventService.save(Mockito.any())).thenReturn(event1);

        MockHttpServletRequestBuilder eventRequest = MockMvcRequestBuilders.patch("/event")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(event1));

        this.mockMvc.perform(eventRequest)
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", roles = { "ADMIN" })
    public void testRemoveEvent() throws Exception {
        // Mock the eventService to perform deletion successfully
        Mockito.doNothing().when(eventService).deleteEvent(Mockito.any());

        MockHttpServletRequestBuilder eventRequest = MockMvcRequestBuilders.delete("/event")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(event1));

        this.mockMvc.perform(eventRequest)
                .andExpect(status().isOk());
    }

}
