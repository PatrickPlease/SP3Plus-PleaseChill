import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
/*

public class FileIO {

    public static User readUserData(String username) {
        try (Scanner scanner = new Scanner(new File("data/UserData/" + username + "_UserData.txt"))) {
            if (scanner.hasNextLine()) {
                String password = scanner.nextLine();
                return new User(username, password);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
        return null;
    }

    public static void saveUserData(User user) {
        try (PrintWriter pWriter = new PrintWriter(new FileWriter("data/UserData/" + user.getUsername() + "_UserData.txt", true))) {
            if (user != null) {
                pWriter.println(user.getPassword());
            }
        } catch (IOException e) {
            System.out.println("Something is wrong with the Datafile: " + e.getMessage());
        }
    }

    public static List<String> readUserWatchlist(String username) {
        try (Scanner scanner = new Scanner(new File("data/UserData/" + username + "_UserData.txt"))) {
            List<String> watchlist = new ArrayList<>();
            while (scanner.hasNextLine()) {
                watchlist.add(scanner.nextLine());
            }
            return watchlist;
        } catch (FileNotFoundException e) {
            System.out.println("Watchlist file not found: " + e.getMessage());
        }
        return null;
    }

    public static void saveUserWatchlist(String username, List<String> watchlist) {
        try (PrintWriter pWriter = new PrintWriter(new FileWriter("data/UserData/" + username + "_UserData.txt"))) {
            for (String watchlistItem : watchlist) {
                pWriter.println(watchlistItem);
            }
        } catch (IOException e) {
            System.out.println("Something is wrong with the Watchlist file: " + e.getMessage());
        }
    }

    public static void addToWatchlist(User user, String newWatchlistItem) {
        try (PrintWriter pWriter = new PrintWriter(new FileWriter("data/UserData/" + user.getUsername() + "_UserData.txt", true))) {
            if (user != null) {
                pWriter.println(newWatchlistItem);
            }
        } catch (IOException e) {
            System.out.println("Something is wrong with the Watchlist file: " + e.getMessage());
        }
    }

    public static List<TvShow> readTvShowsFromFile() {
        List<TvShow> tvShows = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("data/100bedsteserie.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                String title = parts[0].trim();
                String yearRange = parts[1].trim(); // assuming year range is the 2nd element
                String genre = parts[2].trim();
                float rating = Float.parseFloat(parts[3].replace(",", ".").trim());

                // Handling episodes
                String[] episodeData = parts[4].split(",");
                int totalSeasons = episodeData.length;

                // Extracting start and end years from the year range
                String[] years = yearRange.split("-");
                int startYear = Integer.parseInt(years[0].trim());

                // Check if there is an end year
                int endYear = (years.length > 1) ? Integer.parseInt(years[1].trim()) : startYear;


                TvShow tvShow = new TvShow(title, startYear, endYear, rating, genre, seasons);

                for (int i = 0; i < totalSeasons; i++) {
                    String[] seasonData = episodeData[i].split("-");
                    int seasonNumber = Integer.parseInt(seasonData[0].trim());
                    int episodeCount = Integer.parseInt(seasonData[1].trim());

                    tvShow.addSeason(seasonNumber, episodeCount);
                }

                tvShows.add(tvShow);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return tvShows;
    }

    public static List<Movie> readMoviesFromFile() {
        List<Movie> movies = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("data/100bedstefilm.txt"))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                String title = parts[0].trim();
                int releaseYear = Integer.parseInt(parts[1].trim());
                String genre = parts[2].trim();
                float rating = Float.parseFloat(parts[3].replace(",", ".").trim());

                Movie movie = new Movie(title, releaseYear, genre, rating);
                movies.add(movie);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return movies;
    }
    public static List<Integer> searchMovieInFile(String filePath, String searchTerm) {
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
*/