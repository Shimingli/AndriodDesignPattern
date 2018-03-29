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

package fr.expression4j.core.impl;

//import fr.expression4j.basic.MathematicalElement;
//import fr.expression4j.basic.MathematicalException;
//import fr.expression4j.basic.impl.RealImpl;
import fr.expression4j.core.Expression;
import fr.expression4j.core.exception.EvalException;
//import fr.expression4j.factory.NumberFactory;

public class OptimizedExpression extends ExpressionImpl implements Expression {

	public OptimizedExpression(ExpressionImpl expression) throws EvalException {
		this.name = expression.getName() + "2";
		this.catalog = expression.getCatalog();
		this.functionParameters = expression.getParameters();
		
		rootElement = optimizeIt(expression.getRootElement());
	}
	
	/**
	 * Optimize tree element.
	 * 
	 * Optimize all expression operator recursivly.
	 * 
	 * @param element element to optilyze
	 * @return optimized element
	 * @throws EvalException if an error occurd.
	 */
	
	private TreeElement optimizeIt(TreeElement element) throws EvalException {
		//TreeElement leftOperande;
		//TreeElement rightOperande;
		return null;
	/*	
		try {
			switch (element.getElementType()) {
				case TreeElement.TREE_ELEMENT_TYPE_BINARY_OPERATOR:
					leftOperande = optimizeIt(element.getLeftElement());
					rightOperande = optimizeIt(element.getRightElement());
					
					if (leftOperande.getElementType() == TreeElement.TREE_ELEMENT_TYPE_VALUE &&
						rightOperande.getElementType() == TreeElement.TREE_ELEMENT_TYPE_VALUE) {
						MathematicalElement value = leftOperande.getValue().divide(rightOperande.getValue());
						return new TreeElement(TreeElement.TREE_ELEMENT_TYPE_VALUE,null,null,value,null,null);
					}
					return new TreeElement(TreeElement.TREE_ELEMENT_TYPE_OPERATOR_DIVIDE,leftOperande,rightOperande,null,null,null);
					
				case TreeElement.TREE_ELEMENT_TYPE_OPERATOR_EXPONENT:
					leftOperande = optimizeIt(element.getLeftElement());
					rightOperande = optimizeIt(element.getRightElement());
					
					if (leftOperande.getElementType() == TreeElement.TREE_ELEMENT_TYPE_VALUE &&
						rightOperande.getElementType() == TreeElement.TREE_ELEMENT_TYPE_VALUE) {
						MathematicalElement value1 = leftOperande.getValue();
						MathematicalElement value2 = rightOperande.getValue();
						
						if (!(value1 instanceof RealImpl) || !(value2 instanceof RealImpl)) {
							throw new EvalException("Cannot evaluate expression with complex value.");
						}
						
						value1 = NumberFactory.createReal(Math.pow(value1.getRealValue(),value2.getRealValue()));
						return new TreeElement(TreeElement.TREE_ELEMENT_TYPE_VALUE,null,null,value1,null,null);
					}
					return new TreeElement(TreeElement.TREE_ELEMENT_TYPE_OPERATOR_EXPONENT,leftOperande,rightOperande,null,null,null);
					
				case TreeElement.TREE_ELEMENT_TYPE_OPERATOR_FACTOR:
					leftOperande = optimizeIt(element.getLeftElement());
					rightOperande = optimizeIt(element.getRightElement());
					
					if (leftOperande.getElementType() == TreeElement.TREE_ELEMENT_TYPE_VALUE &&
						rightOperande.getElementType() == TreeElement.TREE_ELEMENT_TYPE_VALUE) {
						return new TreeElement(TreeElement.TREE_ELEMENT_TYPE_VALUE,null,null,leftOperande.getValue().factor(rightOperande.getValue()),null,null);
					}
					return new TreeElement(TreeElement.TREE_ELEMENT_TYPE_OPERATOR_FACTOR,leftOperande,rightOperande,null,null,null);
	
				case TreeElement.TREE_ELEMENT_TYPE_OPERATOR_MINUS:
					leftOperande = optimizeIt(element.getLeftElement());
					rightOperande = optimizeIt(element.getRightElement());
					
					if (leftOperande.getElementType() == TreeElement.TREE_ELEMENT_TYPE_VALUE &&
						rightOperande.getElementType() == TreeElement.TREE_ELEMENT_TYPE_VALUE) {
						return new TreeElement(TreeElement.TREE_ELEMENT_TYPE_VALUE,null,null,leftOperande.getValue().diff(rightOperande.getValue()),null,null);
					}
					return new TreeElement(TreeElement.TREE_ELEMENT_TYPE_OPERATOR_MINUS,leftOperande,rightOperande,null,null,null);
	
				case TreeElement.TREE_ELEMENT_TYPE_OPERATOR_MINUS_UNAIRE:
					leftOperande = optimizeIt(element.getLeftElement());
					
					if (leftOperande.getElementType() == TreeElement.TREE_ELEMENT_TYPE_VALUE) {
						MathematicalElement value = NumberFactory.createReal(-leftOperande.getValue().getRealValue());
						return new TreeElement(TreeElement.TREE_ELEMENT_TYPE_VALUE,null,null,value,null,null);
					}
					return new TreeElement(TreeElement.TREE_ELEMENT_TYPE_OPERATOR_MINUS_UNAIRE,leftOperande,null,null,null,null);
	
				case TreeElement.TREE_ELEMENT_TYPE_OPERATOR_PLUS:
					leftOperande = optimizeIt(element.getLeftElement());
					rightOperande = optimizeIt(element.getRightElement());
					
					if (leftOperande.getElementType() == TreeElement.TREE_ELEMENT_TYPE_VALUE &&
						rightOperande.getElementType() == TreeElement.TREE_ELEMENT_TYPE_VALUE) {
						return new TreeElement(TreeElement.TREE_ELEMENT_TYPE_VALUE,null,null,leftOperande.getValue().sum(rightOperande.getValue()),null,null);
					}
					return new TreeElement(TreeElement.TREE_ELEMENT_TYPE_OPERATOR_PLUS,leftOperande,rightOperande,null,null,null);
	
				case TreeElement.TREE_ELEMENT_TYPE_VALUE:
				case TreeElement.TREE_ELEMENT_TYPE_VARIABLE:
				case TreeElement.TREE_ELEMENT_TYPE_CONSTANT:
				case TreeElement.TREE_ELEMENT_TYPE_FUNCTION:
					return element;
					
				case TreeElement.TREE_ELEMENT_TYPE_OPERATOR_PLUS_UNAIRE:
					leftOperande = optimizeIt(element.getLeftElement());
					rightOperande = optimizeIt(element.getRightElement());
					
					if (leftOperande.getElementType() == TreeElement.TREE_ELEMENT_TYPE_VALUE) {
						return new TreeElement(TreeElement.TREE_ELEMENT_TYPE_VALUE,null,null,leftOperande.getValue(),null,null);
					}
					return new TreeElement(TreeElement.TREE_ELEMENT_TYPE_OPERATOR_PLUS_UNAIRE,leftOperande,null,null,null,null);
	
					
				default:
					throw new EvalException("Unknown tree element type (" + element.getElementType() + ")");
			}
		}
		catch (MathematicalException ne) {
			throw new EvalException("Cannot evaluate expression.",ne);
		}
		*/
	}
	
}
