/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import entity.Buyer;
import entity.Purchase;
import entity.Shoes;
import entity.Shop;
import java.util.List;

/**
 *
 * @author pupil
 */
public interface Keeping {
    public void saveShoes(List<Shoes> shoes);
    public List<Shoes> loadShoes();
    public void saveBuyers(List<Buyer> buyers);
    public List<Buyer> loadBuyers();
    public void savePurchases(List<Purchase> purchases);
    public List<Purchase> loadPurchases();
    public void saveShop(List<Shop> shops);
    public List<Shop> loadShop();
}
