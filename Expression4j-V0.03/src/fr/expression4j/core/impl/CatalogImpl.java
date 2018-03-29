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

import fr.expression4j.basic.MathematicalElement;
import fr.expression4j.core.Catalog;
import fr.expression4j.core.Expression;
import fr.expression4j.core.predefine.AbsFunction;
import fr.expression4j.core.predefine.AcosFunction;
import fr.expression4j.core.predefine.AsinFunction;
import fr.expression4j.core.predefine.AtanFunction;
import fr.expression4j.core.predefine.CeilFunction;
import fr.expression4j.core.predefine.CosFunction;
import fr.expression4j.core.predefine.CoshFunction;
import fr.expression4j.core.predefine.ExpFunction;
import fr.expression4j.core.predefine.FloorFunction;
import fr.expression4j.core.predefine.LogFunction;
import fr.expression4j.core.predefine.RandomFunction;
import fr.expression4j.core.predefine.SinFunction;
import fr.expression4j.core.predefine.SinhFunction;
import fr.expression4j.core.predefine.SqrtFunction;
import fr.expression4j.core.predefine.TanFunction;
import fr.expression4j.factory.NumberFactory;

/**
 * Implementation of catalog interface.
 * 
 * <pre>
 * All catalog store the default functions derived from java Math class:
 * 	- abs(x)
 *  - acos(x)
 *  - asin(x)
 *  - atan(x)
 *  - ceil(x)
 *  - cos(x)
 *  - cosh(x)
 *  - exp(x)
 *  - floor(x)
 *  - log(x)
 *  - random()
 *  - sin(x)
 *  - sinh(x)
 *  - sqrt(x)
 *  - tan(x)  
 * 
 * and default constant
 *  - pi = 3.14159 ...
 *  - e  = 2,71828182845904523536028747135266249
 * 
 * </pre>
 * @author SGINER
 *
 */
public class CatalogImpl implements Catalog {

	protected List catalogList;
	protected Map catalog;
	protected Map constant;
	protected String name;
	
	public CatalogImpl(String catalogName) {
		catalog = new HashMap(20);
		constant = new HashMap(10);
		catalogList = new Vector(10);
		name = catalogName;
		
		//add default expression
		Expression expression = new AbsFunction();
		addExpression(expression);
		expression = new AcosFunction();
		addExpression(expression);
		expression = new AsinFunction();
		addExpression(expression);
		expression = new AtanFunction();
		addExpression(expression);
		expression = new CeilFunction();
		addExpression(expression);
		expression = new CosFunction();
		addExpression(expression);
		expression = new CoshFunction();
		addExpression(expression);
		expression = new ExpFunction();
		addExpression(expression);
		expression = new FloorFunction();
		addExpression(expression);
		expression = new LogFunction();
		addExpression(expression);
		expression = new RandomFunction();
		addExpression(expression);
		expression = new SinFunction();
		addExpression(expression);
		expression = new SinhFunction();
		addExpression(expression);
		expression = new SqrtFunction();
		addExpression(expression);
		expression = new TanFunction();
		addExpression(expression);
		
		//add default constant
		MathematicalElement me = NumberFactory.createReal(3.1415926535897932384626433832795);
		constant.put("pi",me);
		me = NumberFactory.createReal(2.71828182845904523536028747135266249);
		constant.put("e",me);
}
	
	/* (non-Javadoc)
	 * @see fr.expression4j.core.Catalog#addExpression(fr.expression4j.core.Expression)
	 */
	public void addExpression(Expression expression) {
		if (expression != null) {
			String expressionName = expression.getName(); 
			catalogList.add(expressionName);
			catalog.put(expressionName,expression);
		}
	}

	/* (non-Javadoc)
	 * @see fr.expression4j.core.Catalog#getExpression(java.lang.String)
	 */
	public Expression getExpression(String name) {
		return (Expression) catalog.get(name);
	}

	/* (non-Javadoc)
	 * @see fr.expression4j.core.Catalog#listCatalog()
	 */
	public List listExpression() {
		return catalogList;
	}

	public String toString() {
		StringBuffer result = new StringBuffer(50);
		Iterator iter = catalogList.iterator();
		
		while (iter.hasNext()) {
			Expression currentExpression = (Expression) iter.next();
			result.append(currentExpression.getName() + " : " + currentExpression + "\n");
		}
		
		
		
		return result.toString();
	}

	public void addConstant(String name, MathematicalElement value) {
		constant.put(name,value);
	}

	public MathematicalElement getConstant(String name) {
		return (MathematicalElement) constant.get(name);
	}

	public List listConstant() {
		Iterator iter = constant.keySet().iterator();
		List result = new Vector();
		
		while (iter.hasNext()) {
			result.add(iter.next());
		}
		return result;
	}
	
	public String getName() {
		return name;
	}
}
