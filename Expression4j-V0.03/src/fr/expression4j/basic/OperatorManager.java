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

import java.util.List;

import fr.expression4j.core.exception.EvalException;

public interface OperatorManager {

	/**
	 * Add an operator implementation to the manager.
	 * @param operatorImpl operator to add.
	 */
	public void addOperatorImpl(OperatorImpl operatorImpl);
	
	/**
	 * Remove an operator implementation to the manager.
	 * @param operatorImpl operator to remove.
	 */
	public void removeOperatorImpl(OperatorImpl operatorImpl);
	
	/**
	 * Get an operator impl.
	 * @param name operator impl to get.
	 * @return the operator impl if found. <code>null</code> otherwise.
	 */
	public OperatorImpl getOperatorImpl(String name);
	
	/**
	 * Get the list of operator impl.
	 * @return list of operator impl as Sting.
	 */
	public List getOperatorImplList();
	
	/**
	 * Eval an unary operator on given value.
	 * @param operatorName operator to apply
	 * @param element value on wich apply the operator 
	 * @return the evalued value.
	 * @throws EvalException if an error occurd (operator not define for the given element ...)
	 */
	public MathematicalElement computeValue(String operatorName,MathematicalElement element) throws EvalException;

	/**
	 * Eval a binary operator on given values.
	 * @param operatorName operator to apply
	 * @param leftElement left value of operator
	 * @param rightElement right value of operator
	 * @return the evalued value
	 * @throws EvalException EvalException if an error occurd (operator not define for the given element ...)
	 */
	public MathematicalElement computeValue(String operatorName,MathematicalElement leftElement,MathematicalElement rightElement) throws EvalException;
	
	/**
	 * get operator manager name
	 * @return operator manager name
	 */
	public String getName();
	
}
