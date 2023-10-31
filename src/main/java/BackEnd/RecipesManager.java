/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackEnd;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SpringLayout;

/**
 *
 * @author Kaedyn Malek
 */
public class RecipesManager {

    /**
     * get number of recipes
     *
     * @param
     * @return integer
     */
    public static int getNumRecipes() {

        int count = 0;
        try {
            Scanner scFile = new Scanner(new File("Recipes.txt"));

            while (scFile.hasNextLine()) {
                count++;
                String line = scFile.nextLine();
            }
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Could not open file.", "Error ", JOptionPane.INFORMATION_MESSAGE);
            Logger.getLogger(RecipesManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        return count;
    }

    /**
     * returns the recipe names
     *
     * @param
     * @return String[]
     */
    public static String[] getRecipeNames() {

        String[] names = new String[getNumRecipes()]; // 4
        String cookingName = "";
        int index = 0;

        try {
            Scanner scFile = new Scanner(new File("Recipes.txt"));

            while (scFile.hasNextLine()) {
                String line = scFile.nextLine();

                Scanner scLine = new Scanner(line).useDelimiter("#");
                cookingName = scLine.next();

                names[index] = cookingName;
                index++;

            }

        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Could not open file.", "Error ", JOptionPane.INFORMATION_MESSAGE);
            Logger.getLogger(RecipesManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        return names;
    }

    public static String getRecipe(String nameOfRecipe) {
        String ingredientsOutput = "";
        try {
            File f = new File("Recipes.txt");
            Scanner fileSc = new Scanner(f);

            while (fileSc.hasNextLine()) {
                String line = fileSc.nextLine();
                Scanner lineSc = new Scanner(line).useDelimiter("#");

//                String name = lineSc.next();
//                String ingredientsLine = lineSc.next();//tokenized
//                String stepsLine = lineSc.next();//tokenized
//                boolean isFrozen = lineSc.nextBoolean();
//                int cookTime = lineSc.nextInt();
//
//                String ingredients = "";
//                Scanner ingredientsSc = new Scanner(ingredientsLine).useDelimiter("$");
//                while (ingredientsSc.hasNext()) {
//                    ingredients += ingredientsSc.next() + "\n";
//                }
//
//                String steps = "";
//                Scanner stepsSc = new Scanner(stepsLine).useDelimiter("$");
//                while (stepsSc.hasNext()) {
//                    steps += stepsSc.next() + "\n";
//                }
//
//                if (name.equalsIgnoreCase(nameOfRecipe)) {
//                    ingredientsOutput = ingredients;
//                }
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(RecipesManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ingredientsOutput;
    }

    public static void saveRecipe(String name, String ingredients1, String ingredients2, String ingredients3, String instructions1, String instructions2, String instructions3) {

        String formattedIngredients = ingredients1 + "$" + ingredients2 + "$" + ingredients3 + "#";
        String formattedInstructions = instructions1 + "$" + instructions2 + "$" + instructions3;
        String storedRecipe = name + "#" + formattedIngredients + formattedInstructions;

        try {
            PrintWriter Fwriter = new PrintWriter(new FileWriter("Recipes.txt", true));

            //Write to the textfile
            Fwriter.println(storedRecipe);

            Fwriter.close();

        } catch (IOException ex) {
            Logger.getLogger(RecipesManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static String viewRecipe(String selectedRecipe) {
        String usersSelectedRecipe = selectedRecipe;
        String recipeWhole = "";
        File f = new File("Recipes.txt");

        String name = "";
        String ingredients = "";
        String instructions = "";

        try {
            Scanner scFile = new Scanner(f);

            while (scFile.hasNextLine()) {
                String line = scFile.nextLine();
                Scanner scLine = new Scanner(line).useDelimiter("#");

                if (scLine.hasNext()) {
                    name = scLine.next();
                    if (scLine.hasNext()) {
                        String restOfLine = scLine.next();

                        //  two more scanners for ingredients and instructions
                        Scanner scIngredients = new Scanner(restOfLine).useDelimiter("$"); 
                        if (scIngredients.hasNext()) {
                            ingredients = scIngredients.next();
                        }

                        if (scIngredients.hasNext()) {
                            instructions = scIngredients.next();
                        }

                        if (name.equals(usersSelectedRecipe)) {
                            
                            recipeWhole = name + "\n\n" + ingredients + "\n\n" + instructions;
                        }
                    }
                }

            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(RecipesManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        return recipeWhole;
    }

}
