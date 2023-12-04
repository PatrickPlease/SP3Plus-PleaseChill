import java.util.List;

public class Watchlist {

    DbIO io = new DbIO();
    private static TextUI ui = new TextUI();
    StreamingService streaming = new StreamingService();

    public void addToWatchlist(User user) {
        List<Movie> movies = io.readMoviesFromFile();
        String selectedMediaTitle = streaming.searchTerm;
        String watchlistItem = "Watchlist: " + selectedMediaTitle;;

        List<String> userWatchlist = io.readUserWatchlist(user.getUsername());

        if (userWatchlist != null) {
            userWatchlist.add(watchlistItem);

            io.saveUserWatchlist(user.getUsername(), userWatchlist);
            ui.displayMessage("Media is now added to your watchlist!");
            streaming.mainpage();
        } else {
            ui.displayMessage("Failed to add media to watchlist. Please try again.");
        }
    }

    public void displayWatchlist(User user) {
        List<String> userWatchlist = io.readUserWatchlist(user.getUsername());
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