package sanketguru.com.sample.Search;

/**
 * Created by Bhavesh on 12-12-2017.
 */

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SearchVacancyResponce {

    @SerializedName("SerachVacancy")
    @Expose
    private List<SerachVacancy> serachVacancy = null;

    public List<SerachVacancy> getSerachVacancy() {
        return serachVacancy;
    }

    public void setSerachVacancy(List<SerachVacancy> serachVacancy) {
        this.serachVacancy = serachVacancy;
    }
}