import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;
import javax.swing.JPanel ;
import java.awt.event.MouseListener ;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent ;
import java.util.*;



public class SemaphoreComponent extends JComponent {

  //this will act as the 'queue' of customers
  private char letter = 'A';

  public ArrayList<SemaProcess> bankTellers = new ArrayList<SemaProcess>();
  //bank tellers are 'blackboxes' that do something with the customer process
  public ArrayList<Box> customers = new ArrayList<Box>();
  //customers is the queue of incoming customers.
  //we used arraylist as our queue - take from index 0 and add to index (size-1)


  public SemaphoreComponent()
  {

    bankTellers.add(new SemaProcess(25,75,this));
    bankTellers.add(new SemaProcess(150,75,this));
    bankTellers.add(new SemaProcess(275,75,this));
    //there are three bank tellers - a maxiumum of three processes or "customers"
    //can be served at one time
  }


  public void paintComponent(Graphics g)
  {
    //go through and redraw all the 'customers' in the list
    Graphics2D g2 = (Graphics2D)g;
    g2.setColor(Color.BLACK);
    for (int x=0; x<bankTellers.size(); x++){
      bankTellers.get(x).draw(g2);

      if(bankTellers.get(x).hasProcess() && !bankTellers.get(x).isAlive()){
        bankTellers.get(x).start();
      }//start serving new customers

    }
    for(int x = 0; x<customers.size(); x++)
    {
      customers.get(x).moveTo(50+40*x,300);
      customers.get(x).draw(g2);
    }

  }//paintComponent
  
  public void signal()
  {
    for(int i = 0; i<3; i++)
    {
      if(!bankTellers.get(i).hasProcess() && customers.size()>0 && customers.get(0) != null)
      {
        bankTellers.set(i,new SemaProcess(25+(i*125),75,this));
        bankTellers.get(i).addProcess(customers.get(0));
        customers.remove(0);

      }
    }
    repaint();
  }//signal is called when a customer is done.
  //open up the bankTeller and take any new customers from the queue

  public void addToQueue()
  {
    customers.add(new Box( 50, 300, letter++,this));
    repaint();
  }

}//SemaphoreComponent
