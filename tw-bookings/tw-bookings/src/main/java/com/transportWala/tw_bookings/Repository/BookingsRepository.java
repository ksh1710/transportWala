package com.transportWala.tw_bookings.Repository;

import com.transportWala.tw_bookings.Entity.Bookings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface BookingsRepository extends JpaRepository<Bookings, Long> {
}
