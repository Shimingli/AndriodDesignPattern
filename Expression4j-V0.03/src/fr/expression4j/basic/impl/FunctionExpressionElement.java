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
import fr.expression4j.basic.OperatorManager;
import fr.expression4j.basic.ParseInfo;
import fr.expression4j.core.Catalog;
import fr.expression4j.core.Expression;
import fr.expression4j.core.ExpressionModel;
import fr.expression4j.core.Parameters;
import fr.expression4j.core.exception.EvalException;
import fr.expression4j.core.exception.ParsingException;
import fr.expression4j.core.impl.ParametersImpl;
import fr.expression4j.core.impl.TreeElement;
import fr.expression4j.util.ExpressionElementUtil;
import fr.expression4j.util.ExpressionUtil;

/**
 * Parse a function in an expression. Function is like f(x) or f(2*x+b-5,y) or foo(foo2(x),y) or ...
 * @author SGINER
 *
 */
public class FunctionExpressionElement implements ExpressionElement {

	public FunctionExpressionElement() {
		super();
	}

	/* (non-Javadoc)
	 * @see fr.expression4j.basic.ExpressionElement#parseElement(java.lang.String, fr.expression4j.basic.ParseInfo)
	 */
	public boolean parseElement(String expression, ExpressionModel expressionModel,
			ParseInfo parseInfo, Catalog catalog, List functionParameters, int priorityOperatorLevel) throws ParsingException {
		int endPos = parseInfo.getEndPos();
		ParseInfo tmpParseInfo = new ParseInfo();
		tmpParseInfo.setEndPos(endPos);
		
		ExpressionElement functionNameExpressionElement = new FunctionNameExpressionElement();
		ExpressionElement parametersExpressionElement = new ParametersExpressionElement();
		
		if (functionNameExpressionElement.parseElement(expression,expressionModel,tmpParseInfo,catalog, functionParameters, 0)) {
			//found variable
			//store the variable name
			String storedFunctionName = tmpParseInfo.getTreeElement().getStringElement();
			
			endPos = tmpParseInfo.getEndPos();
			if (ExpressionElementUtil.checkPosition(expression,endPos) &&
				expression.charAt(endPos) == '(') {
				
				//found '(' paranthesis after the function name
				tmpParseInfo.setEndPos(endPos + 1);
				if (parametersExpressionElement.parseElement(expression,expressionModel,tmpParseInfo,catalog, functionParameters, 0)) {
					//found parameters
					//check for the ')' parenthesis
					endPos = tmpParseInfo.getEndPos();
					
					if (ExpressionElementUtil.checkPosition(expression,endPos) &&
						expression.charAt(endPos) == ')') {
						//we found a function
						//see in catalog if function is define
						Expression tmpExpression = catalog.getExpression(storedFunctionName); 
						if (tmpExpression == null) {
							throw new ParsingException("Function " + storedFunctionName + " not found in given catalog");
						}
						
						//see if function as the same number of parameter
						
						List currentFunctionParameters = (List) tmpParseInfo.getTreeElement().getExpressionElementProperties();
						if (tmpExpression.getParameters().size() != currentFunctionParameters.size()) {
							throw new ParsingException("Function " + storedFunctionName + " as invalid parameter size.");
						}
						
						TreeElement treeElement = new TreeElement(TreeElement.TREE_ELEMENT_TYPE_EXPRESSION_ELEMENT,null,null,null,getName(),storedFunctionName,currentFunctionParameters);
						parseInfo.setEndPos(endPos + 1);
						parseInfo.setTreeElement(treeElement);
						return true;
					}
				}
			}
		}
		
		parseInfo.setEndPos(endPos);
		return false;
	}

	public String getName() {
		return "FunctionExpressionElement";
	}

	public MathematicalElement evaluate(TreeElement element, Catalog catalog, OperatorManager operatorManager, Parameters parameters,ExpressionModel model) throws EvalException {
		Parameters localParameters = new ParametersImpl();
		
		String functionName = element.getStringElement();
		Expression expression = catalog.getExpression(functionName);
		if (expression == null) {
			throw new EvalException("Could not find expression " + functionName + " in catalog.");
		}
		
		List functionParameters  = expression.getParameters();
		int  functionParametersSize      = functionParameters.size();
		
		List parametersValue = (List) element.getExpressionElementProperties();
		int  parametersValueSize          = parametersValue.size();
		
		if (functionParametersSize != parametersValueSize) {
			throw new EvalException("Invalid paramter size for function " + functionName);
		}
		
		for (int i=0; i<functionParametersSize; i++) {
			String currentParameterName = (String) functionParameters.get(i);
			TreeElement currentParameter = (TreeElement) parametersValue.get(i);
			MathematicalElement currentParameterValue = ExpressionUtil.computeElement(currentParameter,catalog,operatorManager,parameters,model);
			
			localParameters.addParameter(currentParameterName,currentParameterValue);
		}
		
		return expression.evaluate(operatorManager, localParameters);
	}

	private String toString(List parameters, ExpressionModel expressionModel) {
		StringBuffer result = new StringBuffer(50);
		String separator = "";

		Iterator iter = parameters.iterator();
		while (iter.hasNext()) {
			TreeElement currentElement = (TreeElement) iter.next();
			result.append(separator + ExpressionUtil.toString(currentElement,expressionModel).getValue());
			separator = ",";
		}
		
		return result.toString();
	}

	public String toString(TreeElement element,ExpressionModel expressionModel) {
		return element.getStringElement() + "(" + toString((List) element.getExpressionElementProperties(),expressionModel) + ")";
	}

}
