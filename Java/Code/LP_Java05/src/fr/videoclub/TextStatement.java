/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.videoclub;

/**
 *
 * @author Kazuya
 */
public class TextStatement extends Statement{

    public String statement(Customer customer) {
        int frequentRenterPoints = 0;
        String result = "Rental Record for " + customer.getName() + "\n";
        for (Rental each : customer._rentals) {
            frequentRenterPoints += each.getFrequentPoint();
            // show figures for this rental
            result += "\t" + each.getMovie().getTitle() + "\t" + String.valueOf(each.getCharge()) + "\n";
        }
        // add footer lines
        result += "Amount owed is " + String.valueOf(customer.getTotalCharge()) + "\n";
        result += "You earned " + String.valueOf(customer.getTotalFrequentRenterPoints()) + " frequent renter points";
        return result;
    }
    
}
