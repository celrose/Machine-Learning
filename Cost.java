import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;


//Rocelle Cadavos

public class Cost {

	public int m;
	public int n = 3;
	public double[][] x = new double[100][4];



	public void load_file(String filename) {
		try(BufferedReader reader = new BufferedReader(new FileReader(filename))) {
			
			String[] temp;
			String line = null;
    		int counter = 0;

    		while ((line = reader.readLine()) != null) {
    			for(int i = 0; i < n; i++){
					temp = line.split(",");
					x[counter][i] = Double.parseDouble(temp[i]);	
    			}
    			counter++;
    			
    		}
    		//initialize theta with whichever value you like
			double[] theta = {2, 1, 2};

    		//number of training examples
    		m = counter;
    		System.out.println("Training examples: " + m);

    		//computing for Hypothesis
    		double[] hypothesis = new double[m];

    		for(int i = 0; i < m; i++) {
				hypothesis[i] = (theta[0]) + (x[i][0] * theta[1]) + (x[i][1] * theta[2]);
    		}

    		cost_func(m, hypothesis, x);

		} catch(IOException c) {
			c.printStackTrace();
		}
	}

	public void cost_func(int m, double[] hypothesis, double[][] x) {
		double[] cost = new double[100];
		double sum = 0;
		double squared = 0.00;

		//Cost function: j = 1/2m (hypothesis - y)^2

		for(int i = 0; i < m; i++) {
			//this part is the (h - y)^2
			squared = (hypothesis[i] - x[i][2]) * (hypothesis[i] - x[i][2]);
			//this part is the summation of all (h - y)^2
			sum = sum +	squared;
			//System.out.println(sum);
		}

		//(1 / 2 * m)(sum)
		cost[0] = sum /  2 * m;
		System.out.println("Cost is:" + cost[0]);
	}

	public static void main(String[] args) {

		Cost sample = new Cost();
		String filename = "HousePricingRelationship.in";
		sample.load_file(filename);
	}
}
