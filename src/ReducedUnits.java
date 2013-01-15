/*
* ********************************************************************************* *
* ********************* By Magdalen Berns for miniproject 2012 ******************** *
* ********************************************************************************* *
*
*   Class to reduce all units for a MD simulation.

*   CONTAINS:

*   instance methods to set reduced temperature, pressure, time and density

    arguments are self explanatory

*   instance 'getter' methods

*/

class ReducedUnits {


    private static final double k = Math.pow(1.38, -23);

	//Setters
    /* ******Converts to derived time for REDUCED Unit of time *** */
    /*twilight zone*/
    public static double time(double mass, double sigma, double epsilon) {
        return (Math.sqrt((mass * Math.pow(sigma, 2) / epsilon)));
    }

/*    public static double temperature(double temperature, double epsilon) { ///Not needed temperature from input file is already reduced/
        return (k * temperature) / epsilon;
        //return temperature;
    }*/
    public static double density(double density, double sigma) {
        return density * Math.pow(sigma, 3);
    }
    public static double length(double length, double sigma){
        return length/sigma;
    }
    public static Vector3D length3D(Vector3D vec, double sigma){  
    		return vec.divide(sigma);
    }
    public static double  temperature(double temperature, double epsilon) {
        return (k * temperature) / epsilon;
    }
    public static double pressure(double pressure, double sigma, double epsilon) {
        return (Math.pow(pressure, 3) * sigma) / epsilon;
    }

}
