import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
/*

 * By Magdalen Berns
 *
 * This is a useful IOUtility class to make IO commands run more smoothly by filling the gaps between methods in JDK7 and commonly written commands.
 * 
 * Static SkipToDouble() Method to ignore non double values and extract data needed . This makes it possible to take data from a file with human readable entries and helps avoid problems
 * where the are various types of parameters to input.
 * 
 * Static SkipToInt() The same as SkipToDouble but strictly for integer values.
 *
 * Static typed typedInput reads user input from a keyboard and returns it.
 *
 * Static abuse() throws a human readable abusive statement when it picks up on some error. - I was a bit bored.
 */

class IOUtil {

    /*
 * *
 * ********** keeps looking until double is found and will 'ignore' values that cannot be converted to double (words)*
 * *********/
    public static double skipToDouble(Scanner scanner) {

        while (scanner.hasNext() && !scanner.hasNextDouble()) {
            scanner.next();
        }
        return scanner.hasNextDouble() ? scanner.nextDouble() : Double.NaN;
    }

    /*
     * *
     * * Picks out the first integer value it sees and returns it.
     * */
    public static int skipToInt(Scanner scanner) {

        while (scanner.hasNext() && !scanner.hasNextInt()) {
            scanner.next();
        }
        //This is probably a bit naughty because 'int' is primitive -but it does what I want
        return scanner.hasNextInt() ? scanner.nextInt() : (int) Double.NaN;
    }
    public static String typedInput() throws IOException {
        BufferedReader keyIn = new BufferedReader(new InputStreamReader(System.in));
        return keyIn.readLine();

    }
    /*
    *Call this every time wrong thing is typed.*/
    public static void abuse() {
        System.out.println("Invalid entry.");
        System.out.println("Abusive statement.");
        System.exit(0);
    }

}