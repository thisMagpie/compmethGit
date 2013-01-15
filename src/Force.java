/*
* ********************************************************************************* *
* ********************* By Magdalen Berns for miniproject 2012 ******************** *
* ********************************************************************************* *

*   CONTAINS:

*   a static method to compute the Lennard Jones force of a particle from the Particle3D class

*   */

class Force {

    // Calculating the Lennard Jones force between two particles.
	//Arguments : the separation between two particles from Particle3D and the cutoff distance 
	public static Vector3D LennardJones(Vector3D tempSep, double cutOff){
        double factor=0.0;
        if (tempSep.mag() <= cutOff) factor = -(48.0 * (Math.pow(tempSep.mag(),-14) - 1/2 * (Math.pow(tempSep.mag(),-8)) ));
        return tempSep.multiply(factor);
    }
}
