public class Main {

    public static void main(String[] args) {
        StreamingService user = new StreamingService();
        DbIO db = new DbIO();
        db.driver();
        //user.setup();
    }
}