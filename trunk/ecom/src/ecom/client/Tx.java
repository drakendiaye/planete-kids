/*-----------------------------------------------------------------------------*/
/* Tx.java                                                                     */
/* ecom application                                                            */
/* Fabienne Boyer - july 2000                                                  */
/*-----------------------------------------------------------------------------*/
package ecom.client;
import java.io.*;

public class Tx {

  private static BufferedReader clavier =
            new BufferedReader(new InputStreamReader(System.in));

  //------------------------------------------------------------------
  // readInt
  //------------------------------------------------------------------
  public static int readInt() {
       try {
               return(Integer.valueOf(clavier.readLine()).intValue());
            }
       catch (Exception e)
        {
           System.out.println("Erreur lecture");
           return 0;
        }
    }

  //------------------------------------------------------------------
  // readString
  //------------------------------------------------------------------
   public static String readString() {
       try {
               return(clavier.readLine());
            }
       catch (Exception e) {
           System.out.println("Erreur lecture");
           return null;
        }

   }

  //------------------------------------------------------------------
  // readChar
  //------------------------------------------------------------------
   public static char readChar() {
       try {
	       String s = clavier.readLine() ;
               return s.charAt(0) ;
            }
       catch (Exception e) {
           System.out.println("Erreur lecture");
           return 0;
        }
    }

  //------------------------------------------------------------------
  // readFloat
  //------------------------------------------------------------------
    public static float readFloat() {
       try {
               return(Float.valueOf(clavier.readLine()).floatValue());
            }
       catch (Exception e) {
           System.out.println("Erreur lecture");
           return (float) 0.0;
        }
    }

  //------------------------------------------------------------------
  // readDouble
  //------------------------------------------------------------------
    public static double readDouble() {
       try {
               return(Double.valueOf(clavier.readLine()).doubleValue());
            }
       catch (Exception e) {
           System.out.println("Erreur lecture");
           return 0.0;
        }
    }

  //------------------------------------------------------------------
  // print
  //------------------------------------------------------------------
  public static void  print(String s) {
           System.out.print(s);
           System.out.flush();
  }

  public static void  newLine() {
           System.out.println();
  }

  public static void  println(String s) {
           System.out.println(s);
  }

 }



