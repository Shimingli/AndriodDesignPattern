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

import fr.expression4j.basic.MathematicalElement;
import fr.expression4j.basic.OperatorImpl;
import fr.expression4j.core.exception.EvalException;

/**
 * implement plus operation between a string and a string.
 * @author SGINER
 *
 */

public class OperatorPlusStringString implements OperatorImpl {

	public OperatorPlusStringString() {
		super();
	}

	public MathematicalElement compute(MathematicalElement leftElement, MathematicalElement rightElement) throws EvalException {
		if (leftElement.getType() != 5) {
			throw new EvalException("Left element is not a StringMathematicalElement");
		}
		
		if (rightElement.getType() != 5) {
			throw new EvalException("Right element is not a StringMathematicalElement");
		}

		StringBuffer result = new StringBuffer((String) leftElement.getValue());
		result.append((String) rightElement.getValue());
		return new StringMathematicalElement(result.toString());
	}

	public int getLeftOperandeType() {
		return 5;
	}

	public String getOperatorName() {
		return "Plus";
	}

	public int getRightOperandeType() {
		return 5;
	}

	public boolean isUnaryOperator() {
		return false;
	}

}
