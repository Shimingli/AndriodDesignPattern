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
 * Parse a parenthesis expression. Parenthesis expression is like (x+b).
 * 
 * BNF is
 * 
 * parenthesis-expression ::= "(" expression ")"
 * expression   ::= [see global BNF in @link ExpressionImp]
 * 
 * @author SGINER
 *
 */
public class ParenthesisExpressionElement implements ExpressionElement {

	public ParenthesisExpressionElement() {
		super();
	}

	/* (non-Javadoc)
	 * @see fr.expression4j.basic.ExpressionElement#parseElement(java.lang.String, fr.expression4j.basic.ParseInfo)
	 */
	public boolean parseElement(String expression,
			ExpressionModel expressionModel, ParseInfo parseInfo,
			Catalog catalog, List functionParameters, int priorityOperatorLevel)
			throws ParsingException {
		
		int endPos = parseInfo.getEndPos();
		ParseInfo tmpParseInfo = new ParseInfo();
		tmpParseInfo.setEndPos(endPos);
		
		//check for "(" expression ")"
		endPos = parseInfo.getEndPos();
		if (ExpressionElementUtil.checkPosition(expression,endPos) &&
			expression.charAt(endPos) == '(') {
			
			tmpParseInfo = new ParseInfo();
			tmpParseInfo.setEndPos(endPos + 1);
			ExpressionElement expressionExpressionElement = new ExpressionExpressionElement();
			
			if (expressionExpressionElement.parseElement(expression,expressionModel,tmpParseInfo,catalog,functionParameters, 1)) {
				endPos = tmpParseInfo.getEndPos();
				if (ExpressionElementUtil.checkPosition(expression,endPos) &&
					expression.charAt(endPos) == ')') {
					
					parseInfo.setEndPos(tmpParseInfo.getEndPos() + 1);
					parseInfo.setTreeElement(tmpParseInfo.getTreeElement());
					return true;
				}
				else {
					parseInfo.setEndPos(tmpParseInfo.getEndPos());
					return false;
				}
			}
			else {
				//found the "(" but not an expression !!!
				parseInfo.setEndPos(tmpParseInfo.getEndPos());
				return false;
			}
		}
		
		parseInfo.setEndPos(endPos);
		return false;
	}

	public String getName() {
		return "ParenthesisExpressionElement";
	}

	public MathematicalElement evaluate(TreeElement element, Catalog catalog, OperatorManager operatorManager, Parameters parameters, ExpressionModel model) throws EvalException {
		throw new EvalException("Cannot evaluate " + getName());
	}

	public String toString(TreeElement element, ExpressionModel expressionModel) {
		return "<" + getName() + ">";
	}

}
