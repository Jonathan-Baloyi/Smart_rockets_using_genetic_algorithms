import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

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
 * The speed of the rockets really matters and acceleration of the rocket when it's fired
 * not forgetting the direction of the rocket
 * The rocket can rotate for 360 degrees in any direction
 * MUTATE() AND CROSSOVER() functions are held in chromosome.
 * 
 * SELECTION OF POPULATION IS IMPLEMENTED UNDER ROCKET POPULATION
 */

public class MainWindow extends JFrame implements ActionListener {
    
  String space=" ";
  double mutationRate = 0.01;
  int inntial_Population_of_rockets = 1000;
  
  //it creates the rocket population
  RocketPopulation population = new RocketPopulation(mutationRate, inntial_Population_of_rockets);
  JButton start_button;
  JButton exit_button;

  public MainWindow() {
    
  
    setTitle("Differential evolution : smart rockets : Optimisation Project");
    setSize(640, 700);
    setVisible(true);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    start_button = new JButton("START");
    start_button.addActionListener(this);
    
    exit_button=new JButton(space+"Exit"+space);
    
    exit_button.addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) 
        {
           int choice=JOptionPane.showConfirmDialog(null, "Are you sure \nYou want to exit?");
           
           if(choice==JOptionPane.YES_OPTION)
           {
               dispose();
           }
        }
    });
    
    setLayout(new FlowLayout());
    add(population);
    add(start_button);
    add(exit_button);
  }

  //Action performed for the start button
  //when clicked, it spreads the 1000 rockets
  public void actionPerformed(ActionEvent e){
    
     population.draw = !population.draw;
    
    if(population.draw) 
    {
      population.repaint();
      start_button.setText("PAUSE");
    }
    else 
    {
      start_button.setText("START");
    }
  }
  
  public static void main(String[] args)
  {
      new MainWindow();
  }
 
}


