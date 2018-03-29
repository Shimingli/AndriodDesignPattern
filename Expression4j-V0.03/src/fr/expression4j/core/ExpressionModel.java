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

import java.util.List;

import fr.expression4j.basic.ExpressionElement;
import fr.expression4j.basic.Operator;
import fr.expression4j.core.exception.ModelException;

public interface ExpressionModel {


	/**
	 * get the name of the expression model
	 * @return expression model name.
	 */
	public String getName();
	
	/**
	 * Add unary operator to the model
	 * @param operator operator to add
	 * @throws ModelException if operator is not an unary operator
	 */
	public void addUnaryOperator(Operator operator) throws ModelException;

	/**
	 * Add a binary operator
	 * @param operator operator to add
	 * @param priority operator priority (from 1 to ... depend of your model)
	 * @throws ModelException if operator is not a binary operator
	 */
	public void addBinaryOperator(Operator operator,int priority) throws ModelException;
	
	/**
	 * Add expression element to the model.
	 * ExpressionElement is an object who know how to parse an element of an expression
	 * (real or integer for example). 
	 * If priority alredy exist in model, the new element step up others.
	 * @param expressionElement element to add.
	 * @param priority order to manage element (from 1 to ...)
	 * @throws ModelException if priority is not consecutive to others priority.
	 */
	public void addExpressionElement(ExpressionElement expressionElement, int priority) throws ModelException;
	
	/**
	 * Get the max binary operator priority.
	 * @return max binary operator priority.
	 */
	public int getMaxOperatorPriority();
	
	/**
	 * Get the max element priority.
	 * @return max element operator priority.
	 */
	public int getMaxExpressionElementPriority();
	
	/**
	 * Get the list of unary operators.
	 * @return list of unary operator as String object.
	 */
	public List getUnaryOperators();

	/**
	 * Get the list of binary operator for a given priority.
	 * @param priority priority operator to get.
	 * @return list of binary operator as Operator object.
	 */
	public List getBinaryOperators(int priority);
	
	/**
	 * Get expression element for a given priority.
	 * @param priority priority expression element to get.
	 * @return expression element or <code>null</code> if not found.
	 */
	public ExpressionElement getExpressionElement(int priority);
	
	/**
	 * Get expression element for a given name.
	 * @param name name of expression element to get.
	 * @return expression element or <code>null</code> if not found.
	 */
	public ExpressionElement getExpressionElement(String name);
	
	/**
	 * Get the named operator.
	 * @param name name of the operator.
	 * @return the operator if found, <code>null</code> otherwise.
	 */
	public Operator getOperator(String name);
	
	/**
	 * Get the level associated to a given operator.
	 * @param operator operator to get the level.
	 * @return the requested level.
	 */
	public int getOperatorLevel(Operator operator);

}
