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

package fr.expression4j.core;

import java.util.Map;

import fr.expression4j.basic.MathematicalElement;
import fr.expression4j.core.exception.ParametersException;

/**
 * Same as function parameters.
 *
 * It is used to set parameters value of a function to evaluate them.
 * If function is f(x,y,z), Parameters object is x,y and z values.
 * @author SGINER
 */
public interface Parameters {
	/**
	 * add a parameter
	 * @param name name of the parameter.
	 * @param value value of the parameter.
	 */
	public void addParameter(String name, MathematicalElement value);
	
	/**
	 * add parameters
	 * @param parameters parameters to add.
	 */
	public void addParameters(Map parameters);
	
	/**
	 * add parameters
	 * @param parameters parameters to add.
	 */
	public void addParameters(Parameters parameters);
	
	/**
	 * get parameter value.
	 * @param name name of the parameter.
	 * @return the value of the given parameter.
	 * @throws ParametersException if the parameter is not found.
	 */
	public MathematicalElement getParameter(String name) throws ParametersException;
	
	/**
	 * get the list of parameters
	 * @return list of parameters.
	 */
	public Map getParameters();
}
