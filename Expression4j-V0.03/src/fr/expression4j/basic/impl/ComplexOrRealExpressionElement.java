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

import org.apache.log4j.Logger;

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
 * Parse a complex or a real element (complex is mind like mathematical complex element like 5i).
 * @author SGINER
 *
 */
public class ComplexOrRealExpressionElement implements ExpressionElement {

	static Logger log = Logger.getLogger(ComplexOrRealExpressionElement.class);
	
	public ComplexOrRealExpressionElement() {
		super();
	}

	/* (non-Javadoc)
	 * @see fr.expression4j.basic.ExpressionElement#parseElement(java.lang.String, fr.expression4j.basic.ParseInfo)
	 */
	public boolean parseElement(String expression, ExpressionModel expressionModel,
			ParseInfo parseInfo, Catalog catalog, List functionParameters, int priorityOperatorLevel) throws ParsingException {
		
		log.debug("In parseElement");
		int startPos = parseInfo.getEndPos();
		int currentPos = parseInfo.getEndPos();

		log.debug("start position: " + startPos);
		
		ParseInfo tmpParseInfo = new ParseInfo();
		tmpParseInfo.setEndPos(currentPos);
		
		ExpressionElement realExpressionElement = new RealExpressionElement();
		
		if (realExpressionElement.parseElement(expression,expressionModel,tmpParseInfo,catalog, functionParameters, 0)) {
			int storedEndPos = tmpParseInfo.getEndPos();
			
			if (ExpressionElementUtil.checkPosition(expression,storedEndPos) &&
				expression.charAt(storedEndPos) == 'i') {
				
				MathematicalElement complexValue = NumberFactory.createComplex(0,tmpParseInfo.getTreeElement().getValue().getRealValue());
				parseInfo.setEndPos(storedEndPos+1);
				TreeElement tmpTreeElement = new TreeElement(TreeElement.TREE_ELEMENT_TYPE_VALUE,null,null,complexValue,getName(),null,null);
				parseInfo.setTreeElement(tmpTreeElement);
				return true;				
			}
			parseInfo.setEndPos(storedEndPos);
			parseInfo.setTreeElement(tmpParseInfo.getTreeElement());
			return true;				
			
		}
		
		//real or complex not found
		return false;
	}

	public String getName() {
		return "ComplexOrRealExpressionElement";
	}

	public MathematicalElement evaluate(TreeElement element, Catalog catalog, OperatorManager operatorManager, Parameters parameters, ExpressionModel model) throws EvalException {
		throw new EvalException("Could not evaluate a value.");
	}

	public String toString(TreeElement element, ExpressionModel expressionModel) {
		return "<" + getName() + ">";
	}

}
