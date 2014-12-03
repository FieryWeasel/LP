package fr.videoclub;

class Rental {

    private Movie _movie;
    private int _daysRented;

    public Rental(Movie movie, int daysRented) {
        _movie = movie;
        _daysRented = daysRented;
    }

    public int getDaysRented() {
        return _daysRented;
    }

    public Movie getMovie() {
        return _movie;
    }
    

    double getCharge() {
        int daysRented = this.getDaysRented();
        return _movie.getCharge(daysRented);
    }

    int getFrequentPoint() {
        return _movie.getFrequentPoint(this.getDaysRented());
    }
}
