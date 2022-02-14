package cl.reto.boot.service;

import cl.reto.boot.entity.Weather;
import cl.reto.boot.model.Report;

import static cl.reto.boot.configuration.Constants.NO_TEMP_MSG;

public class WeatherBuilder {

    public static Report report(Weather w){

        return Report.builder()
                .cityName(w.getCityFromLocation())
                .lowest(w.getLowTemp())
                .highest(w.getHighTemp())
                .message(w.getLowTemp() == null && w.getHighTemp() == null ? NO_TEMP_MSG : "")
                .build();

    }

    public static Weather build(Weather w){

        return Weather.builder()
                .id(w.getId())
                .date(w.getDate())
                .temperature(w.getTemperature())
                .location(w.getLocation())
                .build();

    }

}
