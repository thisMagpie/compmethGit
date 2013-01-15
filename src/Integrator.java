/*
 * ********************************************************************************* *
 * ********************* By Magdalen Berns for miniproject 2012 ******************** *
 * ********************************************************************************* *

 *   CONTAINS:

 *   Static method to update a single Particle3D particle position based on time step, dt and a velocity vector

 *   Static method method to update the velocity of the particle based on a time step, dt and and a force vector;

 *   Static methods to update the positions and velocities for all the particles in an array too

 *   When run these methods can make incremental changes to a simulation but beware that doing this creates a local truncation error

 *   Intended for a simulation with PCBs: these methods have not been tested for use with spacially asymmetric boundaries

 *   */

   class Integrator {

    //Updates the velocity for an array of particles
    //Arguments : Array of particles, timeStep, the cut off, cartesian 3D dimensions of the box (i.e. x, y, z,)
    public static void velocityNBody(Particle3D[] p, double dt, double cutOff, Vector3D boxDims){
        for (int i = 0; i < p.length; i++){
            Vector3D force = new Vector3D();
            for (int j=0; j < p.length; j++){
                if (i != j)
                {

                  force.add(Force.LennardJones(MinimumImage.minDistance(boxDims, p, i, j), cutOff));

                }
            }
            leapVelocity(p[i],dt, force);
        }
    }
    // Updates the position for all p.
    //Arguments : Array of particles, timeStep, a dimension of the box (e.g. x, y, z,)
    public static void positionNBody (Particle3D[] p, double dt, double nBox){

        for (Particle3D aP : p){
            leapPosition(aP, dt);
            
            // handle boundary conditions
            for (int j = 0; j < 3; j++){
                double modulus = aP.getPosition().getDim(j) % nBox;
                if (modulus < 0.0) aP.getPosition().setDim(j, modulus + nBox);
                else aP.getPosition().setDim(j, modulus);
            }
        }
    }
    // Integration instance to evolve the velocity  dv = vo + a * dt
    //Arguments : single particle. timeStep, a 3D representation of force (e.g. x, y, z,)
    private static void leapVelocity(Particle3D p, double dt, Vector3D force){
        p.setVelocity(Vector3D.add(p.getVelocity(), force.divide(p.getMass()).multiply(dt)));
    }
    // Integration instance to evolve the position : dx = v * dt
      // Arguments : single particle, timeStep,
    private static void leapPosition(Particle3D p, double dt){
        p.setPosition(Vector3D.add(p.getPosition(), p.getVelocity().multiply(dt)));
    }
}
