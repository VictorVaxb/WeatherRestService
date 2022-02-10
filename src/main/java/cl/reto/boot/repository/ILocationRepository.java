package cl.reto.boot.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cl.reto.boot.entity.Location;

@Repository
public interface ILocationRepository extends CrudRepository<Location, Long> {

}
