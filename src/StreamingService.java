import java.util.ArrayList;
import java.util.List;


public class StreamingService {
    //private FileIO io = new FileIO();
    DbIO io = new DbIO();
    Category newCategory = new Category();
    MediaWindow newMediaWindow = new MediaWindow();
    private TextUI ui = new TextUI();
    public List<TvShow> tvShows;
    private List<Movie> movies;
    static ArrayList<User> users = new ArrayList<>();
    private static User loggedInUser;
    private Watchlist watchlist;
    public String searchTerm;

    public void setup() {
        tvShows = io.readTvShowsFromFile();
        movies = io.readMoviesFromFile();
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

    public void mainpage() {
        tvShows = io.readTvShowsFromFile();
        movies = io.readMoviesFromFile();

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

    public void moviepage(){
        tvShows = io.readTvShowsFromFile();
        movies = io.readMoviesFromFile();

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
        tvShows = io.readTvShowsFromFile();

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

        searchTerm =  ui.getInput("Enter the media number you want to play: ");

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
                watchlist.addToWatchlist(loggedInUser);
                break;
            default:
                ui.displayMessage("Invalid choice. Please try again.");
                break;
        }
    }
}


