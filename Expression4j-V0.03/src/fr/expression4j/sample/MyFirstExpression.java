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

import fr.expression4j.core.Expression;
import fr.expression4j.factory.ExpressionFactory;

public class MyFirstExpression {


	public static void main(String[] args) {
		try {
			Expression expression = ExpressionFactory.createExpression("f()=2.4e-2");
			System.out.println("Expression name: " + expression.getName());
			System.out.println("Value of expression:" + expression.evaluate(null).getRealValue());
		}
		catch (Exception e) {
			System.out.println("Error: " + e);
		}
	}
	
}
