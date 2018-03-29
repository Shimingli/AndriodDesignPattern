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

package fr.expression4j.test.implementation;

import java.util.HashMap;
import java.util.Map;

import fr.expression4j.core.Parameters;
import fr.expression4j.core.exception.ParametersException;
import fr.expression4j.factory.ExpressionFactory;
import fr.expression4j.factory.NumberFactory;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * test addition in expression
 * @author SGINER
 *
 */
public class TestParameters extends TestCase {
	public static Test suite() {
		return new TestSuite(TestParameters.class);
	}

	public void testEmptyParameters() {
		Parameters parameters = ExpressionFactory.createParameters();
		assertEquals(0,parameters.getParameters().size());
	}

	public void testParameters() {
		Parameters parameters = ExpressionFactory.createParameters();
		assertEquals(0,parameters.getParameters().size());
		
		parameters.addParameter("x",NumberFactory.createReal(2.0));
		parameters.addParameter("y",NumberFactory.createReal(5.0));
		
		try {
			assertEquals(2.0,parameters.getParameter("x").getRealValue(),0.0);
			assertEquals(5.0,parameters.getParameter("y").getRealValue(),0.0);
		}
		catch (ParametersException pe) {
			fail("cannot find parameter.");
		}
	}

	public void testParameters2() {
		Parameters parameters = ExpressionFactory.createParameters();
		assertEquals(0,parameters.getParameters().size());
		
		parameters.addParameter("x",NumberFactory.createReal(2.0));
		parameters.addParameter("x",NumberFactory.createReal(5.0));
		
		try {
			assertEquals(5.0,parameters.getParameter("x").getRealValue(),0.0);
		}
		catch (ParametersException pe) {
			fail("cannot find parameter.");
		}
	}
	
	public void testParameters3() {
		Parameters parameters = ExpressionFactory.createParameters();
		assertEquals(0,parameters.getParameters().size());
		
		parameters.addParameter("toto",NumberFactory.createReal(2.0));
		parameters.addParameter("titi5",NumberFactory.createReal(5.0));
		
		try {
			assertEquals(2.0,parameters.getParameter("toto").getRealValue(),0.0);
			assertEquals(5.0,parameters.getParameter("titi5").getRealValue(),0.0);
		}
		catch (ParametersException pe) {
			fail("cannot find parameter.");
		}
	}

	public void testParametersIntoAnother() {
		Parameters parameters = ExpressionFactory.createParameters();
		Parameters parameters2 = ExpressionFactory.createParameters();;
		assertEquals(0,parameters.getParameters().size());
		
		parameters.addParameter("toto",NumberFactory.createReal(2.0));
		parameters.addParameter("titi5",NumberFactory.createReal(5.0));
		
		parameters2.addParameters(parameters);
		
		try {
			assertEquals(2.0,parameters2.getParameter("toto").getRealValue(),0.0);
			assertEquals(5.0,parameters2.getParameter("titi5").getRealValue(),0.0);
		}
		catch (ParametersException pe) {
			fail("cannot find parameter.");
		}
	}

	public void testParametersIntoAnother2() {
		Parameters parameters = ExpressionFactory.createParameters();
		assertEquals(0,parameters.getParameters().size());

		Map map = new HashMap(2);
		map.put("toto",NumberFactory.createReal(2.0));
		map.put("titi5",NumberFactory.createReal(5.0));
		
		parameters.addParameters(map);
		
		try {
			assertEquals(2.0,parameters.getParameter("toto").getRealValue(),0.0);
			assertEquals(5.0,parameters.getParameter("titi5").getRealValue(),0.0);
		}
		catch (ParametersException pe) {
			fail("cannot find parameter.");
		}
	}

}
