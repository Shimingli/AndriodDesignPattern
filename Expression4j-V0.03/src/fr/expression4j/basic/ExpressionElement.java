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

import fr.expression4j.core.Catalog;
import fr.expression4j.core.ExpressionModel;
import fr.expression4j.core.Parameters;
import fr.expression4j.core.exception.EvalException;
import fr.expression4j.core.exception.ParsingException;
import fr.expression4j.core.impl.TreeElement;

public interface ExpressionElement {

	/**
	 * Parse an element of an expression like term, real, integer ... (See BNF)
	 * @param expression expression to parse.
	 * @param expressionModel meta model of expression.
	 * @param parseInfo info about parsing
	 * @param catalog function and constant catalog
	 * @param functionParameters parameters of the function (f(x), x is a parameter)
	 * @param priorityOperatorLevel priority of current operator.
	 * @return <code>true</code> if the expression content the element at the given position.
	 * @throws ParsingException
	 */
	public boolean parseElement(String expression, ExpressionModel expressionModel, ParseInfo parseInfo, Catalog catalog, List functionParameters, int priorityOperatorLevel) throws ParsingException;

	/**
	 * get the name of the element (like integer, variable ...)
	 * @return the name of the element
	 */
	public String getName();

	/**
	 * Evaluate the value of the current expression element
	 * @param element tree element of the expression.
	 * @param operatorManager operator manager of the current expression.
	 * @param parameters parameters given to the expression.
	 * @return the evaluated value of the expression.
	 */
	public MathematicalElement evaluate(TreeElement element, Catalog catalog, OperatorManager operatorManager, Parameters parameters,ExpressionModel model) throws EvalException;
	
	/**
	 * Create the String object associated to the expression element.
	 * @param element element properties for convertion.
	 * @param expressionModel expression model to use for current expression element.
	 * @return the string of the element.
	 */
	public String toString(TreeElement element,ExpressionModel expressionModel);
}

