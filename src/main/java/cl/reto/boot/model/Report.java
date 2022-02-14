package cl.reto.boot.model;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Report {

	private String cityName;
	private String lowest;
	private String highest;
	private String message;

}
