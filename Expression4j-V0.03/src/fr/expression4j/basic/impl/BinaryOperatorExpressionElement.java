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

package fr.expression4j.basic.impl;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import fr.expression4j.basic.ExpressionElement;
import fr.expression4j.basic.MathematicalElement;
import fr.expression4j.basic.Operator;
import fr.expression4j.basic.OperatorManager;
import fr.expression4j.basic.ParseInfo;
import fr.expression4j.core.Catalog;
import fr.expression4j.core.ExpressionModel;
import fr.expression4j.core.Parameters;
import fr.expression4j.core.exception.EvalException;
import fr.expression4j.core.exception.ParsingException;
import fr.expression4j.core.impl.TreeElement;

/**
 * Check for a binary operator (like +, -, *, /) in a String Expression like "2+3".
 * 
 * @author SGINER
 *
 */
public class BinaryOperatorExpressionElement implements ExpressionElement {

	static Logger log = Logger. getLogger(BinaryOperatorExpressionElement.class);
	
	public BinaryOperatorExpressionElement() {
		super();
	}

	private boolean checkOperator(Operator operator,String expression,int endPos) {
		log.debug("In checkOperator.");
		
		String operatorSymbol = operator.getSymbol();
		int operatorPosition = expression.indexOf(operatorSymbol,endPos);
		if (operatorPosition == endPos) {
			log.debug("Operator " + operator.getName() + " found.");
			return true;
		}
		log.debug("Operator " + operator.getName() + " not found.");
		return false;
	}
	
	/* (non-Javadoc)
	 * @see fr.expression4j.basic.ExpressionElement#parseElement(java.lang.String, fr.expression4j.basic.ParseInfo)
	 */
	public boolean parseElement(String expression,
			ExpressionModel expressionModel, ParseInfo parseInfo,
			Catalog catalog, List functionParameters, int priorityOperatorLevel)
			throws ParsingException {

		log.debug("In parseElement.");
		
		int currentEndPos = parseInfo.getEndPos();
		log.debug("Current end position: " + currentEndPos);
		
		List operators = expressionModel.getBinaryOperators(priorityOperatorLevel);
		Iterator iter = operators.iterator();
		while (iter.hasNext()) {
			Operator currentOperator = (Operator) iter.next();
			if (checkOperator(currentOperator,expression,currentEndPos)) {
				log.debug("Found operator " + currentOperator.getName());
				
				parseInfo.setEndPos(currentEndPos + currentOperator.getSymbol().length());
				TreeElement element = new TreeElement(TreeElement.TREE_ELEMENT_TYPE_BINARY_OPERATOR,null,null,null,getName(),currentOperator.getName(),null);
				parseInfo.setTreeElement(element);
				return true;
			}
		}
		
		log.debug("No operator found.");
		return false;
	}

	public String getName() {
		return "BinaryOperatorExpressionElement";
	}

	public MathematicalElement evaluate(TreeElement element, Catalog catalog, OperatorManager operatorManager, Parameters parameters, ExpressionModel model) throws EvalException {
		throw new EvalException("Could not evaluate an operator.");
	}

	public String toString(TreeElement element, ExpressionModel expressionModel) {
		return "<" + getName() + ">";
	}

}
