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

import fr.expression4j.basic.OperatorManager;
import fr.expression4j.basic.impl.OperatorManagerImpl;
import fr.expression4j.basic.operatorimpl.complex.OperatorDivideComplexComplex;
import fr.expression4j.basic.operatorimpl.complex.OperatorMinusComplexComplex;
import fr.expression4j.basic.operatorimpl.complex.OperatorMultiplyComplexComplex;
import fr.expression4j.basic.operatorimpl.complex.OperatorPlusComplexComplex;
import fr.expression4j.basic.operatorimpl.complex.OperatorUnaryMinusComplex;
import fr.expression4j.basic.operatorimpl.complex.OperatorUnaryPlusComplex;
import fr.expression4j.basic.operatorimpl.real.OperatorDivideRealReal;
import fr.expression4j.basic.operatorimpl.real.OperatorMinusRealReal;
import fr.expression4j.basic.operatorimpl.real.OperatorMultiplyRealReal;
import fr.expression4j.basic.operatorimpl.real.OperatorPlusRealReal;
import fr.expression4j.basic.operatorimpl.real.OperatorPowRealReal;
import fr.expression4j.basic.operatorimpl.real.OperatorUnaryMinusReal;
import fr.expression4j.basic.operatorimpl.real.OperatorUnaryPlusReal;
import fr.expression4j.basic.operatorimpl.realcomplex.OperatorDivideRealComplex;
import fr.expression4j.basic.operatorimpl.realcomplex.OperatorMinusRealComplex;
import fr.expression4j.basic.operatorimpl.realcomplex.OperatorMultiplyRealComplex;
import fr.expression4j.basic.operatorimpl.realcomplex.OperatorPlusRealComplex;

public class OperatorManagerFactory {

	private static Map operatorManagerMap;
	private static OperatorManager defaultOperatorManager;

	static {
		operatorManagerMap = new HashMap(10);

		defaultOperatorManager = new OperatorManagerImpl("default");
		
		defaultOperatorManager.addOperatorImpl(new OperatorDivideRealReal());
		defaultOperatorManager.addOperatorImpl(new OperatorMinusRealReal());
		defaultOperatorManager.addOperatorImpl(new OperatorMultiplyRealReal());
		defaultOperatorManager.addOperatorImpl(new OperatorPlusRealReal());
		defaultOperatorManager.addOperatorImpl(new OperatorUnaryPlusReal());
		defaultOperatorManager.addOperatorImpl(new OperatorUnaryMinusReal());
		defaultOperatorManager.addOperatorImpl(new OperatorPowRealReal());
		
		defaultOperatorManager.addOperatorImpl(new OperatorDivideComplexComplex());
		defaultOperatorManager.addOperatorImpl(new OperatorMinusComplexComplex());
		defaultOperatorManager.addOperatorImpl(new OperatorMultiplyComplexComplex());
		defaultOperatorManager.addOperatorImpl(new OperatorPlusComplexComplex());
		defaultOperatorManager.addOperatorImpl(new OperatorUnaryPlusComplex());
		defaultOperatorManager.addOperatorImpl(new OperatorUnaryMinusComplex());

		defaultOperatorManager.addOperatorImpl(new OperatorDivideRealComplex());
		defaultOperatorManager.addOperatorImpl(new OperatorMinusRealComplex());
		defaultOperatorManager.addOperatorImpl(new OperatorMultiplyRealComplex());
		defaultOperatorManager.addOperatorImpl(new OperatorPlusRealComplex());
		
		operatorManagerMap.put("default",defaultOperatorManager);
	}
	
	public static final OperatorManager getDefaultOperatorManager() {
		return defaultOperatorManager;
	}

	/**
	 * Create an operator manager
	 * @param name name of the operator.
	 * @return specified operator.
	 */
	public static final OperatorManager createOperatorManager(String name) {
		OperatorManager result = (OperatorManager) operatorManagerMap.get(name);
		if (result == null) {
			result = new OperatorManagerImpl(name);
			operatorManagerMap.put(name,result);
		}
		return result;
	}
	
	public static final OperatorManager getOperatorManager(String name) {
		return (OperatorManager) operatorManagerMap.get(name);
	}
	
	public static List getOperatorManagerList() {
		List result = new Vector(10);
		Iterator iter = operatorManagerMap.keySet().iterator();
		while (iter.hasNext()) {
			result.add(iter.next());
		}
		return result;
	}

}
