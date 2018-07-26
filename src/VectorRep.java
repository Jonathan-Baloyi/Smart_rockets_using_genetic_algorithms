/**
 * 
 * @author Jonathan Richard B
 */

/*
 * The solution is represented using vectors which are 2 dimessional
 * it eveolves speed and direction in which the rockets hit the target including the 
 * Acceleration of the rocket.
 * 
 * Innitially, the rockets are randomly popularised and they randomly increase in the number of population
 * 
 * so after a few generations, the rockets 
 * The rockets are able to spot the target
 * 
 * it basically investigate if the rockets can hit the target at a specified time interval
 * The speed of the rockets really matters and acceleration of the rocket when it's fired.
 */

public class VectorRep 
{
  public double X_pos;
  public double Y_pos;

  public VectorRep() {
  }

  public VectorRep(double x, double y)
  {
    this.X_pos = x;
    this.Y_pos = y;
  }

  public VectorRep getVector()
  {
    return new VectorRep(X_pos, Y_pos);
  }

  public VectorRep setVector(double x, double y) 
  {
    this.X_pos = x;
    this.Y_pos = y;
    return this;
  }

  //this function will change the location of the rocket.
  //it starts with 0 velocity and the velocity increases randomly
  //while bringing change to the rocket.
  //the change of the rocket on the frame is achieved through 
  //The update function under rocket physics
  public VectorRep addVector1(VectorRep v)
  {
    X_pos =X_pos + v.X_pos;
    Y_pos = Y_pos + v.Y_pos;
    return this;
  }

  /**
   * The method adds the x-coordinate and the y-coordinate 
   * to the vector representation.
   * @param x
   * @param y
   * @return 
   */
  public VectorRep addVector2(double x, double y) 
  {
    this.X_pos =this.X_pos + x;
    this.Y_pos =this.Y_pos + y;
    return this;
  }

  /**
   * Multiply the whole vector using the multiplicative factor
   * @param n
   * @return 
   */
  public VectorRep multplyVector(double factor) 
  {
    this.X_pos = this.X_pos * factor;
    this.Y_pos = this.Y_pos * factor;
    return this;
  }

  //distance between projected vector 1 and vector 2 
  //so that they can be used to represent
  //The change is x and change in y
  //this is used during Differential Genetic Algorithms
  //finds the magnitude 
  static public double Calculate_distance_btwn_rockets(VectorRep Vector1, VectorRep Vector2) 
  {
      //Calculates the difference between the x values of Vector 1 and Vector 2 : // dx= dx
      double dx = Vector1.X_pos - 
                         Vector2.X_pos;
    
      //Calculates the difference between the y values of Vector 1 and Vector 2 : // dy= dy
      double dy = Vector1.Y_pos - 
                         Vector2.Y_pos;
      
      
      double distBtweenVec=Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
    
      return distBtweenVec ;
  }

  //calculates the distance between the x and y position
  //on the actual positions : i.e. coordinates
  //this is used to check if the rocket has hit the target
  static public double Calculate_distance_to_target(double x_pos1, double y_pos1, double x_pos2, double y_pos2) 
  {
      //Calculates the difference between the first x coordinate and the 2nd x coodinate: // dx= dx
      double dx = x_pos1 - x_pos2;
    
      //Calculates the difference between the first y coordinate and the 2nd y coodinate: // dx= dx
      double dy = y_pos1 - y_pos2;
      
      double distBtweenCoordinates=Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
      
      return distBtweenCoordinates;
  }

  //The method represents a solution as a 2D vector
  static public final double representVector(double val,double startPosVec1, double stopPosVec1,double startPosVec2, double stopPosVec2)
  {
      double vectorRepresentation=startPosVec2 + (stopPosVec2 - startPosVec2) * ((val - startPosVec1) / (stopPosVec1 - startPosVec1));
      return vectorRepresentation ;
  }
}
