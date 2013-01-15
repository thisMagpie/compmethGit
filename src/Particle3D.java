/*
    * ********************************************************************************* *
 	* ********************* By Magdalen Berns for miniproject 2012 ******************** *
	* ********************************************************************************* *

 *   instance methods for 3D vector representation of the mass, position and velocity of a particle;

 *   A default constructor is now zero argument constructor to initialise the properties;

 */

class Particle3D {

    //Instance variables.

    private double mass;

    // Position and velocity are vectors (3 doubles).
    private Vector3D  r;
    private Vector3D  v;

    // Default -zero arg constructor to initialise instance variables
    public Particle3D() {
        mass=Double.NaN;    //"not a number" to indicate uninitialised
        r = new Vector3D();
        v = new Vector3D();
    }
    //Setters for instance variables

    public Vector3D getPosition(){
        return r;
    }
    public Vector3D getVelocity(){
        return v;
    }
    public double getMass(){
       return mass;
    }

    //Getters for instance variables.

    public void setPosition(Vector3D r) {
        this.r = r;
    }
    public void setVelocity(Vector3D v) {
        this.v= v;
    }
    public void setMass(double m){
        mass = m;
    }
}