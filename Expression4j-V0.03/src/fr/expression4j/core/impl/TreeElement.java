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


import fr.expression4j.basic.MathematicalElement;

/**
 * element of the final expression tree.
 * @author SGINER
 *
 */
public class TreeElement {
	public static final int TREE_ELEMENT_TYPE_OPERATOR_NOT_DEFINE = 0;
	public static final int TREE_ELEMENT_TYPE_VALUE               = 1;
	public static final int TREE_ELEMENT_TYPE_BINARY_OPERATOR     = 2;
	public static final int TREE_ELEMENT_TYPE_UNARY_OPERATOR      = 3;
	public static final int TREE_ELEMENT_TYPE_EXPRESSION_ELEMENT  = 4;

	int elementType;
	TreeElement leftElement;
	TreeElement rightElement;
	MathematicalElement value;
	String expressionElementName;
	String stringElement;
	Object expressionElementProperties;
	
	public TreeElement(int type, TreeElement leftElement, TreeElement rightElement,MathematicalElement value, String expressionElementName, String stringElement, Object expressionElementProperties) {
		this.elementType = type;
		this.leftElement = leftElement;
		this.rightElement = rightElement;
		this.value = value;
		this.stringElement = stringElement;
		this.expressionElementProperties = expressionElementProperties;
		this.expressionElementName = expressionElementName;
	}
	
	public int getElementType() {
		return elementType;
	}
	public TreeElement getLeftElement() {
		return leftElement;
	}
	public TreeElement getRightElement() {
		return rightElement;
	}
	public MathematicalElement getValue() {
		return value;
	}
	public String getStringElement() {
		return stringElement;
	}

	public void setElementType(int elementType) {
		this.elementType = elementType;
	}

	public void setLeftElement(TreeElement leftElement) {
		this.leftElement = leftElement;
	}

	public void setRightElement(TreeElement rightElement) {
		this.rightElement = rightElement;
	}

	public void setValue(MathematicalElement value) {
		this.value = value;
	}

	public void setStringElement(String variable) {
		this.stringElement = variable;
	}

	public Object getExpressionElementProperties() {
		return expressionElementProperties;
	}

	public void setExpressionElementProperties(Object properties) {
		this.expressionElementProperties = properties;
	}

	public String getExpressionElementName() {
		return expressionElementName;
	}
}