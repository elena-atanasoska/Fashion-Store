package onlineshopapp.fashionstore.web.controller;

import onlineshopapp.fashionstore.model.Event;
import onlineshopapp.fashionstore.model.exceptions.BadDateFormatException;
import onlineshopapp.fashionstore.repository.EventRepository;
import onlineshopapp.fashionstore.service.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@RestController
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @RequestMapping(value="/allevents", method=RequestMethod.GET)
    public List<Event> allEvents() {
        return eventService.findAll();
    }

    @RequestMapping(value="/event", method=RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Event addEvent(@RequestBody Event event) {
        Event created = eventService.save(event);
        return created;
    }

    @RequestMapping(value="/event", method=RequestMethod.PATCH)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Event updateEvent(@RequestBody Event event) {
        return eventService.save(event);
    }

    @RequestMapping(value="/event", method=RequestMethod.DELETE)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void removeEvent(@RequestBody Event event) {
        eventService.deleteEvent(event);
    }

    @GetMapping("/events")
    public List<Event> getEventsInRange(@RequestParam(value = "start", required = true) String start,
                                        @RequestParam(value = "end", required = true) String end,
                                        HttpServletRequest request) {
        Date startDate = null;
        Date endDate = null;
        SimpleDateFormat inputDateFormat=new SimpleDateFormat("yyyy-MM-dd");

        try {
            startDate = inputDateFormat.parse(start);
        } catch (ParseException e) {
            throw new BadDateFormatException("bad start date: " + start);
        }

        try {
            endDate = inputDateFormat.parse(end);
        } catch (ParseException e) {
            throw new BadDateFormatException("bad end date: " + end);
        }

        LocalDateTime startDateTime = LocalDateTime.ofInstant(startDate.toInstant(),
                ZoneId.systemDefault());

        LocalDateTime endDateTime = LocalDateTime.ofInstant(endDate.toInstant(),
                ZoneId.systemDefault());

        return this.eventService.findAllByDateBetween(startDateTime, endDateTime);
    }
}
