
/* * 
 * *************************************************************************************
 * *********************** By Magdalen Berns for miniProject 2012 **********************
 * *************************************************************************************
 * 
 * CONTAINS:
 *
 * A "default" zero argument constructor Vector3D() that sets all the elements to zero; 
 
 * A constructor Vector3D(x,y,z) that initializes the vector to (x,y,z);

 * A copy contructor and a static copy method for creating copies of Vector3D object instances;

 * Instance methods that return the magnitude squared and the magnitude of the vector
 
 * Static and instance methods to perform vector addition,subtraction operations on two Vector3D instances.

 * Static and an instance methods to perform vector addition,subtraction operations on a Vector3D instance and a double.
 
 * Static method to perform cross product operation on two Vector3D instances.  
 
 * Static method to perform dot product operations on two Vector3D instances.

 *  use of Polymorphsm to keeps the names simple.
 */

public class Vector3D{

    /*
    * instantiate variables as private variables
    * they cannot be referenced outside this class
    */
    private	double x,y,z;

    /*
    * Defined default constructor to be no-argument constructor
    * This initializes instantiated variables x,y,z to be zero
    *
    */
    public Vector3D(){
        x = 0.0; y = 0.0; z = 0.0;
    }

/*
* A public copy constructor Vector3D(a,b,c) initialized x,y,z to values a, b and c
*/
    public Vector3D(double a, double b, double c){
        this.x=a;
        this.y=b;
        this.z=c;
    }

    //Constructor initialise the vector to (x,y,z);
    public Vector3D(Vector3D vector) {

        this.setVector3D(vector.x, vector.y, vector.z);
    }

    //calculate the magnitude squared
    public double magSquared(){

        return x*x + y*y + z*z;
    }
    //magnitude
    public double mag(){

        return Math.sqrt(this.magSquared());
    }

    //static method to compute dot product of 2 vectors and return a double
    public static double dot(Vector3D a, Vector3D b){

        return a.x * b.x + a.y * b.y + a.z * b.z;

    }

    //static method to compute the cross product
    public static Vector3D cross(Vector3D a, Vector3D b) {

        Vector3D v = new Vector3D();
        v.setX(a.y * b.z - a.z * b.y);
        v.setY(a.z * b.x - a.x * b.z);
        v.setZ(a.x * b.y - a.y * b.x);
        return v;
    }

    /*
    * The toString cannot be overridden by void print statement.
    * So I extended functionality of toString by creating a method to print contents of it.
    */
    public void printString(String label) {

        System.out.printf("%s %s\n", label, this.toString());
    }
    /*
    * here is the version required for the task
    */
    public String toString(){
        return " " + x + " " + y + " " + z + " ";
    }

/*
* Static instance methods means that it can have return type of Vector3D
* They can also be instantiated in main without having to use "new" keyword.
*/

    //method to add two vectors and return an object of type Vector3D
    public static Vector3D add(Vector3D a, Vector3D b){

        Vector3D v = new Vector3D();
        v.setX(a.x + b.x);
        v.setY(a.y + b.y);
        v.setZ(a.z + b.z);
        return v;
    }
    //method to subtract one vector from another vector and return an object of type Vector3D)
    public static Vector3D subtract(Vector3D a, Vector3D b){

        Vector3D v = new Vector3D();
        v.setX(a.x - b.x);
        v.setY(a.y - b.y);
        v.setZ(a.z - b.z);
        return v;
    }
    //method to multiply by a Vector3D by a scaler (and return an object of type Vector3D)
    public static Vector3D multiply(Vector3D vector, double scaler){

        Vector3D v = new Vector3D();
        v.setX(vector.x * scaler);
        v.setY(vector.y * scaler);
        v.setZ(vector.z * scaler);
        return v;
    }
    //static method to divide by a scaler (and return an object of type Vector3D)
    public static Vector3D divide(Vector3D vector, double scaler){

        Vector3D v = new Vector3D();
        v.setX(vector.x / scaler);
        v.setY(vector.y / scaler);
        v.setZ(vector.z / scaler);
        return v;
    }
    // copyVector instance method for putting value into an object without changing the object itself
    public Vector3D copy(){

        return new Vector3D(x,y,z);
    }

    // This multiplies vectors by a scalar.
    public Vector3D multiply(double scaler){
        return new Vector3D(x*scaler,y*scaler,z*scaler);
    }
    // This divides vectors by scalar.
    public Vector3D divide(double scaler){
        return multiply(1.0/scaler);
    }
    //instance adds two vectors
   public void add(Vector3D v) {
        x+=v.x;
        y+=v.y;
        z+=v.z;
    }
    //instance method subtracts a vector from another: //
    // The argument instance is subtracted
    public void subtract(Vector3D v) {
        x-=v.x;
        y-=v.y;
        z-=v.z;
    }

/*  ************** Setters *************** */
void setVector3D(double a, double b, double c){
        this.x=a;
        this.y=b;
        this.z=c;
    }
    //set the value of x
    void setX(double x) {
        this.x = x;
    }
    //set the value of y
    void setY(double b){
        this.y = b;
    }
    //set the value pf z
    void setZ(double c){
        this.z = c;
    }

    /**************************************
     ***************** Getters *************
     ***************************************/
      //getter for x
    public double getX(){
        return x;
    }
    //getter for y
    public double getY(){
        return y;
    }
    // getter for z
    public double getZ(){
        return z;
    }

    //sets the x y and z coordinate in a more compact way (saves a bit of space when calling the coordinates)
    public void setDim(int i, double dimension){

        switch (i) {
            case 0:
                x = dimension;
                break;

            case 1:
                y = dimension;
                break;

            case 2:
                z = dimension;
                break;
        }
    }
    //returns the x, y and z coordinates respectively
    public double getDim(int i) {

        double dimension = 0.0;

        switch (i){
            case 0:
                dimension = x;
                break;

            case 1:
                dimension = y;
                break;

            case 2:
                dimension = z;
                break;
        }
        return dimension;
    }
}
