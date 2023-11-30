import java.util.List;
import java.util.Scanner;

public class MediaWindow {
    private static final TextUI ui = new TextUI();
    private final Scanner scanner;

    public MediaWindow() {

        scanner = new Scanner(System.in);
    }

    public void displayMenu() {

        ui.displayMessage("1. Play Media");
        ui.displayMessage("2. Pause Media");
        ui.displayMessage("3. Rewind Media");
        ui.displayMessage("4. Forward Media");
        ui.displayMessage("5. CC");
        ui.displayMessage("6. Exit");
        int choice = scanner.nextInt();

        handleChoice();
    }

    public void handleChoice() {
        int choice = Integer.parseInt(ui.getInput(" 1. Play \n 2. Pause \n 3. Rewind  \n 4. Forward\n 5. CC \n 6. Exit"));
        switch (choice) {
                case 1:
                    play();
                    break;
                case 2:
                    pauseMedia();
                    break;
                case 3:
                    rewindMedia();
                    break;
                case 4:
                    forwardMedia();
                    break;
                case 5:
                    episodeOverview();
                    break;
                case 6:
                    exitMedia();
                    break;
                default:
                    ui.displayMessage("Invalid choice. Please try again.");
                    displayMenu();
            }
        }

    public void playMedia(List<String> searchResults) {
        if (searchResults.isEmpty()) {
            ui.displayMessage("No media to play.");
            displayMenu();
        } else {
            int choice = Integer.parseInt(ui.getInput("Choose media by entering the corresponding number: "));
            if (choice >= 1 && choice <= searchResults.size()) {
                String selectedMedia = searchResults.get(choice - 1);
                ui.displayMessage("Now playing: " + selectedMedia);
                displayMenu();
            } else {
                ui.displayMessage("Invalid choice. Please try again.");
                playMedia(searchResults);
            }
        }
    }

    public void play(){
        ui.displayMessage("Now playing");
        handleChoice();
    }

    public void pauseMedia() {
        ui.displayMessage("Paused.");
        displayMenu();
    }

    public void rewindMedia() {
        ui.displayMessage("Rewinding x2.");
        displayMenu();
    }

    public void forwardMedia() {
        ui.displayMessage("Forwarding 2x.");
        displayMenu();
    }

    public void episodeOverview() {
        ui.displayMessage("List of episodes: ");
        displayMenu();
    }

    public void exitMedia() {
        StreamingService streaming = new StreamingService();
        ui.displayMessage("Exiting media player.");
        streaming.mainpage();
        scanner.close();

    }

    public static void main(String[] args) {
        MediaWindow mediaWindow = new MediaWindow();
        mediaWindow.displayMenu();
    }
}
