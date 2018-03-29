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
package fr.expression4j.sample.custom.element;

import fr.expression4j.basic.MathematicalElement;
import fr.expression4j.basic.OperatorManager;
import fr.expression4j.basic.impl.ComplexOrRealExpressionElement;
import fr.expression4j.basic.impl.ConstantOrVariableExpressionElement;
import fr.expression4j.basic.impl.FunctionExpressionElement;
import fr.expression4j.basic.impl.ParenthesisExpressionElement;
import fr.expression4j.core.Expression;
import fr.expression4j.core.ExpressionModel;
import fr.expression4j.core.Parameters;
import fr.expression4j.factory.ExpressionFactory;
import fr.expression4j.factory.ExpressionModelFactory;
import fr.expression4j.factory.NumberFactory;
import fr.expression4j.factory.OperatorFactory;
import fr.expression4j.factory.OperatorManagerFactory;

public class CustomExpressionElement {


	public static void main(String[] args) {
		try {
			//create a specific expression model
			ExpressionModel stringExpressionModel = ExpressionModelFactory.createExpressionModel("StringExpressionModel");
			
			//add binary operator supported by the specific expression model
			stringExpressionModel.addBinaryOperator(OperatorFactory.getOperator("Plus"),1);

			//add custom expression element
			stringExpressionModel.addExpressionElement(new StringExpressionElement(),1);
			
			//add standardexpression element
			stringExpressionModel.addExpressionElement(new ComplexOrRealExpressionElement(),2);
			stringExpressionModel.addExpressionElement(new FunctionExpressionElement(),3);
			stringExpressionModel.addExpressionElement(new ConstantOrVariableExpressionElement(),4);
			stringExpressionModel.addExpressionElement(new ParenthesisExpressionElement(),5);

			//create a specific operator manager
			OperatorManager stringOperatorManager = OperatorManagerFactory.createOperatorManager("stringOperatorManager");
			
			//add operator specific to previous model
			stringOperatorManager.addOperatorImpl(new OperatorPlusStringString());
			stringOperatorManager.addOperatorImpl(new OperatorPlusStringReal());
			
			//create an expression with the specific expression model
			Expression expression = ExpressionFactory.createExpression("f(x)='toto'+x+'titi'",
										ExpressionFactory.getCatalog(),stringExpressionModel);
			
			//compute expression
			Parameters parameters = ExpressionFactory.createParameters();
			parameters.addParameter("x",new StringMathematicalElement("test"));
			MathematicalElement result = expression.evaluate(stringOperatorManager,parameters);
			
			//display result
			System.out.println("result: " + result.getValue());
			
			//compute expression with a real
			parameters.addParameter("x",NumberFactory.createReal(123.4));
			result = expression.evaluate(stringOperatorManager,parameters);

			//display result
			System.out.println("result: " + result.getValue());

		}
		catch (Exception e) {
			System.out.println("Error: " + e);
		}
		
	}
	
}
