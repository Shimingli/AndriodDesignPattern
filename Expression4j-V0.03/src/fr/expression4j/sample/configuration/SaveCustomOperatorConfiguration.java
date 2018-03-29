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

package fr.expression4j.sample.configuration;

import java.io.FileOutputStream;
import java.io.OutputStream;

import fr.expression4j.basic.Operator;
import fr.expression4j.basic.OperatorManager;
import fr.expression4j.basic.impl.ComplexOrRealExpressionElement;
import fr.expression4j.basic.impl.ConstantOrVariableExpressionElement;
import fr.expression4j.basic.impl.FunctionExpressionElement;
import fr.expression4j.basic.impl.ParenthesisExpressionElement;
import fr.expression4j.config.ConfigurationUtil;
import fr.expression4j.core.ExpressionModel;
import fr.expression4j.factory.ExpressionFactory;
import fr.expression4j.factory.ExpressionModelFactory;
import fr.expression4j.factory.OperatorFactory;
import fr.expression4j.factory.OperatorManagerFactory;
import fr.expression4j.sample.custom.operator.OperatorSupRealReal;

public class SaveCustomOperatorConfiguration {

	/**
	 * @param args
	 */
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
			ExpressionFactory.createExpression("f(x)=x",
										ExpressionFactory.getCatalog(),customOperatorExpressionModel);
			ExpressionFactory.createExpression("g(x)=2>f(x)",
					ExpressionFactory.getCatalog(),customOperatorExpressionModel);
			
			//store configuration in file
			OutputStream outStream = new FileOutputStream("c:/config.xml");
			ConfigurationUtil.saveConfig(outStream);
			outStream.close();
			
		}
		catch (Exception e) {
			System.out.println("Error: " + e);
		}

	}

}
