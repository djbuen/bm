///////////////////////////////////////////////////////////////////////////
//                                                                       //
// Program file name: Bisect.java                                        //
//                                                                       //
// © Tao Pang 2006                                                       //
//                                                                       //
// Last modified: January 18, 2006                                       //
//                                                                       //
// (1) This Java program is part of the book, "An Introduction to        //
//     Computational Physics, 2nd Edition," written by Tao Pang and      //
//     published by Cambridge University Press on January 19, 2006.      //
//                                                                       //
// (2) No warranties, express or implied, are made for this program.     //
//                                                                       //
///////////////////////////////////////////////////////////////////////////

// An example of searching a root via the bisection
// method for f(x)=exp(x)*ln(x)-x*x=0.

import java.lang.*;
public class Bisect {
  public static void main(String argv[]) {
    double x = 0, del = 1e-6, a = 1, b = 2;
    double dx = b-a;
    int k = 0;
    while (Math.abs(dx) > del) {
      x = (a+b)/2;
      if ((f(a)*f(x)) < 0) {
        b  = x;
        dx = b-a;
      }
      else {
        a = x;
        dx = b-a;
      }
      k++;
    }
    System.out.println("Iteration number: " + k);
    System.out.println("Root obtained: " + x);
    System.out.println("Estimated error: " + dx);
  }

// Method to provide function f(x)=exp(x)*log(x)-x*x.

  public static double f(double x) {
    return Math.exp(x)*Math.log(x)-x*x;
  }
}
