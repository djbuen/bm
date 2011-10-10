/*
 * ExpressionPlot.java
 *
 * Created on 16 mars 2006, 15:44
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
import org.jdesktop.swingx.JXGraph;
import org.nfunk.jep.JEP;
import org.nfunk.jep.function.Round;

/**
 *
 * @author gfx
 */
class ExpressionPlot extends JXGraph.Plot {
    private final String expression;
    private final  JEP parser;
    private int precision;
    
    public ExpressionPlot(String expression) {
        this.expression = expression;
        
        parser = new JEP();
        parser.addStandardConstants();
        parser.addStandardFunctions();
    }

    public double compute(double value) {
        parser.addVariable("x", value);
        parser.parseExpression(expression);
        
        return Round(parser.getValue(),6);
    }
    //wa ni labot test rani
    public static void main(String args[]){
    	//test
    	ExpressionPlot e = new ExpressionPlot("x^2");
    	System.out.print(e.getValue());
    }

	private String getValue() {
		// TODO Auto-generated method stub
		return this.expression;
	}
	
	public static double Round(double d, int Rpl) {
		  float p = (float)Math.pow(10,Rpl);
		  d = d * p;
		  float tmp = Math.round(d);
		  return (double)tmp/p;
		  }

}

