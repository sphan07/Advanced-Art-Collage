import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.text.*;
import java.util.*;
import java.util.List; // resolves problem with java.awt.List and java.util.List

/**
 * A class that represents a picture.  This class inherits from 
 * SimplePicture and allows the student to add functionality to
 * the Picture class.  
 * 
 * Copyright Georgia Institute of Technology 2004-2005
 * @author Barbara Ericson ericson@cc.gatech.edu
 */
public class Picture extends SimplePicture 
{
    ///////////////////// constructors //////////////////////////////////

    /**
     * Constructor that takes no arguments 
     */
    public Picture ()
    {
        /* not needed but use it to show students the implicit call to super()
         * child constructors always call a parent constructor 
         */
        super();  
    }

    /**
     * Constructor that takes a file name and creates the picture 
     * @param fileName the name of the file to create the picture from
     */
    public Picture(String fileName)
    {
        // let the parent class handle this fileName
        super(fileName);
    }

    /**
     * Constructor that takes the width and height
     * @param width the width of the desired picture
     * @param height the height of the desired picture
     */
    public Picture(int width, int height)
    {
        // let the parent class handle this width and height
        super(width,height);
    }

    /**
     * Constructor that takes a picture and creates a 
     * copy of that picture
     */
    public Picture(Picture copyPicture)
    {
        // let the parent class do the copy
        super(copyPicture);
    }

    /**
     * Constructor that takes a buffered image
     * @param image the buffered image to use
     */
    public Picture(BufferedImage image)
    {
        super(image);
    }

    ////////////////////// methods ///////////////////////////////////////

    /**
     * Method to return a string with information about this picture.
     * @return a string with information about the picture such as fileName,
     * height and width.
     */
    public String toString()
    {
        String output = "Picture, filename " + getFileName() + 
            " height " + getHeight() 
            + " width " + getWidth();
        return output;

    }

    public static void main(String[] args) 
    {
        String fileName = FileChooser.pickAFile();
        Picture pictObj = new Picture(fileName);
        pictObj.explore();
    }

    /**
     * Method to create raindrops on to a vertically mirrored picture based on width
     * @param red to take the number of the red component used
     * @param green to take the number of the green component used
     * @param blue to take the number of the blue component used
     */
    public void raindrops (int red, int green, int blue)
    {
        //Declare variables
        int width = this.getWidth();
        int mirrorPoint= width/2;
        Pixel leftPixel = null;
        Pixel rightPixel = null;
        Pixel sourcePixel = null;

        //For loop to loop half of pic (mirror)
        for(int y=0; y < getHeight(); y++)
        {

            for (int x=0; x< mirrorPoint; x++)
            {

                leftPixel = this.getPixel(x,y);
                rightPixel = getPixel(width - 1 - x, y);
                rightPixel.setColor(leftPixel.getColor());

                //get vertical stripes every 5th pixel from the left side
                if ((x%5)==0)
                {
                    leftPixel = this.getPixel(x,y);
                    leftPixel.setColor(new Color(red,green,blue));

                    // get horizontal stripes which will be orignal picture color from the 
                    //left side every 5th pixel
                    if ((y%5)==0)
                    {
                        leftPixel.setColor(rightPixel.getColor());
                    }
                    else
                    {
                        //Decreases the Green Value by 50% while also getting
                        //filiter from the left onto the right side of the picture
                        int value = leftPixel.getGreen();
                        value = (int) (value * 0.5);
                        rightPixel.setGreen(value) ;

                        //Decreases red value
                        int value1 = leftPixel.getRed();
                        value1 = (int) (value1*0.2);
                        rightPixel.setRed(value1);
                        
                        //Decreases blue value
                        int value2 = leftPixel.getBlue();
                        value2 = (int) (value1*0.2);
                        rightPixel.setBlue(value1);

                    }

                }

            }

        }
    }

    /**
     * Method to change the picture into gray scale
     */
    // Method Gray Scale
    public void createGrayScale ()
    {

        //Declare variables
        Pixel[]pixelArray = this.getPixels();
        Pixel pixel = null;
        int intensity = 0;
        int index = 0;

        //loop through all the pixels
        while (index < pixelArray.length)
        { 
            //get the current pixel
            pixel = pixelArray[index];

            //change intensity of colors by calculating the average in order to get greyColor
            intensity = (int) ((pixel.getRed() + pixel.getGreen() + pixel.getBlue())/3);

            //set pixel to the new colors
            pixel.setColor( new Color (intensity, intensity, intensity));

            //updates the index
            index++;

        }
    }

    /**
     * Method to create a new picture that is rotated to the left with the negative filiter applied 
     * @returns rotate, the rotated picture
     */

    public Picture negleftRotation()
    {
        //Create a new picture
        Picture rotate = new Picture(this.getHeight(),this.getWidth());

        //Declare source pixel, target pixel and r,g,b value variables
        Pixel sourcePixel, targetPixel;
        int greenValue =0;
        int blueValue = 0;
        int redValue = 0;

        //loops through the columns
        for(int sourceX = 0; sourceX < this.getWidth(); sourceX++)

        //loop thought the rows
        {
            for(int sourceY = 0; sourceY < this.getHeight(); sourceY++)
            {

                //get the current pixel
                sourcePixel = this.getPixel(sourceX, sourceY);

                //rotates the picture by determining where the source pixel will get 
                //copied to the target pixel
                targetPixel = rotate.getPixel(sourceY, this.getWidth()-1-sourceX);

                //Get the current red, green and blue values
                greenValue = sourcePixel.getGreen();
                blueValue = sourcePixel.getBlue();
                redValue = sourcePixel.getRed();

                //set the target pixel to the new color
                targetPixel.setColor(new Color(255-redValue, 255 - greenValue, 255 - blueValue));
            }
        }
        return rotate;
    }

    /**
     * Method to create a Collage using p1 as a template
     * @param p1 which is the first picture
     * @param p2 which is the second picture
     * @param p3 which is the third picture
     * @returns lake, which is a picture
     */
    //Create Collage Method
    public static Picture createCollage(Picture p1, Picture p2, Picture p3) 
    {
        //Create new Picture of p1 as the template for the rest of the pictures to be placed on
        Picture lake = new Picture(p1);

        //Declare source and target Pixels
        Pixel sourcePixel = null; 
        Pixel targetPixel= null;

        //Create nested loops to get source 2 pic color to target pic for pic 2
        //Also scales the picture by 50% 
        for(int sourceX2 = 0, targetX2 = 0; sourceX2 < p2.getWidth(); sourceX2+=2, targetX2++)
        {
            for(int sourceY2 = 0, targetY2 = 0; sourceY2 < p2.getHeight(); sourceY2+=2,targetY2++)
            {
                sourcePixel = p2.getPixel(sourceX2,sourceY2);
                targetPixel = lake.getPixel(targetX2,targetY2);
                targetPixel.setColor(sourcePixel.getColor());
            }
        }

        //Create nested loops to get source pic 3 color to target pic for pic 3
        //Also scales the picture by 50% with hardcoded targetX3 in order to move cooridates
        for(int sourceX3 = 0, targetX3 = 400; sourceX3 < p3.getWidth(); sourceX3+=2, targetX3++)
        {
            for(int sourceY3 = 0, targetY3 = 0; sourceY3 < p3.getHeight(); sourceY3+=2, targetY3++)
            {
                sourcePixel = p3.getPixel(sourceX3,sourceY3);
                targetPixel = lake.getPixel(targetX3,targetY3);
                targetPixel.setColor(sourcePixel.getColor());
            }
        }

        //returns the picture
        return lake;

    }

} // this } is the end of class Picture, put all new methods before this
