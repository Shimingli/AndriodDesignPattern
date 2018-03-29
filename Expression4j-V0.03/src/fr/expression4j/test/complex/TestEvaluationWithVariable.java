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
public class TestEvaluationWithVariable extends TestCase {
	public static Test suite() {
		return new TestSuite(TestEvaluationWithVariable.class);
	}

	public void testEmptyCatalog() {
		Catalog catalog = ExpressionFactory.createCatalog("Catalog");
		assertEquals(15,catalog.listExpression().size());
	}

	public void testEvaluationSmallVariable() {
		String expressionString = "f(x)=1+2*x";
		try {
			Expression expression = ExpressionFactory.createExpression(expressionString);
			assertEquals("Cannot found parameter x.",1,expression.getParameters().size());
			
			Parameters parameters = ExpressionFactory.createParameters();
			parameters.addParameter("x",NumberFactory.createReal(5));
			
			assertEquals("Invalid evaluation value.",11.0,expression.evaluate(parameters).getRealValue(),0);
		}
		catch (ParsingException pe) {
			fail("Cannot parse simple expression. " + pe);
		}
		catch (EvalException ee) {
			fail("Cannot evaluate expression. " + ee);
		}
	}

	public void testEvaluationBigVariable() {
		String expressionString = "f(ABCDEFGHIJKLMNO45)=1+2*ABCDEFGHIJKLMNO45";
		try {
			Expression expression = ExpressionFactory.createExpression(expressionString);
			assertEquals("Cannot found parameter ABCDEFGHIJKLMNO45.",1,expression.getParameters().size());
			
			Parameters parameters = ExpressionFactory.createParameters();
			parameters.addParameter("ABCDEFGHIJKLMNO45",NumberFactory.createReal(5));
			
			assertEquals("Invalid evaluation value.",11.0,expression.evaluate(parameters).getRealValue(),0);
		}
		catch (ParsingException pe) {
			fail("Cannot parse simple expression. " + pe);
		}
		catch (EvalException ee) {
			fail("Cannot evaluate expression. " + ee);
		}
	}
	
	public void testEvaluationVariableMoreThanOne() {
		String expressionString = "f(x)=1+2*x+x/2";
		try {
			Expression expression = ExpressionFactory.createExpression(expressionString);
			assertEquals("Cannot found parameter x.",1,expression.getParameters().size());
			
			Parameters parameters = ExpressionFactory.createParameters();
			parameters.addParameter("x",NumberFactory.createReal(4));
			
			assertEquals("Invalid evaluation value.",11.0,expression.evaluate(parameters).getRealValue(),0);
		}
		catch (ParsingException pe) {
			fail("Cannot parse simple expression. " + pe);
		}
		catch (EvalException ee) {
			fail("Cannot evaluate expression. " + ee);
		}
	}
	
	public void testTranslateVariableName() {
		String expressionString  = "f(x)=1+2*x+x/2";
		String expressionString2 = "g(y)=2*f(y)";
		try {
			Expression expression1 = ExpressionFactory.createExpression(expressionString);
			assertEquals("Cannot found parameter x.",1,expression1.getParameters().size());
			
			Expression expression2 = ExpressionFactory.createExpression(expressionString2);
			assertEquals("Cannot found parameter y.",1,expression2.getParameters().size());
			
			Parameters parameters = ExpressionFactory.createParameters();
			parameters.addParameter("y",NumberFactory.createReal(4));
			
			assertEquals("Invalid evaluation value.",22.0,expression2.evaluate(parameters).getRealValue(),0);
		}
		catch (ParsingException pe) {
			fail("Cannot parse simple expression. " + pe);
		}
		catch (EvalException ee) {
			fail("Cannot evaluate expression. " + ee);
		}
	}

}
