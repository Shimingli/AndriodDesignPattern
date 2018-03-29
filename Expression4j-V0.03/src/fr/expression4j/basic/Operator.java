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

/**
 * Interface used to define an operator (like +, -, * ...)
 * @author SGINER
 *
 */
public interface Operator {
	
	public static final String OPERATOR_DIVIDE      = "Divide";
	public static final String OPERATOR_MULTIPLY    = "Multiply";
	public static final String OPERATOR_PLUS        = "Plus";
	public static final String OPERATOR_MINUS       = "Minus";
	public static final String OPERATOR_POW         = "Pow";
	public static final String OPERATOR_UNARY_PLUS  = "UnaryPlus";
	public static final String OPERATOR_UNARY_MINUS = "UnaryMinus";
	
	
	/**
	 * Tell if operator is an unary operator.
	 * @return <code>true</code> if operator is an unary operator, <code>false</code> otherwise.
	 */
	public boolean isUnary();
	
	/**
	 * Get the name of the operator.
	 * @return the name of the operator.
	 */
	public String getName();
	
	/**
	 * Get the symbol associated to the operator (like +, -, AND ...). 
	 * This symbol is used by the parser to identify the operator.
	 * @return the symbol associated to the operator.
	 */
	public String getSymbol();
}
