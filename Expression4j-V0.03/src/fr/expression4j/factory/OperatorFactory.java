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

package fr.expression4j.factory;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import fr.expression4j.basic.Operator;
import fr.expression4j.basic.impl.GenericOperator;

/**
 * manage operators define by the framework.
 * @author SGINER
 *
 */

public class OperatorFactory {

	private static Map operatorMap = new HashMap(10);
	
	static {
		createOperator(Operator.OPERATOR_UNARY_MINUS,"-",true);
		createOperator(Operator.OPERATOR_UNARY_PLUS,"+",true);
		createOperator(Operator.OPERATOR_PLUS,"+",false);
		createOperator(Operator.OPERATOR_MINUS,"-",false);
		createOperator(Operator.OPERATOR_MULTIPLY,"*",false);
		createOperator(Operator.OPERATOR_DIVIDE,"/",false);
		createOperator(Operator.OPERATOR_POW,"^",false);
	}
	
	/**
	 * Create an operator.
	 * @param name operator name
	 * @param symbol symbol associated to the operator
	 * @param isUnary tel if operator is an unary operator or not.
	 * @return the created operator.
	 */
	public static Operator createOperator(String name, String symbol, boolean isUnary) {
		Operator result = (Operator) operatorMap.get(name);
		if (result == null) {
			result = new GenericOperator(name,symbol,isUnary);
			operatorMap.put(name,result);
		}
		return result;
	}
	
	public static void addOperator(Operator operator) {
		operatorMap.put(operator.getName(),operator);
	}
	
	/**
	 * return operator list as String
	 * @return the operator list.
	 */
	public static List getOperatorList() {
		List result = new Vector(10);
		Iterator iter = operatorMap.keySet().iterator();
		
		while (iter.hasNext()) {
			result.add(iter.next());
		}
		
		return result;
	}
	
	/**
	 * get the given operator name
	 * @param name name of the operator
	 * @return the requested operator if found, <code>null</code> otherwise.
	 */
	public static Operator getOperator(String name) {
		return (Operator) operatorMap.get(name);
	}
}
