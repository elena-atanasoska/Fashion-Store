package onlineshopapp.fashionstore.repository;
import onlineshopapp.fashionstore.model.Event;
import onlineshopapp.fashionstore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByStartGreaterThanEqualAndFinishLessThanEqual(LocalDateTime start, LocalDateTime end);

    @Query("select b from Event b where b.start >= ?1 and b.finish <= ?2")
    List<Event> findByDateBetween(LocalDateTime start, LocalDateTime end);

    List<Event> findAllByUser(User user);

}