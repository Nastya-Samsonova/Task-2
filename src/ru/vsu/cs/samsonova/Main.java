package ru.vsu.cs.samsonova;

import java.util.Scanner;

public class Main {
    private static int moneyAmount;
    private static int amountMyCakes = 0;

    public static void main(String[] args) {
        int price1 = enterNumber("price of first cake");
        int price2 = enterNumber("price of second cake");
        moneyAmount = enterNumber("money amount");
        int amountCake1 = enterNumber("amount first cakes");
        int amountCake2 = enterNumber("amount second cakes");
        int amountBoughtCakes = calculateAmountBoughtCakes(price1, price2, amountCake1, amountCake2);
        System.out.println("you can buy " + amountBoughtCakes + " cakes");
    }

    private static int calculateAmountBoughtCakes(int price1, int price2, int amountCake1, int amountCake2) {
        if (moneyAmount >= price1 + price2 & amountCake1 > 0 & amountCake2 > 0) {
            return calculateCountBoughtCakes(price1, price2, amountCake1, amountCake2);
        } else {
            return 0;
        }
    }

    private static int calculateCountBoughtCakes(int price1, int price2, int amountCake1, int amountCake2) {
        int maxPrice = Math.max(price1, price2);
        int minPrice = Math.min(price1, price2);
        int countExpensiveCakes = findCountOfExpensiveCakes(price1, price2, amountCake1, amountCake2);
        int countCheapCakes = amountCake1 + amountCake2 - countExpensiveCakes;

        countExpensiveCakes = calculateRemainingCountCakes(maxPrice, 1, countExpensiveCakes);

        if (countCheapCakes * minPrice > moneyAmount) {
            return moneyAmount / minPrice + amountMyCakes;
        } else {
            calculateRemainingCountCakes(minPrice, countCheapCakes, countCheapCakes);
            if (moneyAmount > countExpensiveCakes * maxPrice) {
                return calculateRemainingCountCakes(maxPrice, countExpensiveCakes, countExpensiveCakes) + amountMyCakes;
            } else {
                return moneyAmount / maxPrice + amountMyCakes;
            }
        }
    }

    private static int calculateRemainingCountCakes(int price, int countBoughtCakes, int amountShopCakes) {
        moneyAmount -= price * countBoughtCakes;
        amountMyCakes += countBoughtCakes;
        return amountShopCakes - countBoughtCakes;
    }

    private static int findCountOfExpensiveCakes(int price1, int price2, int amountCake1, int amountCake2) {
        return price1 > price2 ? amountCake1 : amountCake2;
    }

    private static int enterNumber(String value) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter " + value + " ");
        return scanner.nextInt();
    }
}
