import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Scanner;
import java.sql.SQLException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DbIO {

    static TextUI ui = new TextUI();

    static Connection connection;
    {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/my_streaming", "root", "testtest");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void driver() {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * FROM movies");
            while (resultSet.next()) {
                String genre = resultSet.getString("genre");
                String title = resultSet.getString("title");
                String releaseYear = resultSet.getString("releaseYear");
                String rating = resultSet.getString("rating");

                System.out.println(rating + " | Title: " + title + ", Genre: " + genre + "Release Year: " + releaseYear);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static User readUserData(String username) {
        String query = "SELECT * FROM user WHERE username = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String password = resultSet.getString("password");
                return new User(resultSet.getString("username"), password);
            }
        } catch (SQLException e) {
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

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM movies WHERE username = '" + username + "'");

            List<String> watchlist = new ArrayList<>();
            while (resultSet.next()) {
                String genre = resultSet.getString("genre");
                String title = resultSet.getString("title");
                String releaseYear = resultSet.getString("releaseYear");
                String rating = resultSet.getString("rating");
                watchlist.add(rating + " | Title: " + title + ", Genre: " + genre + "Release Year: " + releaseYear);
            }
            return watchlist;
        } catch (SQLException e) {
            throw new RuntimeException("Error executing SQL query", e);
        }
    }


//
    public static void saveUserWatchlist(String username, List<String> watchlist) {
        try (PrintWriter pWriter = new PrintWriter(new FileWriter("data/UserData/" + username + "_UserData.txt"))) {
            for (String watchlistItem : watchlist) {
                pWriter.println(watchlistItem);
            }
        } catch (IOException e) {
            System.out.println("Something is wrong with the Watchlist file: " + e.getMessage());
        }
    }

    public static void addToTvshowWatchlist(User user, String newTvshowID) {
        if (user == null) {
            System.out.println("Invalid user");
            return;
        }

        try {
            String username = user.getUsername();

            String query = "INSERT INTO watched_tvshows (userID, tvshowID) VALUES (?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, newTvshowID);
                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    ui.displayMessage("Successfully added to watchlist");
                } else {
                    System.out.println("Failed to add to watchlist");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error adding to watchlist: " + e.getMessage());
        }
    }

    public static void addToMovieWatchlist(User user, String newMovieID) {
        if (user == null) {
            System.out.println("Invalid user");
            return;
        }

        try {
            String username = user.getUsername();

            String query = "INSERT INTO watched_movies (userID, movieID) VALUES (?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, newMovieID);
                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    ui.displayMessage("Successfully added to watchlist");
                } else {
                    System.out.println("Failed to add to watchlist");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error adding to watchlist: " + e.getMessage());
        }
    }

    public static List<TvShow> readTvShowsFromFile() {
        List<TvShow> tvShows = new ArrayList<>();

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM tvshows")) {

            while (resultSet.next()) {
                String title = resultSet.getString("title");
                String yearRange = resultSet.getString("yearRange");
                String genre = resultSet.getString("genre");
                float rating = resultSet.getFloat("rating");
                String episodeData = resultSet.getString("episodeData");

                String[] years = yearRange.split("-");
                int startYear = Integer.parseInt(years[0].trim());
                int endYear = (years.length > 1) ? Integer.parseInt(years[1].trim()) : startYear;

                TvShow tvShow = new TvShow(title, startYear, endYear, rating, genre, 0); // Assuming totalSeasons is not available in the database

                String[] seasonData = episodeData.split(",");
                for (String episode : seasonData) {
                    String[] episodeInfo = episode.split("-");
                    int seasonNumber = Integer.parseInt(episodeInfo[0].trim());
                    int episodeCount = Integer.parseInt(episodeInfo[1].trim());
                    tvShow.addSeason(seasonNumber, episodeCount);
                }

                tvShows.add(tvShow);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tvShows;
    }

    public static List<Movies> readMoviesFromFile() {
        List<Movies> movies = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("data/100bedstefilm.txt"))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                String title = parts[0].trim();
                int releaseYear = Integer.parseInt(parts[1].trim());
                String genre = parts[2].trim();
                float rating = Float.parseFloat(parts[3].replace(",", ".").trim());

                Movies movie = new Movies(title, releaseYear, rating, genre);
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
}