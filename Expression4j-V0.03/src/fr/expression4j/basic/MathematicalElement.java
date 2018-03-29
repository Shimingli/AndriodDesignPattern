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

package fr.expression4j.basic;

import java.util.Properties;

public interface MathematicalElement {
	
	public final static String REAL_VALUE = "realValue"; 
	public final static String COMPLEX_VALUE = "complexValue"; 
	
	/**
	 * get generic value of mathematical element.
	 * Used to get value of custom element define by user.
	 * @return the value of the custom element. 
	 */
	public Object getValue();
	
	/**
	 * get the real value of the number
	 * @return the real value of the number
	 */
	public double getRealValue();
	
	/**
	 * get the complex value of the number
	 * @return the complex value of the number
	 */
	public double getComplexValue() throws MathematicalException;
	
	/**
	 * Return the unique identifier of an element type (1 for real, 2 for complex ...).
	 * @return the unique identifier of a MathematicalElement type.
	 */
	public int getType();

	/**
	 * Set properties for initialisation (when stored in xml property file)
	 * @param properties properties to set
	 */
	public void setProperties(Properties properties);
	
	/**
	 * Get ptoperties used to initialize element (to store it in xml property file).
	 * @return element properties.
	 */
	public Properties getProperties(); 
	
}
