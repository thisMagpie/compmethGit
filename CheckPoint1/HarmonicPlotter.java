/* Checkpoint1 By Magdalen Berns 2012. */

import java.io.*;

public class HarmonicPlotter{
	
	/*
	* instance variable
	*/
	private String file;

	/*
	* zero-arg class constructor to initialize instance variables
	*/
	public HarmonicPlotter(){

		/*
		* preach to the converted
		*/
		file=null;		
	}

	/* 
	*  argument set number to multiply by fundimental period.
	*  has been hard coded to 1 in main for this task.
	*/

	public void setOutPut(double n) throws IOException {


		/* local variables */
		double step = 0.01;
		double period = Math.PI*2;
		double x = 0;	

		/*instantiate file output object */ 	
		PrintWriter fout = new PrintWriter(new FileWriter(file));


		/* 
		* Calculate the value of the function, f(x) print x and f(x) to plot file  
		* while loop runs for a single period (i.e. n=1) for this task 
		*/	
	
		while (x <= n * period){	
	
			double y = setFunction(x);	
			fout.println(x + " " + y);
			x += step;				
		}

		//print statement and close output file
		System.out.println("\nxmgrace " + file + "\n");
		fout.close();     	
	}

	/*
	*setter so name of file can be punched into argument commandline
	*TODO getter can be added to class 
	*/
    public void setFileName(String aFile){
		this.file=aFile;
	}
	//Method to calculate function output
  	public static double setFunction(double x) {
	  
	    double fx = Math.sin(x) + (Math.sin(3.0*x)/3.0) + (Math.sin(5.0*x)/5.0) + (Math.sin(7*x)/7.0);     	    
	    return fx;
  	}

	/*All seeing main method */
	public static void main(String[] argv) throws IOException {
	
		/* 
		*make sure that a filename was read into commandline 
		*/
		if(argv.length!=0){
			
			/*
			* just cause... I felt like it.
			*/
	
			if(argv.length==1)
				System.out.println("\nRun command for file:" );
			if(argv.length>1)
				System.out.println("\nRun command for files:" );
	
			/*
			*	check how many file names were read into commandline and print same data to each of them
			*	 TODO set maximum later... if you want.
			*/
			for(int i = 0; i < argv.length; i++){
					
				HarmonicPlotter hp = new HarmonicPlotter();	
	
				//the input string of first agument becomes name of output file 									
				hp.setFileName(argv[i]);
				hp.setOutPut(1);
			}
	
		}
		/* 
		* if not file name was typed in data won't get printed.
		*/
		else return;
	}
}
		

	
