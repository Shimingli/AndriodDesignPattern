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
public class TestDivision extends TestCase {
	public static Test suite() {
		return new TestSuite(TestDivision.class);
	}

	public void testDivision1() {
		String expressionString = "f()=4/2";
		try {
			Expression expression = ExpressionFactory.createExpression(expressionString);
			assertEquals(2.0,expression.evaluate(null).getRealValue(),0);
		}
		catch (ParsingException pe) {
			fail("Cannot parse simple expression. " + pe);
		}
		catch (EvalException ee) {
			fail("Cannot evaluate expression " + expressionString + ", " + ee);
		}
		
	}

	public void testDivision2() {
		String expressionString = "f()=16/4/2";
		try {
			Expression expression = ExpressionFactory.createExpression(expressionString);
			assertEquals(2.0,expression.evaluate(null).getRealValue(),0);
		}
		catch (ParsingException pe) {
			fail("Cannot parse simple expression. " + pe);
		}
		catch (EvalException ee) {
			fail("Cannot evaluate expression " + expressionString + ", " + ee);
		}
	}

	public void testDivision3() {
		String expressionString = "f()=32/2/4/2";
		try {
			Expression expression = ExpressionFactory.createExpression(expressionString);
			assertEquals(2.0,expression.evaluate(null).getRealValue(),0);
		}
		catch (ParsingException pe) {
			fail("Cannot parse simple expression. " + pe);
		}
		catch (EvalException ee) {
			fail("Cannot evaluate expression " + expressionString + ", " + ee);
		}
	}
	
	public void testDivisionUnariOperator1() {
		String expressionString = "f()=-4/2";
		try {
			Expression expression = ExpressionFactory.createExpression(expressionString);
			assertEquals(-2,expression.evaluate(null).getRealValue(),0);
		}
		catch (ParsingException pe) {
			fail("Cannot parse simple expression. " + pe);
		}
		catch (EvalException ee) {
			fail("Cannot evaluate expression " + expressionString + ", " + ee);
		}
	}

	public void testDivisionUnariOperator2() {
		String expressionString = "f()=4/-2";
		try {
			Expression expression = ExpressionFactory.createExpression(expressionString);
			assertEquals(-2.0,expression.evaluate(null).getRealValue(),0);
		}
		catch (ParsingException pe) {
			fail("Cannot parse simple expression. " + pe);
		}
		catch (EvalException ee) {
			fail("Cannot evaluate expression " + expressionString + ", " + ee);
		}
	}

	public void testDivisionUnariOperator3() {
		String expressionString = "f()=-4/-2";
		try {
			Expression expression = ExpressionFactory.createExpression(expressionString);
			assertEquals(2.0,expression.evaluate(null).getRealValue(),0);
		}
		catch (ParsingException pe) {
			fail("Cannot parse simple expression. " + pe);
		}
		catch (EvalException ee) {
			fail("Cannot evaluate expression " + expressionString + ", " + ee);
		}
	}

	public void testDivisionInvalidExpression1() {
		String expressionString = "f()=1//1";
		try {
			ExpressionFactory.createExpression(expressionString);
			fail("Can parse an invalid expression.");
		}
		catch (ParsingException pe) {
		}
	}

	public void testDivisionInvalidExpression2() {
		String expressionString = "f()=1/";
		try {
			ExpressionFactory.createExpression(expressionString);
			fail("Can parse an invalid expression.");
		}
		catch (ParsingException pe) {
		}
	}
	
	public void testDivisionInvalidExpression3() {
		String expressionString = "f()=/1";
		try {
			ExpressionFactory.createExpression(expressionString);
			fail("Can parse an invalid expression.");
		}
		catch (ParsingException pe) {
		}
	}
	
	public void testDivisionInvalidExpression4() {
		String expressionString = "f()=1/1/1/1/1/1/1/1/1/1/1//1";
		try {
			ExpressionFactory.createExpression(expressionString);
			fail("Can parse an invalid expression.");
		}
		catch (ParsingException pe) {
		}
	}
	
}
