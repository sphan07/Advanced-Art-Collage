
/**
 * Filename: AdvancedCollege.java
 * 
 * AdvancedCollege is for creating a Collage that will also have filiters on the pictures
 * used to create a collage
 *
 * Lead Author:
 * @Samantha Phan
 * 
 * Other contributors:
 * Tasha Frankie
 * Smail Bestybay
 * 
 * References:
 * Guzdial, M. J., & Ericson, B. (2007). Introduction to computing and
 * programming with Java: A multimedia approach. New York: Prentice Hall.
 * 
 * @5/20/23
 */

import java.util.Scanner;
import java.awt.Color;

public class AdvancedCollege
{
     public static void main (String[] args)
    {
        
        //Create the three pictures for use
         Picture lakeway = new Picture("lakeway.jpg");
         Picture waterway = new Picture("waterway.jpg");
         Picture koifish = new Picture("fish.jpg");
         
        // Apply user input to obtain the r,g,b values user would like to use for raindrops
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Choose a color for the raindrops by providing the values for the r,g,b values");
        System.out.println("Please put in the value of the red component for use:");
        int red = keyboard.nextInt();
        System.out.println("Please put in the value of the green component for use:");
        int green = keyboard.nextInt();
        System.out.println("Please put in the value of the blue component for use:");
        int blue = keyboard.nextInt();
        
        //Applies the raindrop filter to the waterway image
        waterway.raindrops(red,green,blue);
        //waterway.explore();
         
        //Applies the koifish filiter to the koifish image
        koifish.createGrayScale();
        koifish.explore();
          
        //Applies the negleftRotation filiter to the lakeway image
        Picture lake = lakeway.negleftRotation();
        lake.explore();

        
        //Applies the create Collage method
        Picture collage = Picture.createCollage(lake,koifish,waterway);
        collage.write("./Phan_AdvancedCollage.jpg");
        collage.explore();
    
       
}
}
