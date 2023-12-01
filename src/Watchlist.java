import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Watchlist {
    private static TextUI ui = new TextUI();
    StreamingService streaming = new StreamingService();

    public void addToWatchlist(User user) {
        List<Movies> movies = DbIO.readMoviesFromFile();
        String selectedMediaTitle = streaming.searchTerm;
        String watchlistItem = "Watchlist: " + selectedMediaTitle;;

        List<String> userWatchlist = FileIO.readUserWatchlist(user.getUsername());

        if (userWatchlist != null) {
            userWatchlist.add(watchlistItem);

            FileIO.saveUserWatchlist(user.getUsername(), userWatchlist);
            ui.displayMessage("Media is now added to your watchlist!");
            streaming.mainpage();
        } else {
            ui.displayMessage("Failed to add media to watchlist. Please try again.");
        }
    }

    public void displayWatchlist(User user) {
        List<String> userWatchlist = FileIO.readUserWatchlist(user.getUsername());
        if (userWatchlist != null) {
            ui.displayMessage("Movies/series in your watchlist:");
            for (String watchlistItem : userWatchlist) {
                if (watchlistItem.startsWith("Watchlist: ")) {
                    ui.displayMessage(watchlistItem.substring("Watchlist: ".length()));
                }
            }
        }
    }
}