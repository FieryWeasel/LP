/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lp_java01;

import java.math.BigDecimal;
import javax.swing.JOptionPane;

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
        //BigDecimal();
        //Double weight = Double.valueOf(JOptionPane.showInputDialog("Votre masse sur Terre : "));
        //calculWeight(weight);
        maSuperFonction(4);
        maSuperFonction(-2);
        
    }
    
    private static void BigDecimal(){
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
    
    private enum Planet {
        MERCURE  (3.303e+23, 2.4397e6),
        VENUS    (4.869e+24, 6.0518e6),
        TERRE    (5.976e+24, 6.37814e6),
        MARS     (6.421e+23, 3.3972e6),
        JUPITER  (1.9e+27, 7.1492e7),
        SATURN   (5.688e+26, 6.0268e7),
        URANUS   (8.686e+25, 2.5559e7),
        NEPTUNE  (1.024e+26, 2.4746e7);

        private final double mass;  // en kg
        private final double radius; // en mètres
        
        Planet(double mass, double radius) {
            this.mass= mass;
            this.radius= radius;
        }
        private double mass() { return mass; }
        private double radius() { return radius; }

        // Constante gravitationnelle universelle (m3 kg-1 s-2)
        public static final double G= 6.67300E-11;

        public double surfaceGravity() {
            return G* mass/ (radius* radius);
        }

        public double surfaceWeight(double otherMass) {
            return otherMass * surfaceGravity();
        }

    }
    
    private static void calculWeight(double weight){
        Planet[] tabPlanets = Planet.values();
        String msg="";
        
        for(int i = 0 ; i < tabPlanets.length ; i++){
            Planet planet = tabPlanets[i];
            msg+= "Masse sur " + planet.name() + " : " + planet.surfaceWeight(weight)/Planet.TERRE.surfaceGravity() + "\n";
        }
        
        JOptionPane.showMessageDialog(null, msg);

    }
    
    private static void maSuperFonction(int i){
        assert i>0;
        System.out.println(i);
    }
}
