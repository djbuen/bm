import java.awt.List;
import java.util.concurrent.ExecutionException;

import javax.swing.JTable;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;

import org.jdesktop.swingx.JXGraph.Plot;
import org.jdesktop.swingx.JXTable;


public class BisectionCalculator extends SwingWorker<Integer, Object[]>{
	JXTable jt;
	DefaultTableModel dm;
	Plot d2;
	int precision=6;
        double point1;
        double point2;
    public int iteration;
    public double root;
    
	public BisectionCalculator(final Object jt,final DefaultTableModel dm,final Plot d2,int point1,int point2,int precision){
		this.jt = (JXTable) jt;
		this.dm = dm;
		this.d2 = d2;
                this.point1=point1;
                this.point2=point2;
                this.precision=precision;
	}
	
	public static void main(String args[]){
		
	}
	@Override
	protected Integer doInBackground() throws Exception {
		// TODO Auto-generated method stub
		
		double del = 1e-6;
		double midpoint = (point1+point2)/2;
		int iteration=0;
                int flag=0;

		while(Math.abs(d2.compute(point2)-d2.compute(point1)) > del){
		//while(iteration < 100){
			//System.out.println(Math.abs(d2.compute(point2)-d2.compute(point1)) + " > " +del);
			publish(recordData(iteration,point1,d2.compute(point1),midpoint,d2.compute(midpoint),point2,d2.compute(point2),Math.abs(d2.compute(point2)-d2.compute(point1))/(2^(iteration+1))));
			if(d2.compute(point1) * d2.compute(midpoint) < 0)
				point2=midpoint;
			else
				point1=midpoint;
                        if( ExpressionPlot.Round(d2.compute(midpoint),precision) == 0 && flag==0){
                            root = ExpressionPlot.Round(midpoint, precision);
                            this.iteration = iteration+1;
                            flag=1;
                        }
                        System.out.println(ExpressionPlot.Round(d2.compute(midpoint),1));
			midpoint = (point1+point2)/2;
			iteration++;
		}		
		System.out.println("root is " + ExpressionPlot.Round(point1,6));
		
                return iteration-1;
	}
	
	private Object[] recordData(Integer iteration, double point1, double compute,
			double midpoint, double compute2, double point2, double compute3,double error) {
		Object []data = {iteration,  ExpressionPlot.Round(point1, precision),  ExpressionPlot.Round(compute, precision),
				ExpressionPlot.Round(midpoint, precision),  ExpressionPlot.Round(compute2, precision),  ExpressionPlot.Round(point2, precision),  ExpressionPlot.Round(compute3, precision),ExpressionPlot.Round(error, precision)};
		return data;		
	}

	protected void process(java.util.List<Object[]> publishedVals){
		for(int i=0;i<publishedVals.size();i++)
		dm.addRow(new Object[]{publishedVals.get(i)[0],publishedVals.get(i)[1],publishedVals.get(i)[2],publishedVals.get(i)[3],publishedVals.get(i)[4],publishedVals.get(i)[5],publishedVals.get(i)[6],publishedVals.get(i)[7]});
	}

	protected void done(){
		try {
			System.out.print(get());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
