import java.util.ArrayList;
import java.util.List;

public class Category {
    private TextUI ui = new TextUI();

    public List<String> searchByName(String name, List<TvShow> tvShows, List<Movies> movies) {
        List<String> results = new ArrayList<>();
        boolean foundResults = false;

        ui.displayMessage("Search results in TV Shows:");
        for (int i = 0; i < tvShows.size(); i++) {
            if (tvShows.get(i).getTitle().toLowerCase().contains(name.toLowerCase().trim())) {
                String result = (i + 1) + ". " + tvShows.get(i).getTitle() + " - Release year: " + tvShows.get(i).getStartYear() + "-" + tvShows.get(i).getEndYear();
                ui.displayMessage(result);
                results.add(result);
                foundResults = true;
            }
        }

        ui.displayMessage("Search results in Movies:");
        for (int i = 0; i < movies.size(); i++) {
            if (movies.get(i).getTitle().toLowerCase().contains(name.toLowerCase().trim())) {
                String result = (i + 1) + ". " + movies.get(i).getTitle() + " - Release year: " + movies.get(i).getReleaseYear();
                ui.displayMessage(result);
                results.add(result);
                foundResults = true;
            }
        }

        if (!foundResults) {
            ui.displayMessage("No results found for '" + name + "'.");
        }

        return results;

    }


    public List<String> searchByGenre(String genre, List<TvShow> tvShows, List<Movies> movies) {
        List<String> results = new ArrayList<>();
        boolean foundResults = false;

        ui.displayMessage("Search results in TV Shows by Genre:");
        for (int i = 0; i < tvShows.size(); i++) {
            TvShow tvShow = tvShows.get(i);
            if (tvShow.getGenre().toLowerCase().contains(genre.toLowerCase())) {
                String result = (i + 1) + ". " + tvShow.getTitle() + " - Release year: " + tvShow.getStartYear() + "-" + tvShow.getEndYear();
                ui.displayMessage(result);
                results.add(result);
                foundResults = true;
            }
        }

        ui.displayMessage("Search results in Movies by Genre:");
        for (int i = 0; i < movies.size(); i++) {
            Movies movie = movies.get(i);
            if (movie.getGenre().toLowerCase().contains(genre.toLowerCase())) {
                String result = (i + 1) + ". " + movie.getTitle() + " - Release year: " + movie.getReleaseYear();
                ui.displayMessage(result);
                results.add(result);
                foundResults = true;
            }
        }

        if (!foundResults) {
            ui.displayMessage("No results found for '" + genre + "'.");
        }

        return results;
    }

    public List<String> searchByYear(int year, List<TvShow> tvShows, List<Movies> movies) {
        List<String> results = new ArrayList<>();
        boolean foundResults = false;

        ui.displayMessage("Search results in TV Shows by Year:");
        for (int i = 0; i < tvShows.size(); i++) {
            TvShow tvShow = tvShows.get(i);
            if (tvShow.getStartYear() <= year && tvShow.getEndYear() >= year) {
                String result = (i + 1) + ". " + tvShow.getTitle() + " - Release year: " + tvShow.getStartYear() + "-" + tvShow.getEndYear();
                ui.displayMessage(result);
                results.add(result);
                foundResults = true;
            }
        }

        ui.displayMessage("Search results in Movies by Year:");
        for (int i = 0; i < movies.size(); i++) {
            Movies movie = movies.get(i);
            if (movie.getReleaseYear() == year) {
                String result = (i + 1) + ". " + movie.getTitle() + " - Release year: " + movie.getReleaseYear();
                ui.displayMessage(result);
                results.add(result);
                foundResults = true;
            }
        }

        if (!foundResults) {
            ui.displayMessage("No results found for '" + year + "'.");
        }

        return results;
    }
}