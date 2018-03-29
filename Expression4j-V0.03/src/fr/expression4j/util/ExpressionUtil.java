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

package fr.expression4j.util;

import fr.expression4j.basic.ExpressionElement;
import fr.expression4j.basic.MathematicalElement;
import fr.expression4j.basic.Operator;
import fr.expression4j.basic.OperatorManager;
import fr.expression4j.core.Catalog;
import fr.expression4j.core.ExpressionModel;
import fr.expression4j.core.Parameters;
import fr.expression4j.core.exception.EvalException;
import fr.expression4j.core.impl.TreeElement;

public class ExpressionUtil {


	private static String getWellFormedtString(Operator currentOperator,StringElement element,ExpressionModel expressionModel) {
		
		if (element.getOperator() == null) {
			return element.getValue();
		}
		
		int currentOperatorLevel = expressionModel.getOperatorLevel(currentOperator);
		int elementOperatorLevel = expressionModel.getOperatorLevel(element.getOperator());
		
		if (element.getType() == TreeElement.TREE_ELEMENT_TYPE_BINARY_OPERATOR &&
			currentOperatorLevel > elementOperatorLevel) {
		
			return "(" + element.getValue() + ")";
		}
			
		return element.getValue();
	}

	/**
	 * Compute the value of a TreeElement and return is value.
	 * @param element expression tree element.
	 * @param catalog catalog used to parse expression.
	 * @param operatorManager operator manager to use for the expression evaluation.
	 * @param parameters parameters to use for the expression evaluation.
	 * @param expressionModel model used to parse the expresion.
	 * @return the evaluated value.
	 * @throws EvalException if an error occurd.
	 */
	public static  MathematicalElement computeElement(TreeElement element, Catalog catalog, OperatorManager operatorManager,Parameters parameters, ExpressionModel expressionModel)  throws EvalException {
		MathematicalElement leftOperande;
		MathematicalElement rightOperande;
		
		switch (element.getElementType()) {
			case TreeElement.TREE_ELEMENT_TYPE_BINARY_OPERATOR:
				leftOperande = computeElement(element.getLeftElement(),catalog,operatorManager,parameters,expressionModel);
				rightOperande = computeElement(element.getRightElement(),catalog,operatorManager,parameters,expressionModel);
				return operatorManager.computeValue(element.getStringElement(),leftOperande,rightOperande);
				
				
			case TreeElement.TREE_ELEMENT_TYPE_UNARY_OPERATOR:
				leftOperande = computeElement(element.getLeftElement(),catalog,operatorManager,parameters,expressionModel);
				return operatorManager.computeValue(element.getStringElement(),leftOperande);

			case TreeElement.TREE_ELEMENT_TYPE_VALUE:
				leftOperande = element.getValue();
				return leftOperande;
				
			case TreeElement.TREE_ELEMENT_TYPE_EXPRESSION_ELEMENT:
				String elementName = element.getExpressionElementName();
				ExpressionElement currentElement = expressionModel.getExpressionElement(elementName);
				if (currentElement == null) {
					throw new EvalException("Could not find expression element " + elementName + " in expression model.");
				}
				return currentElement.evaluate(element,catalog,operatorManager,parameters,expressionModel);
				
			default:
				throw new EvalException("Unknown tree element type (" + element.getElementType() + ")");
		}
	}
	
	/*
	 * this method is less speed than the getElement2
	private static TreeElement getElement(TreeElement element, String way) {
		if (way.length() != 0) {
			char firstChar = way.charAt(0);
			String queue = way.substring(1);
			if (firstChar == 'l') {
				return getElement(element.getLeftElement(),queue);
			}
			return getElement(element.getRightElement(),queue);
		}
		return element;
	}
	*/

	private static TreeElement getElement2(TreeElement element, String way) {
		int wayLen = way.length();
		int currentPos = 0;
		TreeElement result = element;
		
		while (currentPos < wayLen) {
			if (way.charAt(currentPos++) == 'l') {
				result = result.getLeftElement();
			}
			else {
				result = result.getRightElement();
			}
		}
		
		return result;
	}
	
	
	/**
	 * Compute the value of a TreeElement and return is value.
	 * @param way way in tree element to the current node.
	 * @param rootElement expression root tree element.
	 * @param catalog catalog used to parse expression.
	 * @param operatorManager operator manager to use for the expression evaluation.
	 * @param parameters parameters to use for the expression evaluation.
	 * @param expressionModel model used to parse the expresion.
	 * @return the evaluated value.
	 * @throws EvalException if an error occurd.
	 */
	public static  MathematicalElement computeElement(String way,TreeElement rootElement, Catalog catalog, OperatorManager operatorManager,Parameters parameters, ExpressionModel expressionModel)  throws EvalException {
		MathematicalElement leftOperande;
		MathematicalElement rightOperande;
		
		TreeElement currentElement = getElement2(rootElement,way);
		
		switch (currentElement.getElementType()) {
			case TreeElement.TREE_ELEMENT_TYPE_BINARY_OPERATOR:
				leftOperande = computeElement(way + "l",rootElement,catalog,operatorManager,parameters,expressionModel);
				rightOperande = computeElement(way + "r",rootElement,catalog,operatorManager,parameters,expressionModel);
				return operatorManager.computeValue(currentElement.getStringElement(),leftOperande,rightOperande);
				
				
			case TreeElement.TREE_ELEMENT_TYPE_UNARY_OPERATOR:
				leftOperande = computeElement(way + "l",rootElement,catalog,operatorManager,parameters,expressionModel);
				return operatorManager.computeValue(currentElement.getStringElement(),leftOperande);

			case TreeElement.TREE_ELEMENT_TYPE_VALUE:
				leftOperande = currentElement.getValue();
				return leftOperande;
				
			case TreeElement.TREE_ELEMENT_TYPE_EXPRESSION_ELEMENT:
				String elementName = currentElement.getExpressionElementName();
				ExpressionElement currentExpressionElement = expressionModel.getExpressionElement(elementName);
				if (currentExpressionElement == null) {
					throw new EvalException("Could not find expression element " + elementName + " in expression model.");
				}
				return currentExpressionElement.evaluate(currentElement,catalog,operatorManager,parameters,expressionModel);
				
			default:
				throw new EvalException("Unknown tree element type (" + currentElement.getElementType() + ")");
		}
	}
	
	
	/**
	 * Transform the expression tree element in String.
	 * @param element tree element to transform.
	 * @param expressionModel model used by the parser to construct the expression tree element.
	 * @return the string representing the element.
	 */
	public static StringElement toString(TreeElement element,ExpressionModel expressionModel) {
		StringElement leftString;
		StringElement rightString;
		
		int currentType = element.getElementType(); 
		
		switch (currentType) {
			case TreeElement.TREE_ELEMENT_TYPE_EXPRESSION_ELEMENT:
				String elementName = element.getExpressionElementName();
				ExpressionElement currentElement = expressionModel.getExpressionElement(elementName);
				if (currentElement == null) {
					return new StringElement(currentType,"[Expression element not found !]",null);
				}
				return new StringElement(currentType,currentElement.toString(element,expressionModel),null);

			case TreeElement.TREE_ELEMENT_TYPE_BINARY_OPERATOR:
				leftString = toString(element.getLeftElement(),expressionModel);
				rightString = toString(element.getRightElement(),expressionModel);
				Operator currentOperator = expressionModel.getOperator(element.getStringElement()); 
				String operatorSymbol = currentOperator.getSymbol();
				return new StringElement(currentType,getWellFormedtString(currentOperator,leftString,expressionModel) + operatorSymbol + getWellFormedtString(currentOperator,rightString,expressionModel),currentOperator);
				
			case TreeElement.TREE_ELEMENT_TYPE_UNARY_OPERATOR:
				leftString = toString(element.getLeftElement(),expressionModel);
				currentOperator = expressionModel.getOperator(element.getStringElement()); 
				operatorSymbol = currentOperator.getSymbol();
				return new StringElement(currentType,operatorSymbol + leftString.getValue(),currentOperator);
				
			case TreeElement.TREE_ELEMENT_TYPE_VALUE:
				MathematicalElement number = element.getValue();
				return new StringElement(currentType,number.toString(),null);
			
				/*
			case TreeElement.TREE_ELEMENT_TYPE_VARIABLE:
			case TreeElement.TREE_ELEMENT_TYPE_CONSTANT:
				return new StringElement(currentType,element.getStringElement(),null);
			*/	
			
			case TreeElement.TREE_ELEMENT_TYPE_OPERATOR_NOT_DEFINE:
			default:
				return new StringElement(currentType,"[Not define !]",null);
		}
	}

}
