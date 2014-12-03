/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lp_java03;

import java.util.Objects;

/**
 *
 * @author Kazuya
 */
public class Personne {
    
    String firstName;
    String lastName;
    String birthDate;
    
    
    public Personne(String firstName, String lastName, String birthDate){
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.firstName);
        hash = 67 * hash + Objects.hashCode(this.lastName);
        hash = 67 * hash + Objects.hashCode(this.birthDate);
        return hash;
    }

    @Override
    public String toString() {
        return "Personne{" + "firstName=" + firstName + ", lastName=" + lastName + ", birthDate=" + birthDate + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Personne other = (Personne) obj;
        if (!Objects.equals(this.firstName, other.firstName)) {
            return false;
        }
        if (!Objects.equals(this.lastName, other.lastName)) {
            return false;
        }
        return Objects.equals(this.birthDate, other.birthDate);
    }
    
    
}
