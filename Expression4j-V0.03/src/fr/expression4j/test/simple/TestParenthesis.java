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
public class TestParenthesis extends TestCase {
	public static Test suite() {
		return new TestSuite(TestParenthesis.class);
	}

	public void testParenthesis1() {
		String expressionString = "f()=(1+2)";
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

	public void testParenthesis2() {
		String expressionString = "f()=1+(2+3)";
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

	public void testParenthesis3() {
		String expressionString = "f()=(1+2)*(3+4)";
		try {
			Expression expression = ExpressionFactory.createExpression(expressionString);
			assertEquals(21.0,expression.evaluate(null).getRealValue(),0);
		}
		catch (ParsingException pe) {
			fail("Cannot parse simple expression. " + pe);
		}
		catch (EvalException ee) {
			fail("Cannot evaluate expression " + expressionString + ", " + ee);
		}
	}

	public void testParenthesis4() {
		String expressionString = "f()=((1+2)*(3+4))";
		try {
			Expression expression = ExpressionFactory.createExpression(expressionString);
			assertEquals(21.0,expression.evaluate(null).getRealValue(),0);
		}
		catch (ParsingException pe) {
			fail("Cannot parse simple expression. " + pe);
		}
		catch (EvalException ee) {
			fail("Cannot evaluate expression " + expressionString + ", " + ee);
		}
	}

	public void testParenthesis5() {
		String expressionString = "f()=(((1+2)*(3+4))+4)";
		try {
			Expression expression = ExpressionFactory.createExpression(expressionString);
			assertEquals(25.0,expression.evaluate(null).getRealValue(),0);
		}
		catch (ParsingException pe) {
			fail("Cannot parse simple expression. " + pe);
		}
		catch (EvalException ee) {
			fail("Cannot evaluate expression " + expressionString + ", " + ee);
		}
	}
	
	public void testParenthesis6() {
		String expressionString = "f()=1+(2*3)+4";
		try {
			Expression expression = ExpressionFactory.createExpression(expressionString);
			assertEquals(11.0,expression.evaluate(null).getRealValue(),0);
		}
		catch (ParsingException pe) {
			fail("Cannot parse simple expression. " + pe);
		}
		catch (EvalException ee) {
			fail("Cannot evaluate expression " + expressionString + ", " + ee);
		}
	}
	
	public void testParenthesis7() {
		String expressionString = "f()=(2+(2+(2+(2+(2+((((((2+(((((2+((((((((((((((1)+1)+1)+1)+1)+1)+1)+1)+1)+1)+1)+1)+1)+1)+1)+1)))))))))))))))";
		try {
			Expression expression = ExpressionFactory.createExpression(expressionString);
			assertEquals((2+(2+(2+(2+(2+((((((2+(((((2+((((((((((((((1)+1)+1)+1)+1)+1)+1)+1)+1)+1)+1)+1)+1)+1)+1)+1))))))))))))))),expression.evaluate(null).getRealValue(),0);
		}
		catch (ParsingException pe) {
			fail("Cannot parse simple expression. " + pe);
		}
		catch (EvalException ee) {
			fail("Cannot evaluate expression " + expressionString + ", " + ee);
		}
	}
	
	public void testParenthesisUnariOperator1() {
		String expressionString = "f()=-(1)";
		try {
			Expression expression = ExpressionFactory.createExpression(expressionString);
			assertEquals(-1.0,expression.evaluate(null).getRealValue(),0);
		}
		catch (ParsingException pe) {
			fail("Cannot parse simple expression. " + pe);
		}
		catch (EvalException ee) {
			fail("Cannot evaluate expression " + expressionString + ", " + ee);
		}
	}

	public void testParenthesisUnariOperator2() {
		String expressionString = "f()=+(+1)";
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

	public void testParenthesisUnariOperator3() {
		String expressionString = "f()=1+(+1)";
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

	public void testParenthesisUnariOperator4() {
		String expressionString = "f()=+(1)+(+1)+(+1)";
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
	
	public void testParenthesisInvalidExpression1() {
		String expressionString = "f()=((+1)";
		try {
			ExpressionFactory.createExpression(expressionString);
			fail("Can parse an invalid expression.");
		}
		catch (ParsingException pe) {
		}
	}

	public void testParenthesisInvalidExpression2() {
		String expressionString = "f()=(1*3))";
		try {
			ExpressionFactory.createExpression(expressionString);
			fail("Can parse an invalid expression.");
		}
		catch (ParsingException pe) {
		}
	}
	
	public void testParenthesisInvalidExpression3() {
		String expressionString = "f()=(2+4)*(5-8)+3*(4-3))";
		try {
			ExpressionFactory.createExpression(expressionString);
			fail("Can parse an invalid expression.");
		}
		catch (ParsingException pe) {
		}
	}
	
	public void testParenthesisInvalidExpression4() {
		String expressionString = "f()=(2+(2+(2+(2+(2+((((((2+(((((2+((((((((((((((1)+1)+1)+1)+1)+1)+1)+1)+1)+1)+1)+1)+1)+1)+1)+1))))))))))))))";
		try {
			ExpressionFactory.createExpression(expressionString);
			fail("Can parse an invalid expression.");
		}
		catch (ParsingException pe) {
		}
	}
	
	public void testIncredibleParenthesis() {
		int nbParenthesis = 100;
		StringBuffer prefix = new StringBuffer(100);
		StringBuffer postfix = new StringBuffer(100);
		for (int i=0; i<nbParenthesis; i++) {
			prefix.append("(");
			postfix.append(")");
		}
		String expressionString = "f(x)=" + prefix + "2+x" + postfix;
		try {
			ExpressionFactory.createExpression(expressionString);
		}
		catch (ParsingException pe) {
			fail("Can parse expression with " + 2*nbParenthesis + " parenthesis. " + pe);
		}
	}
}
