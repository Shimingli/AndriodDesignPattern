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

package fr.expression4j.test.optimization;

import fr.expression4j.core.Expression;
import fr.expression4j.factory.ExpressionFactory;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * test addition in expression
 * @author SGINER
 *
 */
public class TestOptimization extends TestCase {
	public static Test suite() {
		return new TestSuite(TestOptimization.class);
	}

	public void testOptimizeSimpleExpression() {
		try {
			Expression expression = ExpressionFactory.createExpression("f()=1+2+3+4+5");
			Expression exp2 = ExpressionFactory.optimizeExpression(expression);
			assertEquals("Invalid simplification.",15.0,exp2.evaluate(null).getRealValue(),0);
		}
		catch (Exception e) {
			fail("Cannot simplify expression : " + e);
		}
	}
}
