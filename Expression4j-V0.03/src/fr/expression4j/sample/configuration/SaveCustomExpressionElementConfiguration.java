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
import fr.expression4j.sample.custom.element.OperatorPlusStringReal;
import fr.expression4j.sample.custom.element.OperatorPlusStringString;
import fr.expression4j.sample.custom.element.StringExpressionElement;

public class SaveCustomExpressionElementConfiguration {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			//create expressions and constant
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
			ExpressionFactory.createExpression("f(x)='toto'+x+'titi'",
										ExpressionFactory.getCatalog(),stringExpressionModel);
			ExpressionFactory.createExpression("g(x,y)=f(y)+' '+(x+' '+f(x+y))",
					ExpressionFactory.getCatalog(),stringExpressionModel);
			
			
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
