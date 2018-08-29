/**
 * Driver
 * A Program that solve any good simple expression using the algorithm discussed in class.
 * Artin Malekian
 * Dr. Hoang
 * CSC 201 - Assignment#3
 * 29 November 2016
 */


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Driver {

	public static void main(String[] args) {

		//String s = "(2+3)*((3+2)+2)";
		//String s = "(2+3)*((3+2)+2) + 3+(3)";
		//String s = "(2+3)*(2) + 3";
		String s = "(14)";

		
			//System.out.println(s);
			double result = ExpressionSolver.solve(s);
			System.out.println("the result is " + result + "\n");
	}

}
