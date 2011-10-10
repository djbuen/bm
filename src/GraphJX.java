
import java.awt.Color;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JFrame;

import org.jdesktop.swingx.JXGraph;




public class GraphJX extends JFrame implements Runnable{
	
	/**
	 * 
	 */
	public GraphJX(ExpressionPlot exp){
		  createGraphDemo(exp);   
		 
	     } 
	  
	     //TODO inject properties 
	     private void createGraphDemo(ExpressionPlot exp) { 
	    	 
	         Point2D origin = new Point2D.Double(0.0d, 0.0d); 
	         Rectangle2D view = new Rectangle2D.Double(-10.0d, -10.0d, 20.0d, 20.0d); 
	         JXGraph graph = new JXGraph(origin, view, 5, 5, 3,3);
	         //JXGraph.Plot d = new ExpressionPlot("x^4-((7*x)^3)+(18*x)-(20*x)+8"); 
	         //JXGraph.Plot d = new ExpressionPlot("(cos(x))^2 + 6 -x");	         // anhi lang try ug butang sa expression like sin(x), polynomials
	         JXGraph.Plot d = exp;
	         graph.addPlots(Color.red,d);

	         getContentPane().add(graph);
	         pack();
	         setVisible(true);
	         setTitle("The Graph");
	     } 

	public static void main(String args[]){
		GraphJX graph = new GraphJX(new ExpressionPlot("cos(x)-x"));
		Thread worker = new Thread(graph);
		worker.start();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub	
	}
}
