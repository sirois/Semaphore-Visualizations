import java.awt.geom.*;
import java.awt.*;


public class Box extends SemaProcess
{
	private final int BOX_DIM = 20;
	int xPos, yPos, val;
	char letter=' ';
	private Rectangle rect;

	public Box(int x, int y, char letter, SemaphoreComponent sc)
	{
		super(x, y,sc);
		xPos = x;
		yPos = y;
		this.letter = letter;

		rect = new Rectangle(x, y, BOX_DIM, BOX_DIM);
	}
	public int getX()
	{
		return xPos;
	}
	public int getY()
	{
		return yPos;
	}

	public void moveTo(int x, int y)
	{
		rect.setLocation(x,y);
		xPos = x;
		yPos = y;
	}
	public char getLetter(){
		return letter;
	}
	public void draw(Graphics2D g2)
	{
		g2.setColor(Color.GREEN);
		g2.draw(rect);
		g2.setColor(Color.BLACK);
		g2.drawString("" + letter, xPos + 8, yPos + 12);
	}
}
