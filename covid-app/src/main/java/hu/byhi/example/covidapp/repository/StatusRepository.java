package hu.byhi.example.covidapp.repository;

import hu.byhi.example.covidapp.model.StatusEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface StatusRepository extends CrudRepository<StatusEntity, Long> {

    @Query("SELECT s FROM StatusEntity s WHERE s.lastUpdatedAtApify > :startDate and s.lastUpdatedAtApify < :endDate")
    List<StatusEntity> findAllByDateInterval(@Param("startDate") LocalDateTime intervalStart, @Param("endDate") LocalDateTime intervalEnd);
}
