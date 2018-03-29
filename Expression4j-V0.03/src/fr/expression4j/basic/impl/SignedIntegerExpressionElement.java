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

import java.util.List;

import fr.expression4j.basic.ExpressionElement;
import fr.expression4j.basic.MathematicalElement;
import fr.expression4j.basic.OperatorManager;
import fr.expression4j.basic.ParseInfo;
import fr.expression4j.core.Catalog;
import fr.expression4j.core.ExpressionModel;
import fr.expression4j.core.Parameters;
import fr.expression4j.core.exception.EvalException;
import fr.expression4j.core.exception.ParsingException;
import fr.expression4j.core.impl.TreeElement;
import fr.expression4j.util.ExpressionElementUtil;

public class SignedIntegerExpressionElement implements ExpressionElement {

	public SignedIntegerExpressionElement() {
		super();
	}

	/**
	 * check if char at position in expression is in given operator list.
	 * @param operatorList operator list as string.
	 * @param position position to check.
	 * @return <code>true</code> if operator at position is in the given operator list.
	 */
	protected boolean operator(String expression,String operatorList, int position) {
		return operatorList.indexOf(expression.charAt(position)) != -1;
	}

	public boolean parseElement(String expression, ExpressionModel expressionModel,
			ParseInfo parseInfo, Catalog catalog, List functionParameters, int priorityOperatorLevel) throws ParsingException {

		int currentPos = parseInfo.getEndPos();
		ExpressionElement integerExpressionElement = new IntegerExpressionElement();

		if (ExpressionElementUtil.checkPosition(expression,currentPos) &&
			operator(expression,"+-",currentPos)) {
			
			int operator = TreeElement.TREE_ELEMENT_TYPE_UNARY_OPERATOR;
			
			ParseInfo tmpParseInfo = new ParseInfo();
			tmpParseInfo.setEndPos(currentPos + 1);
			if (integerExpressionElement.parseElement(expression,expressionModel,tmpParseInfo,catalog, functionParameters, 0)) {
				TreeElement treeElement = new TreeElement(operator,tmpParseInfo.getTreeElement(),null,null,getName(),null,null);
				parseInfo.setEndPos(tmpParseInfo.getEndPos());
				parseInfo.setTreeElement(treeElement);
				return true;
			}
		}

		return integerExpressionElement.parseElement(expression,expressionModel,parseInfo,catalog, functionParameters, 0); 
	}

	public String getName() {
		return "SignedIntegerExpressionElement";
	}

	public MathematicalElement evaluate(TreeElement element, Catalog catalog, OperatorManager operatorManager, Parameters parameters, ExpressionModel model) throws EvalException {
		throw new EvalException("Cannot evaluate " + getName());
	}

	public String toString(TreeElement element, ExpressionModel expressionModel) {
		return "<" + getName() + ">";
	}

}
