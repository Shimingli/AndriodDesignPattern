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

package fr.expression4j.test.simple;

import fr.expression4j.core.Expression;
import fr.expression4j.core.exception.EvalException;
import fr.expression4j.core.exception.ParsingException;
import fr.expression4j.factory.ExpressionFactory;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * test addition in expression
 * @author SGINER
 *
 */
public class TestExponent extends TestCase {
	public static Test suite() {
		return new TestSuite(TestExponent.class);
	}

	public void testExponent1() {
		String expressionString = "f()=2^3";
		try {
			Expression expression = ExpressionFactory.createExpression(expressionString);
			assertEquals(8.0,expression.evaluate(null).getRealValue(),0);
		}
		catch (ParsingException pe) {
			fail("Cannot parse simple expression. " + pe);
		}
		catch (EvalException ee) {
			fail("Cannot evaluate expression " + expressionString + ", " + ee);
		}
		
	}

	public void testExponent2() {
		String expressionString = "f()=2^2^2";
		try {
			Expression expression = ExpressionFactory.createExpression(expressionString);
			assertEquals(16,expression.evaluate(null).getRealValue(),0);
		}
		catch (ParsingException pe) {
			fail("Cannot parse simple expression. " + pe);
		}
		catch (EvalException ee) {
			fail("Cannot evaluate expression " + expressionString + ", " + ee);
		}
	}

	public void testExponent3() {
		String expressionString = "f()=2^2^2^2";
		try {
			Expression expression = ExpressionFactory.createExpression(expressionString);
			assertEquals(256,expression.evaluate(null).getRealValue(),0);
		}
		catch (ParsingException pe) {
			fail("Cannot parse simple expression. " + pe);
		}
		catch (EvalException ee) {
			fail("Cannot evaluate expression " + expressionString + ", " + ee);
		}
	}
	
	public void testExponentUnariOperator1() {
		String expressionString = "f()=2^+2";
		try {
			Expression expression = ExpressionFactory.createExpression(expressionString);
			assertEquals(4,expression.evaluate(null).getRealValue(),0);
		}
		catch (ParsingException pe) {
			fail("Cannot parse simple expression. " + pe);
		}
		catch (EvalException ee) {
			fail("Cannot evaluate expression " + expressionString + ", " + ee);
		}
	}

	public void testExponentUnariOperator2() {
		String expressionString = "f()=2^-2";
		try {
			Expression expression = ExpressionFactory.createExpression(expressionString);
			assertEquals(0.25,expression.evaluate(null).getRealValue(),0);
		}
		catch (ParsingException pe) {
			fail("Cannot parse simple expression. " + pe);
		}
		catch (EvalException ee) {
			fail("Cannot evaluate expression " + expressionString + ", " + ee);
		}
	}


	public void testExponentInvalidExpression1() {
		String expressionString = "f()=1^^1";
		try {
			ExpressionFactory.createExpression(expressionString);
			fail("Can parse an invalid expression.");
		}
		catch (ParsingException pe) {
		}
	}

	public void testExponentInvalidExpression2() {
		String expressionString = "f()=1^";
		try {
			ExpressionFactory.createExpression(expressionString);
			fail("Can parse an invalid expression.");
		}
		catch (ParsingException pe) {
		}
	}
	
	public void testExponentInvalidExpression3() {
		String expressionString = "f()=^1";
		try {
			ExpressionFactory.createExpression(expressionString);
			fail("Can parse an invalid expression.");
		}
		catch (ParsingException pe) {
		}
	}
	
	public void testExponentInvalidExpression4() {
		String expressionString = "f()=1^1^1^1^1^^1*1*1*1*1*1**1";
		try {
			ExpressionFactory.createExpression(expressionString);
			fail("Can parse an invalid expression.");
		}
		catch (ParsingException pe) {
		}
	}
	
}
