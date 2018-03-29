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

package fr.expression4j.test.complex;

import fr.expression4j.core.Catalog;
import fr.expression4j.core.Expression;
import fr.expression4j.core.Parameters;
import fr.expression4j.core.exception.EvalException;
import fr.expression4j.core.exception.ParsingException;
import fr.expression4j.factory.ExpressionFactory;
import fr.expression4j.factory.NumberFactory;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * test addition in expression
 * @author SGINER
 *
 */
public class TestFunction extends TestCase {
	public static Test suite() {
		return new TestSuite(TestFunction.class);
	}

	public void testSimpleFunction() {
		String expressionString = "f(x)=1+2*x";
		String expressionString2 = "g(x)=f(x)";
		try {
			Expression expression = ExpressionFactory.createExpression(expressionString);
			assertEquals("Cannot found parameter x.",1,expression.getParameters().size());

			Expression expression2 = ExpressionFactory.createExpression(expressionString2);
			assertEquals("Cannot found parameter x.",1,expression2.getParameters().size());
			
			Parameters parameters = ExpressionFactory.createParameters();
			parameters.addParameter("x",NumberFactory.createReal(5));
			
			assertEquals("Invalid evaluation value.",11.0,expression2.evaluate(parameters).getRealValue(),0);
		}
		catch (ParsingException pe) {
			fail("Cannot parse simple expression. " + pe);
		}
		catch (EvalException ee) {
			fail("Cannot evaluate expression. " + ee);
		}
	}

	public void testPredefinedFunction() {
		String expressionString = "f(x)=abs(2*-x)";
		try {
			Expression expression = ExpressionFactory.createExpression(expressionString);
			assertEquals("Cannot found parameter x.",1,expression.getParameters().size());

			Parameters parameters = ExpressionFactory.createParameters();
			parameters.addParameter("x",NumberFactory.createReal(5));
			
			assertEquals("Invalid evaluation value.",10.0,expression.evaluate(parameters).getRealValue(),0);
		}
		catch (ParsingException pe) {
			fail("Cannot parse simple expression. " + pe);
		}
		catch (EvalException ee) {
			fail("Cannot evaluate expression. " + ee);
		}
	}

	public void testInvalidCatalog() {
		String expressionString = "f(x)=foo(2*-x)";
		try {
			Expression expression = ExpressionFactory.createExpression(expressionString);
			fail("Can parse an expression with a function not present in catalog.");
			assertEquals("Cannot found parameter x.",1,expression.getParameters().size());

		}
		catch (ParsingException pe) {
		}
	}
	
	public void testFunctionWithGivenCatalog() {
		String expressionString = "foo(x)=(2*-x)";
		String expressionString2 = "f(x)=foo(2*-x)";
		try {
			Expression expression = ExpressionFactory.createExpression(expressionString);
			assertEquals("Cannot found parameter x.",1,expression.getParameters().size());
			
			Catalog catalog = ExpressionFactory.createCatalog("Catalog");
			catalog.addExpression(expression);

			Expression expression2 = ExpressionFactory.createExpression(expressionString2,catalog);
			assertEquals("Cannot found parameter x.",1,expression2.getParameters().size());

			Parameters parameters = ExpressionFactory.createParameters();
			parameters.addParameter("x",NumberFactory.createReal(5));
			
			assertEquals("Invalid evaluation value.",20.0,expression2.evaluate(parameters).getRealValue(),0);
		}
		catch (ParsingException pe) {
			fail("Cannot parse simple expression. " + pe);
		}
		catch (EvalException ee) {
			fail("Cannot evaluate expression. " + ee);
		}
	}
	
}
