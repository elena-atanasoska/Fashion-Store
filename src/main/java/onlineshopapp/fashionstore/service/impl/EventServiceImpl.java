package onlineshopapp.fashionstore.service.impl;

import onlineshopapp.fashionstore.model.Event;
import onlineshopapp.fashionstore.model.User;
import onlineshopapp.fashionstore.model.exceptions.UserNotFoundException;
import onlineshopapp.fashionstore.repository.EventRepository;
import onlineshopapp.fashionstore.repository.UserRepository;
import onlineshopapp.fashionstore.service.EventService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    public EventServiceImpl(EventRepository eventRepository, UserRepository userRepository) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Event> findAllByDateBetween(LocalDateTime startDate, LocalDateTime endDate) {
        return this.eventRepository.findByStartGreaterThanEqualAndFinishLessThanEqual(startDate, endDate);
    }

    @Override
    public List<Event> findAll() {
        return this.eventRepository.findAll();
    }

    @Override
    public Event save(Event event) {
        return this.eventRepository.save(event);
    }

    @Override
    public void deleteEvent(Event event) {
        this.eventRepository.delete(event);
    }

    @Override
    public List<Event> findAllByUser(String username) {
        User user = this.userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));

        return this.eventRepository.findAllByUser(user);
    }
}
