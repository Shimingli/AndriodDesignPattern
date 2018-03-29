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

/**
 * Parse a signed expression. Signed expression is like -2.34 or -(epression) or ...
 * 
 * @author SGINER
 *
 */
public class SignedExpressionExpressionElement implements ExpressionElement {

	public SignedExpressionExpressionElement() {
		super();
	}

	/* (non-Javadoc)
	 * @see fr.expression4j.basic.ExpressionElement#parseElement(java.lang.String, fr.expression4j.basic.ParseInfo)
	 */
	public boolean parseElement(String expression, ExpressionModel expressionModel,
			ParseInfo parseInfo, Catalog catalog, List functionParameters, int priorityOperatorLevel)
			throws ParsingException {
		int endPos = parseInfo.getEndPos();

		ExpressionElement simpleExpressionElement = new SimpleExpressionExpressionElement();
		ParseInfo operatorParseInfo = new ParseInfo();
		operatorParseInfo.setEndPos(endPos);
		ExpressionElement unaryOperatorExpressionElement = new UnaryOperatorExpressionElement();
		
		if (ExpressionElementUtil.checkPosition(expression,endPos) &&
				unaryOperatorExpressionElement.parseElement(expression,expressionModel,operatorParseInfo,catalog,functionParameters,0)) {
			
			ParseInfo tmpParseInfo = new ParseInfo();
			tmpParseInfo.setEndPos(operatorParseInfo.getEndPos());
			
			if (simpleExpressionElement.parseElement(expression,expressionModel,tmpParseInfo,catalog,functionParameters,0)) {
				parseInfo.setEndPos(tmpParseInfo.getEndPos());
				TreeElement element = new TreeElement(TreeElement.TREE_ELEMENT_TYPE_UNARY_OPERATOR,tmpParseInfo.getTreeElement(),null,null,getName(),operatorParseInfo.getTreeElement().getStringElement(),null);
				parseInfo.setTreeElement(element);
				return true;
			}
			
			parseInfo.setEndPos(tmpParseInfo.getEndPos());
			return false;
		}
		else {
			return simpleExpressionElement.parseElement(expression,expressionModel,parseInfo,catalog,functionParameters,0);
		}
	}

	public String getName() {
		return "SignedExpressionExpressionElement";
	}

	public MathematicalElement evaluate(TreeElement element, Catalog catalog, OperatorManager operatorManager, Parameters parameters, ExpressionModel model) throws EvalException {
		throw new EvalException("Cannot evaluate " + getName());
	}

	public String toString(TreeElement element, ExpressionModel expressionModel) {
		return "<" + getName() + ">";
	}

}
