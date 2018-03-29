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
 * Parse an expression (like 2*x+4-4.5*(2+4-f(x))).
 * 
 * @author SGINER
 *
 */
public class ExpressionExpressionElement implements ExpressionElement {

	public ExpressionExpressionElement() {
		super();
	}

	/* (non-Javadoc)
	 * @see fr.expression4j.basic.ExpressionElement#parseElement(java.lang.String, fr.expression4j.basic.ParseInfo)
	 */
	public boolean parseElement(String expression, ExpressionModel expressionModel,
			ParseInfo parseInfo, Catalog catalog, List functionParameters, int priorityOperatorLevel) throws ParsingException {

		int maxOperatorLevel = expressionModel.getMaxOperatorPriority();
		if (priorityOperatorLevel == maxOperatorLevel) {
			return parseSimpleElement(expression,expressionModel,parseInfo,catalog,functionParameters,priorityOperatorLevel);
		}
		
		int endPos = parseInfo.getEndPos();
		
		ParseInfo tmpParseInfo = new ParseInfo();
		tmpParseInfo.setEndPos(endPos);
		
		if (parseElement(expression,expressionModel,tmpParseInfo,catalog,functionParameters,priorityOperatorLevel + 1)) {
			//found a term
			//check for the operator
			int storedEndPos = tmpParseInfo.getEndPos();
			TreeElement storedTreeElement = tmpParseInfo.getTreeElement();
			ParseInfo operatorParseInfo = new ParseInfo();
			operatorParseInfo.setEndPos(storedEndPos);
			ExpressionElement binaryOperatorExpressionElement = new BinaryOperatorExpressionElement();

			while (ExpressionElementUtil.checkPosition(expression,storedEndPos) && 
				binaryOperatorExpressionElement.parseElement(expression,expressionModel,operatorParseInfo,catalog,functionParameters,priorityOperatorLevel)) {

				tmpParseInfo.setEndPos(operatorParseInfo.getEndPos());
				
				if (parseElement(expression,expressionModel,tmpParseInfo,catalog,functionParameters,priorityOperatorLevel + 1)) {
					//found expression
					storedEndPos = tmpParseInfo.getEndPos();
					operatorParseInfo.setEndPos(storedEndPos);
					storedTreeElement = new TreeElement(TreeElement.TREE_ELEMENT_TYPE_BINARY_OPERATOR,storedTreeElement,tmpParseInfo.getTreeElement(),null,getName(),operatorParseInfo.getTreeElement().getStringElement(),null);
				}
				else {
					//expression not found !!
					//only a term
					parseInfo.setEndPos(storedEndPos);
					parseInfo.setTreeElement(storedTreeElement);
					return true;
				}
			}
			//operator not found !!
			parseInfo.setEndPos(storedEndPos);
			parseInfo.setTreeElement(storedTreeElement);
			return true;
		}
		//expression not found !!!
		parseInfo.setEndPos(tmpParseInfo.getEndPos());
		return false;
	}

	public String getName() {
		return "ExpressionExpressionElement";
	}

	public boolean parseSimpleElement(String expression, ExpressionModel expressionModel,
			ParseInfo parseInfo, Catalog catalog, List functionParameters, int priorityOperatorLevel) throws ParsingException {

		int endPos = parseInfo.getEndPos();
		
		ParseInfo tmpParseInfo = new ParseInfo();
		tmpParseInfo.setEndPos(endPos);

		ExpressionElement signedExpressionExpressionElement = new SignedExpressionExpressionElement();
		
		if (signedExpressionExpressionElement.parseElement(expression,expressionModel,tmpParseInfo,catalog,functionParameters,priorityOperatorLevel + 1)) {
			//found a term
			//check for the operator
			int storedEndPos = tmpParseInfo.getEndPos();
			TreeElement storedTreeElement = tmpParseInfo.getTreeElement();
			ParseInfo operatorParseInfo = new ParseInfo();
			operatorParseInfo.setEndPos(storedEndPos);
			ExpressionElement binaryOperatorExpressionElement = new BinaryOperatorExpressionElement();

			while (ExpressionElementUtil.checkPosition(expression,storedEndPos) && 
					binaryOperatorExpressionElement.parseElement(expression,expressionModel,operatorParseInfo,catalog,functionParameters,priorityOperatorLevel)) {

				tmpParseInfo.setEndPos(operatorParseInfo.getEndPos());
				
				if (signedExpressionExpressionElement.parseElement(expression,expressionModel,tmpParseInfo,catalog,functionParameters,priorityOperatorLevel + 1)) {
					//found expression
					storedEndPos = tmpParseInfo.getEndPos();
					operatorParseInfo.setEndPos(storedEndPos);
					storedTreeElement = new TreeElement(TreeElement.TREE_ELEMENT_TYPE_BINARY_OPERATOR,storedTreeElement,tmpParseInfo.getTreeElement(),null,getName(),operatorParseInfo.getTreeElement().getStringElement(),null);
				}
				else {
					//expression not found !!
					//only a term
					parseInfo.setEndPos(storedEndPos);
					parseInfo.setTreeElement(storedTreeElement);
					return true;
				}
			}
			//operator not found !!
			parseInfo.setEndPos(storedEndPos);
			parseInfo.setTreeElement(storedTreeElement);
			return true;
		}
		//term not found !!!
		parseInfo.setEndPos(tmpParseInfo.getEndPos());
		return false;
	}
	
	public MathematicalElement evaluate(TreeElement element, Catalog catalog, OperatorManager operatorManager, Parameters parameters, ExpressionModel model) throws EvalException {
		throw new EvalException("Cannot evaluate " + getName());
	}

	public String toString(TreeElement element, ExpressionModel expressionModel) {
		return "<" + getName() + ">";
	}


}
