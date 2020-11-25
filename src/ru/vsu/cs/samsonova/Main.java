package ru.vsu.cs.samsonova;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int price1 = enterNumber("price of first cake");
        int price2 = enterNumber("price of second cake");
        int moneyAmount = enterNumber("money amount");
        int amountCake1 = enterNumber("amount first cakes");
        int amountCake2 = enterNumber("amount second cakes");
        int amountCakesForBuy = determinePossibleCountCakesForBuy(price1, price2, amountCake1, amountCake2,
                moneyAmount);
        System.out.println("you can buy " + amountCakesForBuy + " cakes");
    }

    private static int determinePossibleCountCakesForBuy(int price1, int price2, int amountCake1, int amountCake2,
                                                         int moneyAmount) {
        if (moneyAmount >= price1 + price2 && amountCake1 > 0 && amountCake2 > 0) {
            return calculateCountCakesForBuy(price1, price2, amountCake1, amountCake2, moneyAmount);
        } else {
            return 0;
        }
    }

    public static int calculateCountCakesForBuy(int price1, int price2, int amountCake1, int amountCake2,
                                                int moneyAmount) {
        int maxPrice;
        int minPrice;
        int countExpensiveCakes;
        int countCheapCakes;
        if (price1 > price2) {
            maxPrice = price1;
            minPrice = price2;
            countExpensiveCakes = amountCake1;
            countCheapCakes = amountCake2;
        } else {
            maxPrice = price2;
            minPrice = price1;
            countExpensiveCakes = amountCake2;
            countCheapCakes = amountCake1;
        }
        if (countCheapCakes * minPrice > moneyAmount - maxPrice) {
            return (moneyAmount - maxPrice) / minPrice + 1;
        } else {
            if (moneyAmount - (maxPrice + minPrice * countCheapCakes) > (countExpensiveCakes - 1) * maxPrice) {
                return countExpensiveCakes + countCheapCakes;
            } else {
                return countCheapCakes + 1 + (moneyAmount - (maxPrice + minPrice * countCheapCakes)) / maxPrice;
            }
        }
    }

    private static int enterNumber(String value) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter " + value + " ");
        return scanner.nextInt();
    }
}
