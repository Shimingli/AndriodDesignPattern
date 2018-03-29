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
import java.util.Vector;

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
 * Parse a function parameters in an expression.
 * in expression "f(x)=g(2*x+1)" parameters is "2*x+1".
 * 
 * BNF is
 * 
 * parameters   ::= expression "," parameters | expression | ""
 * expression   ::= [see global BNF in @link ExpressionImp]
 * 
 * @author SGINER
 *
 */
public class ParametersExpressionElement implements ExpressionElement {

	public ParametersExpressionElement() {
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
	
	/* (non-Javadoc)
	 * @see fr.expression4j.basic.ExpressionElement#parseElement(java.lang.String, fr.expression4j.basic.ParseInfo)
	 */
	public boolean parseElement(String expression, ExpressionModel expressionModel,
			ParseInfo parseInfo, Catalog catalog, List functionParameters, int priorityOperatorLevel) throws ParsingException {
		int endPos = parseInfo.getEndPos();
		List parameterList = new Vector();
		
		ParseInfo tmpParseInfo = new ParseInfo();
		tmpParseInfo.setEndPos(endPos);
		
		//check directly for the ')' parenthesis
		if (ExpressionElementUtil.checkPosition(expression,endPos) &&
			expression.charAt(endPos) == ')') {
			parseInfo.setTreeElement(new TreeElement(TreeElement.TREE_ELEMENT_TYPE_OPERATOR_NOT_DEFINE,null,null,null,getName(),null,parameterList));
			return true;
		}
		
		ExpressionElement expressionExpressionElement = new ExpressionExpressionElement();
		
		if (expressionExpressionElement.parseElement(expression,expressionModel,tmpParseInfo,catalog,functionParameters,1)) {
			//found an expression
			//check for s�parator
			int storedEndPos = tmpParseInfo.getEndPos();
			parameterList.add(tmpParseInfo.getTreeElement());

			while (ExpressionElementUtil.checkPosition(expression,storedEndPos) && 
					operator(expression,",",storedEndPos)) {

				tmpParseInfo.setEndPos(storedEndPos + 1);
				
				if (expressionExpressionElement.parseElement(expression,expressionModel,tmpParseInfo,catalog,functionParameters,1)) {
					//found signed expression
					storedEndPos = tmpParseInfo.getEndPos();
					parameterList.add(tmpParseInfo.getTreeElement());
				}
				else {
					//expression not found !!
					parseInfo.setEndPos(tmpParseInfo.getEndPos());
					return false;
				}
			}
			//, not found !!
			parseInfo.setEndPos(storedEndPos);
			TreeElement result = new TreeElement(TreeElement.TREE_ELEMENT_TYPE_OPERATOR_NOT_DEFINE,null,null,null,getName(),null,parameterList);
			parseInfo.setTreeElement(result);
			return true;
		}
		//signed expression not found !!!
		parseInfo.setEndPos(tmpParseInfo.getEndPos());
		return false;
	}

	public String getName() {
		return "ParametersExpressionElement";
	}

	public MathematicalElement evaluate(TreeElement element, Catalog catalog, OperatorManager operatorManager, Parameters parameters, ExpressionModel model) throws EvalException {
		throw new EvalException("Cannot evaluate " + getName());
	}

	public String toString(TreeElement element, ExpressionModel expressionModel) {
		return "<" + getName() + ">";
	}

}
