import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

/**
 * 
 * @author Jonathan Richard B
 */

public class RocketPopulation extends JPanel implements MouseListener
{
  
  
  public static int panelSize = 610;

  public double mutationRate;
  
  public RocketPhysics[] population;//population of rockets
  
  public ArrayList<RocketPhysics> matingPool;
  
  public int generationsCounter;
  
  public int cycleNum;
  
  public static int LT = panelSize; // the life time of a rocket
  
  public boolean draw;
  
  public static VectorRep TheTarget;

  public RocketPopulation(double mutRate, int number)
  {
       
    draw = false;
    
    TheTarget = new VectorRep(panelSize/2, 24);

    mutationRate = mutRate;
    
    population = new RocketPhysics[number];//the number refers to the size of the individuals in the population. eg 1000
    
    matingPool = new ArrayList<RocketPhysics>();
    
    generationsCounter = 0;

    
    /**
     * The for loop generates the initial population 
     * and place in the frame
     */
    for(int i = 0; i < population.length; i++) 
    {
      //generate innitial population
      VectorRep location = new VectorRep(panelSize / 2, panelSize - 20);
      population[i] = new RocketPhysics(location, new RocketDNA());
    }

    setBorder(BorderFactory.createLineBorder(Color.red));
    setBackground(Color.BLACK);
    
    //this allows for the change of the location of the rocket
    //by just dragging the mouse.
    addMouseListener(new MouseAdapter()
    {
        @Override
        public void mousePressed(MouseEvent e)
        {
            TheTarget.setVector(e.getX(), e.getY()); 
        }
    
    });
       
  }

 
  //gets the prefered sizes for the dimensions
  public Dimension getPreferredSize() 
  {
    return new Dimension(panelSize, panelSize);
  }

  public void paintComponent(Graphics g) 
  {
     super.paintComponent(g);
     
     //it draws the strings on the panel
     g.setColor(Color.WHITE);
     g.drawString("Generation Counter: " + getGenerations(), 10, 16);
     
     g.setColor(Color.white);
     g.drawString("Number of Cycles to next generation: " + (LT - cycleNum), 10, 34);
     
     //it draws the TheTarget
     g.setColor(Color.red);
     g.fillOval((int)TheTarget.X_pos-10, (int)TheTarget.Y_pos, 30, 30);
     g.drawString("Target", (int)TheTarget.X_pos - 10,(int) TheTarget.Y_pos);
     
     //Draws the rockets in the panel
     g.setColor(Color.YELLOW);
     
     for(int i = 0; i < population.length; i++) 
     {
       g.fillOval((int)population[i].rocketLocation.X_pos, (int)population[i].rocketLocation.Y_pos, 5, 20);
     }
     
     //algorithm : when the thread is started, fitness is evaluated, selection is made and reproduction
     //for new offsprings occurs
      if(draw) 
      {
        if(cycleNum < LT) 
        {
          fireRocket();//it starts evolving
          cycleNum++;
        }
        else 
        {
          cycleNum = 0;
          fitness();
          selectPopulation();
          reproduction();
        }
     }
     try 
     {
       Thread.sleep(10);
     }  
     catch(InterruptedException e) {}

    repaint();
  }

  public void fireRocket()
  {
    for(int i = 0; i < population.length; i++)
    {
      population[i].StartEvolving();
    }
  }

  public void fitness() 
  {
    for(int i = 0; i < population.length; i++) 
    {
      population[i].CalcFitness();
    }
  }

  /*
   * it uses proportional selection which calculates the percentages
   * and maps the fitnessValue between 0-1 both included,
   * and creates the roulette wheel
   */
  public void selectPopulation() 
  {
    matingPool.clear();

    for(int i = 0; i < population.length; i++) 
    {
      double normalFitness = VectorRep.representVector(population[i].getFitnessValue(), 0, getMaxFitness(), 0, 1);
      
      int n = (int) (normalFitness * 100);
      
      for(int j = 0; j < n; j++) 
      {
        matingPool.add(population[i]);//The population of fitter individuals is passed to the next generation
      }
    }
  }

  /*
   * it performs sexual reproduction
   */
  public void reproduction()
  {
    for(int i = 0; i < population.length; i++)
    {
      int parent1 = (int) (Math.random() * matingPool.size());
      int parent2 = (int) (Math.random() * matingPool.size());

      while(parent2 == parent1)
      {
        parent2 = (int) (Math.random() * matingPool.size());
      }

      RocketPhysics mom = matingPool.get(parent1);
      RocketPhysics dad = matingPool.get(parent2);

      RocketDNA momgenes = mom.getChromsm();
      RocketDNA dadgenes = dad.getChromsm();

      RocketDNA child =  momgenes.crossover(dadgenes);

      child.mutate(mutationRate);

      VectorRep location = new VectorRep(panelSize / 2, panelSize - 20);
      
      population[i] = new RocketPhysics(location, child);
    }
    generationsCounter++;
  }

  public int getGenerations() 
  {
    return generationsCounter;
  }

  public double getMaxFitness() 
  {
    double rcrd = 0;    //record
    
    for(int i = 0; i < population.length; i++) 
    {
      if(population[i].getFitnessValue()> rcrd) 
      {
        rcrd = population[i].getFitnessValue();
      }
    }
    return rcrd;
  }

    @Override
    public void mouseClicked(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
