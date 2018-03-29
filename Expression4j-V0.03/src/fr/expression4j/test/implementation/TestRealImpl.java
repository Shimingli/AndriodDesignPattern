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

import java.util.Properties;

import fr.expression4j.basic.MathematicalElement;
import fr.expression4j.basic.MathematicalException;
import fr.expression4j.basic.impl.RealImpl;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * test implementation of real number
 * @author SGINER
 *
 */
public class TestRealImpl extends TestCase {
	public static Test suite() {
		return new TestSuite(TestRealImpl.class);
	}


	public void testRealImpl() {
		RealImpl realImpl = new RealImpl(230);
		assertEquals("Invalid realImpl type",1,realImpl.getType());
		assertEquals("Invalid real value",230,realImpl.getRealValue(),0);
		try {
			realImpl.getComplexValue();
			fail("Can get complex value on an integer.");
		}
		catch (MathematicalException me) {
			// all right
		}
	}
	
	public void testRealImplProperties() {
		MathematicalElement realImpl = new RealImpl(0);
		Properties properties = new Properties();
		
		properties.setProperty(MathematicalElement.REAL_VALUE,"10.2");
		realImpl.setProperties(properties);
		assertEquals("Invalid real value form properties.",10.2,realImpl.getRealValue(),0);
	}
	
}
