import javax.swing.*;
import java.awt.*;
import java.awt.event.*;



public class SemaphoreGUI
{
  public static void main(String[] args)
  {
    SemaphoreComponent comp = new SemaphoreComponent();

    JFrame frame = new JFrame();
    JButton qAdd;
    JMenuBar menuBar;
    JMenu file;
    JMenuItem close;
    JPanel panel;


    menuBar = new JMenuBar();
    file = new JMenu("File");
    close = new JMenuItem("Exit");
    file.add(close);
    menuBar.add(file);
    panel = new JPanel();
    qAdd = new JButton("Add");
    panel.add(qAdd);

    frame.add(comp);
    frame.add(menuBar,BorderLayout.NORTH);
    frame.add(panel,BorderLayout.SOUTH);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(750, 550);
    frame.setTitle("Visualization of semaphores");
    frame.setVisible(true);


    class AddToQueueListener implements ActionListener
    {
      public void actionPerformed(ActionEvent e)
      {
        comp.addToQueue();
        comp.signal();
        comp.repaint();
        //System.out.println("BUTTON");
      }
    }
    ActionListener qButton = new AddToQueueListener();
    qAdd.addActionListener(qButton);

    class ExitListener implements ActionListener
    {
      public void actionPerformed(ActionEvent e)
      {
        System.exit(0);
      }
    }
    ActionListener exitListener = new ExitListener();
    close.addActionListener(exitListener);
  }
}
