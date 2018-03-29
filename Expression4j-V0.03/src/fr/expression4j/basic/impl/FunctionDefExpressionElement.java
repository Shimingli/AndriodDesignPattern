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
 * parse a function d�finition. Function definition is like f(x,y,z) or myFunc(foo,p2,p3). 
 * @author SGINER
 *
 */
public class FunctionDefExpressionElement implements ExpressionElement {

	public FunctionDefExpressionElement() {
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
		
		ExpressionElement functionNameElement = new FunctionNameExpressionElement();

		if (functionNameElement.parseElement(expression,expressionModel,tmpParseInfo,catalog,functionParameters,0)) {
			//found function name
			//store the function name
			String storedFunctionName = tmpParseInfo.getTreeElement().getStringElement();
			
			endPos = tmpParseInfo.getEndPos();
			if (ExpressionElementUtil.checkPosition(expression,endPos) &&
				expression.charAt(endPos) == '(') {
				
				//found '(' paranthesis after the function name
				tmpParseInfo.setEndPos(endPos + 1);
				ExpressionElement parametersDef = new ParametersDefExpressionElement();
				if (parametersDef.parseElement(expression,expressionModel,tmpParseInfo,catalog,functionParameters,0)) {
					//found parameters
					//check for the ')' parenthesis
					endPos = tmpParseInfo.getEndPos();
					
					if (ExpressionElementUtil.checkPosition(expression,endPos) &&
						expression.charAt(endPos) == ')') {
						//we found a function
						parseInfo.setFunctionName(storedFunctionName);
						//parameter list is alredy define by parametersDef function.
						parseInfo.setEndPos(endPos + 1);
						return true;
					}
				}
			}
		}
		
		parseInfo.setEndPos(endPos);
		return false;
	}

	public String getName() {
		return "FunctionDefExpressionElement";
	}

	public MathematicalElement evaluate(TreeElement element, Catalog catalog, OperatorManager operatorManager, Parameters parameters, ExpressionModel model) throws EvalException {
		throw new EvalException("Cannot evaluate " + getName());
	}

	public String toString(TreeElement element, ExpressionModel expressionModel) {
		return "<" + getName() + ">";
	}

}
