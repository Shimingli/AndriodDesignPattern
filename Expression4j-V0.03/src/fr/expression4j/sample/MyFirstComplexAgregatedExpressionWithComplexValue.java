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

public class MyFirstComplexAgregatedExpressionWithComplexValue {


	public static void main(String[] args) {
		try {
			ExpressionFactory.createExpression("f(x,y)=2*x-3*y");
			ExpressionFactory.createExpression("g(x,y)=4*f(x+3,f(y*2,x/2))+3");
			Expression expression3 = ExpressionFactory.createExpression("h(x)=4*g(x+3,f(x*2,x))+3");
			System.out.println("Expression name: " + expression3.getName());
			System.out.println("Expression : " + expression3);
			System.out.println("Expression parameters: " + expression3.getParameters());
			
			MathematicalElement me1 = NumberFactory.createComplex(2,4);
			Parameters parameters = ExpressionFactory.createParameters();
			parameters.addParameter("x",me1);
			
			System.out.println("Value of expression h :" + expression3.evaluate(parameters));

		}
		catch (Exception e) {
			System.out.println("Error: " + e);
		}
	}
	
}
