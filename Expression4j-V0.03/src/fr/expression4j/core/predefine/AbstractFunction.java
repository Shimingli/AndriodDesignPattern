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

package fr.expression4j.core.predefine;

import java.util.List;
import java.util.Vector;

import fr.expression4j.basic.ExpressionElement;
import fr.expression4j.basic.Operator;
import fr.expression4j.core.Expression;
import fr.expression4j.core.ExpressionModel;
import fr.expression4j.core.exception.ModelException;


public abstract class AbstractFunction implements Expression {

	private class LocalExpressionModel implements ExpressionModel {

		public void addBinaryOperator(Operator operator, int priority) throws ModelException {
		}

		public void addExpressionElement(ExpressionElement expressionElement, int priority) throws ModelException {
		}

		public void addUnaryOperator(Operator operator) throws ModelException {
		}

		public List getBinaryOperators(int priority) {
			return new Vector();
		}

		public ExpressionElement getExpressionElement(int priority) {
			return null;
		}

		public ExpressionElement getExpressionElement(String name) {
			return null;
		}

		public int getMaxExpressionElementPriority() {
			return 0;
		}

		public int getMaxOperatorPriority() {
			return 0;
		}

		public String getName() {
			return "Predefine";
		}

		public Operator getOperator(String name) {
			return null;
		}

		public int getOperatorLevel(Operator operator) {
			return 0;
		}

		public List getUnaryOperators() {
			return new Vector();
		}
		
	}

	public String toString() {
		return "Predefine";
	}

	public ExpressionModel getExpressionModel() {
		return new AbstractFunction.LocalExpressionModel();
	}
}
