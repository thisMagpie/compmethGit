====== README ======

* ********************************************************************************* *
* ********************* By Magdalen Berns for miniproject 2012 ******************** *
* ******************************** Group number 20 ******************************** *
* ********************************************************************************* *


IMPORTANT This program was written using Java 7 using JDK 1.7 and may not run with 1.6 
If there are any problems getting to a computer with 1.6 
you can revert 'for each' loops to normal 'for' loops quite easily and that should solve.

Contents:

Input Files:
* liquid 
* gas
* parameters 

Output files:

* liquidtraj.xyz
* gastraj.xyz
* kineticenergyliquid
* kineticenergygas
* potentialenergygas
* potentialenergyliquid
* totalenergygas
* totalenergyliquid
* gasRDF
* liquidRDF


//More detailed discriptions are inside the classes themselves.

Classes:

* Distribution.java //Handles RDF calculations
* IOUtil.java   //Handy class to help with IO processes methods make it possible to have nicely formatted input data files     
* Particle3D.java    // properties of a 3d particle go get
* Vector3D.java //helps with cartesian coordinates for 3D vectors
* Energy.java  //Computers Lennard Jones Energy as well as kinetic      
* LJSimulation.java  
* ReducedUnits.java // Converts into reduced units not much point to this yet
* Force.java //computes lennard jones force.        
* MDUtilities.java   
* StatsUtil.java //RDF smoothing 
* Integrator.java  //Handles integration 
* MinimumImage.java //minimum image algorithm  
* Trajectory.java //Writes a file in the trajectory .xyz format.

Note: There are some additional files for the last part of the experiment but they are not fully tested.
Though the reduced units class can help make things easier with handling the data input from them.

Instructions:

* Move the 'liquid', 'gas' and 'parameters' files in the InputFiles folder to the same directory as 
  The .java files (if you are using in eclipse or a similar development environment 
* Your directories may be configured differently by default.)

* Open a terminal and type:

>cd path/to/group20mdproject


* If there are no class files in the folder type the following to compile:

>javac *.java

* To run,type the following into the terminal:

   java LJSimulation parameters energy traj
	
IMPORTANT: 

* Program machine concatinates the trajectory filename with an appending .xyz so typing file.xyz 
* could throw an error. untested.

When the program is running:
*
*
* You will then be prompted to choose between a liquid or a gas - files must exist and be in correct format:
* If you do not input the three arguments when you run the program it will exit following an abusive statement.
* If you do not input the correct name for parameters the program will exit following an abusive statement.
* You must wait until the java program has finished running before you can run the output files.


To run trajectory in vmd type the following into terminal:

>vmd file.xyz

To run in energy type the following into terminal (any order):

>xmgrace potential kinetic total

To run in RDF type the following into terminal:


>xmgrace RDF


KNOWN BUGS:

May not work in java 6 untested.

WISHLIST: 

* Make it possible for the user file exension and type options for csv and perhaps even open in gnu 
* Plot with data labels etc
* To develop reduced units class
* To be able to mix particles of different masses
* To use higher order algorithm to truncate velocity and position.
* Extend with a class to handle polar and spherical cylindrical coordinates
* Be a bit taller (unrelated). 

Intructions on how to run with your own input files:

* Open a terminal and type: 

>java Test "any_parameter_file" "anything" "anything".xyz'

	
Without speech marks "anything" means the name of files is a user choice:
"any_parameter_file" must be in the program director or path for program to run. It must be in format:

= FORMAT =
particleNo 0
mass = 0.0
epsilon = 0.0
sigma = 0.0

Your substance files must be in the format:

= FORMAT =
Substance
temperature = 0.0
density = 0.0
cutoff = 0.0
steps = 0
timestep = 0.0

Enjoy!
