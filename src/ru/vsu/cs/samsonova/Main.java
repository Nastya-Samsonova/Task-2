package ru.vsu.cs.samsonova;

import java.util.Scanner;

public class Main {
    private static int moneyAmount;
    private static int amountReceivedCakes = 0;
    public static void main(String[] args) {
	    int price1 = enterNumber("price of first cake");
	    int price2 = enterNumber("price of second cake");
	    moneyAmount = enterNumber("money amount");
	    int amountCake1 = enterNumber("amount first cakes");
	    int amountCake2 = enterNumber("amount second cakes");
	    int countBoughtCakes = calculateReceivedAmountCakes(price1, price2, amountCake1,amountCake2);
        System.out.println("you can buy " + countBoughtCakes + " cakes");
    }

    private static int calculateReceivedAmountCakes(int price1, int price2, int amountCake1, int amountCake2) {
        if (moneyAmount > price1 + price2) {
            return calculateAmountBoughtCakes(price1, price2, amountCake1, amountCake2);
        } else {
          return 0;
        }
    }

    private static int calculateAmountBoughtCakes(int price1, int price2, int amountCake1, int amountCake2) {
        int maxPrice = Math.max(price1, price2);
        int minPrice = Math.min(price1, price2);
        int countExpensiveCakes = findCountOfExpensiveCakes(price1, price2, amountCake1, amountCake2);
        int countCheapCakes = amountCake1 + amountCake2 - countExpensiveCakes;

        countExpensiveCakes = buyCakes(maxPrice,1, countExpensiveCakes);

        if (countCheapCakes * minPrice > moneyAmount) {
            return moneyAmount / minPrice + amountReceivedCakes;
        } else {
            buyCakes(minPrice, countCheapCakes , countCheapCakes);
            if (moneyAmount > countExpensiveCakes * maxPrice){
                return buyCakes(maxPrice, countExpensiveCakes, countExpensiveCakes) + amountReceivedCakes;
            } else {
                return moneyAmount / maxPrice + amountReceivedCakes;
            }
        }
    }

    private static int buyCakes(int price, int amountCakesForSell, int amountShopCakes){
        moneyAmount -= price * amountCakesForSell;
        amountReceivedCakes += amountCakesForSell;
        return amountShopCakes - amountCakesForSell;
    }

    private static int findCountOfExpensiveCakes(int price1, int price2, int amountCake1, int amountCake2) {
        return price1 > price2 ? amountCake1 : amountCake2;
    }

    private static int enterNumber(String value){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter " + value + " ");
        return scanner.nextInt();
    }
}
