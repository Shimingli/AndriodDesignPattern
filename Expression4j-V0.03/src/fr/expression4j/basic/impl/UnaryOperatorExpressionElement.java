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

public class UnaryOperatorExpressionElement implements ExpressionElement {

	public UnaryOperatorExpressionElement() {
		super();
	}

	private boolean checkOperator(Operator operator,String expression,int endPos) {
		String operatorSymbol = operator.getSymbol();
		int operatorPosition = expression.indexOf(operatorSymbol,endPos);
		if (operatorPosition == endPos) {
			return true;
		}
		return false;
	}
	
	public boolean parseElement(String expression,
			ExpressionModel expressionModel, ParseInfo parseInfo,
			Catalog catalog, List functionParameters, int priorityOperatorLevel)
			throws ParsingException {

		int currentEndPos = parseInfo.getEndPos();
		List operators = expressionModel.getUnaryOperators();
		Iterator iter = operators.iterator();
		while (iter.hasNext()) {
			Operator currentOperator = (Operator) iter.next();
			if (checkOperator(currentOperator,expression,currentEndPos)) {
				parseInfo.setEndPos(currentEndPos + currentOperator.getSymbol().length());
				TreeElement element = new TreeElement(TreeElement.TREE_ELEMENT_TYPE_UNARY_OPERATOR,null,null,null,getName(),currentOperator.getName(),null);
				parseInfo.setTreeElement(element);
				return true;
			}
		}
		
		return false;
	}

	public String getName() {
		return "UnaryOperatorExpressionElement";
	}

	public MathematicalElement evaluate(TreeElement element, Catalog catalog, OperatorManager operatorManager, Parameters parameters, ExpressionModel model) throws EvalException {
		throw new EvalException("Cannot evaluate " + getName());
	}

	public String toString(TreeElement element, ExpressionModel expressionModel) {
		return "<" + getName() + ">";
	}

}
