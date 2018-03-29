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
import fr.expression4j.factory.NumberFactory;
import fr.expression4j.util.ExpressionElementUtil;

/**
 * Parse an integer.
 * BNF of integer is
 * 
 * integer ::= number integer | number
 * number  ::= 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9
 * 
 * @author SGINER
 *
 */
public class IntegerExpressionElement implements ExpressionElement {

	public IntegerExpressionElement() {
		super();
	}

	/* (non-Javadoc)
	 * @see fr.expression4j.basic.ExpressionElement#parseElement(java.lang.String, fr.expression4j.basic.ParseInfo)
	 */
	public boolean parseElement(String expression, ExpressionModel expressionModel,
			ParseInfo parseInfo, Catalog catalog, List functionParameters, int priorityOperatorLevel) throws ParsingException {
		int startPos = parseInfo.getEndPos();
		int currentPos = parseInfo.getEndPos();
		
		while (ExpressionElementUtil.checkPosition(expression,currentPos)  && "0123456789".indexOf(expression.charAt(currentPos)) != -1) {
			currentPos ++;
		}
		
		if (currentPos == startPos) {
			return false;
		}
		
		String valueString = expression.substring(startPos,currentPos);
		MathematicalElement value = NumberFactory.createReal(Double.parseDouble(valueString));
		TreeElement result = new TreeElement(TreeElement.TREE_ELEMENT_TYPE_VALUE,null,null,value,getName(),null,null);
		parseInfo.setTreeElement(result);
		parseInfo.setEndPos(currentPos);
		return true;
	}

	public String getName() {
		return "IntegerExpressionElement";
	}

	public MathematicalElement evaluate(TreeElement element, Catalog catalog, OperatorManager operatorManager, Parameters parameters, ExpressionModel model) throws EvalException {
		throw new EvalException("Cannot evaluate " + getName());
	}

	public String toString(TreeElement element, ExpressionModel expressionModel) {
		return "<" + getName() + ">";
	}

}
