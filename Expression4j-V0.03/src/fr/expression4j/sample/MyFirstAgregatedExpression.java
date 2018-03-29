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

import fr.expression4j.basic.MathematicalElement;
import fr.expression4j.core.Expression;
import fr.expression4j.core.Parameters;
import fr.expression4j.factory.ExpressionFactory;
import fr.expression4j.factory.NumberFactory;

public class MyFirstAgregatedExpression {


	public static void main(String[] args) {
		try {
			ExpressionFactory.createExpression("f(x,y)=2.4e-2*x-3.2*y");
			Expression expression2 = ExpressionFactory.createExpression("g(x,y)=4*f(x,y)+3");
			System.out.println("Expression name: " + expression2.getName());
			System.out.println("Expression : " + expression2);
			System.out.println("Expression parameters: " + expression2.getParameters());
			
			MathematicalElement me1 = NumberFactory.createReal(2);
			MathematicalElement me2 = NumberFactory.createReal(4);
			Parameters parameters = ExpressionFactory.createParameters();
			parameters.addParameter("x",me1);
			parameters.addParameter("y",me2);
			
			System.out.println("Value of expression g for x=2 and y = 4:" + expression2.evaluate(parameters).getRealValue());

			me1 = NumberFactory.createReal(3);
			me2 = NumberFactory.createReal(2);
			parameters.addParameter("x",me1);
			parameters.addParameter("y",me2);

			System.out.println("Value of expression g for x=3 and y = 2:" + expression2.evaluate(parameters).getRealValue());
			
		}
		catch (Exception e) {
			System.out.println("Error: " + e);
		}
	}
	
}
