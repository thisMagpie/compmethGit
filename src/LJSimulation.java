import java.util.Scanner;
import java.io.*;
/*
* ********************************************************************************* *
* ********************* By Magdalen Berns for miniproject 2012 ******************** *
* ********************************************************************************* *

*   CONTAINS:

*   The  main method to run the program: it takes 3 arguments and won't work without them (SEE README.txt for more information)

*   Static method to handle user experience.
*
*   Static method which takes file names as argument and deals with the file reading and writing.

*/
public class LJSimulation {

    public static void main (String[] args) throws IOException {
        String trajectoryFileName;
        String paramFileName;
        String energyFileName;
        String rdfFileName = "RDF";

		/* Save memory: Open file if and only if correct number arguments entered
		/* N.B. See print statements for explanation of possible errors*/
        try {
            if (args.length == 3) {

			/*	These parameters are relevant to all substances being tested */
                paramFileName = args[0];
                trajectoryFileName = args[2] + ".xyz";
            /*
			* Give scientists some choice about what they are modelling
			* This is useful for times when you only model a gas or liquid from a properties file
			* Automatically assigns it the name "gas or liquid" so appropriate file can be read.
			**/
                String propertiesFileName = gasOrLiquid();

          	//Concatenate properties name (i.e. liquid or gas) with the energy File so it is easy to find
                energyFileName = args[1] + propertiesFileName;
                rdfFileName = propertiesFileName + rdfFileName;
                trajectoryFileName = propertiesFileName + trajectoryFileName;

                //checks passed:Onto next stage of program
                fileNames(paramFileName, propertiesFileName, energyFileName, trajectoryFileName, rdfFileName);
            }
		    //Error print statements
            else if (args.length < 3) {
                System.out.println(" Please pick your file names to run code in the following format: \njava LJSimulation parameters energy trajectory");
                IOUtil.abuse();
            } else {
                System.out.println(" You need to enter a file called traj.xyz followed by the name of your output file");
                IOUtil.abuse();
            }
        } catch (FileNotFoundException e) {

            System.out.println(" But there was no parameters file detected so not a lot can be done without that. ");
        }
    }
    /* ********************************************************* */
	/* ********** Get data and handle Output files ************* */
	/* ********************************************************* */

    private static void fileNames(String paramFileName, String propertiesFileName, String energyFileName, String trajectoryFileName, String rdfFileName) throws IOException {

        //Get ready to scan input files
        BufferedReader propFileName = new BufferedReader(new FileReader(propertiesFileName));
        Scanner propertiesFile = new Scanner(propFileName);
        BufferedReader inputParams = new BufferedReader(new FileReader(paramFileName));
        Scanner params = new Scanner(inputParams);

        //read files
        int noParticles = IOUtil.skipToInt(params);
        double mass = IOUtil.skipToDouble(params);

        //Tells java how many to reference
        Particle3D[] p = new Particle3D[noParticles];
        String[] positionLabel = new String[noParticles];

        for (int i = 0; i < noParticles; i++) {
        	
           //initialises each element of the array.
            p[i] = new Particle3D();
            positionLabel[i]="position"+"["+i+"]";
            p[i].setMass(mass);


        }
        double epsilon = IOUtil.skipToDouble(params);
        double sigma = IOUtil.skipToDouble(params);
        params.close();

        //Set initial velocity
        MDUtilities.setInitialVelocities(IOUtil.skipToDouble(propertiesFile), p);


     	//Set the simulation box with instance variable;
      //  double rho =IOUtil.skipToDouble(propertiesFile);
        double rho =ReducedUnits.density(IOUtil.skipToDouble(propertiesFile),sigma);

        Vector3D boxDims = MDUtilities.setInitialPositions(rho, p);

      //  double cutOff = ReducedUnits.length(IOUtil.skipToDouble(propertiesFile),sigma);
        double cutOff = IOUtil.skipToDouble(propertiesFile);

        int noSteps = IOUtil.skipToInt(propertiesFile);
        double dt = IOUtil.skipToDouble(propertiesFile);
        propertiesFile.close();
        double t= ReducedUnits.time(mass, sigma, epsilon);

        System.out.println(" Sigma = " + sigma + " epsilon = "+ epsilon +" and mass = "+ p[0].getMass());//All masses are equal so can get away with using first one

        double binWidth = cutOff / 4000.0;//determines the width of the bins for the histogram


        //Instantiate and initialise output file objects
        PrintWriter trajectoryFile = new PrintWriter(trajectoryFileName);
        PrintWriter kineticEnergyFile = new PrintWriter(new FileWriter("kinetic" +  energyFileName));
        PrintWriter potentialEnergyFile = new PrintWriter(new FileWriter("potential"+energyFileName));
        PrintWriter totalEnergyFile = new PrintWriter(new FileWriter("total"+energyFileName));
        PrintWriter rdfFile = new PrintWriter(new FileWriter (rdfFileName));
        
        // instantiate and initialise energy array for error calculations 
        double[] totArray = new double[noSteps];
        double[] keArray =  new double[noSteps];
        double[] peArray = new double[noSteps];
        
        //instantiate and initialise histogram
        int[] nr=new int[(int)(binWidth/cutOff+0.5)];

        //one small (half) step for an array of particles, (one giant leap for a physics student.;-))
        Integrator.velocityNBody(p, dt * 0.5, cutOff, boxDims);
        //initialise average energy per particle
        double ke = Energy.NBodyKinetic(p)/noParticles;
        double pe = Energy.LennardJones(cutOff, boxDims, p)/noParticles;
        double tot = ke+pe;
        int count=0;
   
     
        for (int i=0;i<noSteps;i++){
        	
            Integrator.positionNBody(p, dt, boxDims.getX());
            t = t + dt;
            nr=Distribution.nr(binWidth, cutOff, boxDims, p);


             //initialise average energy per particle
            ke = Energy.NBodyKinetic(p)/noParticles;
            pe = Energy.LennardJones(cutOff, boxDims, p)/noParticles;
            tot = ke+pe;
            
            keArray[i]+= ke;
            peArray[i]+= pe;
            totArray[i]+= tot;  //fill arrays
            
          
             //Write energy and trajectory files
            if(i%4==0){       //to make file smaller
            	count+=1;
            	 	          
                Trajectory.fileWriter(trajectoryFile,p, i, positionLabel);
                potentialEnergyFile.println(t + "  " + pe + " ");
                kineticEnergyFile.println(t+" "+ ke +" ");
                totalEnergyFile.println(t + " " + tot+" ");
            }
            Integrator.velocityNBody(p, dt, cutOff, boxDims);            
        }
       
        double keMean =StatsUtil.mean(keArray);
        double peMean = StatsUtil.mean(peArray);
        double totMean =StatsUtil.mean(totArray);
        System.out.println(" Mean of kinetic energies is "+keMean);
        System.out.println(" Mean of potential energies is "+peMean);
        System.out.println(" Mean of summative total energies is "+ totMean);  
        
        System.out.println(" Standard error of kinetic energy is "+StatsUtil.dev(StatsUtil.devSq(keArray,keMean), noSteps));
        System.out.println(" Standard error of potential energy is "+StatsUtil.dev(StatsUtil.devSq(peArray,peMean),noSteps));
        System.out.println(" Standard error of total energy is "+StatsUtil.dev(StatsUtil.devSq(totArray,totMean),noSteps));
        System.out.println(" The number of data points taken for energies was "+ count);
      
        //close trajectory and energy files.
        trajectoryFile.close();
        totalEnergyFile.close();
        kineticEnergyFile.close();
        potentialEnergyFile.close();
        int sampleNumber=200;

        //perform the calculations for the RDF
        int[] gr = Distribution.calculateRDF(binWidth,nr,rho);
        System.out.println(" Number of points before smoothing = "+ gr.length);
        System.out.println(" Number of points after smoothing = "+ sampleNumber);
        //
        
        double mean= StatsUtil.mean(gr);
        double devSq = StatsUtil.devSq(gr,mean);
        double stdDev = StatsUtil.dev(devSq,gr.length);
        System.out.println(" Standard error of rdf = "+ stdDev);

        //This is to get rid of the noise in the RDF signal
        //Produces smoother set of data points by taking moving average.
        double[] gaussian = StatsUtil.gaussian(sampleNumber, devSq, mean);
        double[] smoothed=   StatsUtil.convolve(gr, gaussian, sampleNumber);
        double peak= StatsUtil.findSampleNo(StatsUtil.findPeak(smoothed),smoothed);

        //Lastly, write the radial distribution file
        for(int  i = 0; i<smoothed.length;i++){
        	
        	if(smoothed[i]>=peak){
        		System.out.println("peak occurs at "+(i+0.5)* binWidth);
        	}
        	rdfFile.printf("%10.2f %10.2f\n",(float) (i+0.5)* binWidth, (float) smoothed[i]/(noParticles));//0.5 to prevent error in rounding.
        }
        rdfFile.close();
        
        //helpful print
        System.out.println("\n Output files are called: " + rdfFileName +" " +"\n total" +energyFileName+ " " +"\n kinetic"+ energyFileName+ "\n potential"+energyFileName+ " " +trajectoryFileName+ " ");
        System.exit(0);
    }
    //static method to pick between the gas or liquid file
    private static String gasOrLiquid() throws IOException {
        String propertiesFileName = " ";
        System.out.println("Press 1 for gas, or 2 for liquid: ");
        String in = IOUtil.typedInput();

        if (in.equals("1")) {

            System.out.println(" Gas: ");
            propertiesFileName = "gas";

        } else if (in.equals("2")) {

            System.out.println(" Liquid: ");
            propertiesFileName="liquid";
        } else {
            IOUtil.abuse();
        }
        return propertiesFileName;
    }
}

