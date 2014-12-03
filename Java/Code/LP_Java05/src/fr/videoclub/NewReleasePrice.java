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
public class NewReleasePrice extends Price{

    @Override
    public int getPriceCode() {
        return Movie.NEW_RELEASE;
    }
    
    @Override
    public double getCharge(int daysRented) {
        return daysRented * 3;
    }

    @Override
    public int frequentPoints(int daysRented) {
        if(daysRented > 1)
            return 2;
        return 1;
    }
    
    
    
}
