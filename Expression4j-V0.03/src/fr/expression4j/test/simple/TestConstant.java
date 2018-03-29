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
import fr.expression4j.core.Catalog;
import fr.expression4j.core.Expression;
import fr.expression4j.core.exception.EvalException;
import fr.expression4j.core.exception.ParsingException;
import fr.expression4j.factory.ExpressionFactory;
import fr.expression4j.factory.NumberFactory;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * test constant in expression
 * @author SGINER
 *
 */
public class TestConstant extends TestCase {
	public static Test suite() {
		return new TestSuite(TestConstant.class);
	}

	public void testConstantPi() {
		String expressionString = "f()=pi";
		try {
			Expression expression = ExpressionFactory.createExpression(expressionString);
			assertEquals("Cannot evaluate pi constant",3.14,expression.evaluate(null).getRealValue(),0.01);
		}
		catch (ParsingException pe) {
			fail("Cannot parse simple expression. " + pe);
		}
		catch (EvalException ee) {
			fail("Cannot evaluate expression " + expressionString + ", " + ee);
		}
	}

	public void testConstantE() {
		String expressionString = "f()=e";
		try {
			Expression expression = ExpressionFactory.createExpression(expressionString);
			assertEquals("Cannot evaluate e constant",2.71,expression.evaluate(null).getRealValue(),0.01);
		}
		catch (ParsingException pe) {
			fail("Cannot parse simple expression. " + pe);
		}
		catch (EvalException ee) {
			fail("Cannot evaluate expression " + expressionString + ", " + ee);
		}
	}

	public void testConstantEWithReal() {
		String expressionString = "f()=2.3e-3*e";
		try {
			Expression expression = ExpressionFactory.createExpression(expressionString);
			assertEquals("Cannot evaluate expression with e constant.",0.006,expression.evaluate(null).getRealValue(),0.001);
		}
		catch (ParsingException pe) {
			fail("Cannot parse simple expression. " + pe);
		}
		catch (EvalException ee) {
			fail("Cannot evaluate expression " + expressionString + ", " + ee);
		}
	}

	public void testCustomConstant() {
		Catalog catalog = ExpressionFactory.createCatalog("Catalog");
		MathematicalElement me = NumberFactory.createReal(2.5);
		catalog.addConstant("fooConstant",me);
		
		String expressionString = "f()=2*fooConstant";
		try {
			Expression expression = ExpressionFactory.createExpression(expressionString,catalog);
			assertEquals("Cannot evaluate expression with custom foo constant.",5,expression.evaluate(null).getRealValue(),0);
		}
		catch (ParsingException pe) {
			fail("Cannot parse simple expression. " + pe);
		}
		catch (EvalException ee) {
			fail("Cannot evaluate expression " + expressionString + ", " + ee);
		}
	}

	public void testCustomComplexConstant() {
		Catalog catalog = ExpressionFactory.createCatalog("Catalog");
		MathematicalElement me = NumberFactory.createComplex(2.5,3);
		catalog.addConstant("fooConstant",me);
		
		String expressionString = "f()=2*fooConstant";
		try {
			Expression expression = ExpressionFactory.createExpression(expressionString,catalog);
			assertEquals("Cannot evaluate real value for expression with custom foo constant.",5,expression.evaluate(null).getRealValue(),0);
			assertEquals("Cannot evaluate complex value for expression with custom foo constant.",6,expression.evaluate(null).getComplexValue(),0);
		}
		catch (ParsingException pe) {
			fail("Cannot parse simple expression. " + pe);
		}
		catch (EvalException ee) {
			fail("Cannot evaluate expression " + expressionString + ", " + ee);
		}
		catch (MathematicalException mex) {
			fail("Cannot get complex value " + expressionString + ", " + mex);
		}
	}
}
