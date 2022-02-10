package cl.reto.boot;


import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import cl.reto.boot.entity.Location;
import cl.reto.boot.entity.Weather;
import cl.reto.boot.repository.ILocationRepository;
import cl.reto.boot.repository.IWeatherRepository;



@SpringBootApplication
public class WeatherRestServiceApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(WeatherRestServiceApplication.class, args);

		/*
		ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(WeatherRestServiceApplication.class, args);
		
		ILocationRepository locationRepository = configurableApplicationContext.getBean(ILocationRepository.class);
		IWeatherRepository weatherRepository = configurableApplicationContext.getBean(IWeatherRepository.class);

		Location loc1 = new Location(1L, 32.4534f, 87.2535f, "Dallas", "Usa");
		Location loc2 = new Location(2L, 33.4534f, 88.2535f, "Buenos Aires", "Argentina");
		Location loc3 = new Location(3L, 34.4534f, 89.2536f, "Rio de Janeiro", "Brazil");
		Location loc4 = new Location(4L, 35.4534f, 90.2536f, "Santiago", "Chile");
		Location loc5 = new Location(5L, 36.4534f, 91.2536f, "Bogota", "Colombia");
		Location loc6 = new Location(6L, 37.4534f, 92.2536f, "Otawa", "Canada");
		Location loc7 = new Location(7L, 38.4534f, 93.2536f, "Madrid", "Espana");
		
		locationRepository.save(loc1);
		locationRepository.save(loc2);
		locationRepository.save(loc3);
		locationRepository.save(loc4);
		locationRepository.save(loc5);
		locationRepository.save(loc6);
		locationRepository.save(loc7);
		
		List<Double> temps1 = Arrays.asList(21.4, 25.6, 28.7, 32.9);
		List<Double> temps2 = Arrays.asList(23.4, 28.6, 38.7, 42.9);
		List<Double> temps3 = Arrays.asList(25.4, 35.6, 28.7, 32.9);
		List<Double> temps4 = Arrays.asList(29.4, 45.6, 28.7, 32.9);
		List<Double> temps5 = Arrays.asList(31.4, 45.6, 28.7, 32.9);
		List<Double> temps6 = Arrays.asList();
		List<Double> temps7 = Arrays.asList(51.4, 65.6, 68.7, 72.9);
		
		Weather weather = new Weather(1L, LocalDate.of(2020, 1, 8), temps1, loc1);
		Weather weather2 = new Weather(2L, LocalDate.of(2020, 6, 18), temps2, loc2);
		Weather weather3 = new Weather(3L, LocalDate.of(2020, 10, 28), temps3, loc3);
		Weather weather4 = new Weather(4L, LocalDate.of(2021, 2, 6), temps4, loc4);
		Weather weather5 = new Weather(5L, LocalDate.of(2021, 7, 16), temps5, loc5);
		Weather weather6 = new Weather(6L, LocalDate.of(2021, 11, 26), temps6, loc6);
		Weather weather7 = new Weather(7L, LocalDate.of(2022, 1, 12), temps7, loc7);
		
		weatherRepository.save(weather);
		weatherRepository.save(weather2);
		weatherRepository.save(weather3);
		weatherRepository.save(weather4);
		weatherRepository.save(weather5);
		weatherRepository.save(weather6);
		weatherRepository.save(weather7);
		 */

		/*
		 * 
		 Luego de iniciar checkear el registro guardado en:
		 http://localhost:8081/h2
		 * 
		 */
		
	}
	

}
