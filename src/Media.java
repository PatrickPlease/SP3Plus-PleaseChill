public abstract class Media {
        private String title;
        private int releaseYear;
        private float rating;
        private String genre;
        private boolean mediaType;


        public Media(String title, int releaseYear, float rating, String genre, boolean mediaType) {
                this.title = title;
                this.releaseYear = releaseYear;
                this.rating = rating;
                this.genre = genre;
                this.mediaType = mediaType;
        }


        public String getTitle() {
                return title;
        }

        public int getReleaseYear() {
                return releaseYear;
        }

        public float
        getRating() {
                return rating;
        }

        public String getGenre() {
                return genre;
        }

        public boolean isMediaType() {
                return mediaType;
        }

        public abstract int getTimeWatched();
}
