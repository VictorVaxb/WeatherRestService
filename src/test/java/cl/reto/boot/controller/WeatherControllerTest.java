package cl.reto.boot.controller;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.aop.AopAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import cl.reto.boot.entity.Location;
import cl.reto.boot.entity.Weather;
import cl.reto.boot.repository.IWeatherRepository;
import cl.reto.boot.service.IWeatherService;
import cl.reto.boot.service.WeatherServiceImpl;

public class WeatherControllerTest {
	
	private MockMvc mockMvc;
	
	@Mock
	private IWeatherRepository weatherRepo;
	
	@Mock
	private WeatherServiceImpl weatherService;
	
	@InjectMocks
	private WeatherController weatherController;
	
	ObjectMapper objectMapper = new ObjectMapper();
	ObjectWriter objectWriter = objectMapper.writer();
	
	private List<Weather> lstWeather = new ArrayList<Weather>();
	
	@SuppressWarnings("deprecation")
	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(weatherController).build();

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
		
		lstWeather.add(weather1);
		lstWeather.add(weather2);
		lstWeather.add(weather3);
		lstWeather.add(weather4);
		lstWeather.add(weather5);
		lstWeather.add(weather6);
		lstWeather.add(weather7);
	}
	
	@Test
	public void getTest() {
		WeatherController cont = new WeatherController();
		
		ResponseEntity<?> response = cont.getTest();
		
		System.out.println("response.getBody(): " + response.getBody());
		
		assertEquals("OK", response.getBody());
	}

	@Test
	public void getTest2() {
		try {
			mockMvc.perform(MockMvcRequestBuilders
							.get("http://localhost:8081/api/test"))
					.andExpect(status().isOk())
					.andExpect(MockMvcResultMatchers.content().string("OK"));
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}
	}
	
	@Test
	public void testGetAllWeathers() {
		when(weatherService.findAll()).thenReturn(lstWeather);

		try {
			mockMvc.perform(MockMvcRequestBuilders
					.get("http://localhost:8081/api/weathers"))
					.andDo(print())
					.andExpect(status().isOk());
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}
		
	}

	@Test
	public void testGetWeatherById() {
		when(weatherService.findWeatherById(1L)).thenReturn(Optional.ofNullable(lstWeather.get(0)));

		try {
			mockMvc.perform(MockMvcRequestBuilders
					.get("http://localhost:8081/api/weathers/1"))
					.andDo(print())
					.andExpect(jsonPath("$.id", is(1)))
					.andExpect(jsonPath("$location.city", is("Dallas")))
					.andExpect(jsonPath("$location.state", is("Usa")))
					.andExpect(status().isOk());
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}
	}

	@Test
	public void testGetWeathersByDate() {
		when(weatherService.findWeathersByDate(LocalDate.of(2020, 6, 18)))
				.thenReturn((lstWeather.stream().filter(w -> w.getId() == 2).collect(Collectors.toList())));

		try {
			mockMvc.perform(MockMvcRequestBuilders
							.get("http://localhost:8081/api/weathers?date=2020-06-18"))
					.andDo(print())
					.andExpect(jsonPath("$[date]", is("2020-06-18")))
					.andExpect(status().isOk());
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}
	}

}
