package cl.reto.boot.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import cl.reto.boot.entity.Weather;
import cl.reto.boot.model.Report;

public interface IWeatherService {
	
	public List<Weather> findAll();
	
	public Optional<Weather> findWeatherById(Long id);
	
	public void deleteAllWeathers();
	
	public void deleteWeatherById(Long id);
	
	public List<Weather> findWeathersByDate(LocalDate date);
	
	public List<Report> findWeathersByRangeDate(LocalDate inidate, LocalDate enddate);
	
	public void saveNewWeather(Weather weather);
	
}
