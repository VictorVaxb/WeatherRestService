CREATE TABLE IF NOT EXISTS WEATHER (
    ID BIGINT PRIMARY KEY, 
    DATE DATE,
    LOCATION_ID BIGINT
);

CREATE TABLE IF NOT EXISTS LOCATION (
    ID BIGINT PRIMARY KEY, 
    CITY VARCHAR(255),
    LAT FLOAT,
    LON FLOAT,
    STATE VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS WEATHER_TEMPERATURE (
    WEATHER_ID BIGINT, 
    TEMPERATURE DOUBLE
);