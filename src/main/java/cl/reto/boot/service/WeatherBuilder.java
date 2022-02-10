package cl.reto.boot.service;

import cl.reto.boot.entity.Weather;

public class WeatherBuilder {

    public static Weather report(Weather weather){

        return Weather.builder()
                .id(weather.getId())
                .date(weather.getDate())
                .location(weather.getLocation())
                .temperature(weather.getTemperature())
                .build();

    }

}
