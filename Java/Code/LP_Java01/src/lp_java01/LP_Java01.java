/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lp_java01;

import java.math.BigDecimal;

/**
 *
 * @author Kazuya
 */
public class LP_Java01 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        float money = 1f;
        float candyPrice = 0.1f;
        int candyNumber =0;
        
        while((money-candyPrice) > 0){
            money = money-candyPrice;
            candyPrice+=0.1f;
            candyNumber++;
        }
        
        System.out.println("Money : " + money + " / Candy Number : " + candyNumber);
        
        
        BigDecimal moneyBD = new BigDecimal("1.0");
        BigDecimal candyPriceBD = new BigDecimal("0.1");
        BigDecimal candyNumberBD =new BigDecimal("0");
        BigDecimal minMoney =new BigDecimal("0");
        boolean go = false;
        
        
        while((moneyBD.subtract(candyPriceBD)).compareTo(minMoney) >0){
            
            candyPriceBD.add(new BigDecimal("0.1"));
            candyNumberBD.add(new BigDecimal("1"));
            
        }
        
        System.out.println("Money : " + moneyBD + " / Candy Number : " + candyNumberBD);
        
    }
    
}
