package cl.reto.boot.entity;

import lombok.*;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Weather {

	//TODO Revisar longbok getter y setter

	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY) //Comented due to the POST save new weather functionality
	private Long id;
	
	private LocalDate date;

	@ElementCollection
	private List<Double> temperature;
	
	@OneToOne()
	@JoinColumn(referencedColumnName = "id")
	private Location location;

	public String getLowTemp() {
		if(temperature.size() > 0) {
			return temperature.stream().min(Comparator.naturalOrder()).get().toString();
		}
		return null;
	}

	public String getHighTemp() {
		if(temperature.size() > 0) {
			return temperature.stream().max(Comparator.naturalOrder()).get().toString();
		}
		return null;
	}
	
}
