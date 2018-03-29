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

package fr.expression4j.basic.operatorimpl.real;

import fr.expression4j.basic.MathematicalElement;
import fr.expression4j.basic.Operator;
import fr.expression4j.basic.OperatorImpl;
import fr.expression4j.basic.impl.RealImpl;
import fr.expression4j.core.exception.EvalException;

public class OperatorPlusRealReal implements OperatorImpl {

	public boolean isUnaryOperator() {
		return false;
	}

	public int getLeftOperandeType() {
		return 1;
	}

	public int getRightOperandeType() {
		return 1;
	}

	public String getOperatorName() {
		return Operator.OPERATOR_PLUS;
	}

	public MathematicalElement compute(MathematicalElement leftElement,
			MathematicalElement rightElement) throws EvalException {
		
		if (leftElement.getType() != 1 || rightElement.getType() != 1) {
			throw new EvalException("Incosistent element type for operator plus real real.");
		}
		
		return new RealImpl(leftElement.getRealValue() + rightElement.getRealValue());
	}

}
