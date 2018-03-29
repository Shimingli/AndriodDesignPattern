//   Copyright 2006 Stephane GINER
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

package fr.expression4j.basic.impl;

import java.util.Properties;

import org.apache.log4j.Logger;

import fr.expression4j.basic.MathematicalElement;
import fr.expression4j.basic.MathematicalException;

public class ComplexImpl extends RealImpl {

	static Logger log = Logger. getLogger(ComplexImpl.class);

	private double complexValue;

	public ComplexImpl(double realValue, double complexValue) {
		super(realValue);
		log.debug("In constructor: real: " + realValue + " complex: " + complexValue);
		this.complexValue = complexValue;
		properties.setProperty(MathematicalElement.COMPLEX_VALUE,String.valueOf(complexValue));
	}


	public double getComplexValue() throws MathematicalException {
		return complexValue;
	}

	public String toString() {
		String result = "";
		if (realValue != 0) {
			result += realValue;
		}
		if (complexValue != 0) {
			if (complexValue < 0) {
				result += complexValue;
			}
			else {
				result += "+" + complexValue;
			}
			result += "i";
		}
		return result;
	}

	public int getType() {
		return 2;
	}

	public void setProperties(Properties properties) {
		super.setProperties(properties);

		String stringValue = properties.getProperty(MathematicalElement.COMPLEX_VALUE);
		if (stringValue != null) {
			complexValue = Double.parseDouble(stringValue);
		}
		else {
			complexValue = 0;
		}
	}
	
}
