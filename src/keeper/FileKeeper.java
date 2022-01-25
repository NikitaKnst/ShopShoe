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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pupil
 */
public class FileKeeper implements Keeping{
    @Override
    public void saveShoes(List<Shoes> shoes) {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream("shoes");//использование текущей директории, букс будет создан в папке проекта
            oos = new ObjectOutputStream(fos);
            oos.writeObject(shoes);
            oos.flush();//проталкивание данных на жесткий диск
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileKeeper.class.getName()).log(Level.SEVERE, "file shoes not found", ex);
        } catch (IOException ex) {
            Logger.getLogger(FileKeeper.class.getName()).log(Level.SEVERE, "input error", ex);
        }
    }

    @Override
    public List<Shoes> loadShoes() {
       List <Shoes> listShoes = new ArrayList();
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = new FileInputStream("shoes");
            ois = new ObjectInputStream(fis);
            listShoes = (List<Shoes>) ois.readObject();
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileKeeper.class.getName()).log(Level.SEVERE, "file shoes not found", ex);
        } catch (IOException ex) {
            Logger.getLogger(FileKeeper.class.getName()).log(Level.SEVERE, "output error", ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FileKeeper.class.getName()).log(Level.SEVERE, "class not found", ex);
        }
        
       return listShoes;
    }

    @Override//переопределение
    public void saveBuyers(List<Buyer> buyers) {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream("buyers");//использование текущей директории, букс будет создан в папке проекта
            oos = new ObjectOutputStream(fos);
            oos.writeObject(buyers);
            oos.flush();//проталкивание данных на жесткий диск
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileKeeper.class.getName()).log(Level.SEVERE, "file buyers not found", ex);
        } catch (IOException ex) {
            Logger.getLogger(FileKeeper.class.getName()).log(Level.SEVERE, "input error", ex);
        }
    }

    @Override
    public List<Buyer> loadBuyers() {
       List <Buyer> listBuyers = new ArrayList();
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = new FileInputStream("buyers");
            ois = new ObjectInputStream(fis);
            listBuyers = (List<Buyer>) ois.readObject();
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileKeeper.class.getName()).log(Level.SEVERE, "file buyers not found", ex);
        } catch (IOException ex) {
            Logger.getLogger(FileKeeper.class.getName()).log(Level.SEVERE, "output error", ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FileKeeper.class.getName()).log(Level.SEVERE, "class not found", ex);
        }
        
       return listBuyers;
    }

    @Override//переопределение
    public void savePurchases(List<Purchase> purchases) {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream("purchases");//использование текущей директории, букс будет создан в папке проекта
            oos = new ObjectOutputStream(fos);
            oos.writeObject(purchases);
            oos.flush();//проталкивание данных на жесткий диск
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileKeeper.class.getName()).log(Level.SEVERE, "file purchases not found", ex);
        } catch (IOException ex) {
            Logger.getLogger(FileKeeper.class.getName()).log(Level.SEVERE, "input error", ex);
        }
    }

    @Override
    public List<Purchase> loadPurchases() {
       List <Purchase> listPurchases = new ArrayList();
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = new FileInputStream("purchases");
            ois = new ObjectInputStream(fis);
            listPurchases = (List<Purchase>) ois.readObject();
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileKeeper.class.getName()).log(Level.SEVERE, "file purchases not found", ex);
        } catch (IOException ex) {
            Logger.getLogger(FileKeeper.class.getName()).log(Level.SEVERE, "output error", ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FileKeeper.class.getName()).log(Level.SEVERE, "class not found", ex);
        }
        
       return listPurchases;
    }

    @Override
    public void saveShop(List<Shop> shops) {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream("shop");//использование текущей директории, букс будет создан в папке проекта
            oos = new ObjectOutputStream(fos);
            oos.writeObject(shops);
            oos.flush();//проталкивание данных на жесткий диск
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileKeeper.class.getName()).log(Level.SEVERE, "file shop not found", ex);
        } catch (IOException ex) {
            Logger.getLogger(FileKeeper.class.getName()).log(Level.SEVERE, "input error", ex);
        }
    }

    @Override
    public List<Shop> loadShop() {
        List <Shop> listShops = new ArrayList();
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = new FileInputStream("shop");
            ois = new ObjectInputStream(fis);
            listShops = (List<Shop>) ois.readObject();
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileKeeper.class.getName()).log(Level.SEVERE, "file shop not found", ex);
        } catch (IOException ex) {
            Logger.getLogger(FileKeeper.class.getName()).log(Level.SEVERE, "output error", ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FileKeeper.class.getName()).log(Level.SEVERE, "class not found", ex);
        }
        
       return listShops;
    }



}
