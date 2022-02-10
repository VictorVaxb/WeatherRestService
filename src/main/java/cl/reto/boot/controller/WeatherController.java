package cl.reto.boot.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cl.reto.boot.entity.Weather;
import cl.reto.boot.model.Report;
import cl.reto.boot.service.IWeatherService;

import static cl.reto.boot.configuration.Constants.DAYS_BEFORE_START_DATE;

@RestController
@RequestMapping("/api")
public class WeatherController {

	private static final Logger log = LoggerFactory.getLogger(WeatherController.class);

	@Autowired
	private IWeatherService weatherServ;

	private static final String PATH = "/weathers";
	
	//GET all weathers
	@GetMapping(
			value = PATH,
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	@ResponseBody
	public ResponseEntity<?> getAllWeathers(){

		log.info("starting getAllWeathers()");

		List<Weather> lstWeather = weatherServ.findAll();
		
		if(lstWeather != null && lstWeather.size() > 0) {			
			return new ResponseEntity<>(lstWeather, HttpStatus.OK);
		}
		
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}

	//GET a weather by id
	@GetMapping(
			value = PATH + "/{weatherId}",
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	@ResponseBody
	public ResponseEntity<?> getWeatherById(@PathVariable(value = "weatherId") Long weatherId){
		Optional<Weather> weather = weatherServ.findWeatherById(weatherId);
		
		if(weather != null && !Optional.empty().equals(weather)) {
			return new ResponseEntity<>(weather, HttpStatus.OK);
		}
		
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
	
	//GET weathers by date 
	@GetMapping(
			value = PATH,
			params = "date",
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	@ResponseBody
	public ResponseEntity<?> getWeathersByDate(
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
		){
		List<Weather> weathers = weatherServ.findWeathersByDate(date);
		
		if(weathers != null) {
			return new ResponseEntity<>(weathers, HttpStatus.OK);
		}
		
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
	
	//GET weathers by date range
	@GetMapping(
			value = PATH + "/report",
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	@ResponseBody
	public ResponseEntity<?> getWeathersByRangeDate(
			@RequestParam(value="startDate", required=false,
				defaultValue = "#{T(java.time.LocalDate).now().minusDays("+ DAYS_BEFORE_START_DATE +")}")
				@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
			@RequestParam(value = "endDate", required = false,
				defaultValue = "#{T(java.time.LocalDate).now()}")
				@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate
		){
		List<Report> reports = weatherServ.findWeathersByRangeDate(startDate, endDate);
		
		if(reports != null) {
			return new ResponseEntity<>(reports, HttpStatus.OK);
		}
		
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
	
	//POST save weather (if exist do not update)
	@PostMapping(PATH)
	public ResponseEntity<?> saveNewWeather(@RequestBody Weather weather){
		
		log.info("Weather a grabar: " + weather.getId());
		log.info(weather.toString());
		
		Optional<Weather> dbWeather = weatherServ.findWeatherById(weather.getId());
		
		if(dbWeather == null && Optional.empty().equals(dbWeather)) {
			weatherServ.saveNewWeather(weather);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		
		return new ResponseEntity<Void>(HttpStatus.CONFLICT);
	}
	
	//PUT save weather if not exist, update if exist, then return the weather object
	@PutMapping(
			value = PATH,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	public ResponseEntity<?> saveOrUpdateWeather(@RequestBody Weather weather){

		log.info("Weather a grabar o actualizar: " + weather.getId());
		log.info(weather.toString());
		
		weatherServ.saveNewWeather(weather);
		
		return new ResponseEntity<>(weather, HttpStatus.OK);
	}	
	
	//DELETE all weathers
	@DeleteMapping(PATH)
	public ResponseEntity<?> deleteAllWeather(){
		weatherServ.deleteAllWeathers();
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}	
	
	//DELETE weather by id
	@DeleteMapping(PATH + "/{weatherId}")
	public ResponseEntity<?> deleteWeatherById(@PathVariable(value = "weatherId") Long weatherid){
		weatherServ.deleteWeatherById(weatherid);
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	//Just for test
	@GetMapping("/test")
	@ResponseBody
	public ResponseEntity<?> getTest(){
		return new ResponseEntity<>("OK", HttpStatus.OK);
	}
	
}
