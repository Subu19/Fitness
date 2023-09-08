package com.telusko.secureapp.Repo;

import com.telusko.secureapp.Entities.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FoodRepo extends JpaRepository<Food,Long> {
    @Query("SELECT f FROM Food f WHERE f.name LIKE %:name%")
    List<Food> searchByName(@Param("name") String name);
    @Query(value = "SELECT * FROM food ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Food findRandomItem();
}
