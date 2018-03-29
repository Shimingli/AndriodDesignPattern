//Copyright 2006 Stephane GINER
//
//   Licensed under the Apache License, Version 2.0 (the "License");
//   you may not use this file except in compliance with the License.
//   You may obtain a copy of the License at
//
//   http://www.apache.org/licenses/LICENSE-2.0
//
//   Unless required by applicable law or agreed to in writing, software
//   distributed under the License is distributed on an "AS IS" BASIS,
//   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//   See the License for the specific language governing permissions and
//   limitations under the License.

package fr.expression4j.sample;

import java.util.List;

import fr.expression4j.basic.MathematicalElement;
import fr.expression4j.basic.OperatorManager;
import fr.expression4j.core.Catalog;
import fr.expression4j.core.Expression;
import fr.expression4j.core.ExpressionModel;
import fr.expression4j.core.Parameters;
import fr.expression4j.core.exception.EvalException;
import fr.expression4j.core.exception.ParametersException;
import fr.expression4j.core.predefine.AbstractFunction;
import fr.expression4j.factory.ExpressionFactory;
import fr.expression4j.factory.NumberFactory;
import fr.expression4j.util.ParameterUtil;

public class MyFirstUserDefineFunction {

	private static class Factoriel extends AbstractFunction {

		/**
		 * compute the factoril of a number
		 * @param x number to compute
		 * @return factoriel of the number.
		 */
		private double fact(double x) {
			if (x == 0) {
				return 1;
			}
			return x * fact(x-1);
		}
		
		/**
		 * evaluation method called by the Expression4j when needed.
		 * 
		 * @param parameters parameter given to the function for the evaluation.
		 */
		public MathematicalElement evaluate(Parameters parameters) throws EvalException {
			try {
				MathematicalElement me = parameters.getParameter("x");
				double tmpValue = me.getRealValue(); 
				if (tmpValue < 0) {
					throw new EvalException("Cannot evaluate fact of negativ number.");
				}
				double result = fact(tmpValue);
				
				return NumberFactory.createReal(result);
			}
			catch (ParametersException pe) {
				throw new EvalException("Cannot evaluate fact(x). " + pe);
			}
			
		}

		
		public MathematicalElement evaluate(OperatorManager operatorManager,Parameters parameters) throws EvalException {
			return evaluate(parameters);
		}

		public Catalog getCatalog() {
			return ExpressionFactory.getCatalog();
		}

		/**
		 * get the function name
		 */
		public String getName() {
			return "fact";
		}

		/**
		 * get parameter list of the function.
		 */
		public List getParameters() {
			//this util method get one parameter "x".
			return ParameterUtil.generateXParameters();
		}

		public ExpressionModel getExpressionModel() {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
	
	
	public static void main(String[] args) {
		try {
			//define a specific catalog (not necessary,
			//we can use the default catalog instead)
			Catalog catalog = ExpressionFactory.createCatalog("catalog");
			
			//add the user define function to the catalog
			catalog.addExpression(new Factoriel());
			
			//create the expression who use the user define function
			//dont forget to put the catalog of the user define function
			//if default catalog is not used.
			Expression expression1 = ExpressionFactory.createExpression("f(x)=fact(x+1)",catalog);
			System.out.println("Expression name: " + expression1.getName());
			System.out.println("Expression : " + expression1);
			System.out.println("Expression parameters: " + expression1.getParameters());
			
			//compute a value
			MathematicalElement me1 = NumberFactory.createReal(5);
			Parameters parameters = ExpressionFactory.createParameters();
			parameters.addParameter("x",me1);
			
			System.out.println("Value of expression h :" + expression1.evaluate(parameters));

		}
		catch (Exception e) {
			System.out.println("Error: " + e);
		}
	}
	
}
