package cl.reto.boot.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cl.reto.boot.entity.Weather;

@Repository
public interface IWeatherRepository extends CrudRepository<Weather, Long> {
	
	@Query("select w from Weather w where w.date = ?1")
	public List<Weather> getWeathersByDate(LocalDate date);
	
	@Query("select w from Weather w where w.date BETWEEN :inidate AND :enddate")
	public List<Weather> getWeatherBetweenDates(LocalDate inidate, LocalDate enddate);
	
}
