package org.example;

import java.util.Scanner;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static double errorCheck(String q) {
        //method for checking if the input from user is a double which cannot be negative.
        Scanner reader = new Scanner(System.in);
        boolean n = true;
        double val = 0;
        double holder;
        while (n) {
            System.out.println(q);
            String l = reader.next();
            try {
                holder = Double.parseDouble(l);
                val = holder;
                if (val <= 0) {
                    System.out.println("Invalid input. Try again");
                } else {
                    n = false;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Try again");
            }
        }
        return val;
    }
    static int convDouble(String q){
        //method for checking that the value is an integer, so it will be okay to convert from a double.
        double val=0;
        boolean n=true;
        while (n) {
            val = errorCheck(q);
            if (val % 2 == 0 || (val + 1) % 2 == 0) {
                n = false;
            } else {
                System.out.println("Value must be an integer. Try again.");
            }
        }
        return (int) val;
    }
    static double yN(String q,double l){
        //method for changing a variable when the user has input 1 into the console when asked. If 2 is entered it doesn't change the value from the original value.
        boolean n = true;
        int yesNo;
        double redo=0;
        while(n){
            yesNo = convDouble(q);
            if (yesNo == 1){
                redo = errorCheck("Please enter desired value: (do not enter units)");
                n = false;
            } else if (yesNo == 2) {
                redo=l;
                n=false;
            } else{
                System.out.println("Incorrect value entered. Try again.");
            }
        }
        return redo;
    }
    public static void main(String[] args) {
        //Initializing some of the variables we will be using.
        double litrePerMeterSqred = 0.1;
        double priceLitre = 20.0;
        double canLitre = 5;
        boolean n = true;
        String change = "Would you like to use this value? (1 for no or 2 for yes)";
        //using the defined method for yN to ask if they wanted to change variables.
        System.out.println("The standard can of paint holds "+canLitre+"L.");
        canLitre = yN(change,canLitre);
        System.out.println("The standard price of paint for "+canLitre+"L is £"+priceLitre+".");
        priceLitre = yN(change,priceLitre);
        System.out.println("The standard for paint usage is "+litrePerMeterSqred +" litre/m².");
        litrePerMeterSqred = yN(change,litrePerMeterSqred);
        double milToMetre = 0.001;
        double cenToMetre = 0.01;
        int number=0;
        //will use meters for all calculations so changing units depending on what the user inputs.
        String unit="What unit of length would you like to use? (1 for mm, 2 for cm, 3 for m)";
        while(n){
            number=convDouble(unit);
            if ((number == 1) || (number == 2) || (number == 3)) n = false;
            else {
                System.out.println("Incorrect value entered. Try again.");
            }
        }
        String coatsOfPaint = "How many coats of paint do you want to use?";
        int coatsPaint=convDouble(coatsOfPaint);
        String quantityOfWall = "How many walls do you have values for?";
        int quantityWall=convDouble(quantityOfWall);
        double [][] valuesWall = new double [quantityWall][2];
        double [] areaWall = new double [quantityWall];
        for (int i=0 ; i<quantityWall; i++) {
            String lengthOfWall = "What is the length of wall " + (i + 1) + "?";
            double lengthWall = errorCheck(lengthOfWall);
            valuesWall[i][0] = lengthWall;
            String heightOfWall = "What is the height of the wall " + (i + 1) + "?";
            double heightWall = errorCheck(heightOfWall);
            valuesWall[i][1] = heightWall;
            switch(number){
                case 1:
                    valuesWall[i][0]= valuesWall[i][0] * milToMetre;
                    valuesWall[i][1] = valuesWall[i][1] * milToMetre;
                    break;
                case 2:
                    valuesWall[i][0]= valuesWall[i][0] * cenToMetre;
                    valuesWall[i][1] = valuesWall[i][1] * cenToMetre;
                    break;
                case 3:
                    break;
            }
            areaWall[i]= valuesWall[i][0] * valuesWall[i][1];
        }
        double areaPaint=0;
        for(int i=0;i<quantityWall;i++){
            areaPaint=areaPaint+areaWall[i];
        }
        double litrePaint = areaPaint*litrePerMeterSqred;
        litrePaint = litrePaint * coatsPaint;
        double litreReq = canLitre *(Math.ceil(litrePaint/canLitre));
        int litreNeed = (int) litreReq;
        double finalPrice = ((double) litreNeed /canLitre) * priceLitre;
        System.out.println("The conversion rate for litres of paint to area in m² is "+litrePerMeterSqred+". " +
                "The price of paint for every "+canLitre +"L is £"+priceLitre+".");
        double areaBack;
        switch (number){
            case 1:
                areaBack = areaPaint/(milToMetre*milToMetre);
                System.out.println("The Area of wall needing paint is "+areaPaint+"m². ("+areaBack+"mm²) " +
                        " Multiplying this by the number of layers required we get "+(areaPaint*coatsPaint)+"m². " +
                        "The amount of paint required therefore is "+litreNeed+"L." +
                        " The price of this will be £"+finalPrice+".");
                break;
            case 2:
                areaBack =areaPaint/(cenToMetre*cenToMetre);
                System.out.println("The Area of wall needing paint is "+areaPaint+"m². ("+areaBack+"cm²) " +
                        " Multiplying this by the number of layers required we get "+(areaPaint*coatsPaint)+"m². " +
                        "The amount of paint required therefore is "+(areaPaint*coatsPaint)+"L." +
                        " The price of this will be £"+finalPrice+".");
                break;
            case 3:
                System.out.println("The Area of wall needing paint is "+areaPaint+"m²." +
                        " Multiplying this by the number of layers required we get "+(areaPaint*coatsPaint)+"m². " +
                        "The amount of paint required therefore is "+litreNeed+"L." +
                        " The price of this will be £"+finalPrice+".");
                break;
        }
    }
}
