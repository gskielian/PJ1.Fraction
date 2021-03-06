/*************************************************************************************
 *
 * This class represents a fraction whose numerator and denominator are integers.
 *
 * Requirements:
 *      Implement interfaces: FractionInterface and Comparable (i.e. compareTo())
 *      Implement methods equals() and toString() from class Object
 *
 *      Should work for both positive and negative fractions
 *      Must always reduce fraction to lowest term
 *      For number such as 3/-10, it is same as -3/10 (see hints 2. below)
 *      Must display negative fraction as -x/y,
 *         example: (-3)/10 or 3/(-10), must display as -3/10
 *      Must throw excpetion in case of errors
 *      Must not add new or modify existing data fields
 *      Must not add new public methods
 *      May add private methods
 *
 * Hints:
 *
 * 1. To reduce a fraction such as 4/8 to lowest terms, you need to divide both
 *    the numerator and the denominator by their greatest common denominator.
 *    The greatest common denominator of 4 and 8 is 4, so when you divide
 *    the numerator and denominator of 4/8 by 4, you get the fraction 1/2.
 *    The recursive algorithm which finds the greatest common denominator of
 *    two positive integers is implemnted (see code)
 *
 * 2. It will be easier to determine the correct sign of a fraction if you force
 *    the fraction's denominator to be positive. However, your implementation must
 *    handle negative denominators that the client might provide.
 *
 * 3. You need to downcast reference parameter FractionInterface to Fraction if
 *    you want to use it as Fraction. See add, subtract, multiply and divide methods
 *
 * 4. Use "this" to access this object if it is needed
 *
 ************************************************************************************/

package PJ1;

public class Fraction implements FractionInterface, Comparable<Fraction>
{
	private	int num;	// Numerator	
	private	int den;	// Denominator	

	public Fraction()
	{
		// set fraction to default = 0/1
		setFraction(0, 1);
	}	// end default constructor

	public Fraction(int numerator, int denominator)
	{
                
                num = numerator;
                den = denominator;
                this.reduceToLowestTerms();
		setFraction(num, den);
	}	// end constructor

	public void setFraction(int numerator, int denominator)
	{
		// return ArithmeticException if initialDenominator is 0
		if (denominator == 0) {
                  throw new ArithmeticException("denominator is less than zero.");
                }
                else if (denominator < 0 && numerator >= 0) {
                 
                  num = -numerator;
                  den = -denominator;
                  // this.reduceToLowestTerms();
                }
                else {
                  num = numerator;
                  den = denominator;
                 // this.reduceToLowestTerms();
                }

	}	// end setFraction

	public int getNumerator()
	{
		// implement this method!
		return num;
	}	// end getNumerator

	public int getDenominator()
	{
		// implement this method!
		return den;
	}	// end getDenominator

	public char getSign()
	{
                if (num * den < 0) {
                  return '-';
                }
                else {
                  return '+';
                }
	}	// end getSign

	public void switchSign()
	{
		// implement this method!
		num = -num;
	}	// change setSign

	public FractionInterface add(FractionInterface secondFraction)
	{
		// a/b + c/d is (ad + cb)/(bd)
		
                int a, b, c, d;
                a = num;
                b = den;
                c = secondFraction.getNumerator();
                d = secondFraction.getDenominator();

                FractionInterface sumFraction = new Fraction(a*d + b*c, b*d);
                return sumFraction;
        }	// end add

	public FractionInterface subtract(FractionInterface secondFraction)
	{
		// a/b - c/d is (ad - cb)/(bd)
	        int a, b, c, d;
                a = num;
                b = den;
                c = secondFraction.getNumerator();
                d = secondFraction.getDenominator();

                FractionInterface subFraction = new Fraction(a*d - b*c, b*d);
                return subFraction;
		 
        }	// end subtract

	public FractionInterface multiply(FractionInterface secondFraction)
	{
		// a/b * c/d is (ac)/(bd)
		int a, b, c, d;
                a = num;
                b = den;
                c = secondFraction.getNumerator();
                d = secondFraction.getDenominator();

                FractionInterface mulFraction = new Fraction(a*c, b*d);
                return mulFraction;
        }	// end multiply

	public FractionInterface divide(FractionInterface secondFraction)
	{
		// return ArithmeticException if secondFraction is 0
		// a/b / c/d is (ad)/(bc)
		// implement this method!
		return this.multiply(secondFraction.getReciprocal());
	}	// end divide

	public FractionInterface getReciprocal() 
	{
		// return ArithmeticException if secondFraction is 0
		// implement this method!
                if (this.getDenominator() == 0) {
                  throw new ArithmeticException();
                }
                else {
                  FractionInterface recipFraction = new Fraction(den, num);
                  return recipFraction;
                }
	} // end getReciprocal


	public boolean equals(Object that)
	{
		// implement this method!
               Fraction otherFraction = (Fraction) that;
               return num * otherFraction.getDenominator() == otherFraction.getNumerator() * den;
        } // end equals


	public int compareTo(Fraction that)
	{
		// implement this method!
		// this.reduceToLowestTerms();
                // that.reduceToLowestTerms();
                
                if (this.equals(that)) {
                 return 0;
                }
                else if (num * that.getDenominator() > that.getNumerator() * den) {
                  return 1;
                }
                else {
                  return -1;
                }
                
                
	} // end compareTo

	
	public String toString()
	{
		return num + "/" + den;
	} // end toString


	/** Task: Reduces a fraction to lowest terms. */

        //-----------------------------------------------------------------
        //  private methods start here
        //-----------------------------------------------------------------

	private void reduceToLowestTerms()
	{
                // implement this method!
                //
                // Outline:
                // compute GCD of num & den
                //   greatestCommonDivisor works for + numbers.
                //   So, you should eliminate - sign
                // then reduce numbers : num/GCD and den/GCD
                int g;
                // if (num < 0 ) {

                //   g = greatestCommonDivisor(-num, den);

                // }
                // else {
                //   g = greatestCommonDivisor(num, den);
                // }
                g = greatestCommonDivisor(num, den);

                num = num / g;
                den = den / g;
                

                


	}	// end reduceToLowestTerms

  	/** Task: Computes the greatest common secondFraction of two integers.
	 *  @param integerOne	 an integer
	 *  @param integerTwo	 another integer
	 *  @return the greatest common divisor of the two integers */
	private int greatestCommonDivisor(int integerOne, int integerTwo)
	{
		 int result;

		 if (integerOne % integerTwo == 0)
			result = integerTwo;
		 else
			result = greatestCommonDivisor(integerTwo, integerOne % integerTwo);

		 return result;
	}	// end greatestCommonDivisor


	//-----------------------------------------------------------------
	//  Simple test is provided here 

	public static void main(String[] args)
	{
		FractionInterface firstOperand = null;
		FractionInterface secondOperand = null;
		FractionInterface result = null;

		Fraction nineSixteenths = new Fraction(9, 16);	// 9/16
		Fraction oneFourth = new Fraction(1, 4);        // 1/4

		// 7/8 + 9/16
		firstOperand = new Fraction(7, 8);
		result = firstOperand.add(nineSixteenths);
		System.out.println("The sum of " + firstOperand + " and " +
				nineSixteenths + " is \t\t" + result);

		// 9/16 - 7/8
		firstOperand = nineSixteenths;
		secondOperand = new Fraction(7, 8);
		result = firstOperand.subtract(secondOperand);
		System.out.println("The difference of " + firstOperand	+
				" and " +	secondOperand + " is \t" + result);

		// 15/-2 * 1/4
		firstOperand.setFraction(15, -2);
		result = firstOperand.multiply(oneFourth);
		System.out.println("The product of " + firstOperand	+
				" and " +	oneFourth + " is \t" + result);

		// (-21/2) / (3/7)
		firstOperand.setFraction(-21, 2);
		secondOperand.setFraction(3, 7);
		result = firstOperand.divide(secondOperand);
		System.out.println("The quotient of " + firstOperand	+
				" and " +	secondOperand + " is \t" + result);

		// -21/2 + 7/8
		firstOperand.setFraction(-21, 2);
		secondOperand.setFraction(7, 8);
		result = firstOperand.add(secondOperand);
		System.out.println("The sum of " + firstOperand	+
				" and " +	secondOperand + " is \t\t" + result);

		System.out.println();

		// equality check
		if (firstOperand.equals(firstOperand))
			System.out.println("Identity of fractions OK");
		else
			System.out.println("ERROR in identity of fractions");

		secondOperand.setFraction(-42, 4);
		if (firstOperand.equals(secondOperand))
			System.out.println("Equality of fractions OK");
		else
			System.out.println("ERROR in equality of fractions");

		// comparison check
		Fraction first  = (Fraction)firstOperand;
		Fraction second = (Fraction)secondOperand;
		
		if (first.compareTo(second) == 0)
			System.out.println("Fractions == operator OK");
		else
			System.out.println("ERROR in fractions == operator");

		second.setFraction(7, 8);
		if (first.compareTo(second) < 0)
			System.out.println("Fractions < operator OK");
		else
			System.out.println("ERROR in fractions < operator");

		if (second.compareTo(first) > 0)
			System.out.println("Fractions > operator OK");
		else
			System.out.println("ERROR in fractions > operator");

		System.out.println();

		try {
			Fraction a1 = new Fraction(1, 0);		    
		}
		catch ( ArithmeticException arithmeticException )
           	{
              		System.err.printf( "\nException: %s\n", arithmeticException );
           	} // end catch

		try {
			Fraction a2 = new Fraction();		    
			Fraction a3 = new Fraction(1, 2);		    
			a3.divide(a2);
		}
		catch ( ArithmeticException arithmeticException )
           	{
              		System.err.printf( "\nException: %s\n", arithmeticException );
           	} // end catch



	}	// end main
} // end Fraction

