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

import java.io.FileInputStream;
import java.io.InputStream;

import fr.expression4j.basic.MathematicalElement;
import fr.expression4j.basic.OperatorManager;
import fr.expression4j.config.ConfigurationUtil;
import fr.expression4j.core.Expression;
import fr.expression4j.core.Parameters;
import fr.expression4j.factory.ExpressionFactory;
import fr.expression4j.factory.NumberFactory;
import fr.expression4j.factory.OperatorManagerFactory;

public class LoadCustomOperatorConfiguration {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			InputStream inputStream = new FileInputStream("c:/config.xml");
			ConfigurationUtil.loadConfig(inputStream);
			
			//compute expression
			Parameters parameters = ExpressionFactory.createParameters();
			Expression expression = ExpressionFactory.getCatalog().getExpression("g");
			OperatorManager opManager = OperatorManagerFactory.getOperatorManager("supOperatorManager");
			
			for (int i=0; i<10; i++) {
				parameters.addParameter("x",NumberFactory.createReal(i));
				MathematicalElement result = expression.evaluate(opManager,parameters);
			
				//display result
				System.out.println("result: i=" +i + " : " + result.getValue());
			}
			
		} catch (Exception e) {
			System.out.println("Error: " + e);
			System.out.println("error: " + e.getCause());
		}

	}

}
