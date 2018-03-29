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

import fr.expression4j.basic.MathematicalException;
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
public class TestNumber extends TestCase {
	public static Test suite() {
		return new TestSuite(TestNumber.class);
	}

	public void testReal1() {
		String expressionString = "f()=123";
		try {
			Expression expression = ExpressionFactory.createExpression(expressionString);
			assertEquals(123.0,expression.evaluate(null).getRealValue(),0);
		}
		catch (ParsingException pe) {
			fail("Cannot parse simple expression. " + pe);
		}
		catch (EvalException ee) {
			fail("Cannot evaluate expression " + expressionString + ", " + ee);
		}
		
	}

	public void testReal2() {
		String expressionString = "f()=1.1";
		try {
			Expression expression = ExpressionFactory.createExpression(expressionString);
			assertEquals(1.1,expression.evaluate(null).getRealValue(),0);
		}
		catch (ParsingException pe) {
			fail("Cannot parse simple expression. " + pe);
		}
		catch (EvalException ee) {
			fail("Cannot evaluate expression " + expressionString + ", " + ee);
		}
	}

	public void testReal3() {
		String expressionString = "f()=1.1e1";
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
	
	public void testReal4() {
		String expressionString = "f()=1.1e-1";
		try {
			Expression expression = ExpressionFactory.createExpression(expressionString);
			assertEquals(0.11,expression.evaluate(null).getRealValue(),0);
		}
		catch (ParsingException pe) {
			fail("Cannot parse simple expression. " + pe);
		}
		catch (EvalException ee) {
			fail("Cannot evaluate expression " + expressionString + ", " + ee);
		}
	}

	public void testReal5() {
		String expressionString = "f()=-1.1e1";
		try {
			Expression expression = ExpressionFactory.createExpression(expressionString);
			assertEquals(-11.0,expression.evaluate(null).getRealValue(),0);
		}
		catch (ParsingException pe) {
			fail("Cannot parse simple expression. " + pe);
		}
		catch (EvalException ee) {
			fail("Cannot evaluate expression " + expressionString + ", " + ee);
		}
	}
	
	public void testReal6() {
		String expressionString = "f()=1e-3";
		try {
			Expression expression = ExpressionFactory.createExpression(expressionString);
			assertEquals(0.001,expression.evaluate(null).getRealValue(),0);
		}
		catch (ParsingException pe) {
			fail("Cannot parse simple expression. " + pe);
		}
		catch (EvalException ee) {
			fail("Cannot evaluate expression " + expressionString + ", " + ee);
		}
	}

	public void testComplex1() {
		String expressionString = "f()=123i";
		try {
			Expression expression = ExpressionFactory.createExpression(expressionString);
			assertEquals("invalid real value",0,expression.evaluate(null).getRealValue(),0);
			assertEquals("invalid complex value",123.0,expression.evaluate(null).getComplexValue(),0);
		}
		catch (ParsingException pe) {
			fail("Cannot parse simple expression. " + pe);
		}
		catch (EvalException ee) {
			fail("Cannot evaluate expression " + expressionString + ", " + ee);
		}
		catch (MathematicalException me) {
			fail("Cannot evaluate value of complex number. " + me);
		}
		
	}
	
	public void testComplex2() {
		String expressionString = "f()=1.1i";
		try {
			Expression expression = ExpressionFactory.createExpression(expressionString);
			assertEquals("invalid real value",0,expression.evaluate(null).getRealValue(),0);
			assertEquals("invalid complex value",1.1,expression.evaluate(null).getComplexValue(),0);
		}
		catch (ParsingException pe) {
			fail("Cannot parse simple expression. " + pe);
		}
		catch (EvalException ee) {
			fail("Cannot evaluate expression " + expressionString + ", " + ee);
		}
		catch (MathematicalException me) {
			fail("Cannot evaluate value of complex number. " + me);
		}
	}

	public void testComplex3() {
		String expressionString = "f()=1.1e1i";
		try {
			Expression expression = ExpressionFactory.createExpression(expressionString);
			assertEquals("invalid real value",0,expression.evaluate(null).getRealValue(),0);
			assertEquals("invalid complex value",11,expression.evaluate(null).getComplexValue(),0);
		}
		catch (ParsingException pe) {
			fail("Cannot parse simple expression. " + pe);
		}
		catch (EvalException ee) {
			fail("Cannot evaluate expression " + expressionString + ", " + ee);
		}
		catch (MathematicalException me) {
			fail("Cannot evaluate value of complex number. " + me);
		}
	}

	public void testComplex4() {
		String expressionString = "f()=1.1e-1i";
		try {
			Expression expression = ExpressionFactory.createExpression(expressionString);
			assertEquals("invalid real value",0,expression.evaluate(null).getRealValue(),0);
			assertEquals("invalid complex value",0.11,expression.evaluate(null).getComplexValue(),0);
		}
		catch (ParsingException pe) {
			fail("Cannot parse simple expression. " + pe);
		}
		catch (EvalException ee) {
			fail("Cannot evaluate expression " + expressionString + ", " + ee);
		}
		catch (MathematicalException me) {
			fail("Cannot evaluate value of complex number. " + me);
		}
	}

	public void testComplex5() {
		String expressionString = "f()=-1.1e1i";
		try {
			Expression expression = ExpressionFactory.createExpression(expressionString);
			assertEquals("invalid real value",0,expression.evaluate(null).getRealValue(),0);
			assertEquals("invalid complex value",-11,expression.evaluate(null).getComplexValue(),0);
		}
		catch (ParsingException pe) {
			fail("Cannot parse simple expression. " + pe);
		}
		catch (EvalException ee) {
			fail("Cannot evaluate expression " + expressionString + ", " + ee);
		}
		catch (MathematicalException me) {
			fail("Cannot evaluate value of complex number. " + me);
		}
	}
	
	public void testComplex6() {
		String expressionString = "f()=1e-3i";
		try {
			Expression expression = ExpressionFactory.createExpression(expressionString);
			assertEquals("invalid real value",0,expression.evaluate(null).getRealValue(),0);
			assertEquals("invalid complex value",0.001,expression.evaluate(null).getComplexValue(),0);
		}
		catch (ParsingException pe) {
			fail("Cannot parse simple expression. " + pe);
		}
		catch (EvalException ee) {
			fail("Cannot evaluate expression " + expressionString + ", " + ee);
		}
		catch (MathematicalException me) {
			fail("Cannot evaluate value of complex number. " + me);
		}
	}

	public void testComplex7() {
		String expressionString = "f()=1e-3+1e-3i";
		try {
			Expression expression = ExpressionFactory.createExpression(expressionString);
			assertEquals("invalid real value",0.001,expression.evaluate(null).getRealValue(),0);
			assertEquals("invalid complex value",0.001,expression.evaluate(null).getComplexValue(),0);
		}
		catch (ParsingException pe) {
			fail("Cannot parse simple expression. " + pe);
		}
		catch (EvalException ee) {
			fail("Cannot evaluate expression " + expressionString + ", " + ee);
		}
		catch (MathematicalException me) {
			fail("Cannot evaluate value of complex number. " + me);
		}
	}
	
}
