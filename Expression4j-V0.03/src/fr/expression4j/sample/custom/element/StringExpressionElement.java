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

package fr.expression4j.sample.custom.element;

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
 * Parse a string.
 * BNF of string is
 * 
 * string ::= 'chars'
 * chars  ::= char chars | char
 * char   ::= [a-zA-Z]
 * 
 * @author SGINER
 *
 */
public class StringExpressionElement implements ExpressionElement {

	public StringExpressionElement() {
		super();
	}

	/* (non-Javadoc)
	 * @see fr.expression4j.basic.ExpressionElement#parseElement(java.lang.String, fr.expression4j.basic.ParseInfo)
	 */
	public boolean parseElement(String expression, ExpressionModel expressionModel,
			ParseInfo parseInfo, Catalog catalog, List functionParameters, int priorityOperatorLevel) throws ParsingException {
		int startPos = parseInfo.getEndPos();
		int currentPos = parseInfo.getEndPos();
		
		//check if current position start with a '
		if (ExpressionElementUtil.checkPosition(expression,currentPos) &&
			expression.charAt(currentPos) == '\'') {
			currentPos ++;
			
			//lokk for the end '
			while (ExpressionElementUtil.checkPosition(expression,currentPos)  && 
					expression.charAt(currentPos) != '\'') {
				currentPos ++;
			}
			
			if (expression.charAt(currentPos) != '\'') {
				//not found the end of the string.
				return false;
			}

			//found the ending '
			//create the corresponding TreeElement of the StringExpressionElement
			String valueString = expression.substring(startPos+1,currentPos);
			MathematicalElement value = new StringMathematicalElement(valueString); 
			TreeElement result = new TreeElement(TreeElement.TREE_ELEMENT_TYPE_VALUE,null,null,value,getName(),null,null);
			parseInfo.setTreeElement(result);
			parseInfo.setEndPos(currentPos+1);
			return true;
		}
		
		return false;
		
	}

	public String getName() {
		return "StringExpressionElement";
	}

	public MathematicalElement evaluate(TreeElement element, Catalog catalog, OperatorManager operatorManager, Parameters parameters, ExpressionModel model) throws EvalException {
		//throw an exception. 
		//evaluate method is reserved for function or specific custom expression element
		//like "if" operator.
		throw new EvalException("Cannot evaluate " + getName());
	}

	public String toString(TreeElement element, ExpressionModel expressionModel) {
		return "<" + getName() + ">";
	}

}
