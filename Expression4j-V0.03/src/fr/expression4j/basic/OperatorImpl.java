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

import fr.expression4j.core.exception.EvalException;

public interface OperatorImpl {

	/**
	 * Tell if operator is an unary or bynary operator.
	 * @return <code>true</code> if operator is unary.
	 */
	public boolean isUnaryOperator();
	
	/**
	 * Get the left operande managed by this operator implementation.
	 * @return the left operande type of the operator.
	 */
	public int getLeftOperandeType();

	/**
	 * Get the right operande managed by this operator implementation.
	 * @return the right operande type of the operator.
	 */
	public int getRightOperandeType();
	
	/**
	 * Get the operator name associated to this operator implementation.
	 * @return the operator name associated to this operator implementation.
	 */
	public String getOperatorName();
	
	/**
	 * Set the operator to the given element.
	 * @param leftElement left element for operator.
	 * @param rightElement right element for operator.
	 * @return result.
	 */
	public MathematicalElement compute(MathematicalElement leftElement,MathematicalElement rightElement) throws EvalException;
}
