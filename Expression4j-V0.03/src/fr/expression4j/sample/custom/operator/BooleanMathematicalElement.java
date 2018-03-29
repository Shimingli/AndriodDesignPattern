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
package fr.expression4j.sample.custom.operator;

import java.util.Properties;

import fr.expression4j.basic.MathematicalElement;
import fr.expression4j.basic.MathematicalException;

/**
 * define a mathematical element who contens a boolean element.
 * @author SGINER
 *
 */
public class BooleanMathematicalElement implements MathematicalElement {

	//parameter value used to store element in a config file.
	public final static String BOOLEAN_VALUE = "BoleanValue"; 

	private Boolean booleanValue;
	
	public BooleanMathematicalElement(boolean value) {
		super();
		this.booleanValue = Boolean.valueOf(value);
	}

	public double getComplexValue() throws MathematicalException {
		//no used
		return 0;
	}

	public Properties getProperties() {
		//used to store objet in xml file (used by configuration manager)
		Properties result = new Properties();
		result.put(BOOLEAN_VALUE,booleanValue.toString());
		return result;
	}

	public double getRealValue() {
		//not used
		return 0;
	}

	public int getType() {
		//create a new constant for Boolean type.
		return 6;
	}

	public Object getValue() {
		//get the value of the String as an Object.
		return booleanValue;
	}

	public void setProperties(Properties properties) {
		//used to store objet in xml file (used by configuration manager)
		booleanValue = Boolean.valueOf(properties.getProperty(BOOLEAN_VALUE));
	}

}
