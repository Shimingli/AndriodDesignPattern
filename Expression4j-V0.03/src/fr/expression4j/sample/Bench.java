package fr.expression4j.sample;

import fr.expression4j.basic.MathematicalElement;
import fr.expression4j.basic.impl.RealImpl;
import fr.expression4j.core.Expression;
import fr.expression4j.core.Parameters;
import fr.expression4j.core.exception.EvalException;
import fr.expression4j.core.exception.ParsingException;
import fr.expression4j.factory.ExpressionFactory;

public class Bench {

	public Bench() {
		super();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		long startTime = 0;
		long endTime = 0;
		double result = 0;
		
		startTime = System.currentTimeMillis();
		for (int i=0; i<50; i++) {
			result = i/Math.cos(2.5e-3*8.2e4*i)*i;
			System.out.println("Result : " + result);
		}
		endTime = System.currentTimeMillis();
		System.out.println("Elapsed time = " + (endTime - startTime));
		
		Expression expression = null;
		try {
			expression = ExpressionFactory.createExpression("f(x)=x/cos(2.5e-3*8.2e4*x)*x");
			MathematicalElement result2 = null;
			Parameters parameters = ExpressionFactory.createParameters();
			RealImpl param = new RealImpl(0);
			parameters.addParameter("x",param);
			
			startTime = System.currentTimeMillis();
			for (int i=0; i<50; i++) {
				param.setRealValue(i);
				result2 = expression.evaluate(parameters);
				System.out.println("Result2 : " + result2.getRealValue());
			}
			endTime = System.currentTimeMillis();
			System.out.println("Elapsed time = " + (endTime - startTime));
		}
		catch (ParsingException pe) {
			System.out.println("Error in expression: " + pe);
			System.exit(0);
		}
		catch (EvalException ee) {
			System.out.println("Error in evaluation: " + ee);
			System.exit(0);
		}
		
		
	}

}
