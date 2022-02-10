package cl.reto.boot.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

import cl.reto.boot.entity.Location;
import cl.reto.boot.entity.Weather;
import cl.reto.boot.repository.IWeatherRepository;

class WeatherServiceImplTest {

	@Mock
	private IWeatherRepository weatherRepo;
	
	@InjectMocks
	private WeatherServiceImpl weatherService;
	
	private List<Weather> lstWeather = new ArrayList<Weather>();
	
	@SuppressWarnings("deprecation")
	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);

		Location loc1 = new Location(1L, 32.4534f, 87.2535f, "Dallas", "Usa");
		Location loc2 = new Location(2L, 33.4534f, 88.2535f, "Buenos Aires", "Argentina");
		Location loc3 = new Location(3L, 34.4534f, 89.2536f, "Rio de Janeiro", "Brazil");
		Location loc4 = new Location(4L, 35.4534f, 90.2536f, "Santiago", "Chile");
		Location loc5 = new Location(5L, 36.4534f, 91.2536f, "Bogota", "Colombia");
		Location loc6 = new Location(6L, 37.4534f, 92.2536f, "Otawa", "Canada");
		Location loc7 = new Location(7L, 38.4534f, 93.2536f, "Madrid", "Espana");

		List<Double> temps1 = Arrays.asList(21.4, 25.6, 28.7, 32.9);
		List<Double> temps2 = Arrays.asList(23.4, 28.6, 38.7, 42.9);
		List<Double> temps3 = Arrays.asList(25.4, 35.6, 28.7, 32.9);
		List<Double> temps4 = Arrays.asList(29.4, 45.6, 28.7, 32.9);
		List<Double> temps5 = Arrays.asList(31.4, 45.6, 28.7, 32.9);
		List<Double> temps6 = Arrays.asList();
		List<Double> temps7 = Arrays.asList(51.4, 65.6, 68.7, 72.9);
		
		Weather weather1 = new Weather(1L, LocalDate.of(2020, 1, 8), temps1, loc1);
		Weather weather2 = new Weather(2L, LocalDate.of(2020, 6, 18), temps2, loc2);
		Weather weather3 = new Weather(3L, LocalDate.of(2020, 10, 28), temps3, loc3);
		Weather weather4 = new Weather(4L, LocalDate.of(2021, 2, 6), temps4, loc4);
		Weather weather5 = new Weather(5L, LocalDate.of(2021, 7, 16), temps5, loc5);
		Weather weather6 = new Weather(6L, LocalDate.of(2021, 11, 26), temps6, loc6);
		Weather weather7 = new Weather(7L, LocalDate.of(2022, 1, 12), temps7, loc7);
		
		lstWeather.add(weather1); //TODO ver como hacerlo en una linea
		lstWeather.add(weather2);
		lstWeather.add(weather3);
		lstWeather.add(weather4);
		lstWeather.add(weather5);
		lstWeather.add(weather6);
		lstWeather.add(weather7);
	}
	
	@Test
	void testFindAll() {
		System.out.println("testFindAll");
		when(weatherRepo.findAll()).thenReturn(lstWeather);
		
		List<Weather> lstWeatherOk = weatherService.findAll();
		
		System.out.println(lstWeatherOk);
		
		assertEquals(7, lstWeatherOk.size());
	}
	
	@Test
	void findWeatherById() {
		System.out.println("findWeatherById");
		Optional<Weather> optWeather = Optional.of(lstWeather.get(0));
		
		when(weatherRepo.findById(1L)).thenReturn(optWeather);
		
		Optional<Weather> realOptWeather = weatherService.findWeatherById(1L);
		
		System.out.println(realOptWeather.get());
		
		assertEquals("Dallas", realOptWeather.get().getLocation().getCity());
	}
	
	@SuppressWarnings("unchecked")
	@Test
	void findWeathersByDate() {
		System.out.println("findWeathersByDate");
		
		List<Weather> lstFiltered = (List<Weather>) lstWeather
														.stream()
														.filter(w -> "2020-01-08".equals(w.getDate().toString()))
														.collect(Collectors.toList());
		
		System.out.println(lstFiltered);
		
		when(weatherRepo.getWeathersByDate(LocalDate.of(2022, 1, 8))).thenReturn(lstFiltered);
		
		List<Weather> realLstWeather = weatherService.findWeathersByDate(LocalDate.of(2022, 1, 8));
		
		System.out.println(realLstWeather);
		
		assertEquals("2020-01-08", realLstWeather.get(0).getDate().toString());
	}

}
