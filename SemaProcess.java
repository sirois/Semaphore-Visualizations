import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.*;
import java.awt.*;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;


public class SemaProcess extends Thread
{
  private int x, y;
  private boolean hasProc =false;
  private Box process;
  private SemaphoreComponent sc;

  public boolean hasProcess(){
    return hasProc;
  }
  public SemaProcess(int x1,int y1, SemaphoreComponent sc)
  {
    this.sc =sc;
    x = x1 ;
    y = y1 ;
    hasProc = false;
  }

  public void draw (Graphics2D g2)
  {
    Rectangle processContainer=new Rectangle (x,y, 100, 100);
    g2.fill(processContainer);
    if(hasProc){
      process.moveTo(x,y-20);
      process.draw(g2);
    }
  }

  public void addProcess(Box box)
  {
    process = box;
    hasProc = true;
  }

  public void killProcess()
  {
    hasProc = false;
  }

  public void run()
  {
    try {
      for(int i = 0; i < 5; ++i) {
        if(process!=null){
          System.out.println(process.getLetter() + " is being served");
          Thread.sleep(1500);
        }
      }//for loop
      System.out.println(process.getLetter() + " is done.. NEXT!");
      killProcess(); sc.signal();
      //call signal, because this process is now finished!
    }//try block
    catch(InterruptedException e) {
      System.out.println("Interrupted");
    }
  }//run method

}//SemaProcess class
