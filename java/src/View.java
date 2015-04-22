import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class View extends JFrame
{
    private int x[],y[];
    private BlenderPanel p;
    private int order;
    private Polygon poly;
    public View(Point points[])
    {
        super("Blender Show!");
        x=new int[points.length];
        y=new int[points.length];
        int i=0;
        for(Point p: points){
            x[i]=(int) points[i].x.doubleValue();
            y[i]=(int) points[i].y.doubleValue();
            i++;
        }
        for( i=0;i<x.length;i++)
        {
            if(x[i]>0)
                x[i] = x[i];
            else
                x[i] = x[i]*(-1);
            if(y[i]>0)
                y[i] = 100 - y[i];
            else
                y[i] = 100 - y[i]*(-1);
        }
        order = 1;
        poly = new Polygon(this.x,this.y,this.x.length);
        p = new BlenderPanel();
        add(p,BorderLayout.CENTER);
        
    }
    private class BlenderPanel extends JPanel
    {
        public BlenderPanel()
        {
            setBackground(Color.WHITE);
        }
        @Override
        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            g.setColor(Color.BLACK);
        /*    for(int w=0;w<order;w++)
            {
                g.fillOval(x[w], y[w], 5, 5);
            }*/
            g.drawPolygon(poly);
        }
    }
}