import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MediaApplication {
    private static final TextUI ui = new TextUI();

    public static void tvShowPrinter() {
        List<TvShow> tvShows = DbIO.readTvShowsFromFile();

        ui.displayMessage("TV Shows:");
        int tIndex = 1;
        for (TvShow tvShow : tvShows) {
            ui.displayMessage(tIndex + "." + tvShow.getTitle() + " - release year: " + tvShow.getStartYear() + "-" + tvShow.getEndYear());
            tIndex++;
        }
    }

    public static void moviesPrinter() {
        List<Movies> movies = DbIO.readMoviesFromFile();

        ui.displayMessage("Movies:");
        int mIndex = 1;
        for (Movies movie : movies) {
            ui.displayMessage(mIndex + ". " + movie.getTitle() + " - release year: " + movie.getReleaseYear());
            mIndex++;
        }
    }
}