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



import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import fr.expression4j.basic.ExpressionElement;
import fr.expression4j.basic.MathematicalElement;
import fr.expression4j.basic.OperatorManager;
import fr.expression4j.basic.ParseInfo;
import fr.expression4j.basic.impl.ExpressionExpressionElement;
import fr.expression4j.basic.impl.FunctionDefExpressionElement;
import fr.expression4j.core.Catalog;
import fr.expression4j.core.Expression;
import fr.expression4j.core.ExpressionModel;
import fr.expression4j.core.Parameters;
import fr.expression4j.core.exception.EvalException;
import fr.expression4j.core.exception.ParsingException;
import fr.expression4j.factory.ExpressionFactory;
import fr.expression4j.factory.OperatorManagerFactory;
import fr.expression4j.util.ExpressionElementUtil;
import fr.expression4j.util.ExpressionUtil;


/**
 * <pre>
 * Construct expression relative to the BNF:
 * 
 * expression        ::= term additive-op expression | term
 * additive-op       ::= "+" | "-"
 * term              ::= factor multiplicatice-op term | factor
 * multiplicative-op ::= "*" | "-"
 * factor            ::= (signed-expression "^" factor) | signed-expression
 * signed-expression ::= additive-op simple-expression | simple-expression
 * simple-expression ::= complex | real | constant | variable | function | "(" expression ")"
 * 
 * complex           ::= real "i"
 * real              ::= real-simple | real-simple "e" signed-integer
 * real-simple       ::= integer | integer "." integer 
 * signed-integer    ::= additive-op integer | integer
 * integer           ::= number integer | number
 * number            ::= 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9
 * 
 * constant			 ::= variable
 * variable          ::= letters integer | letters
 * letters           ::= letter letters | letter
 * letter            ::= a - Z
 * 
 * function          ::= function-name "(" parameters ")"
 * function-name     ::= variable
 * parameters        ::= expression "," parameters | expression | ""
 * 
 * function-def      ::= function-name "(" parameters-def ")=" expression
 * parameters-def    ::= variable "," parameters-def | variable | ""
 * 
 * expr              ::= function-def = expression
 * 
 * Valid expression is like:
 *		- f()=2*5+1
 *		- g(x)=2.5*x
 *		- h(x)=-2e-3*(x+1)
 *		- i(x,y)=h(y)*2+sqrt(g(x))
 *		- j(z)=h(2*z+4)-g(h(z))
 *		- ...
 *
 * How does it work
 *
 * The parser parse expression and create an expression tree modeling the expression.
 * Expression is not parsed each time you evaluate it. When evaluate expression
 * only the tree is used.
 *
 *
 * 			g(x)=4*2/2+f(x)
 * 
 *  			  +
 *  			 / \
 *  			/    f()
 * 			   / \    |
 * 			  *   2   x
 * 			 / \
 * 			4   2
 * 
 * </pre>
 * @author SGINER
 */
public class ExpressionImpl implements Expression {

	//private String expression;
	protected TreeElement rootElement;
	protected String name;
	protected List functionParameters;
	protected Catalog catalog;
	protected ExpressionModel expressionModel;

	/**
	 * Constructor.
	 *
	 */
	public ExpressionImpl() {
		functionParameters = new Vector();
	}
	
	public void parse(String expression,Catalog catalog, ExpressionModel model) throws ParsingException {
		
		//this.expression = expression;
		this.catalog = catalog;
		expressionModel = model;
		
		ParseInfo parseInfo = new ParseInfo();
		parseInfo.setEndPos(0);
		
		
		ExpressionElement functionDefExpressionElement = new FunctionDefExpressionElement();
		
		if (functionDefExpressionElement.parseElement(expression,expressionModel,parseInfo,catalog,functionParameters,0)) {
			//find function def
			int endPos = parseInfo.getEndPos();
			this.name = parseInfo.getFunctionName();
			
			if (ExpressionElementUtil.checkPosition(expression,endPos) &&
				expression.charAt(endPos) == '=') {
				parseInfo.setEndPos(endPos + 1);

				ExpressionElement expressionElement = new ExpressionExpressionElement();
				if (expressionElement.parseElement(expression,expressionModel,parseInfo,catalog,functionParameters,1)) {
					rootElement = parseInfo.getTreeElement();
					if (parseInfo.getEndPos() != expression.length()) {
						throw new ParsingException("Invalid expression at " + parseInfo.getEndPos());
					}
					return;
				}
			}
		}
		
		throw new ParsingException("Invalid expression at " + parseInfo.getEndPos());
	}
	
	
	/* (non-Javadoc)
	 * @see fr.expression4j.core.Expression#getCatalog()
	 */
	public Catalog getCatalog() {
		return catalog;
	}

	/* (non-Javadoc)
	 * @see fr.expression4j.core.Expression#evaluate(fr.expression4j.core.Parameters)
	 */
	public MathematicalElement evaluate(OperatorManager operatorManager, Parameters parameters) throws EvalException {
		if (rootElement == null) {
			throw new EvalException("Invalid expression, parsing error.");
		}
		return ExpressionUtil.computeElement(rootElement,catalog,operatorManager,parameters,expressionModel);
	}

	public MathematicalElement evaluate(Parameters parameters) throws EvalException {
		OperatorManager defaultOperatorManager = OperatorManagerFactory.getDefaultOperatorManager();
		return evaluate(defaultOperatorManager,parameters);
	}

	/* (non-Javadoc)
	 * @see fr.expression4j.core.Expression#getName()
	 */
	public String getName() {
		return name;
	}

	/* (non-Javadoc)
	 * @see fr.expression4j.core.Expression#getParameters()
	 */
	public List getParameters() {
		return functionParameters;
	}

	public TreeElement getRootElement() {
		return rootElement;
	}

	/*
	public static void main(String args[]) {
		Parameters parameters = new ParametersImpl();
		
		ExpressionImpl expression = new ExpressionImpl();
		try {
			String expressionString = "f(tototo)=tototo^2"; 
			expression.parse(expressionString);
			for (int i=0; i<50; i++) {
				parameters.addParameter("tototo",i);
				System.out.println("Expression: " + expressionString + " = " + expression.evaluate(parameters));
			}
		}
		catch (Exception e) {
			System.out.println("Error: " + e);
		}
	}
	*/
	


	private final String functionParametersToString(List functionParameters) {
		StringBuffer result = new StringBuffer(10);
		String separator = ""; 
		
		Iterator iter = functionParameters.iterator();
		while (iter.hasNext()) {
			result.append(separator + iter.next());
			separator = ",";
		}
		
		return result.toString();
	}
	
	
	public String toString() {
		return name + "(" + functionParametersToString(functionParameters) + ")=" + ExpressionUtil.toString(rootElement,expressionModel).getValue();
	}
	
	public static void main(String[] args) {
		try {
			Expression expression = ExpressionFactory.createExpression("f(x)=2*(x+4)-2i");
			System.out.println("Result: " + expression);
		}
		catch (ParsingException pe) {
			System.out.println("Cannot add real with complex. " + pe);
		}
	}

	public ExpressionModel getExpressionModel() {
		return expressionModel;
	}

	
}
