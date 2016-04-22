package eu.isdc.aircheckapp.domain;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Mihai.Traistaru on 22-Apr-16.
 */
public class GetWeatherByCityResponse {

    @SerializedName("coord")
    private Coord coord;
    @SerializedName("weather")
    private List<WeatherItem> weather;
    @SerializedName("base")
    private String base;
    @SerializedName("main")
    private MainWeatherParams mainWeatherParams;
    @SerializedName("wind")
    private Wind wind;
    @SerializedName("clouds")
    private Clouds clouds;
    @SerializedName("dt")
    private long dt;
    @SerializedName("sys")
    private Sys sys;
    @SerializedName("id")
    private long id;
    @SerializedName("name")
    private String name;
    @SerializedName("cod")
    private int cod;

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    public List<WeatherItem> getWeather() {
        return weather;
    }

    public void setWeather(List<WeatherItem> weather) {
        this.weather = weather;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public MainWeatherParams getMainWeatherParams() {
        return mainWeatherParams;
    }

    public void setMainWeatherParams(MainWeatherParams mainWeatherParams) {
        this.mainWeatherParams = mainWeatherParams;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public long getDt() {
        return dt;
    }

    public void setDt(long dt) {
        this.dt = dt;
    }

    public Sys getSys() {
        return sys;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }
}
