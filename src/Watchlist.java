import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Watchlist {
    private static TextUI ui = new TextUI();

    public void addMedia(User user, String mediaTitle) {
        String watchlistItem = "Watchlist:" + mediaTitle;
        FileIO.addToWatchlist(user, watchlistItem);
        ui.displayMessage("Media is now added to your watchlist!");
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