/*
 * Filename: Tester.java
 *
 * Lead Author(s):
 * @author Name
 *
 * Other contributors:
 * Tasha Frankie
 * 
 * References:
 * Guzdial, M. J., & Ericson, B. (2007). Introduction to computing and
 * programming with Java: A multimedia approach. New York: Prentice Hall.
 * 
 * Date:
 *
 * Responsibilities of class:
 */

public class Tester
{
    //The line below is magic, you don't have to understand it (yet)
    public static void main (String[] args)
    {
        //Put all your commands to create a Picture and manipulate Pixels
        //here AND REMOVE THIS COMMENT
        Picture pic = new Picture(FileChooser.pickAFile());
        pic.explore();
    }
}
