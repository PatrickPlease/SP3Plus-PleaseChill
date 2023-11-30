import java.util.ArrayList;
import java.util.List;

public class TvShow extends Media {
    private final int startYear;
    private final int endYear;
    private final List<Season> seasons;

    public TvShow(String title, int startYear, int endYear, float rating, String genre, int totalSeasons) {
        super(title, startYear, rating, genre, true);
        this.startYear = startYear;
        this.endYear = endYear;
        this.seasons = new ArrayList<>(totalSeasons);
    }

    public void addSeason(int seasonNumber, int episodeCount) {
        Season season = new Season(seasonNumber, episodeCount);
        seasons.add(season);
    }

    public int getStartYear() {
        return startYear;
    }

    public int getEndYear() {
        return endYear;
    }

    @Override
    public int getTimeWatched() {
        // Implement logic to get the time watched for the TV show, if applicable
        return 0;
    }
}

class Season {
    private final int seasonNumber;
    private final int episodeCount;

    public Season(int seasonNumber, int episodeCount) {
        this.seasonNumber = seasonNumber;
        this.episodeCount = episodeCount;
    }

    public int getSeasonNumber() {
        return seasonNumber;
    }

    public int getEpisodeCount() {
        return episodeCount;
    }
}
