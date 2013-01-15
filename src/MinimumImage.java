/*
* ********************************************************************************* *
* ********************* By Magdalen Berns for miniproject 2012 ******************** *
* ********************************************************************************* *

*   CONTAINS:

*   a static method to compute the separation

*   a static method to compute minimum image distance

*/

class MinimumImage {

    //static method computes the separation distance between two particles
    //Arguments: two particles from the Particle3D class
    private static Vector3D separation(Particle3D pa, Particle3D pb){

        return Vector3D.subtract(pb.getPosition(), pa.getPosition());
    }
    //static method computes the separation distance between two particles with minimum image correction.
    //Arguments: the dimensions of the bounding box, an array of Particle3D particles,   ... two integer values so particle pairs can be calculated when the method is called inside two for loops.
    public static Vector3D minDistance(Vector3D boxDims, Particle3D[] p, int i, int j) {

        double xSep = separation(p[i], p[j]).getX();
        double ySep = separation(p[i], p[j]).getY();
        double zSep = separation(p[i], p[j]).getZ();
        xSep = (xSep - boxDims.getX()* (int) Math.round(xSep / boxDims.getX()));
        ySep = (ySep - boxDims.getY()* (int) Math.round(ySep/  boxDims.getY()));
        zSep = (zSep - boxDims.getZ()* (int) Math.round(zSep / boxDims.getZ()));

        return new Vector3D(xSep, ySep,zSep);
    }
}