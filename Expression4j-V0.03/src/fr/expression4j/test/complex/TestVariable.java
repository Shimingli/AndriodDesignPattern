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
public class TestVariable extends TestCase {
	public static Test suite() {
		return new TestSuite(TestVariable.class);
	}

	public void testEmptyCatalog() {
		Catalog catalog = ExpressionFactory.createCatalog("Catalog");
		assertEquals(15,catalog.listExpression().size());
	}

	public void testSmallVariable() {
		String expressionString = "f(x)=1+2*x";
		try {
			Expression expression = ExpressionFactory.createExpression(expressionString);
			assertEquals("Cannot found parameter x.",1,expression.getParameters().size());
		}
		catch (ParsingException pe) {
			fail("Cannot parse simple expression. " + pe);
		}
	}

	public void testLageNumberOfVariable() {
		String expressionString = "f(a,b,c,d,e,f,g,h,i,j,k,l,m,n,o)=a+b+c+d+e+f+g+h+i+j+k+l+m+n+o";
		try {
			Expression expression = ExpressionFactory.createExpression(expressionString);
			assertEquals("Cannot found all parameters.",15,expression.getParameters().size());
		}
		catch (ParsingException pe) {
			fail("Cannot parse simple expression. " + pe);
		}
	}
	
	public void testBigVariable() {
		String expressionString = "f(abcdefghijklmno)=abcdefghijklmno+abcdefghijklmno";
		try {
			Expression expression = ExpressionFactory.createExpression(expressionString);
			assertEquals("Cannot found big variable abcdefghijklmno.",1,expression.getParameters().size());
			assertEquals("Variable as not the same name","abcdefghijklmno",expression.getParameters().get(0));
		}
		catch (ParsingException pe) {
			fail("Cannot parse simple expression. " + pe);
		}
	}

	public void testMoreVariable() {
		String expressionString = "f(a,b,c)=a+b-a";
		try {
			Expression expression = ExpressionFactory.createExpression(expressionString);
			assertEquals("Cannot found big variable abcdefghijklmno.",3,expression.getParameters().size());
		}
		catch (ParsingException pe) {
			fail("Cannot parse simple expression. " + pe);
		}
	}
	
	public void testInvalidVariableName() {
		String expressionString = "f(2ab)=2ab+c";
		try {
			ExpressionFactory.createExpression(expressionString);
			fail("Can parse an invalid variable name !!");
		}
		catch (ParsingException pe) {
		}
	}

	public void testInvalidVariableNumber() {
		String expressionString = "f(a,b)=a+b+c";
		try {
			ExpressionFactory.createExpression(expressionString);
			fail("Can parse an invalid variable number !!");
		}
		catch (ParsingException pe) {
		}
	}
	
}
