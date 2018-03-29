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
package fr.expression4j.sample.custom.operator;

import fr.expression4j.basic.MathematicalElement;
import fr.expression4j.basic.Operator;
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

public class CustomOperator {


	public static void main(String[] args) {
		try {
			//create a specific expression model
			ExpressionModel customOperatorExpressionModel = ExpressionModelFactory.createExpressionModel("CustomOperatorExpressionModel");
			
			//create the new operator
			Operator supOperator = OperatorFactory.createOperator("sup",">",false);
			
			//add binary operator supported by the specific expression model
			customOperatorExpressionModel.addBinaryOperator(supOperator,1);

			//add standardexpression element
			customOperatorExpressionModel.addExpressionElement(new ComplexOrRealExpressionElement(),1);
			customOperatorExpressionModel.addExpressionElement(new FunctionExpressionElement(),2);
			customOperatorExpressionModel.addExpressionElement(new ConstantOrVariableExpressionElement(),3);
			customOperatorExpressionModel.addExpressionElement(new ParenthesisExpressionElement(),4);

			//create a specific operator manager
			OperatorManager supOperatorManager = OperatorManagerFactory.createOperatorManager("supOperatorManager");
			
			//add operator specific to previous model
			supOperatorManager.addOperatorImpl(new OperatorSupRealReal());
			
			//create an expression with the specific expression model
			Expression expression = ExpressionFactory.createExpression("f()=2>3",
										ExpressionFactory.getCatalog(),customOperatorExpressionModel);
			
			//compute expression
			MathematicalElement result = expression.evaluate(supOperatorManager,null);
			
			//display result
			System.out.println("result: " + result.getValue());
			
			//create an expression with the specific expression model
			expression = ExpressionFactory.createExpression("f()=3>2",
										ExpressionFactory.getCatalog(),customOperatorExpressionModel);
			
			//compute expression
			result = expression.evaluate(supOperatorManager,null);
			
			//display result
			System.out.println("result: " + result.getValue());

			//create an expression with the specific expression model
			ExpressionFactory.createExpression("f(x)=x",
										ExpressionFactory.getCatalog(),customOperatorExpressionModel);
			Expression expression2 = ExpressionFactory.createExpression("g(x)=2>f(x)",
					ExpressionFactory.getCatalog(),customOperatorExpressionModel);
			
			//compute expression
			Parameters parameters = ExpressionFactory.createParameters();
			
			for (int i=0; i<10; i++) {
				parameters.addParameter("x",NumberFactory.createReal(i));
				result = expression2.evaluate(supOperatorManager,parameters);
			
				//display result
				System.out.println("result: i=" +i + " : " + result.getValue());
			}
			
			//but be carefull, the customOperatorExpressionModel does not manage
			//standard operator, the folowing expression is not valid
			try {
				ExpressionFactory.createExpression("f(x)=x*2",
						ExpressionFactory.getCatalog(),customOperatorExpressionModel);
			}
			catch (Exception e) {
				System.out.println("Could not parse standard operator with customOperatorExpressionModel: " + e);
			}
		}
		catch (Exception e) {
			System.out.println("Error: " + e);
		}
		
	}
	
}
