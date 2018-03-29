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
package fr.expression4j.sample.custom.operator;

import fr.expression4j.basic.MathematicalElement;
import fr.expression4j.basic.OperatorImpl;
import fr.expression4j.core.exception.EvalException;

/**
 * implement superior operation between a real and a real.
 * @author SGINER
 *
 */

public class OperatorSupRealReal implements OperatorImpl {

	public OperatorSupRealReal() {
		super();
	}

	public MathematicalElement compute(MathematicalElement leftElement, MathematicalElement rightElement) throws EvalException {
		if (leftElement.getType() != 1) {
			throw new EvalException("Left element is not a RealMathematicalElement");
		}
		
		if (rightElement.getType() != 1) {
			throw new EvalException("Right element is not a RealMathematicalElement");
		}

		BooleanMathematicalElement result = new BooleanMathematicalElement(leftElement.getRealValue() > rightElement.getRealValue());
		return result;
	}

	public int getLeftOperandeType() {
		return 1;
	}

	public String getOperatorName() {
		//this name must be the same as the name given in the operator.
		return "sup";
	}

	public int getRightOperandeType() {
		return 1;
	}

	public boolean isUnaryOperator() {
		return false;
	}

}
