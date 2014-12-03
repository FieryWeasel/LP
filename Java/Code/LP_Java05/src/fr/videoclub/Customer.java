package fr.videoclub;

import java.util.*;

public class Customer {

    private String _name;
    List<Rental> _rentals = new ArrayList<>();

    public Customer(String name) {
        _name = name;
    }

    public void addRental(Rental arg) {
        _rentals.add(arg);
    }

    public String getName() {
        return _name;
    }

    public String statement() {
        return new TextStatement().statement(this);

    }

    public double getTotalCharge() {
        double totalAmount = 0;
        for (Rental rental : _rentals) {
            totalAmount += rental.getCharge();
        }
        return totalAmount;
    }

    public int getTotalFrequentRenterPoints() {
        int totalFrequentRenterPoints = 0;
        for (Rental rental : _rentals) {
            totalFrequentRenterPoints += rental.getFrequentPoint();
        }
        return totalFrequentRenterPoints;
    }

    public String htmlStatement() {
        return new HtmlStatement().htmlStatement(this);
    }

}
