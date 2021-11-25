package com.xy.tools.hotelreservationservice.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xy.tools.hotelreservationservice.persistence.entity.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {

}
