/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package keeper;

import entity.Buyer;
import entity.Purchase;
import entity.Shoes;
import entity.Shop;
import interfaces.Keeping;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author pupil
 */
public class BaseKeeper implements Keeping{
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaApplication16PU");
    private EntityManager em = emf.createEntityManager();
    private EntityTransaction tx = em.getTransaction();

    @Override
    public void saveShoes(List<Shoes> shoes) {
        tx.begin();
        for (int i=0; i<shoes.size(); i++){
            if(shoes.get(i).getId()==null){
                em.persist(shoes.get(i));
            }
        }
        tx.commit();
    }

    @Override
    public List<Shoes> loadShoes() {
        List<Shoes> shoes = null;
        try{
            shoes = em.createQuery("SELECT shoes from Shoes shoes")
                    .getResultList();            
        }catch (Exception e){
            shoes = new ArrayList<>();
        }
        return shoes;
    }

    @Override
    public void saveBuyers(List<Buyer> buyers) {
        tx.begin();
        for (int i=0; i<buyers.size(); i++){
            if(buyers.get(i).getId()==null){
                em.persist(buyers.get(i));
            }
        }
        tx.commit();
    }

    @Override
    public List<Buyer> loadBuyers() {
        List<Buyer> buyers = null;
        try{
            buyers = em.createQuery("SELECT buyer FROM Buyer buyer").getResultList();            
        }catch (Exception e){
            buyers = new ArrayList<>();
        }
        return buyers;
    }

    @Override
    public void savePurchases(List<Purchase> purchases) {
        tx.begin();
        for (int i=0; i<purchases.size(); i++){
            if(purchases.get(i).getId()==null){
                em.persist(purchases.get(i));
            }
        }
        tx.commit();
    }

    @Override
    public List<Purchase> loadPurchases() {
        List<Purchase> purchases = null;
        try{
            purchases = em.createQuery("SELECT purchase FROM Purchase purchase")
                    .getResultList();            
        }catch (Exception e){
            purchases = new ArrayList<>();
        }
        return purchases;
    }

    @Override
    public void saveShop(List<Shop> shops) {
        tx.begin();
        for (int i=0; i<shops.size(); i++){
            if(shops.get(i).getId()==null){
                em.persist(shops.get(i));
            }
        }
        tx.commit();
    }

    @Override
    public List<Shop> loadShop() {
        List<Shop> shops = null;
        try{
            shops = em.createQuery("SELECT shop FROM Shop shop")
                    .getResultList();            
        }catch (Exception e){
            shops = new ArrayList<>();
        }
        return shops;
    }
}
