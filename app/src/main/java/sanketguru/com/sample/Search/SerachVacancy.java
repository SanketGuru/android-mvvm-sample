package sanketguru.com.sample.Search;

/**
 * Created by Bhavesh on 12-12-2017.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SerachVacancy {

    @SerializedName("VacancyID")
    @Expose
    private int vacancyID;
    @SerializedName("lookup_Value")
    @Expose
    private String lookupValue;

    public int getVacancyID() {
        return vacancyID;
    }

    public void setVacancyID(int vacancyID) {
        this.vacancyID = vacancyID;
    }

    public String getLookupValue() {
        return lookupValue;
    }

    public void setLookupValue(String lookupValue) {
        this.lookupValue = lookupValue;
    }

}
