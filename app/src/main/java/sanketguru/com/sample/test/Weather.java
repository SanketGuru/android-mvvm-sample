package sanketguru.com.sample.test;

/**
 * Created by sanket on 12/10/2017.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Weather {

    @SerializedName("dateFormatted")
    @Expose
    private String dateFormatted;
    @SerializedName("temperatureC")
    @Expose
    private Integer temperatureC;
    @SerializedName("summary")
    @Expose
    private String summary;
    @SerializedName("temperatureF")
    @Expose
    private Integer temperatureF;

    public String getDateFormatted() {
        return dateFormatted;
    }

    public void setDateFormatted(String dateFormatted) {
        this.dateFormatted = dateFormatted;
    }

    public Integer getTemperatureC() {
        return temperatureC;
    }

    public void setTemperatureC(Integer temperatureC) {
        this.temperatureC = temperatureC;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Integer getTemperatureF() {
        return temperatureF;
    }

    public void setTemperatureF(Integer temperatureF) {
        this.temperatureF = temperatureF;
    }

}
