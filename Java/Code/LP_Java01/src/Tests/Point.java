/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Tests;

/**
 *
 * @author Kazuya
 */
public class Point {
    
    private final int x;
    private final int y;
    
    public Point(int x, int y){
        this.x=x;
        this.y=y;
    }

    public double distance(Point other){
        final int delatX = x-other.x;
        final int delaty = y-other.y;
        return Math.sqrt(delatX*delatX+delaty*delaty);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.x;
        hash = 89 * hash + this.y;
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
        final Point other = (Point) obj;
        if (this.x != other.x) {
            return false;
        }
        return this.y == other.y;
    }
    
    
    
    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }
    
}
