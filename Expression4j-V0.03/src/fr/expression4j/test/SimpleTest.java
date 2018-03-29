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

package fr.expression4j.test;

import fr.expression4j.test.simple.TestAddition;
import fr.expression4j.test.simple.TestConstant;
import fr.expression4j.test.simple.TestDivision;
import fr.expression4j.test.simple.TestExponent;
import fr.expression4j.test.simple.TestMultiplication;
import fr.expression4j.test.simple.TestNumber;
import fr.expression4j.test.simple.TestParenthesis;
import fr.expression4j.test.simple.TestParsing;
import fr.expression4j.test.simple.TestSoustraction;
import junit.framework.TestSuite;


/**
 * do simple test like addition.
 * @author SGINER
 *
 */
public class SimpleTest extends TestSuite {
	public static void main(String[] args) {
		junit.textui.TestRunner.run(suite());
	}
	
	protected void setUp() {
	}
	
	public static TestSuite suite() {
		TestSuite suite = new TestSuite();
		
		//module test
		suite.addTest(TestAddition.suite());
		suite.addTest(TestSoustraction.suite());
		suite.addTest(TestMultiplication.suite());
		suite.addTest(TestDivision.suite());
		suite.addTest(TestParenthesis.suite());
		suite.addTest(TestExponent.suite());
		suite.addTest(TestNumber.suite());
		suite.addTest(TestParsing.suite());
		suite.addTest(TestConstant.suite());

		return suite;
	}

}
