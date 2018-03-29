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
public class TestParsing extends TestCase {
	public static Test suite() {
		return new TestSuite(TestParsing.class);
	}

	public void testParsing1() {
		String expressionString = "f()=1+2";
		try {
			Expression expression = ExpressionFactory.createExpression(expressionString);
			assertEquals("f",expression.getName());
			assertEquals(0,expression.getParameters().size());
		}
		catch (ParsingException pe) {
			fail("Cannot parse simple expression. " + pe);
		}
	}

	public void testParsing2() {
		String expressionString = "f(x)=1+2";
		try {
			Expression expression = ExpressionFactory.createExpression(expressionString);
			assertEquals("f",expression.getName());
			assertEquals(1,expression.getParameters().size());
			assertEquals("x",expression.getParameters().get(0));
		}
		catch (ParsingException pe) {
			fail("Cannot parse simple expression. " + pe);
		}
	}
	
	public void testParsing3() {
		String expressionString = "foo(x,y,z)=1+2";
		try {
			Expression expression = ExpressionFactory.createExpression(expressionString);
			assertEquals("foo",expression.getName());
			assertEquals(3,expression.getParameters().size());
			assertEquals("x",expression.getParameters().get(0));
			assertEquals("y",expression.getParameters().get(1));
			assertEquals("z",expression.getParameters().get(2));
		}
		catch (ParsingException pe) {
			fail("Cannot parse simple expression. " + pe);
		}
	}

	public void testParsing4() {
		String expressionString = "foo(toto,TITI4,TuTu)=1+2";
		try {
			Expression expression = ExpressionFactory.createExpression(expressionString);
			assertEquals("foo",expression.getName());
			assertEquals(3,expression.getParameters().size());
			assertEquals("toto",expression.getParameters().get(0));
			assertEquals("TITI4",expression.getParameters().get(1));
			assertEquals("TuTu",expression.getParameters().get(2));
		}
		catch (ParsingException pe) {
			fail("Cannot parse simple expression. " + pe);
		}
	}
}
