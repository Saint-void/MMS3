package com.niit.app;

public class FruitColorsApp {
    public static void main(String[] args) {

        String words[] = {"i", "am", "building", "my", "first", "Java", "Arrays", "In", "Jshell", "Close"};

        String colors[] = {"red","black","orange","white","blue","green","purple","pink","navy blue","sharp pink","sharp blue"};

        String fruits[] = {"orange","lemon","apple", "banana", "melon", "berry", "blue berry", "black berry", "guava","tomato"};

        String[][] fruitColors2 = {
                {fruits[0], colors[0]},
                {fruits[1], colors[1]},
                {fruits[2], colors[2]},
                {fruits[3], colors[3]},
                {fruits[4], colors[4]},
                {fruits[5], colors[5]},
                {fruits[6], colors[6]},
                {fruits[7], colors[7]},
                {fruits[8], colors[8]},
                {fruits[9], colors[9]}
        };

        for (int i = 0; i < fruitColors2.length; i++) {
            System.out.println("Fruit & Color: " + fruitColors2[i][0] + " - " + fruitColors2[i][1]);
        }
    }
}