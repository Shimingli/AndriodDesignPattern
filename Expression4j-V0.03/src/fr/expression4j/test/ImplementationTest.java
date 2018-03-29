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

import fr.expression4j.test.implementation.TestCatalog;
import fr.expression4j.test.implementation.TestComplexImpl;
import fr.expression4j.test.implementation.TestParameters;
import fr.expression4j.test.implementation.TestRealImpl;
import junit.framework.TestSuite;


/**
 * do simple test like addition.
 * @author SGINER
 *
 */
public class ImplementationTest extends TestSuite {
	public static void main(String[] args) {
		junit.textui.TestRunner.run(suite());
	}
	
	protected void setUp() {
	}
	
	public static TestSuite suite() {
		TestSuite suite = new TestSuite();
		
		//module test
		suite.addTest(TestCatalog.suite());
		suite.addTest(TestParameters.suite());
		suite.addTest(TestRealImpl.suite());
		suite.addTest(TestComplexImpl.suite());

		return suite;
	}

}
