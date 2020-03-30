package SecondHighestScorer;

import java.util.List;

public class SecondHighestScorerPojo {

   private int count;
   private Filters filters;
   private Competition competition;


    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Filters getFilters() {
        return filters;
    }

    public void setFilters(Filters filters) {
        this.filters = filters;
    }

    public Competition getCompetition() {
        return competition;
    }

    public void setCompetition(Competition competition) {
        this.competition = competition;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public Season getSeason() {
        return season;
    }

    public void setSeason(Season season) {
        this.season = season;
    }

    private String lastUpdated;
   Season season;


    private Scorers [] scorers;

    public Scorers[] getScorers() {
        return scorers;
    }

    public void setScorers(Scorers[] scorers) {
        this.scorers = scorers;
    }
}
