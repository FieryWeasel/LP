/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Tests;

import java.util.Objects;

/**
 *
 * @author Kazuya
 */
public class Circle {
    private Point center;
    private int radius;
    
    public Circle (Point center, int radius){
        this.center = center;
        this.radius = radius;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.center);
        hash = 59 * hash + this.radius;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Circle other = (Circle) obj;
        if (!Objects.equals(this.center, other.center)) {
            return false;
        }
        if (this.radius != other.radius) {
            return false;
        }
        return true;
    }
    
    
}
