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

package fr.expression4j.basic.operatorimpl.complex;

import fr.expression4j.basic.MathematicalElement;
import fr.expression4j.basic.MathematicalException;
import fr.expression4j.basic.Operator;
import fr.expression4j.basic.OperatorImpl;
import fr.expression4j.basic.impl.ComplexImpl;
import fr.expression4j.core.exception.EvalException;

public class OperatorDivideComplexComplex implements OperatorImpl {

	public boolean isUnaryOperator() {
		return false;
	}

	public int getLeftOperandeType() {
		return 2;
	}

	public int getRightOperandeType() {
		return 2;
	}

	public String getOperatorName() {
		return Operator.OPERATOR_DIVIDE;
	}

	public MathematicalElement compute(MathematicalElement leftElement,
			MathematicalElement rightElement) throws EvalException {
		
		try {
			double realValue;
			double complexValue;

			if (leftElement.getType() != 2 || rightElement.getType() != 2) {
				throw new EvalException("Incosistent element type for operator multiply complex complex.");
			}
	
			//(4+5i)/(3+2i) = ((4+5i)(3-3i))/((3+2i)(3-2i))
			double a = leftElement.getRealValue();
			double b = leftElement.getComplexValue();
			double c = rightElement.getRealValue();
			double d = rightElement.getComplexValue();
			if (d > 0) {
				realValue = (a*c+b*d)/(c*c+d*d);
				complexValue = -(b*c-a*d)/(c*c+d*d);
			}
			else {
				realValue = (a*c-b*d)/(c*c+d*d);
				complexValue = (a*d+b*c)/(c*c+d*d);
			}
			return new ComplexImpl(realValue,complexValue);
		}
		catch (MathematicalException me) {
			throw new EvalException("Cannot evaluate value for divide complex complex.",me);
		}
	}

}
