package hu.byhi.example.covidapp.repository;

import hu.byhi.example.covidapp.model.StatusEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository extends CrudRepository<StatusEntity, Long> {
}
