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

import fr.expression4j.basic.MathematicalElement;
import fr.expression4j.basic.MathematicalException;
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
public class TestAddition extends TestCase {
	public static Test suite() {
		return new TestSuite(TestAddition.class);
	}

	public void testAddition1() {
		String expressionString = "f()=1+2";
		try {
			Expression expression = ExpressionFactory.createExpression(expressionString);
			assertEquals(3.0,expression.evaluate(null).getRealValue(),0);
		}
		catch (ParsingException pe) {
			fail("Cannot parse simple expression. " + pe);
		}
		catch (EvalException ee) {
			fail("Cannot evaluate expression " + expressionString + ", " + ee);
		}
	}

	public void testAddition2() {
		String expressionString = "f()=1+2+3";
		try {
			Expression expression = ExpressionFactory.createExpression(expressionString);
			assertEquals(6.0,expression.evaluate(null).getRealValue(),0);
		}
		catch (ParsingException pe) {
			fail("Cannot parse simple expression. " + pe);
		}
		catch (EvalException ee) {
			fail("Cannot evaluate expression " + expressionString + ", " + ee);
		}
	}

	public void testAddition3() {
		String expressionString = "f()=1+2+3+4";
		try {
			Expression expression = ExpressionFactory.createExpression(expressionString);
			assertEquals(10.0,expression.evaluate(null).getRealValue(),0);
		}
		catch (ParsingException pe) {
			fail("Cannot parse simple expression. " + pe);
		}
		catch (EvalException ee) {
			fail("Cannot evaluate expression " + expressionString + ", " + ee);
		}
	}
	
	public void testAdditionUnariOperator1() {
		String expressionString = "f()=1";
		try {
			Expression expression = ExpressionFactory.createExpression(expressionString);
			assertEquals(1.0,expression.evaluate(null).getRealValue(),0);
		}
		catch (ParsingException pe) {
			fail("Cannot parse simple expression. " + pe);
		}
		catch (EvalException ee) {
			fail("Cannot evaluate expression " + expressionString + ", " + ee);
		}
	}

	public void testAdditionUnariOperator2() {
		String expressionString = "f()=+1";
		try {
			Expression expression = ExpressionFactory.createExpression(expressionString);
			assertEquals(1.0,expression.evaluate(null).getRealValue(),0);
		}
		catch (ParsingException pe) {
			fail("Cannot parse simple expression. " + pe);
		}
		catch (EvalException ee) {
			fail("Cannot evaluate expression " + expressionString + ", " + ee);
		}
	}

	public void testAdditionUnariOperator3() {
		String expressionString = "f()=1++1";
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

	public void testAdditionUnariOperator4() {
		String expressionString = "f()=+1++1++1";
		try {
			Expression expression = ExpressionFactory.createExpression(expressionString);
			assertEquals(3.0,expression.evaluate(null).getRealValue(),0);
		}
		catch (ParsingException pe) {
			fail("Cannot parse simple expression. " + pe);
		}
		catch (EvalException ee) {
			fail("Cannot evaluate expression " + expressionString + ", " + ee);
		}
	}
	
	public void testAdditionInvalidExpression1() {
		String expressionString = "f()=+1+++1++1";
		try {
			ExpressionFactory.createExpression(expressionString);
			fail("Can parse an invalid expression.");
		}
		catch (ParsingException pe) {
		}
	}

	public void testAdditionInvalidExpression2() {
		String expressionString = "f()=+1+";
		try {
			ExpressionFactory.createExpression(expressionString);
			fail("Can parse an invalid expression.");
		}
		catch (ParsingException pe) {
		}
	}
	
	public void testAdditionInvalidExpression3() {
		String expressionString = "f()=++1";
		try {
			ExpressionFactory.createExpression(expressionString);
			fail("Can parse an invalid expression.");
		}
		catch (ParsingException pe) {
		}
	}
	
	public void testAdditionInvalidExpression4() {
		String expressionString = "f()=+1++1++1++1++1++1++1++1++1++1++1++1+++1";
		try {
			ExpressionFactory.createExpression(expressionString);
			fail("Can parse an invalid expression.");
		}
		catch (ParsingException pe) {
		}
	}
	
	public void testAdditionWithComplex() {
		MathematicalElement mathematicalElement = null;
		try {
			Expression expression = ExpressionFactory.createExpression("f(x)=2*x+4-2i");
			mathematicalElement = NumberFactory.createReal(4);
			Parameters parameters = ExpressionFactory.createParameters();
			parameters.addParameter("x",mathematicalElement);
			
			MathematicalElement result = expression.evaluate(parameters);
			assertEquals("Invalid real value.",12,result.getRealValue(),0);
			assertEquals("Invalid complex value.",-2,result.getComplexValue(),0);
			
			mathematicalElement = NumberFactory.createComplex(4,5);
			parameters.addParameter("x",mathematicalElement);
			
			result = expression.evaluate(parameters);
			assertEquals("Invalid real value.",12,result.getRealValue(),0);
			assertEquals("Invalud complex value.",8,result.getComplexValue(),0);
		}
		catch (ParsingException pe) {
			fail("Cannot add real with complex. " + pe);
		}
		catch (EvalException ee) {
			fail("Cannot evaluate expression with " + mathematicalElement + " value. " + ee);
		}
		catch (MathematicalException me) {
			fail("Cannot get complex value of result. " + me);
		}
	}
	
}
