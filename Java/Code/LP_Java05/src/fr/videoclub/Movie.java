package fr.videoclub;

public class Movie {

    public static final int CHILDRENS = 2;
    public static final int REGULAR = 0;
    public static final int NEW_RELEASE = 1;
    private String _title;
    private Price price;

    public Movie(String title, int priceCode) {
        _title = title;
        setPriceCode(priceCode);
    }

    public int getPriceCode() {
        return price.getPriceCode();
    }

    public void setPriceCode(int arg) {
        switch (arg) {
            case Movie.CHILDRENS:
                price = new ChildrenPrice();
                break;
            case Movie.NEW_RELEASE:
                price = new NewReleasePrice();
                break;
            case Movie.REGULAR:
                price = new RegularPrice();
                break;
            default:
                throw new IllegalArgumentException("Incorrect price code");
        }
    }

    public String getTitle() {
        return _title;
    }

    double getCharge(int daysRented) {
        return price.getCharge(daysRented);
    }

    public int getFrequentPoint(int daysRented){
        return price.frequentPoints(daysRented);
    }

}
