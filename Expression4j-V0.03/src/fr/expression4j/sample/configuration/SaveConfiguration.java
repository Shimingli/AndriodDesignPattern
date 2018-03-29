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

import fr.expression4j.config.ConfigurationUtil;
import fr.expression4j.core.Catalog;
import fr.expression4j.factory.ExpressionFactory;
import fr.expression4j.factory.NumberFactory;

public class SaveConfiguration {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			//create expressions and constant
			ExpressionFactory.createExpression("f(a,b,x)=2*a*x+b");
			ExpressionFactory.createExpression("g(r)=2*pi*r");
			Catalog catalog = ExpressionFactory.getCatalog();
			catalog.addConstant("maConstante",NumberFactory.createReal((54)));
			ExpressionFactory.createExpression("h(x)=x-maConstante");
			
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
