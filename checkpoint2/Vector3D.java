/* * 
 * *******************************************************************************
 * ***************** By Magdalen Berns for checkpoint2 2012 **********************
 * *******************************************************************************
 * 
 * CONTAINS:
 *
 * A "default" zero argument constructor Vector3D() that sets all the elements to zero; 			
 *
 * A constructor Vector3D(x,y,z) that initializes the vector to (x,y,z);	
 *
 * A static copy method for creating copies of Vector3D object instances; 				
 *
 * Instance methods that return the magnitude squared and the magnitude of the vector respectively;
 *
 * A toString() instance method and toStr() that prints out the elements of the vector	+ Label		
 *
 * Instance methods for scalar multiply and scalar divide by a double.					
 *
 * Method to perform vector addition operation on two Vector3D instances.			
 * 
 * Method to perform subtraction operation on two Vector3D instances. 					
 * 
 * Method to perform cross product operation on two Vector3D instances.  
 *
 * Method to perform dot product operations on two Vector3D instances. 
 */

public class Vector3D{

	/*
	* instantiate variables as private variables 
	* they cannot be referenced outside this class
	*/ 
	private	double x,y,z;	

	/*
	*	Defined default constructor to be no-argument constructor 
	* This initializes instantiated variables x,y,z to be zero
	*
	*/
	public Vector3D(){

		x = 0; y = 0; z = 0;

	}   

	/* 
	*  A public constructor Vector3D(a,b,c)	initialized x,y,z to values a, b and c
	*/   

	public Vector3D(double a, double b, double c){

		this.x=a;
		this.y=b;
		this.z=c;
	}

	//Constructor  initialise the vector to (x,y,z);
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
	public static double dotProduct(Vector3D a, Vector3D b){

		double c = a.x * b.x + a.y * b.y + a.z * b.z;
		return c;	
	}

	//static method to compute the cross product 
	public static Vector3D crossProduct(Vector3D a, Vector3D b) {
		
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
	public void toStr(String label) {
	
	  System.out.printf("%s %s\n", label, this.toString());
	}
	/*
	* here is the version required for the task
	*/
	public String toString(){
		return "(" + x + "," + y + "," + z + ")";
	} 
	
	/*
	*   Static instance methods means that it can have return type of Vector3D 
	*   They can also be instantiated in main without having to use "new" keyword.
	*/
	
	//method to add two vectors and return an object of type Vector3D
	public static Vector3D addVectors(Vector3D a, Vector3D b){

		Vector3D v = new Vector3D();
		 v.setX(a.x + b.x);
		 v.setY(a.y + b.y);
		 v.setZ(a.z + b.z);
		return v;
	}
	//method to subtract one vector from another vector and return an object of type Vector3D)
	public static Vector3D subtractVectors(Vector3D a, Vector3D b){

		Vector3D v = new Vector3D();
		v.setX(a.x - b.x);
		v.setY(a.y - b.y);
		v.setZ(a.z - b.z);
		return v;
	}
	//method to multiply by a Vector3D by a scaler (and return an object of type Vector3D)
	public static Vector3D multiplyByScaler(Vector3D vector, double scaler){
	
		Vector3D v = new Vector3D();
		v.setX(vector.x * scaler);
		v.setY(vector.y * scaler);
		v.setZ(vector.z * scaler);
		return v;
	}
	//static method to  divide by a scaler (and return an object of type Vector3D)
	public static Vector3D divideByScaler(Vector3D vector, double scaler){
	
		Vector3D v = new Vector3D();
		v.setX(vector.x / scaler);
		v.setY(vector.y / scaler);
		v.setZ(vector.z / scaler);
		return v;
	}
	
	
	//static method to  add a vector a scaler (and return an object of type Vector3D) 
	public static Vector3D addToScaler(Vector3D vector, double scaler){
	
		Vector3D v = new Vector3D();
		v.setX(vector.x + scaler);
		v.setY(vector.y + scaler);
		v.setZ(vector.z + scaler);
		return v;
	}

	/*
	*	Cannot assign objects as we do primitive types because objects are derived types 
	* If we try to treat objects as primitive tpyes the value would not simply be copied from one variable to the next
	*	because it changes the object which is being referenced rather than changing the reference of the variable.
	*
	*/
 
	//To copy a vector object I have created a copyVector method:
	public Vector3D copyVector(){
	
		return new Vector3D(x,y,z);
	}

	/* 
	**************  Setters ***************
	**************************************
	*/
	
	public void setVector3D(double a, double b, double c){
		this.x=a;
		this.y=b;
		this.z=c;
	}
	//set the value of x
	public void setX(double a) {
		this.x = a;
	}
	//set the value of y
	public void setY(double b){
		this.y = b;
	}
	//set the value pf z
	public void setZ(double c){
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
}
