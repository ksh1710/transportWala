package com.transportWala.tw_driver.Repository;

import com.transportWala.tw_driver.Entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {
}
