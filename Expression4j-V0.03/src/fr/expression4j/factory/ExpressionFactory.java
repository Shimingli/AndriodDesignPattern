//   Copyright 2006 Stephane GINER
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

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import fr.expression4j.core.Catalog;
import fr.expression4j.core.Expression;
import fr.expression4j.core.ExpressionModel;
import fr.expression4j.core.Parameters;
import fr.expression4j.core.exception.EvalException;
import fr.expression4j.core.exception.ModelException;
import fr.expression4j.core.exception.ParsingException;
import fr.expression4j.core.impl.CatalogImpl;
import fr.expression4j.core.impl.ExpressionImpl;
import fr.expression4j.core.impl.OptimizedExpression;
import fr.expression4j.core.impl.ParametersImpl;

/**
 * <pre>
 * Factory used to create 
 * 		- Expression
 * 		- Catalog
 * 		- Parameters
 * 
 * and to get the default Catalog.
 * 
 * Default catalog is catalog who store all functions created by factory.
 * 
 * </pre>
 * 
 * @author SGINER
 *
 */
public final class ExpressionFactory {

	static Logger log = Logger.getLogger(ExpressionFactory.class);
	
	private static Map catalogMap;
	private static Catalog generalCatalog;
	
	static {
		//initialise log4j for the moment
		//TODO remove in the future to initialize correctly the logger
		BasicConfigurator.configure();
		
		
		log.debug("Initialize factory");
		catalogMap = new HashMap(10);
		generalCatalog = new CatalogImpl("general");
		catalogMap.put("general",generalCatalog);
		log.debug("Factory initialised");
	}
	
	/**
	 * Create an expression.
	 * Expression must be conform to the BNF define in ExpressionImpl like
	 * 	- f(x)=2*x
	 * 	- g()=2*50.4e-2
	 * 
	 * @param expression String of the expression.
	 * @return the expression.
	 * @throws ParsingException when expression is not valid.
	 */
	public static Expression createExpression(String expression) throws ParsingException {
		try {
			ExpressionModel model = ExpressionModelFactory.getDefaultExpressionModel();
			
			return createExpression(expression,generalCatalog,model);
		}
		catch (ModelException me) {
			throw new ParsingException("Cannot create default ExpressionModel.",me);
		}
	}

	/**
	 * Create an expression with the given catalog.
	 * Expression must be conform to the BNF define in ExpressionImpl like
	 * 	- f(x)=2*x
	 * 	- g()=2*50.4e-2
	 * 
	 * @param expression String of the expression.
	 * @param catalog catalog to use to create function.
	 * @return the expression.
	 * @throws ParsingException when expression is not valid.
	 */
	public static Expression createExpression(String expression,Catalog catalog) throws ParsingException {
		try {
			ExpressionModel model = ExpressionModelFactory.getDefaultExpressionModel();
			
			return createExpression(expression,catalog,model);
		}
		catch (ModelException me) {
			throw new ParsingException("Cannot create default ExpressionModel.",me);
		}
	}

	/**
	 * Create an expression with the given catalog and the given expression model.
	 * Expression must be conform to the BNF define in ExpressionImpl like
	 * 	- f(x)=2*x
	 * 	- g()=2*50.4e-2
	 * 
	 * @param expression String of the expression.
	 * @param catalog catalog to use to create function.
	 * @param model model tu use to parse expression.
	 * @return the expression.
	 * @throws ParsingException when expression is not valid.
	 */
	public static Expression createExpression(String expression,Catalog catalog, ExpressionModel model) throws ParsingException {
		log.debug("Create expression.");
		ExpressionImpl result = new ExpressionImpl();
		
		log.debug("Expression to create: " + expression);
		log.debug("Catalog: " + catalog.getName());
		log.debug("Model: " + model.getName());
		
		result.parse(expression,catalog,model);
		catalog.addExpression(result);
		
		log.debug("Expression created");
		
		return result;
	}
	
	/**
	 * Create an empty catalog.
	 * @param catalogName name of the catalog. This name is unique. Crate a new catalog with the same name return the same catalog.
	 * @return an empty catalog (with no expression).
	 */
	public static Catalog createCatalog(String catalogName) {
		log.debug("Create catalog: " + catalogName);
		
		Catalog tmpCatalog = (Catalog) catalogMap.get(catalogName);
		if (tmpCatalog == null) {
			tmpCatalog = new CatalogImpl(catalogName);
			catalogMap.put(catalogName,tmpCatalog);
		}
		else {
			log.warn("Catalog " + catalogName + " already exist.");
		}
		return tmpCatalog;
	}
	
	/**
	 * Create an empty parameters
	 * @return empty parameters
	 */
	public static Parameters createParameters() {
		return new ParametersImpl();
	}
	
	/**
	 * get the default catalog with all expression created by the factory.
	 * @return default catalog.
	 */
	public static Catalog getCatalog() {
		return generalCatalog;
	}
	
	/**
	 * get the specified catalog.
	 * @param catalogName requested catalog name.
	 * @return the catalog if found, <code>null</code> if not.
	 */
	public static Catalog getCatalog(String catalogName) {
		return (Catalog) catalogMap.get(catalogName);
	}
	
	/**
	 * get catalog list.
	 * @return catalog as list of String name.
	 */
	public static List getCatalogList() {
		List result = new Vector(5);
		Iterator iter = catalogMap.keySet().iterator();
		while (iter.hasNext()) {
			result.add(iter.next());
		}
		
		return result;
	}
	
	/**
	 * Optimize expression
	 * 
	 * Sample:
	 * f(x)=2+5-4*x+3*2 => f2(x)=13-4*x
	 * 
	 * @param expression expression to optimize
	 * @return the optimized expression if possible or the given expression otherwise.
	 */
	public static Expression optimizeExpression(Expression expression) {
		if (expression instanceof ExpressionImpl) {
			try {
				return new OptimizedExpression((ExpressionImpl) expression);
			}
			catch (EvalException ee) {
				return expression;
			}
		}
		return expression;
	}
}
