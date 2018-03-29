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

import fr.expression4j.basic.impl.ComplexOrRealExpressionElement;
import fr.expression4j.basic.impl.ConstantOrVariableExpressionElement;
import fr.expression4j.basic.impl.FunctionExpressionElement;
import fr.expression4j.basic.impl.ParenthesisExpressionElement;
import fr.expression4j.core.ExpressionModel;
import fr.expression4j.core.exception.ModelException;
import fr.expression4j.core.impl.ExpressionModelImpl;

/**
 * Manage expression model.
 * Expression model is meta model associated to an expression.
 * 
 * @author SGINER
 */
public class ExpressionModelFactory {

	private static Map expressionModelMap;
	private static ExpressionModel defaultExpressionModel;
	
	static {
		expressionModelMap = new HashMap(10);

		defaultExpressionModel = new ExpressionModelImpl("default");
		
		try {
			defaultExpressionModel.addUnaryOperator(OperatorFactory.getOperator("UnaryMinus"));
			defaultExpressionModel.addUnaryOperator(OperatorFactory.getOperator("UnaryPlus"));
			
			defaultExpressionModel.addBinaryOperator(OperatorFactory.getOperator("Plus"),1);
			defaultExpressionModel.addBinaryOperator(OperatorFactory.getOperator("Minus"),1);
	
			defaultExpressionModel.addBinaryOperator(OperatorFactory.getOperator("Multiply"),2);
			defaultExpressionModel.addBinaryOperator(OperatorFactory.getOperator("Divide"),2);
			
			defaultExpressionModel.addBinaryOperator(OperatorFactory.getOperator("Pow"),3);
	
			defaultExpressionModel.addExpressionElement(new ComplexOrRealExpressionElement(),1);
			defaultExpressionModel.addExpressionElement(new FunctionExpressionElement(),2);
			defaultExpressionModel.addExpressionElement(new ConstantOrVariableExpressionElement(),3);
			defaultExpressionModel.addExpressionElement(new ParenthesisExpressionElement(),4);
		}
		catch (ModelException me) {
			//TODO
		}
		
		expressionModelMap.put("default",defaultExpressionModel);
	}
	
	/**
	 * Get the default expression model 
	 * @return default expression model
	 * @throws ModelException if error occurd.
	 */
	public static ExpressionModel getDefaultExpressionModel() throws ModelException {
		return defaultExpressionModel;
	}

	/**
	 * Create an empty expression model.
	 * @return empty expression model.
	 */
	public static final ExpressionModel createExpressionModel(String name) {
		ExpressionModel result = (ExpressionModel) expressionModelMap.get(name);
		if (result == null) {
			result = new ExpressionModelImpl(name);
			expressionModelMap.put(name,result);
		}
		return result;
	}

	/**
	 * Get an expression model.
	 * @return empty expression model.
	 */
	public static final ExpressionModel getExpressionModel(String name) {
		return (ExpressionModel) expressionModelMap.get(name);
	}
	
	/**
	 * get all expression model define.
	 * @return list of all expression model name as sting.
	 */
	public static final List getAllExpressionModel() {
		List result = new Vector(5);
		Iterator iter = expressionModelMap.keySet().iterator();
		while (iter.hasNext()) {
			result.add(iter.next());
		}
		return result;
	}
}
