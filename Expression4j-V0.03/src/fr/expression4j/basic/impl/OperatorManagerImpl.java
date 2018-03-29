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

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import fr.expression4j.basic.MathematicalElement;
import fr.expression4j.basic.OperatorImpl;
import fr.expression4j.basic.OperatorManager;
import fr.expression4j.core.exception.EvalException;

public class OperatorManagerImpl implements OperatorManager {

	private String name;
	private Map operatorMap = new HashMap(20);
	
	public OperatorManagerImpl(String name) {
		this.name = name;
	}

	private String computeKeyMap(String opName,int left, int right) {
		return opName + ":" + left + ":" + right;
	}
	
	public void addOperatorImpl(OperatorImpl operatorImpl) {
		if (operatorImpl == null) {
			return;
		}
		
		if (operatorImpl.isUnaryOperator()) {
			operatorMap.put(computeKeyMap(operatorImpl.getOperatorName(),operatorImpl.getLeftOperandeType(),0),operatorImpl);
		}
		else {
			operatorMap.put(computeKeyMap(operatorImpl.getOperatorName(),operatorImpl.getLeftOperandeType(),operatorImpl.getRightOperandeType()),operatorImpl);
		}
	}

	public void removeOperatorImpl(OperatorImpl operatorImpl) {
		if (operatorImpl == null) {
			return;
		}
		
		if (operatorImpl.isUnaryOperator()) {
			operatorMap.remove(computeKeyMap(operatorImpl.getOperatorName(),operatorImpl.getLeftOperandeType(),0));
		}
		else {
			operatorMap.remove(computeKeyMap(operatorImpl.getOperatorName(),operatorImpl.getLeftOperandeType(),operatorImpl.getRightOperandeType()));
		}
	}

	public MathematicalElement computeValue(String operatorName,
			MathematicalElement element) throws EvalException {

		OperatorImpl currentOperatorImpl = (OperatorImpl) operatorMap.get(computeKeyMap(operatorName,element.getType(),0));
		return currentOperatorImpl.compute(element,null);
	}

	public MathematicalElement computeValue(String operatorName,
			MathematicalElement leftElement, MathematicalElement rightElement)
			throws EvalException {

		OperatorImpl currentOperatorImpl = (OperatorImpl) operatorMap.get(computeKeyMap(operatorName,leftElement.getType(),rightElement.getType()));
		if (currentOperatorImpl == null) {
			currentOperatorImpl = (OperatorImpl) operatorMap.get(computeKeyMap(operatorName,rightElement.getType(),leftElement.getType()));
		}
		if (currentOperatorImpl == null) {
			throw new EvalException("Could not find operator implementation for " + computeKeyMap(operatorName,leftElement.getType(),rightElement.getType()));
		}
		return currentOperatorImpl.compute(leftElement,rightElement);
	}

	public String getName() {
		return name;
	}

	public OperatorImpl getOperatorImpl(String name) {
		return (OperatorImpl) operatorMap.get(name);
	}

	public List getOperatorImplList() {
		List result = new Vector(10);
		Iterator iter = operatorMap.keySet().iterator();

		while (iter.hasNext()) {
			result.add(iter.next());
		}

		return result;
	}
	
}
