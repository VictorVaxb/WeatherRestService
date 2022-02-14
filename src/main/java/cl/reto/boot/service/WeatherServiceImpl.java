package cl.reto.boot.service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.reto.boot.entity.Weather;
import cl.reto.boot.model.Report;
import cl.reto.boot.repository.IWeatherRepository;

import static cl.reto.boot.configuration.Constants.NO_TEMP_MSG;

@Service
public class WeatherServiceImpl implements IWeatherService {

	private static final Logger log = LoggerFactory.getLogger(WeatherServiceImpl.class);

	@Autowired
	private IWeatherRepository weatherRepo;
	
	/*
	 Return all weathers 
	 Sorted by ID in descending order
	 * */
	@Override
	public List<Weather> findAll() {
		List<Weather> lstWeather = (List<Weather>) weatherRepo.findAll();

		lstWeather.stream()
				//.sorted(Comparator.comparing(Weather::getId).reversed()) //No ordena bien
				.map(WeatherBuilder::build)
				.collect(Collectors.toList());

		lstWeather.sort((w1, w2) -> Long.compare(w2.getId(), w1.getId()));

		return lstWeather;
	}

	@Override
	public Optional<Weather> findWeatherById(Long id) {
		return weatherRepo.findById(id);
	}

	@Override
	public void deleteAllWeathers() {
		weatherRepo.deleteAll();
	}

	@Override
	public void deleteWeatherById(Long id) {
		weatherRepo.deleteById(id);
	}

	@Override
	public List<Weather> findWeathersByDate(LocalDate date) {
		return weatherRepo.getWeathersByDate(date);
	}

	/*
	 When startdate is not informed, startdate = enddate - 30 days
	 When enddate is not informed, enddate = today
	 Report example:
	 	Ordered by city name Ascendent
		 	lowest temperature
		 	date lowest
		 	highest temperature
		 	date highest
	 		
	 		if no temperature data: message: No se encontraron datos meteorológicos en el intervalo de fechas solicitado
	 * */
	@Override
	public List<Report> findWeathersByRangeDate(LocalDate startdate, LocalDate enddate) {

		log.info("startdate: " + startdate);
		log.info("enddate: " + enddate);

		List<Report> reportsList = weatherRepo.getWeatherBetweenDates(startdate, enddate)
				.stream()
				.sorted(Comparator.comparing(Weather::getCityFromLocation))
				.map(WeatherBuilder::report)
				.collect(Collectors.toList());

		return reportsList;

	}

	@Override
	public void saveNewWeather(Weather weather) {
		weatherRepo.save(weather);
	}
	
}
