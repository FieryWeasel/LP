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
public class Rectangle {
    private Point topLeftCorner;
    private int width;
    private int height;
    
    public Rectangle(Point topLeftCorner, int width, int height){
        this.topLeftCorner = topLeftCorner;
        this.width = width;
        this.height = height;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.topLeftCorner);
        hash = 37 * hash + this.width;
        hash = 37 * hash + this.height;
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
        final Rectangle other = (Rectangle) obj;
        if (!Objects.equals(this.topLeftCorner, other.topLeftCorner)) {
            return false;
        }
        if (this.width != other.width) {
            return false;
        }
        if (this.height != other.height) {
            return false;
        }
        return true;
    }
    
    
}
