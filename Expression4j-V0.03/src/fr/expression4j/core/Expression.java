//	 Copyright 2006 Stephane GINER
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

import java.util.List;

import fr.expression4j.basic.MathematicalElement;
import fr.expression4j.basic.OperatorManager;
import fr.expression4j.core.exception.EvalException;

/**
 * Manage mathematic expression.
 * @author SGINER
 */
public interface Expression {

	/**
	 * return the catalog of the expression.
	 * @return catalog of current expression, <code>null</code> if no catalog is needed of define.
	 */
	public Catalog getCatalog();
	
	/**
	 * Evaluate the value of the expression with the default operator manager.
	 * @param parameters parameters values for evaluating expression.
	 * @return value of the evaluated expression as a double.
	 * @throws EvalException when error occurd (parameter not found ...)
	 */
	public MathematicalElement evaluate(Parameters parameters) throws EvalException;

	/**
	 * Evaluate the value of the expression.
	 * @param operatorManager operator manager to use to evaluate expression.
	 * @param parameters parameters values for evaluating expression.
	 * @return value of the evaluated expression as a double.
	 * @throws EvalException when error occurd (parameter not found ...)
	 */
	public MathematicalElement evaluate(OperatorManager operatorManager, Parameters parameters) throws EvalException;
	
	/**
	 * Get the parameter list associated to the function. If function is
	 * f(x,y,z), parameter list is an ordered list of String with "x", "y" and "z".
	 * @return parameter list associated to the expression.
	 */
	public List getParameters(); 
	
	/**
	 * Get the name of the function. If function is f(x,y,z), name if "f".
	 * @return name of the function.
	 */
	public String getName();
	
	/**
	 * Return the expression model associated to the expression.
	 * @return expression model associated to the expression.
	 */
	public ExpressionModel getExpressionModel();
	
}
