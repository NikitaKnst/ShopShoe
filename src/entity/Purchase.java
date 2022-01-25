/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author pupil
 */
@Entity
public class Purchase implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Shoes shoes;
    private Buyer buyer;
    private boolean bought;
    private int price;
    private int month;
    private int year;
    
    public Purchase(){
        
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Shoes getShoes() {
        return shoes;
    }

    public void setShoes(Shoes shoes) {
        this.shoes = shoes;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    public boolean isBought() {
        return bought;
    }

    public void setBought(boolean bought) {
        this.bought = bought;
    }

    @Override
    public String toString() {
        return "Purchase: " + "shoes: " + shoes + ", buyer: " + buyer + ", quantity: " + bought;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + Objects.hashCode(this.id);
        hash = 47 * hash + Objects.hashCode(this.shoes);
        hash = 47 * hash + Objects.hashCode(this.buyer);
        hash = 47 * hash + (this.bought ? 1 : 0);
        hash = 47 * hash + this.price;
        hash = 47 * hash + this.month;
        hash = 47 * hash + this.year;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Purchase other = (Purchase) obj;
        if (this.bought != other.bought) {
            return false;
        }
        if (this.price != other.price) {
            return false;
        }
        if (this.month != other.month) {
            return false;
        }
        if (this.year != other.year) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.shoes, other.shoes)) {
            return false;
        }
        if (!Objects.equals(this.buyer, other.buyer)) {
            return false;
        }
        return true;
    }

       
    
}
