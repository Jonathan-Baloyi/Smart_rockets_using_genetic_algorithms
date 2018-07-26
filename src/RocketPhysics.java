
/**
 * 
 * @author Jonathan Richard B
 */

/** 
 * The class contains the physics behind the rocket/projectile/missile
 */

public class RocketPhysics 
{
  VectorRep rocketLocation;                     
  VectorRep rocketSpeed;
  VectorRep rocketAcceleration;
  VectorRep target;

  double fitnessValue;
          
  RocketDNA rdna;

  int genesNum = 0;

  boolean RocketHitTarget = false;

  public RocketPhysics(VectorRep lc, RocketDNA Rdna) 
  {
    rocketSpeed = new VectorRep();
    rocketAcceleration = new VectorRep();
    this.rocketLocation = lc.getVector();
    target = RocketPopulation.TheTarget.getVector();
    rdna = Rdna;
  }

  //increases the accelaration of the rocket
  //by adding the force to it. <Newtons Law>
  public void applyForceToRocket(VectorRep force)
  {
    rocketAcceleration.addVector1(force);
  }

  //it performs an update on the rockets
  //it increases the velocity of each rocket randomly
  //by adding the random speed to the rocket/Vector
  public void updateRocket() 
  {
    rocketSpeed.addVector1(rocketAcceleration);//adds accelaration to the velocity vector
    rocketLocation.addVector1(rocketSpeed);//changes the location of the rocket by adding velocity to the rocket
    rocketAcceleration.multplyVector(0);//clears the accelaration at the end
  }

  public void CalcFitness() 
  {
    //calculate distance from rocket to TheTarget. the lower the distance, the higher the fitnessValue
    double dist = VectorRep.Calculate_distance_to_target(rocketLocation.X_pos, rocketLocation.Y_pos, target.X_pos, target.Y_pos);
    fitnessValue = 1 / dist;
  }

  public void StartEvolving() 
  {
    checkTarget();
    
    if(!RocketHitTarget) 
    {
      applyForceToRocket(rdna.genes[genesNum]); 
      genesNum = (genesNum + 1) % rdna.genes.length;
      updateRocket();
    }
  }

  //it checks if the rocket has hit the target
  public void checkTarget() 
  {
    double dist = VectorRep.Calculate_distance_to_target(rocketLocation.X_pos, rocketLocation.Y_pos, target.X_pos, target.Y_pos);
    
    //tests if the distance of the target and the rocket are within 10 units
    if(dist < 15) 
    {
      RocketHitTarget = true;
    }
  }

  public double getFitnessValue() 
  {
    return fitnessValue;
  }

  public RocketDNA getChromsm()
  {
      
    return rdna;
  }
}
