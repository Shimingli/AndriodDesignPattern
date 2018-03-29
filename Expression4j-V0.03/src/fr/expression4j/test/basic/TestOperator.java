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

package fr.expression4j.test.basic;

import fr.expression4j.basic.Operator;
import fr.expression4j.basic.impl.GenericOperator;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * test addition in expression
 * @author SGINER
 *
 */
public class TestOperator extends TestCase {
	public static Test suite() {
		return new TestSuite(TestOperator.class);
	}

	public void testGenericOperator1() {
		Operator operator = new GenericOperator("test","+",true);
		assertEquals("Invalid operator name.","test",operator.getName());
		assertEquals("Invalid operator symbol.","+",operator.getSymbol());
		assertEquals("Invalid even.",true,operator.isUnary());
	}

	public void testGenericOperator2() {
		Operator operator = new GenericOperator("test2","az",false);
		assertEquals("Invalid operator name.","test2",operator.getName());
		assertEquals("Invalid operator symbol.","az",operator.getSymbol());
		assertEquals("Invalid even.",false,operator.isUnary());
	}
	
}
