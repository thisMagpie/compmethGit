/*
* ********************************************************************************* *
* ********************* By Magdalen Berns for miniproject 2012 ******************** *
* ********************************************************************************* *

*   CONTAINS:

*   a static method to compute a histogram of the particle density of a Particle3D array
*
*   a static method to compute the unsmoothed radial distribution function of the Particle3D array
*   */
class Distribution {

    //Histogram the mean number of particles in 'shell' at given width and distance
    //Arguments the width of the bins, the cutOff distance, the boundary dimensions, and a Particle3D array
    public static int[] nr(double binWidth, double cutOff, Vector3D boxDims, Particle3D[] p) {

        int[] n = new int[(int)(cutOff/binWidth+0.5)]; //add 0.5 to avoid rounding error
         // Loop over particle pairs and histogram the particle density
        for (int i=0; i<p.length-1; i++) {
            for (int j=i+1; j<p.length; j++){
               Vector3D r = MinimumImage.minDistance(boxDims, p, i, j);
               if (r.mag() <= cutOff) n[(int)(r.mag()/binWidth)] += 2;  //+= 2 to account for the fact this two particles have been found.
            }
        }
        return n;
    }
 //Method uses the Histogram to calculator the main part of the Radial distribution function.
 //N.B. data needs smoothing this can be done using methods in StatsUtil
 //Arguments: width of the bins, the histogram and rho;
 public static int[] calculateRDF(double binWidth, int[] n, double rho){
      for (int i=0; i<n.length; i++) n[i] /= (4.0*Math.PI*(Math.pow((i+1),3)-Math.pow(i,3))* Math.pow(binWidth,3)*rho)/3.0; //Calculate g[r]
      return n;
 }
}


