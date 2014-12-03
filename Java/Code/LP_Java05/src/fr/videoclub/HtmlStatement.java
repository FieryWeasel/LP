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
public class HtmlStatement extends Statement{

    public String htmlStatement(Customer customer) {
        String result = "<META HTTP-EQUIV=\"Content-Type\" CONTENT=\"text/html; charset=UTF-8\">";
        result += "<H1>Rentals for <EM>" + customer.getName() + "</EM></ H1><P>\n";
        for (Rental each : customer._rentals) {
            //show figures for each rental
            result += each.getMovie().getTitle() + ": " + String.valueOf(each.getCharge()) + "<BR>\n";
        }
        //add footer lines
        result += "<P>You owe <EM>" + String.valueOf(customer.getTotalCharge()) + "</EM><P>\n";
        result += "On this rental you earned <EM>" + String.valueOf(customer.getTotalFrequentRenterPoints()) + "</EM> frequent renter points<P>";
        return result;
    }
    
}
