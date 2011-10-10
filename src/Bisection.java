import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import org.jdesktop.swingx.JXGraph;
import org.jdesktop.swingx.JXGraph.Plot;
import org.jdesktop.swingx.JXTable;
import org.nfunk.jep.JEP;


public class Bisection extends JFrame implements Runnable{
	
	private static String[] columnNames;
	private static JFrame frame;
	private static BisectionCalculator bisectionCalc;
	private static JXGraph.Plot d;
	public static int iteration;
        public  static int flag=0;


	private DefaultTableModel model;
	private JXTable table;
	private Object[][] datas;
	private JScrollPane pane;
	private JTableHeader header;
	
    @SuppressWarnings("static-access")
	public Bisection(ExpressionPlot d,int point1,int point2,int precision) {
                this.d = d;
		frame = new JFrame("Data");
		columnNames = new String[] {"i",
                "a",
                "f(a)",
                "c",
                "f(c)",
                "b",
                "f(b)",
                "error"};

		datas = new Object[][] {
			    {"", "",
			     "", "", "","",""},
			    };
		model = new DefaultTableModel(datas,columnNames);
		table = new JXTable(model);

		frame.setLayout(new BorderLayout());
		frame.add(table.getTableHeader(), BorderLayout.PAGE_START);
		frame.add(table, BorderLayout.CENTER);
		

		 pane = new JScrollPane(table);
		 table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);	
		 header = table.getTableHeader();
		 
		 bisectionCalc = new BisectionCalculator(table, model,d,point1,point2,precision);
		 bisectionCalc.execute();
		 
		 frame.add(pane);
		 frame.setSize(800,800);
		 frame.setUndecorated(true);
		 frame.getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);
		 frame.setVisible(true);
	}
	
	//para test rani
	public static double getRoot(Plot d2,double point1,double point2,int precision){
		double del = 1e-6;
		double midpoint = (point1+point2)/2;
		iteration = -1;
		while(Math.abs(d2.compute(point2)-d2.compute(point1)) > del){
			if(d2.compute(point1) * d2.compute(midpoint) < 0){
				point2=midpoint;
                        }
			else
				point1=midpoint;		
			midpoint = (point1+point2)/2;
		iteration++;
		}
                
                return ExpressionPlot.Round(midpoint, precision);
	}

        //para test rani
       private static void setRootFlag(double point1, double point2, double midpoint) {
            flag=1;
       }
	//para test rani
	public static void main(String args[]) {
		//Bisection bm = new Bisection();
		//Thread worker = new Thread(bm);
		//worker.start();
	}

	@Override
	public void run() {
	}

        public double getRoot(){
            return bisectionCalc.root;
        }

        public int getIteration(){
            return bisectionCalc.iteration-1;
        }
}
