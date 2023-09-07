package com.telusko.secureapp.Repo;

import com.telusko.secureapp.Entities.DailyC;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
@Repository
public interface DailyCRepo extends JpaRepository<DailyC, Long> {
    @Query("Select d from DailyC d where d.date = :date")
    List<DailyC> getDailyCByDate(@Param("date")Date date);


}
