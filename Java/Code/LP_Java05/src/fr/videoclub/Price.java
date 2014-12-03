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
public abstract class Price {
    
    public abstract int getPriceCode();

    public abstract double getCharge(int daysRented);
    
    public int frequentPoints(int daysRented){
        return 1;
    }
    
}
