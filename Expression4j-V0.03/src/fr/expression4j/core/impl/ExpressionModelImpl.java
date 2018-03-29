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

package fr.expression4j.core.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import fr.expression4j.basic.ExpressionElement;
import fr.expression4j.basic.Operator;
import fr.expression4j.core.ExpressionModel;
import fr.expression4j.core.exception.ModelException;

public class ExpressionModelImpl implements ExpressionModel {

	private Map operatorMapName = new HashMap(10);
	private Map unariOperatorMap = new HashMap(5);
	private Map binaryOperatorMap = new HashMap(10);
	private Map binaryOperatorLevelMap = new HashMap(10);
	private Map expressionElementMap = new HashMap(10);
	private Map expressionElementMapName = new HashMap(10);
	
	private int maxOperatorLevel = 0;
	private int maxExpressionLevel = 0;
	
	private String name;
	
	/**
	 * 
	 */
	public ExpressionModelImpl(String name) {
		this.name = name;
	}

	/* (non-Javadoc)
	 * @see fr.expression4j.core.ExpressionModel#addUnaryOperator(fr.expression4j.basic.Operator)
	 */
	public void addUnaryOperator(Operator operator) throws ModelException {
		if (!operator.isUnary()) {
			throw new ModelException("Operator is not unary.");
		}
		
		unariOperatorMap.put(operator.getName(),operator);
		operatorMapName.put(operator.getName(),operator);
	}

	/* (non-Javadoc)
	 * @see fr.expression4j.core.ExpressionModel#addBinaryOperator(fr.expression4j.basic.Operator, int)
	 */
	public void addBinaryOperator(Operator operator, int priority)
			throws ModelException {
		
		if (operator == null) {
			return;
		}
		
		if (operator.isUnary()) {
			throw new ModelException("Operator is not binary.");
		}
		
		Integer priorityLevel = new Integer(priority);

		//check if priority level is not breaked
		//TODO
		
		// add operator
		List operatorList = (List) binaryOperatorMap.get(priorityLevel);
		if (operatorList == null) {
			//no operator with this priority
			//add it
			operatorList = new Vector(5);
		}
		operatorList.add(operator);
	
		if (priority > maxOperatorLevel) {
			maxOperatorLevel = priority;
		}
		
		binaryOperatorMap.put(priorityLevel,operatorList);
		operatorMapName.put(operator.getName(),operator);
		binaryOperatorLevelMap.put(operator.getName(),priorityLevel);
	}

	/* (non-Javadoc)
	 * @see fr.expression4j.core.ExpressionModel#addExpressionElement(fr.expression4j.basic.ExpressionElement, int)
	 */
	public void addExpressionElement(ExpressionElement expressionElement,int priority) throws ModelException {
		
		Integer priorityLevel = new Integer(priority);
		
		//check if priority level is not breaked
		//TODO

		//add current expression element
		ExpressionElement currentElement = (ExpressionElement) expressionElementMap.get(priorityLevel);
		if (currentElement != null) {
			expressionElementMap.put(priorityLevel,expressionElement);
			addExpressionElement(currentElement,priority + 1);
		}
		else {
			expressionElementMap.put(priorityLevel,expressionElement);
		}
		
		if (priority > maxExpressionLevel) {
			maxExpressionLevel = priority;
		}
		
		expressionElementMapName.put(expressionElement.getName(),expressionElement);
	}

	/* (non-Javadoc)
	 * @see fr.expression4j.core.ExpressionModel#getMaxOperatorPriority()
	 */
	public int getMaxOperatorPriority() {
		return maxOperatorLevel;
	}

	/* (non-Javadoc)
	 * @see fr.expression4j.core.ExpressionModel#getMaxExpressionElementPriority()
	 */
	public int getMaxExpressionElementPriority() {
		return maxExpressionLevel;
	}

	/* (non-Javadoc)
	 * @see fr.expression4j.core.ExpressionModel#getUnaryOperators()
	 */
	public List getUnaryOperators() {
		List result = new Vector();
		Iterator iter = unariOperatorMap.keySet().iterator();
		while (iter.hasNext()) {
			String operatorName = (String) iter.next();
			result.add(unariOperatorMap.get(operatorName));
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see fr.expression4j.core.ExpressionModel#getBinaryOperators(int)
	 */
	public List getBinaryOperators(int priority) {
		return (List) binaryOperatorMap.get(new Integer(priority));
	}

	/* (non-Javadoc)
	 * @see fr.expression4j.core.ExpressionModel#getExpressionElement(int)
	 */
	public ExpressionElement getExpressionElement(int priority) {
		return (ExpressionElement) expressionElementMap.get(new Integer(priority));
	}

	public ExpressionElement getExpressionElement(String name) {
		return (ExpressionElement) expressionElementMapName.get(name);
	}

	public Operator getOperator(String name) {
		return (Operator) operatorMapName.get(name);
	}

	public int getOperatorLevel(Operator operator) {
		Integer level = (Integer) binaryOperatorLevelMap.get(operator.getName());
		if (level == null) {
			return 0;
		}
		
		return level.intValue();
	}

	public String getName() {
		return name;
	}

}
