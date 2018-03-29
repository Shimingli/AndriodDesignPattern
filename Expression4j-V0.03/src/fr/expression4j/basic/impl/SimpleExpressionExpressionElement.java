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

public class SimpleExpressionExpressionElement implements ExpressionElement {

	public SimpleExpressionExpressionElement() {
		super();
	}

	public boolean parseElement(String expression, ExpressionModel expressionModel,
			ParseInfo parseInfo, Catalog catalog, List functionParameters, int priorityOperatorLevel)
			throws ParsingException {
		int endPos = parseInfo.getEndPos();
		ParseInfo tmpParseInfo = new ParseInfo();
		
		
		int nbExpressionElement = expressionModel.getMaxExpressionElementPriority();
		for (int i=1; i<=nbExpressionElement; i++) {
			ExpressionElement currentExpressionElement = expressionModel.getExpressionElement(i);
			tmpParseInfo.setEndPos(endPos);
			if (currentExpressionElement.parseElement(expression,expressionModel,tmpParseInfo,catalog,functionParameters,0)) {
				parseInfo.setEndPos(tmpParseInfo.getEndPos());
				parseInfo.setTreeElement(tmpParseInfo.getTreeElement());
				return true;
			}
		}
		
		return false;
	}

	public String getName() {
		return "SimpleExpressionExpressionElement";
	}

	public MathematicalElement evaluate(TreeElement element, Catalog catalog, OperatorManager operatorManager, Parameters parameters, ExpressionModel model) throws EvalException {
		throw new EvalException("Cannot evaluate " + getName());
	}

	public String toString(TreeElement element, ExpressionModel expressionModel) {
		return "<" + getName() + ">";
	}

}
