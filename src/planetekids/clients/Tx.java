/*-----------------------------------------------------------------------------*/
/* Tx.java                                                                     */
/* ecom application                                                            */
/* Fabienne Boyer - july 2000                                                  */
/*-----------------------------------------------------------------------------*/
package planetekids.clients;
import java.io.*;

public class Tx {
    
    private static BufferedReader clavier =
            new BufferedReader(new InputStreamReader(System.in));
    
    //------------------------------------------------------------------
    // readInt
    //------------------------------------------------------------------
    public static int readInt() throws Exception {
        return(Integer.valueOf(clavier.readLine()).intValue());
    }
    
    //------------------------------------------------------------------
    // readString
    //------------------------------------------------------------------
    public static String readString() throws Exception {
        return(clavier.readLine());
    }
    
    //------------------------------------------------------------------
    // readChar
    //------------------------------------------------------------------
    public static char readChar() throws Exception {
        String s = clavier.readLine() ;
        return s.charAt(0) ;
    }
    
    //------------------------------------------------------------------
    // readFloat
    //------------------------------------------------------------------
    public static float readFloat() throws Exception {
        return(Float.valueOf(clavier.readLine()).floatValue());
    }
    
    //------------------------------------------------------------------
    // readDouble
    //------------------------------------------------------------------
    public static double readDouble() throws Exception {
        return(Double.valueOf(clavier.readLine()).doubleValue());
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



