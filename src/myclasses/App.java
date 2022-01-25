/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myclasses;

import entity.Buyer;
import entity.Purchase;
import entity.Shoes;
import entity.Shop;
import interfaces.Keeping;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TimeZone;
import keeper.FileKeeper;
import keeper.BaseKeeper;

/**
 *
 * @author pupil
 */
public class App {
    public static boolean isBase;
    private Scanner scanner = new Scanner(System.in);
    private Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Tallinn"));
    private List <Shoes> shoes = new ArrayList<>();
    private List <Buyer> buyers = new ArrayList<>();
    private List <Purchase> purchases = new ArrayList<>();
    //private List <Shop> shops = new ArrayList<>();
    private Keeping keeper;
    
        
    public App(){
        if(App.isBase){
            keeper = new BaseKeeper(); //база данных
        }else{
            keeper = new FileKeeper(); //файлы
        }
        shoes = keeper.loadShoes();
        buyers = keeper.loadBuyers();
        purchases = keeper.loadPurchases();
    }
    public void run(){
        String repeat = "y";
        do{
            System.out.println("�������� ������");
            System.out.println("0: END");
            System.out.println("1: �������� �����/�����");
            System.out.println("2: ������ �������(printproduct)");
            System.out.println("3: �������� ����������(owner)");
            System.out.println("4: ������ �����������(listOwner)");
            System.out.println("5: ������ �����(addBuing)");
            System.out.println("6: ������� �������");
            System.out.println("7: ����� ��������(AllMoney)");
            System.out.println("8: �������� �����(addMoneyToOwner)");
            System.out.println("9: ��������� ��������");
            System.out.println("10: ��������� ����������");
                
            int task = getNumber();
            switch(task){
                case 0: 
                    repeat ="q";
                    System.out.println("end");
                    break;
                
                case 1: 
                    addShoes();
                    break;
                    
                case 2:
                    printShoes();
                    break;
                
                case 3:
                    addBuyer();
                    break;
                
                case 4:
                    printBuyers();
                    break;
                    
                case 5:
                    addPurchase();
                    break;
                
                case 6:
                    purchasesHistory();
                    break;
                    
                case 7:
                    shopMoney();
                    break;
                    
                case 8:
                    addMoneyToBuyer();
                    break;
                    
                case 9:
                    updateShoes();
                    break;
                
                case 10:
                    updateBuyer();
                    break;
            }
        }while("y".equals(repeat));
    }
    
    private void addShoes(){
        System.out.println("���������� ������ �����");
        if(quit()) return;
        Shoes shoe = new Shoes();
         System.out.print("�������� ������: ");
        shoe.setName(scanner.nextLine());
        System.out.print("����: ");
        shoe.setPrice(getNumber()*100);
        System.out.print("���-�� �� ������: ");
        shoe.setAmount(getNumber());
        shoes.add(shoe);
        keeper.saveShoes(shoes);
    }
    
    private void addBuyer(){
        System.out.println("���������� ����������");
        if(quit()) return;
        Buyer buyer = new Buyer();
        System.out.print("��� ����������: ");
        buyer.setName(scanner.nextLine());
        System.out.print("������� ����������: ");
        buyer.setPhone(scanner.nextLine());
        System.out.print("����� ����������: ");
        buyer.setMoney(getNumber()*100);
        buyers.add(buyer);
        keeper.saveBuyers(buyers);
    }
    
    private void addPurchase(){
        System.out.println("������� ������");
        if(quit()) return;
        Purchase purchase = new Purchase();
        
        Set<Integer> setNumbersBuyers = printBuyers();
        if(setNumbersBuyers.isEmpty()){
            return;
        } 
        System.out.print("����� ����������: ");
        int buyerNumber = insertNumber(setNumbersBuyers);
        purchase.setBuyer(buyers.get(buyerNumber-1));
        
        Set <Integer> setNumbersShoes = printShoes();
        if(setNumbersShoes.isEmpty()){
            return;
        }
        System.out.print("����� ������: ");
        int shoesNumber = insertNumber(setNumbersShoes);
        purchase.setShoes(shoes.get(shoesNumber-1));


        if(buyers.get(buyerNumber-1).getMoney()>shoes.get(shoesNumber-1).getPrice() && shoes.get(shoesNumber-1).getAmount()>0){
            purchase.setBuyer(buyers.get(buyerNumber-1));
            purchase.setShoes(shoes.get(shoesNumber-1));
            purchase.setBought(true);
            purchase.setPrice(shoes.get(shoesNumber-1).getPrice());
            buyers.get(buyerNumber-1).setMoney(buyers.get(buyerNumber-1).getMoney()-shoes.get(shoesNumber-1).getPrice());
            shoes.get(shoesNumber-1).setAmount(shoes.get(shoesNumber-1).getAmount()-1);
            purchase.setMonth(cal.get(Calendar.MONTH));
            purchase.setYear(cal.get(Calendar.YEAR));
        }else{
            purchase.setBuyer(buyers.get(buyerNumber-1));
            purchase.setShoes(shoes.get(shoesNumber-1));
            purchase.setMonth(cal.get(Calendar.MONTH));
            purchase.setYear(cal.get(Calendar.YEAR));
            purchase.setPrice(shoes.get(shoesNumber-1).getPrice());
            purchase.setBought(false);
        }

        purchases.add(purchase);
        keeper.savePurchases(purchases);
        keeper.saveBuyers(buyers);
        keeper.saveShoes(shoes);
    }

    private Set<Integer> printBuyers(){
        Set<Integer> setNumbersBuyers = new HashSet();
         System.out.println("������ �����������");
        for (int i = 0; i < buyers.size(); i++) {
            if (buyers.get(i)!=null){
                System.out.printf("%d. %s%n", (i+1), buyers.get(i).toString()); 
                setNumbersBuyers.add(i+1);
            }
        }
        if(setNumbersBuyers.isEmpty()){
            System.out.println("������ ����������� ����");
        }
        return setNumbersBuyers;
    }
    
    private Set<Integer> printShoes(){
        Set <Integer> setNumbersShoes = new HashSet();
        System.out.println("������ ������� �����:");
        for (int i = 0; i < shoes.size(); i++) {
            if (shoes.get(i)!=null && shoes.get(i).getAmount()>0){
                System.out.println((i+1)+ " " + shoes.get(i).toString());
                setNumbersShoes.add(i+1);
            }else if(shoes.get(i)!=null){
                System.out.println("%d. %s ��� � �������.");
            }
        }
        return setNumbersShoes;
    }
    
    private int getNumber(){
        do{
            try{
                String strNumber = scanner.nextLine();
                return Integer.parseInt(strNumber);
            }catch(Exception e){
                 System.out.println("���������� ��� ���");
            }
        }while(true);
    }
    
    private boolean quit(){
        System.out.println("����� ��������� �������� ������� \"q\", ��� ����������� ����� ������ ������");
        String quit = scanner.nextLine();
        if("q".equals(quit)) return true;
      return false;
    }
    
    private int insertNumber(Set<Integer> setNumbers){
        do{
            int historyNumber = getNumber();
            if (setNumbers.contains(historyNumber)){
                return historyNumber; 
            }
            System.out.println("�������� ��� ���");
        }while(true);  
    }
    
    private Set<Integer> purchasesHistory(){
        System.out.println("������� �������");
        Set<Integer> setNumberPurchases = new HashSet();
        for (int i = 0; i < purchases.size(); i++) {
            if (purchases.get(i)!=null){
                System.out.printf("%d. ������ %s ����� %s, ������� %s, ���������: %d%n",
                            (i+1),
                            purchases.get(i).getShoes().getName(), 
                            purchases.get(i).getBuyer().getName(),
                            purchases.get(i).isBought(),
                            purchases.get(i).getPrice()/100
                        );
                setNumberPurchases.add(i+1);
            }
        }
        if(setNumberPurchases.isEmpty()){
            System.out.println("������ ������� ����");
        }
        return setNumberPurchases;
    }
    
     private Set<Integer> shopMoney(){
        Set <Integer> setNumberPurchases = new HashSet();
        System.out.print("����� ��������. ������� �����: ");
        int monthMoney = getNumber();
        System.out.print("������� ���: ");
        int yearMoney = getNumber();
        int money=0;
        for (int i = 0; i < purchases.size(); i++) {
            if (purchases.get(i)!=null && 
                    purchases.get(i).getMonth()+1 == monthMoney && 
                    purchases.get(i).getYear()==yearMoney &&
                    purchases.get(i).isBought()==true){
                money = money+purchases.get(i).getPrice();
                setNumberPurchases.add(i+1);
            }
        }
        if(setNumberPurchases.isEmpty()){
            System.out.println("������ ������� ����");
        }else{
            System.out.println("����� �������� �� "+monthMoney+"."+yearMoney +" : " + money/100);
        }
        return setNumberPurchases;
    }
    
    private void addMoneyToBuyer(){
        System.out.println("���������� ������� ����������");
        if(quit()) return;
        
        Set<Integer> setNumbersBuyers = printBuyers();
        if(setNumbersBuyers.isEmpty()){
            return;
        }
        
        System.out.print("����� ����������: ");
        int buyerNumber = insertNumber(setNumbersBuyers);
         System.out.print("������� ����� ����� ��� ����������: ");
        int moneyAdd = scanner.nextInt(); scanner.nextLine();
        buyers.get(buyerNumber-1).setMoney(buyers.get(buyerNumber-1).getMoney()+ moneyAdd*100);
        keeper.saveBuyers(buyers);
    }
    
    private void updateShoes(){
        System.out.println("��������� ������ �����");
        Set <Integer> setNumbersShoes = printShoes();
        if(setNumbersShoes.isEmpty()){//если книг нет то закроется
            return;
        }
         System.out.print("������� ����� ������: ");
        int shoesNumber = insertNumber(setNumbersShoes);
        System.out.println("������������� ��������:" + shoes.get(shoesNumber-1).getName());
        System.out.print("y/n: ");
        String answer = scanner.nextLine();
        if("y".equals(answer)){
            System.out.print("������� ����� ��������: ");
            shoes.get(shoesNumber-1).setName(scanner.nextLine());
        }
        System.out.println("������������� ����:" + shoes.get(shoesNumber-1).getPrice()/100);
        System.out.print("y/n: ");
        answer = scanner.nextLine();
        if("y".equals(answer)){
            System.out.print("������� ����� ����: ");
            shoes.get(shoesNumber-1).setPrice(getNumber()*100);
        }
        System.out.println("�������� ���������� �����������? ������ �� ������: " + shoes.get(shoesNumber-1).getAmount());
        System.out.print("y/n: ");
        answer = scanner.nextLine();
        if("y".equals(answer)){
            System.out.print("������� ����������: ");
            int newAmount;
            do{
                newAmount = getNumber();
                if(newAmount >= 0){
                    break;
                }
                System.out.println("�������� ���");
            }while(true);
            shoes.get(shoesNumber-1).setAmount(newAmount);
        }
        keeper.saveShoes(shoes);  
    }

    private void updateBuyer(){
        System.out.println("��������� ������ ����������");
        if(quit()) return;
        Set <Integer> setNumbersBuyers = printBuyers();
        if(setNumbersBuyers.isEmpty()){
            return;
        }
        System.out.print("������� ����� ���������� �� ������: ");
        int buyerNumber = insertNumber(setNumbersBuyers);
        System.out.println("������������� ���� ��������:" + buyers.get(buyerNumber-1).getName());
        System.out.print("y/n: ");
        String answer = scanner.nextLine();
        if("y".equals(answer)){
            System.out.print("������� ����� ���: ");
            buyers.get(buyerNumber-1).setName(scanner.nextLine());
        }
        System.out.println("������������� �������:" + buyers.get(buyerNumber-1).getPhone());
        System.out.print("y/n: ");
        answer = scanner.nextLine();
        if("y".equals(answer)){
            System.out.print("������� ����� �������: ");
            buyers.get(buyerNumber-1).setPhone(scanner.nextLine());
        }
        System.out.println("�������� ���������� �����:" + buyers.get(buyerNumber-1).getMoney()/100);
        System.out.print("y/n: ");
        answer = scanner.nextLine();
        if("y".equals(answer)){
            System.out.print("������� ����������: ");
            int newMoney;
            do{
                newMoney = getNumber();
                if(newMoney >= 0){
                    break;
                }
                System.out.println("�������� ���");
            }while(true);
            buyers.get(buyerNumber-1).setMoney(newMoney*100);
        }
        keeper.saveBuyers(buyers); 
    }
}