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

package fr.expression4j.core;

import java.util.List;

import fr.expression4j.basic.MathematicalElement;

/**
 * Catalog of expressions.
 *
 * Manage a set of expressions. Used to parse or evaluate expression.
 * Eache time the parser found a function in an expression, it check
 * if it is define in the catalog given for the parsing.
 * @author SGINER
 */
public interface Catalog {
	/**
	 * add an expression to catalog.
	 * @param expression expression to add.
	 */
	public void addExpression(Expression expression);
	
	/**
	 * Get an expression from the catalog.
	 * @param name name of the expression to get
	 * @return the expression if found, null otherwise.
	 */
	public Expression getExpression(String name);
	
	/**
	 * List the content of the catalog
	 * @return list of functions name.
	 */
	public List listExpression();
	
	/**
	 * Add a constant to the catalog.
	 * @param name name of the constant.
	 * @param value value of the constant.
	 */
	public void addConstant(String name, MathematicalElement value);
	
	/**
	 * Get a constant from the catalog
	 * @param name constant name to get.
	 * @return the constant if found, <code>null</code> if not found.
	 */
	public MathematicalElement getConstant(String name);
	
	/**
	 * List all constant stored in catalog.
	 * @return list of all constant stored in catalog a String.
	 */
	public List listConstant();

	/**
	 * Get the catalog name.
	 * @return catalog name.
	 */
	public String getName();

}
