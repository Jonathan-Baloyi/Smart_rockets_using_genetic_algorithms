/**
 * 
 * @author Jonathan Richard B
 */

/*
 * The class manages the rocket. it controls the rocket
 * it performs MUTATION and CROSSOVER
 * 
 * 
 */
public class RocketDNA 
{
  public VectorRep [] genes;

  
  double anglOfRocket;

  public RocketDNA() {
    
     
    genes = new VectorRep[RocketPopulation.LT];//generates first generation of rockets 
    
    //iterate through the array of genes
    for(int i = 0; i < genes.length; i++) 
    {
      
        //the random angle is assigned to each rocket/Vector 
      anglOfRocket= Math.random() * 2 * Math.PI;
      
      //each gene is represented in a 2d vector accepts a random angle
      genes[i] = new VectorRep(Math.cos(anglOfRocket), Math.sin(anglOfRocket));
      
      //each gene is able to daviate when the multiplicative factor is applied to it.
      genes[i].multplyVector(Math.random() * 0.1);//0.1 is the multiplicative factor
    }
  }

  public RocketDNA(VectorRep[] newgenes) 
  {
    genes = newgenes;
  }

  /**
   * 
   * One point crossover is used to achieve reproduction
   * 
   * Xij (t) ={ uij if j E J
   *          { xij otherwise
   * 
   */
  public RocketDNA crossover(RocketDNA chromosm) 
  {
     VectorRep[] child = new VectorRep[genes.length];
    
     int crossover = (int) (Math.random() * genes.length);//randomly chooses a middle point
    
     //then take one of the half based the bigger one to facilitate diversity
     for(int i = 0; i < genes.length; i++) 
     {
       if(i > crossover) 
       {
         child[i] = genes[i];
       }
       else 
       {
         child[i] = chromosm.genes[i];
       }
    }
  
    RocketDNA newgenes = new RocketDNA(child); //return back the chromosome of the child
    
    return newgenes;
  }

  /**
   * Random multiplicative mutation is used by using the mutation rate of 0.01 
   * which is passed in the main class
   * 
   * ui (t) = xi1 (t) + F(xi2(t) + xi3(t))
   */
  public void mutate(double mutation) 
  { 
    for(int i = 0; i < genes.length; i++)
    {
       if(Math.random() < mutation) 
       {
         double rocketAngle = Math.random() * 2 * Math.PI;
         
         genes[i] = new VectorRep(Math.cos(rocketAngle), Math.sin(rocketAngle));
         
         genes[i].multplyVector(Math.random() * 0.1);
      }
    }
  }
}
