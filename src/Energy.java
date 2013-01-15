/*
* ********************************************************************************* *
* ********************* By Magdalen Berns for miniproject 2012 ******************** *
* ********************************************************************************* *

*   CONTAINS:

*   a static method to compute the kinetic energy of a particle from the Particle3D class

*   a static method to compute the kinetic energy of an array of particles from the Particle3D class

*   A static method to compute the LennardJones potential of particle pairs from a particle array

*   */


class Energy {

   //Calculates the kinetic energy of an array
   //Arguments : Array of particles from Particle3D class
   public static double NBodyKinetic(Particle3D[] p){
        double tempKinetic = 0.0;
        for (Particle3D aP : p) tempKinetic += kinetic(aP);  
        return tempKinetic;
    }
    //Calculates the kinetic energy of a single particle/
    //Arguments : Particle from Particle3D class
    private static double kinetic(Particle3D p){
        return 0.5*p.getMass()*(p.getVelocity().magSquared());
    }

    //Calculates the Potential energy on a particle due to all the other particles in array
    //Arguments : CutOff, the dimensions of the bounding box and  array of particles from Particle3D class
    public static double LennardJones(double cutOff, Vector3D boxDims, Particle3D[] p){
        double energy = 0.0;
        for (int i = 0; i < p.length; i++){
            for (int j = i+1; j < p.length; j++){
                Vector3D tempSep= MinimumImage.minDistance(boxDims, p, i, j);
                if (tempSep.mag() <= cutOff) energy+=4.0 *((Math.pow(tempSep.mag(),-12.0) - Math.pow(tempSep.mag(),-6.0)));
            }
        }
        return energy;
    }
}
