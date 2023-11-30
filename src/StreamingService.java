import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class StreamingService {
    CreateAccount newAccount;
    Category newCategory = new Category();
    MediaWindow newMediaWindow = new MediaWindow();
    Watchlist list;
    private FileIO io = new FileIO();
    private TextUI ui = new TextUI();
    private List<TvShow> tvShows;
    private List<Movies> movies;
    static ArrayList<User> users = new ArrayList<>();
    private static User loggedInUser;
    private Watchlist watchlist;

    public void setup() {
        tvShows = MediaApplication.readTvShowsFromFile();
        movies = MediaApplication.readMoviesFromFile();
        users = new ArrayList<>();
        Watchlist watchlist = new Watchlist();
        ui.displayMessage("Welcome to PleaseChill, your favorite streaming platform");

        int choice = Integer.parseInt(ui.getInput("Press 1 to Login, Press 2 to Create an account"));

        switch (choice) {
            case 1:
                login();
                break;
            case 2:
                ui.displayMessage("You have chosen to create an account");
                CreateAccount.createUser();
                break;
            default:
                ui.displayMessage("Invalid choice. Please try again.");
                setup();
                break;
        }

        ui.displayMessage("============================================= \n");
        mainpage();
    }

    public void login() {
        String username = ui.getInput("Enter your username: ");
        String password = ui.getInput("Enter your password: ");

        loggedInUser = io.readUserData(username);

        if (loggedInUser != null && loggedInUser.getPassword().equals(password)) {
            ui.displayMessage("Login successful. Welcome back, " + loggedInUser.getUsername() + "!");
        } else {
            ui.displayMessage("Invalid username or password. Please try again.");
            loggedInUser = null; // Set to null if login fails
            login();
        }
    }

    private User findUser(String username, String password, ArrayList<User> users) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    public void mainpage() {
        tvShows = MediaApplication.readTvShowsFromFile();
        movies = MediaApplication.readMoviesFromFile();

        int mainpage = Integer.parseInt(ui.getInput(" 1. Search \n 2. Movies \n 3. Series \n 4. Log out"));
        switch (mainpage) {
            case 1:
                ui.displayMessage("=============================================\n");
                int searchOption = Integer.parseInt(ui.getInput(" 1. Search by name \n 2. Search by genre \n 3. Search by year \n 4. Return "));
                switch (searchOption) {
                    case 1:
                        String nameSearch = ui.getInput("Enter the name to search: ");
                        newCategory.searchByName(nameSearch, tvShows, movies);
                        break;
                    case 2:
                        String genreSearch = ui.getInput("Enter the genre to search: ");
                        newCategory.searchByGenre(genreSearch, tvShows, movies);
                        break;
                    case 3:
                        String yearSearch = ui.getInput("Enter the year to search: ");
                        newCategory.searchByYear(Integer.parseInt(yearSearch), tvShows, movies);
                        break;
                    case 4: //tilbage til valgmulighederne 1. search 2. movies osv..
                        mainpage();
                        break;
                    default:
                        ui.displayMessage("Invalid choice. Please try again.");
                        mainpage();
                        break;
                }
                break;
            case 2:
                moviepage();
                break;
            case 3:
                seriespage();
                break;
            case 4:
                ui.displayMessage("You are now leaving PleaseChill..");
                ui.displayMessage("============================================= \n");
                setup();
                break;
            default:
                ui.displayMessage("Invalid choice. Please try again.");
                mainpage();
                break;
        }
        wannaPlay();
    }

    public void moviepage() {
        tvShows = MediaApplication.readTvShowsFromFile();
        movies = MediaApplication.readMoviesFromFile();

        ui.displayMessage("============================================= \n");
        int moviepage = Integer.parseInt(ui.getInput(" 1. Popular \n 2. Trending \n 3. Genres  \n 4. Recently watched \n 5. Watchlist \n 6. Return "));
        switch (moviepage) {
            case 1:
                MediaApplication.moviesPrinter();
                break;
            case 2:
                ui.displayMessage("============================================= \n");
                break;
            case 3:
                ui.displayMessage("============================================= \n");
                break;
            case 4:
                ui.displayMessage("============================================= \n");
                break;
            case 5:
                ui.displayMessage("============================================= \n");
                watchlist.displayWatchlist(loggedInUser);
                break;
            case 6:
                ui.displayMessage("============================================= \n");
                mainpage();
                break;
            default:
                ui.displayMessage("Invalid choice. Please try again.");
                break;
        }
    }

    public void seriespage() {
        ui.displayMessage("============================================= \n");
        int seriespage = Integer.parseInt(ui.getInput(" 1. Popular \n 2. Trending \n 3. Genres  \n 4. Recently watched \n 5. Watchlist \n 6. Return "));
        switch (seriespage) {
            case 1:
                ui.displayMessage("============================================= \n");
                MediaApplication.tvShowPrinter();
                break;
            case 2:
                ui.displayMessage("============================================= \n");
                break;
            case 3:
                ui.displayMessage("============================================= \n");
                break;
            case 4:
                ui.displayMessage("============================================= \n");
                break;
            case 5:
                ui.displayMessage("============================================= \n");
                break;
            case 6:
                ui.displayMessage("============================================= \n");
                mainpage();
                break;
            default:
                ui.displayMessage("Invalid choice. Please try again.");
                break;
        }
    }


 public void wannaPlay(){
     ui.displayMessage("============================================= \n");

     String searchTerm = ui.getInput("Enter the media number you want to play: ");

     ui.displayMessage("Wanna play media?");
     int wannaPlay = Integer.parseInt(ui.getInput(" 1. Yes \n 2. No \n 3. Add to watchlist\n" ));
     switch (wannaPlay) {
         case 1:
             newMediaWindow.play();
             break;
         case 2:
             mainpage();
             break;
         case 3:
             addToWatchlist(loggedInUser);
             break;
         default:
             ui.displayMessage("Invalid choice. Please try again.");
             break;
     }
 }



    void runStreamingService() {
    }

    public void addToWatchlist(User user) {
        List<Movies> movies = MediaApplication.readMoviesFromFile();
        String selectedMediaTitle = " Movie";
        String watchlistItem = "Watchlist:" + selectedMediaTitle;;


        List<String> userWatchlist = FileIO.readUserWatchlist(user.getUsername());


        if (userWatchlist != null) {
            userWatchlist.add(watchlistItem);


            FileIO.saveUserWatchlist(user.getUsername(), userWatchlist);
            ui.displayMessage("Media is now added to your watchlist!");
            mainpage();
        } else {
            ui.displayMessage("Failed to add media to watchlist. Please try again.");
        }
    }

    public static class SearchInFile {
        public static List<Integer> searchInFile(String filePath, String searchTerm) {
            List<Integer> foundIndexes = new ArrayList<>();

            try (BufferedReader reader = new BufferedReader(new FileReader("data/100bedstefilm.txt"))) {
                String line;
                int currentIndex = 0;

                while ((line = reader.readLine()) != null) {
                    if (line.contains(searchTerm)) {
                        foundIndexes.add(currentIndex);
                    }

                    currentIndex++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return foundIndexes;
        }
    }

    public static User getLoggedInUser() {
        return loggedInUser;
    }
}


// TextUI skal laves om til metode (getInput og displaymessage)
// Alt fra start() skal sættes in Streaming service
// Struktur ud fra klasse diagram

