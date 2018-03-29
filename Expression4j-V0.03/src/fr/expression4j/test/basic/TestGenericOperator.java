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
import fr.expression4j.basic.impl.OperatorDivide;
import fr.expression4j.basic.impl.OperatorMinus;
import fr.expression4j.basic.impl.OperatorMultiply;
import fr.expression4j.basic.impl.OperatorPlus;
import fr.expression4j.basic.impl.OperatorPow;
import fr.expression4j.basic.impl.OperatorUnaryMinus;
import fr.expression4j.basic.impl.OperatorUnaryPlus;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * test addition in expression
 * @author SGINER
 *
 */
public class TestGenericOperator extends TestCase {
	public static Test suite() {
		return new TestSuite(TestGenericOperator.class);
	}

	public void testOperatorPlus() {
		Operator operator = new OperatorPlus();
		assertEquals("Invalid operator name.","plus",operator.getName());
		assertEquals("Invalid operator symbol.","+",operator.getSymbol());
		assertEquals("Invalid even.",false,operator.isUnary());
	}

	public void testOperatorMinus() {
		Operator operator = new OperatorMinus();
		assertEquals("Invalid operator name.","minus",operator.getName());
		assertEquals("Invalid operator symbol.","-",operator.getSymbol());
		assertEquals("Invalid even.",false,operator.isUnary());
	}

	public void testOperatorMultiply() {
		Operator operator = new OperatorMultiply();
		assertEquals("Invalid operator name.","multiply",operator.getName());
		assertEquals("Invalid operator symbol.","*",operator.getSymbol());
		assertEquals("Invalid even.",false,operator.isUnary());
	}
	
	public void testOperatorDivide() {
		Operator operator = new OperatorDivide();
		assertEquals("Invalid operator name.","divide",operator.getName());
		assertEquals("Invalid operator symbol.","/",operator.getSymbol());
		assertEquals("Invalid even.",false,operator.isUnary());
	}

	public void testOperatorPow() {
		Operator operator = new OperatorPow();
		assertEquals("Invalid operator name.","pow",operator.getName());
		assertEquals("Invalid operator symbol.","^",operator.getSymbol());
		assertEquals("Invalid even.",false,operator.isUnary());
	}

	public void testOperatorUnaryPlus() {
		Operator operator = new OperatorUnaryPlus();
		assertEquals("Invalid operator name.","unary plus",operator.getName());
		assertEquals("Invalid operator symbol.","+",operator.getSymbol());
		assertEquals("Invalid even.",true,operator.isUnary());
	}
	
	public void testOperatorUnaryMinus() {
		Operator operator = new OperatorUnaryMinus();
		assertEquals("Invalid operator name.","unary minus",operator.getName());
		assertEquals("Invalid operator symbol.","-",operator.getSymbol());
		assertEquals("Invalid even.",true,operator.isUnary());
	}
	
}
