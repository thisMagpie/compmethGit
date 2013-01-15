/*
* ********************************************************************************* *
* ********************* By Magdalen Berns for miniproject 2012 ******************** *
* ********************************************************************************* *

*   CONTAINS:

*   a static method to write a Trajectory file format given a the correct inputs.

*   */
import java.io.PrintWriter;

class Trajectory {

 /*
 * Write current positions to a trajectory file.
 * Arguments: the output file, an array of particles from the Particle3D class,
 * number of the increment to print to file.
 * Position label to indicate which particle is being referred to.
 */
    public static void fileWriter(PrintWriter file, Particle3D[] p, int step, String[] positionLabel) {

        file.print(p.length+"\n");
        file.print(step+"\n");
        //particle coordinates
        for (int i=0; i<p.length; i++) file.printf("%s %s\n", positionLabel[i] + "["+ step + "]", p[i].getPosition());
    }
}
