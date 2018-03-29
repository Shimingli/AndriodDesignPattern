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

package fr.expression4j.test.basic;

import java.util.List;
import java.util.Vector;

import fr.expression4j.basic.ExpressionElement;
import fr.expression4j.basic.MathematicalException;
import fr.expression4j.basic.Operator;
import fr.expression4j.basic.ParseInfo;
import fr.expression4j.basic.impl.BinaryOperatorExpressionElement;
import fr.expression4j.basic.impl.ComplexOrRealExpressionElement;
import fr.expression4j.basic.impl.ConstantOrVariableExpressionElement;
import fr.expression4j.basic.impl.ConstantOrVariableWithoutCheckExpressionElement;
import fr.expression4j.basic.impl.FunctionDefExpressionElement;
import fr.expression4j.basic.impl.FunctionExpressionElement;
import fr.expression4j.basic.impl.FunctionNameExpressionElement;
import fr.expression4j.basic.impl.IntegerExpressionElement;
import fr.expression4j.basic.impl.ParametersDefExpressionElement;
import fr.expression4j.basic.impl.ParametersExpressionElement;
import fr.expression4j.core.Catalog;
import fr.expression4j.core.ExpressionModel;
import fr.expression4j.core.exception.ModelException;
import fr.expression4j.core.exception.ParsingException;
import fr.expression4j.factory.ExpressionFactory;
import fr.expression4j.factory.ExpressionModelFactory;
import fr.expression4j.factory.OperatorFactory;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * test addition in expression
 * @author SGINER
 *
 */
public class TestExpressionElement extends TestCase {
	public static Test suite() {
		return new TestSuite(TestExpressionElement.class);
	}


	public void testBinaryOperatorExpressionElement() {
		try {
			ExpressionElement expressionElement = new BinaryOperatorExpressionElement();
			assertEquals("Invalid expression element name.","BinaryOperatorExpressionElement",expressionElement.getName());
			
			ExpressionModel model = ExpressionModelFactory.createExpressionModel("expressionModel");
			model.addBinaryOperator(OperatorFactory.createOperator("plus","+",false),1);
			model.addBinaryOperator(OperatorFactory.createOperator("minus","-",false),1);
			
			ParseInfo parseInfo = new ParseInfo();
			parseInfo.setEndPos(0);
			
			expressionElement.parseElement("+",model, parseInfo, null,null,1);
			assertEquals("Invalid parse end position.",1,parseInfo.getEndPos());
			assertEquals("Invalid operator found.","plus",parseInfo.getTreeElement().getStringElement());
		}
		catch (ModelException me) {
			fail("Could not add info into model. " + me);
		}
		catch (ParsingException pe) {
			fail("Could not parse expression. " + pe);
		}
	}

	private static class OperatorAnd implements Operator {

		public String getName() {
			return "and";
		}

		public String getSymbol() {
			return "and";
		}

		public boolean isUnary() {
			return false;
		}
		
	}
	
	public void testBinaryOperatorExpressionElementForCustomOperator() {
		try {
			ExpressionElement expressionElement = new BinaryOperatorExpressionElement();
			assertEquals("Invalid expression element name.","BinaryOperatorExpressionElement",expressionElement.getName());
			
			ExpressionModel model = ExpressionModelFactory.createExpressionModel("expresionModel");
			model.addBinaryOperator(OperatorFactory.createOperator("plus","+",false),1);
			model.addBinaryOperator(OperatorFactory.createOperator("minus","-",false),1);
			model.addBinaryOperator(new OperatorAnd(),1);
			
			ParseInfo parseInfo = new ParseInfo();
			parseInfo.setEndPos(0);
			
			expressionElement.parseElement("and",model, parseInfo, null,null,1);
			assertEquals("Invalid parse end position.",3,parseInfo.getEndPos());
			assertEquals("Invalid operator found.","and",parseInfo.getTreeElement().getStringElement());
		}
		catch (ModelException me) {
			fail("Could not add info into model. " + me);
		}
		catch (ParsingException pe) {
			fail("Could not parse expression. " + pe);
		}
	}

	public void testComplexOrRealExpressionElement() {
		try {
			ExpressionElement expressionElement = new ComplexOrRealExpressionElement();
			assertEquals("Invalid expression element name.","ComplexOrRealExpressionElement",expressionElement.getName());
			
			ParseInfo parseInfo = new ParseInfo();

			parseInfo.setEndPos(0);
			expressionElement.parseElement("2.34",null, parseInfo, null,null,0);
			assertEquals("Invalid parse end position.",4,parseInfo.getEndPos());
			assertEquals("Invalid type result found.",1,parseInfo.getTreeElement().getValue().getType());
			assertEquals("Invalid value found.",2.34,parseInfo.getTreeElement().getValue().getRealValue(),0);

			parseInfo.setEndPos(0);
			expressionElement.parseElement("234e-2",null, parseInfo, null,null,0);
			assertEquals("Invalid parse end position.",6,parseInfo.getEndPos());
			assertEquals("Invalid type result found.",1,parseInfo.getTreeElement().getValue().getType());
			assertEquals("Invalid value found.",2.34,parseInfo.getTreeElement().getValue().getRealValue(),0);
		
			parseInfo.setEndPos(0);
			expressionElement.parseElement("2.34e2",null, parseInfo, null,null,0);
			assertEquals("Invalid parse end position.",6,parseInfo.getEndPos());
			assertEquals("Invalid type result found.",1,parseInfo.getTreeElement().getValue().getType());
			assertEquals("Invalid value found.",234,parseInfo.getTreeElement().getValue().getRealValue(),0);

			try {
				parseInfo.setEndPos(0);
				expressionElement.parseElement("2.34i",null, parseInfo, null,null,0);
				assertEquals("Invalid parse end position.",5,parseInfo.getEndPos());
				assertEquals("Invalid type result found.",2,parseInfo.getTreeElement().getValue().getType());
				assertEquals("Invalid real value found.",0,parseInfo.getTreeElement().getValue().getRealValue(),0);
				assertEquals("Invalid value found.",2.34,parseInfo.getTreeElement().getValue().getComplexValue(),0);
	
				parseInfo.setEndPos(0);
				expressionElement.parseElement("234e-2i",null, parseInfo, null,null,0);
				assertEquals("Invalid parse end position.",7,parseInfo.getEndPos());
				assertEquals("Invalid type result found.",2,parseInfo.getTreeElement().getValue().getType());
				assertEquals("Invalid real value found.",0,parseInfo.getTreeElement().getValue().getRealValue(),0);
				assertEquals("Invalid value found.",2.34,parseInfo.getTreeElement().getValue().getComplexValue(),0);
			
				parseInfo.setEndPos(0);
				expressionElement.parseElement("2.34e2i",null, parseInfo, null,null,0);
				assertEquals("Invalid parse end position.",7,parseInfo.getEndPos());
				assertEquals("Invalid type result found.",2,parseInfo.getTreeElement().getValue().getType());
				assertEquals("Invalid real value found.",0,parseInfo.getTreeElement().getValue().getRealValue(),0);
				assertEquals("Invalid value found.",234,parseInfo.getTreeElement().getValue().getComplexValue(),0);
			}
			catch (MathematicalException me) {
				fail("Cannot evaluate complex value of result." + me);
			}
		}
		catch (ParsingException pe) {
			fail("Could not parse expression. " + pe);
		}
	}
	
	public void testConstantOrVariableExpressionElement() {
		try {
			ExpressionElement expressionElement = new ConstantOrVariableExpressionElement();
			assertEquals("Invalid expression element name.","ConstantOrVariableExpressionElement",expressionElement.getName());
			
			ParseInfo parseInfo = new ParseInfo();
			
			Catalog catalog = ExpressionFactory.createCatalog("catalog");
			List functionParameters = new Vector(1);
			
			parseInfo.setEndPos(0);
			functionParameters.add("foo");
			expressionElement.parseElement("foo",null, parseInfo, catalog,functionParameters,1);
			assertEquals("Invalid parse end position.",3,parseInfo.getEndPos());
			assertEquals("Invalid variable found.","foo",parseInfo.getTreeElement().getStringElement());

			parseInfo.setEndPos(0);
			functionParameters.add("foo23");
			expressionElement.parseElement("foo23",null, parseInfo, catalog,functionParameters,1);
			assertEquals("Invalid parse end position.",5,parseInfo.getEndPos());
			assertEquals("Invalid variable found.","foo23",parseInfo.getTreeElement().getStringElement());

			parseInfo.setEndPos(0);
			expressionElement.parseElement("e",null, parseInfo, catalog,null,1);
			assertEquals("Invalid parse end position.",1,parseInfo.getEndPos());
			assertEquals("Invalid variable found.","e",parseInfo.getTreeElement().getStringElement());

			parseInfo.setEndPos(0);
			expressionElement.parseElement("pi",null, parseInfo, catalog,null,1);
			assertEquals("Invalid parse end position.",2,parseInfo.getEndPos());
			assertEquals("Invalid variable found.","pi",parseInfo.getTreeElement().getStringElement());

			parseInfo.setEndPos(0);
			try {
				if (expressionElement.parseElement("toto",null, parseInfo, catalog,functionParameters,1)) {
					fail("Can parse a variable not define in parameters.");
				}
			}
			catch (ParsingException pe) {
				//nothing to do
			}

			parseInfo.setEndPos(0);
			try {
				if (expressionElement.parseElement("32foo",null, parseInfo, catalog,functionParameters,1)) {
					fail("Can parse an invalid variable.");
				}
			}
			catch (ParsingException pe) {
				//nothing to do
			}
		}
		catch (ParsingException pe) {
			fail("Could not parse expression. " + pe);
		}
	}

	public void testConstantOrVariableExpressionElementWithoutCheck() {
		try {
			ExpressionElement expressionElement = new ConstantOrVariableWithoutCheckExpressionElement();
			assertEquals("Invalid expression element name.","ConstantOrVariableWithoutCheckExpressionElement",expressionElement.getName());
			
			ParseInfo parseInfo = new ParseInfo();
			
			Catalog catalog = ExpressionFactory.createCatalog("Catalog");
			
			parseInfo.setEndPos(0);
			expressionElement.parseElement("foo",null, parseInfo, catalog,null,1);
			assertEquals("Invalid parse end position.",3,parseInfo.getEndPos());
			assertEquals("Invalid variable found.","foo",parseInfo.getTreeElement().getStringElement());

			parseInfo.setEndPos(0);
			expressionElement.parseElement("foo23",null, parseInfo, catalog,null,1);
			assertEquals("Invalid parse end position.",5,parseInfo.getEndPos());
			assertEquals("Invalid variable found.","foo23",parseInfo.getTreeElement().getStringElement());

			parseInfo.setEndPos(0);
			expressionElement.parseElement("e",null, parseInfo, catalog,null,1);
			assertEquals("Invalid parse end position.",1,parseInfo.getEndPos());
			assertEquals("Invalid variable found.","e",parseInfo.getTreeElement().getStringElement());

			parseInfo.setEndPos(0);
			expressionElement.parseElement("pi",null, parseInfo, catalog,null,1);
			assertEquals("Invalid parse end position.",2,parseInfo.getEndPos());
			assertEquals("Invalid variable found.","pi",parseInfo.getTreeElement().getStringElement());

			parseInfo.setEndPos(0);
			if (expressionElement.parseElement("toto",null, parseInfo, catalog,null,1)) {
				//all right
			}
			else {
				fail("Canot parse a variable not define in parameters.");
			}

			parseInfo.setEndPos(0);
			if (expressionElement.parseElement("32foo",null, parseInfo, catalog,null,1)) {
				fail("Can parse an invalid variable.");
			}
		}
		catch (ParsingException pe) {
			fail("Could not parse expression. " + pe);
		}
	}

	public void testFunctionDefExpressionElementWithoutCheck() {
		try {
			ExpressionElement expressionElement = new FunctionDefExpressionElement();
			assertEquals("Invalid expression element name.","FunctionDefExpressionElement",expressionElement.getName());
			
			ParseInfo parseInfo = new ParseInfo();
			List functionParameters = new Vector();
			
			parseInfo.setEndPos(0);
			if (expressionElement.parseElement("foo()",null, parseInfo, null,functionParameters,1)) {
				assertEquals("Invalid parse end position.",5,parseInfo.getEndPos());
				assertEquals("Invalid function found.","foo",parseInfo.getFunctionName());
			}
			else {
				fail("Could not parse a valid function def 3.");
			}

			parseInfo.setEndPos(0);
			if (expressionElement.parseElement("foo23()",null, parseInfo, null,functionParameters,1)) {
				assertEquals("Invalid parse end position.",7,parseInfo.getEndPos());
				assertEquals("Invalid function found.","foo23",parseInfo.getFunctionName());
			}
			else {
				fail("Could not parse a valid function def 2.");
			}

			parseInfo.setEndPos(0);
			if (expressionElement.parseElement("foo23(x)",null, parseInfo, null,functionParameters,1)) {
				assertEquals("Invalid parse end position.",8,parseInfo.getEndPos());
				assertEquals("Invalid function found.","foo23",parseInfo.getFunctionName());
				assertEquals("Invalid parameters size.",1,functionParameters.size());
				assertEquals("Invalid parameters name.","x",functionParameters.get(0));
			}
			else {
				fail("Could not parse a valid function def 3.");
			}

			parseInfo.setEndPos(0);
			functionParameters.clear();
			if (expressionElement.parseElement("foo23(foo,x,y)",null, parseInfo, null,functionParameters,1)) {
				assertEquals("Invalid parse end position.",14,parseInfo.getEndPos());
				assertEquals("Invalid function found.","foo23",parseInfo.getFunctionName());
				assertEquals("Invalid parameters size.",3,functionParameters.size());
				assertEquals("Invalid parameters name.","foo",functionParameters.get(0));
				assertEquals("Invalid parameters name.","x",functionParameters.get(1));
				assertEquals("Invalid parameters name.","y",functionParameters.get(2));
			}
			else {
				fail("Could not parse a valid function def 4.");
			}

			parseInfo.setEndPos(0);
			if (expressionElement.parseElement("foo23foo,x,y)",null, parseInfo, null,functionParameters,1)) {
				fail("Could parse an invalid function def 5.");
			}
		
			parseInfo.setEndPos(0);
			if (expressionElement.parseElement("foo23(foo,x,y",null, parseInfo, null,functionParameters,1)) {
				fail("Could parse an invalid function def 6.");
			}
		}
		catch (ParsingException pe) {
			fail("Could not parse expression. " + pe);
		}
	}
	
	public void testFunctionExpressionElement() {
		try {
			ExpressionElement expressionElement = new FunctionExpressionElement();
			assertEquals("Invalid expression element name.","FunctionExpressionElement",expressionElement.getName());
			
			ParseInfo parseInfo = new ParseInfo();
			List functionParameters = new Vector();

			ExpressionFactory.createExpression("foo()=23");
			
			parseInfo.setEndPos(0);
			if (expressionElement.parseElement("foo()",null, parseInfo, ExpressionFactory.getCatalog(),functionParameters,1)) {
				assertEquals("Invalid parse end position.",5,parseInfo.getEndPos());
				assertEquals("Invalid function found.","foo",parseInfo.getTreeElement().getStringElement());
			}
			else {
				fail("Could not parse a valid function 1.");
			}

			parseInfo.setEndPos(0);
			ExpressionFactory.createExpression("foo23()=23");
			if (expressionElement.parseElement("foo23()",null, parseInfo,ExpressionFactory.getCatalog(),functionParameters,1)) {
				assertEquals("Invalid parse end position.",7,parseInfo.getEndPos());
				assertEquals("Invalid function found.","foo23",parseInfo.getTreeElement().getStringElement());
			}
			else {
				fail("Could not parse a valid function 2.");
			}

			parseInfo.setEndPos(0);
			ExpressionFactory.createExpression("foo23(x)=23");
			
			if (expressionElement.parseElement("foo23(3)",ExpressionModelFactory.getDefaultExpressionModel(), parseInfo, ExpressionFactory.getCatalog(),functionParameters,1)) {
				assertEquals("Invalid parse end position.",8,parseInfo.getEndPos());
				assertEquals("Invalid function found.","foo23",parseInfo.getTreeElement().getStringElement());
				assertEquals("Invalid parameters size.",1,(((List) parseInfo.getTreeElement().getExpressionElementProperties()).size()));
			}
			else {
				fail("Could not parse a valid function 3.");
			}

			parseInfo.setEndPos(0);
			ExpressionFactory.createExpression("foo23(x,y,z)=23");
			if (expressionElement.parseElement("foo23(2*2,5,6)",ExpressionModelFactory.getDefaultExpressionModel(), parseInfo, ExpressionFactory.getCatalog(),functionParameters,1)) {
				assertEquals("Invalid parse end position.",14,parseInfo.getEndPos());
				assertEquals("Invalid function found.","foo23",parseInfo.getTreeElement().getStringElement());
				assertEquals("Invalid parameters size.",3,(((List) parseInfo.getTreeElement().getExpressionElementProperties()).size()));
			}
			else {
				fail("Could not parse a valid function 4.");
			}

			parseInfo.setEndPos(0);
			if (expressionElement.parseElement("foo23foo,x,y)",null, parseInfo, ExpressionFactory.getCatalog(),functionParameters,1)) {
				fail("Could parse an invalid function 5.");
			}
		
			parseInfo.setEndPos(0);
			try {
				if (expressionElement.parseElement("foo23(foo,x,y",ExpressionModelFactory.getDefaultExpressionModel(), parseInfo, ExpressionFactory.getCatalog(),functionParameters,1)) {
					fail("Could parse an invalid function 6.");
				}
			}
			catch (ParsingException pe) {
				//all right 
			}
		}
		catch (ParsingException pe) {
			fail("Could not parse expression. " + pe);
		}
		catch (ModelException me) {
			fail("Could not get expression model. " + me);
		}
	}
	
	public void testFunctionNameExpressionElement() {
		try {
			ExpressionElement expressionElement = new FunctionNameExpressionElement();
			assertEquals("Invalid expression element name.","FunctionNameExpressionElement",expressionElement.getName());
			
			ParseInfo parseInfo = new ParseInfo();
			List functionParameters = new Vector();

			parseInfo.setEndPos(0);
			if (expressionElement.parseElement("foo",null, parseInfo, null,functionParameters,1)) {
				assertEquals("Invalid parse end position.",3,parseInfo.getEndPos());
				assertEquals("Invalid function found.","foo",parseInfo.getTreeElement().getStringElement());
			}
			else {
				fail("Could not parse a valid function name 1.");
			}

			parseInfo.setEndPos(0);
			if (expressionElement.parseElement("foo23",null, parseInfo,ExpressionFactory.getCatalog(),functionParameters,1)) {
				assertEquals("Invalid parse end position.",5,parseInfo.getEndPos());
				assertEquals("Invalid function found.","foo23",parseInfo.getTreeElement().getStringElement());
			}
			else {
				fail("Could not parse a valid function 2.");
			}


			parseInfo.setEndPos(0);
			if (expressionElement.parseElement("23foo",null, parseInfo, ExpressionFactory.getCatalog(),functionParameters,1)) {
				fail("Could parse an invalid function 5.");
			}
		
		}
		catch (ParsingException pe) {
			fail("Could not parse expression. " + pe);
		}
	}

	public void testIntegerExpressionElement() {
		try {
			ExpressionElement expressionElement = new IntegerExpressionElement();
			assertEquals("Invalid expression element name.","IntegerExpressionElement",expressionElement.getName());
			
			ParseInfo parseInfo = new ParseInfo();
			List functionParameters = new Vector();

			parseInfo.setEndPos(0);
			if (expressionElement.parseElement("123",null, parseInfo, null,functionParameters,1)) {
				assertEquals("Invalid parse end position.",3,parseInfo.getEndPos());
				assertEquals("Invalid function found.",123,parseInfo.getTreeElement().getValue().getRealValue(),0);
			}
			else {
				fail("Could not parse a valid integer 1.");
			}

			parseInfo.setEndPos(0);
			if (expressionElement.parseElement("1234567890",null, parseInfo,ExpressionFactory.getCatalog(),functionParameters,1)) {
				assertEquals("Invalid parse end position.",10,parseInfo.getEndPos());
				assertEquals("Invalid function found.",1234567890,parseInfo.getTreeElement().getValue().getRealValue(),0);
			}
			else {
				fail("Could not parse a valid integer 2.");
			}

			parseInfo.setEndPos(0);
			if (expressionElement.parseElement("1234.567890",null, parseInfo,ExpressionFactory.getCatalog(),functionParameters,1)) {
				assertEquals("Invalid parse end position.",4,parseInfo.getEndPos());
				assertEquals("Invalid function found.",1234,parseInfo.getTreeElement().getValue().getRealValue(),0);
			}
			else {
				fail("Could not parse a valid integer 2.");
			}

			parseInfo.setEndPos(0);
			if (expressionElement.parseElement("1234.567890",null, parseInfo,ExpressionFactory.getCatalog(),functionParameters,1)) {
				assertEquals("Invalid parse end position.",4,parseInfo.getEndPos());
				assertEquals("Invalid function found.",1234,parseInfo.getTreeElement().getValue().getRealValue(),0);
			}
			else {
				fail("Could not parse a valid integer 2.");
			}

			parseInfo.setEndPos(0);
			if (expressionElement.parseElement("a1234.567890",null, parseInfo,ExpressionFactory.getCatalog(),functionParameters,1)) {
				fail("Could parse an invalid integer 2.");
			}
			else {
				//all right
			}
		}
		catch (ParsingException pe) {
			fail("Could not parse expression. " + pe);
		}
	}

	public void testParametersDefExpressionElement() {
		try {
			ExpressionElement expressionElement = new ParametersDefExpressionElement();
			assertEquals("Invalid expression element name.","ParametersDefExpressionElement",expressionElement.getName());
			
			ParseInfo parseInfo = new ParseInfo();
			List functionParameters = new Vector();

			parseInfo.setEndPos(0);
			if (expressionElement.parseElement("x",null, parseInfo, null,functionParameters,1)) {
				assertEquals("Invalid parse end position.",1,parseInfo.getEndPos());
				assertEquals("Invalid parameter size.",1,functionParameters.size());
				assertEquals("Invalid function found.","x",functionParameters.get(0));
			}
			else {
				fail("Could not parse a valid parameterDef 1.");
			}

			parseInfo.setEndPos(0);
			functionParameters.clear();
			if (expressionElement.parseElement("x,y",null, parseInfo, null,functionParameters,1)) {
				assertEquals("Invalid parse end position.",3,parseInfo.getEndPos());
				assertEquals("Invalid parameter size.",2,functionParameters.size());
				assertEquals("Invalid function found.","x",functionParameters.get(0));
				assertEquals("Invalid function found.","y",functionParameters.get(1));
			}
			else {
				fail("Could not parse a valid parameterDef 2.");
			}
			
			parseInfo.setEndPos(0);
			functionParameters.clear();
			if (expressionElement.parseElement("x,y,z,toto",null, parseInfo, null,functionParameters,1)) {
				assertEquals("Invalid parse end position.",10,parseInfo.getEndPos());
				assertEquals("Invalid parameter size.",4,functionParameters.size());
				assertEquals("Invalid parameter found.","x",functionParameters.get(0));
				assertEquals("Invalid parameter found.","y",functionParameters.get(1));
				assertEquals("Invalid parameter found.","z",functionParameters.get(2));
				assertEquals("Invalid parameter found.","toto",functionParameters.get(3));
			}
			else {
				fail("Could not parse a valid parameterDef 2.");
			}

			parseInfo.setEndPos(0);
			functionParameters.clear();
			if (expressionElement.parseElement(",x,y,z,toto",null, parseInfo, null,functionParameters,1)) {
				fail("Could parse an invalid parameterDef.");
			}
			else {
				//nothing to do
			}
		}
		catch (ParsingException pe) {
			fail("Could not parse expression. " + pe);
		}
	}

	public void testParametersExpressionElement() {
		try {
			ExpressionElement expressionElement = new ParametersExpressionElement();
			assertEquals("Invalid expression element name.","ParametersExpressionElement",expressionElement.getName());
			
			ParseInfo parseInfo = new ParseInfo();
			List functionParameters = new Vector();

			functionParameters.add("x");
			functionParameters.add("y");
			functionParameters.add("z");
			
			parseInfo.setEndPos(0);
			if (expressionElement.parseElement("x+y", ExpressionModelFactory.getDefaultExpressionModel(), parseInfo,  ExpressionFactory.getCatalog(),functionParameters,1)) {
				assertEquals("Invalid parse end position.",3,parseInfo.getEndPos());
				assertEquals("Invalid parameter size.",1,((List) parseInfo.getTreeElement().getExpressionElementProperties()).size());
			}
			else {
				fail("Could not parse a valid parameter 1.");
			}

			parseInfo.setEndPos(0);
			if (expressionElement.parseElement("x+y,y-z", ExpressionModelFactory.getDefaultExpressionModel(), parseInfo,  ExpressionFactory.getCatalog(),functionParameters,1)) {
				assertEquals("Invalid parse end position.",7,parseInfo.getEndPos());
				assertEquals("Invalid parameter size.",2,((List) parseInfo.getTreeElement().getExpressionElementProperties()).size());
			}
			else {
				fail("Could not parse a valid parameter 2.");
			}
			
			parseInfo.setEndPos(0);
			if (expressionElement.parseElement("x+y,y-z,x*z", ExpressionModelFactory.getDefaultExpressionModel(), parseInfo, ExpressionFactory.getCatalog(),functionParameters,1)) {
				assertEquals("Invalid parse end position.",11,parseInfo.getEndPos());
				assertEquals("Invalid parameter size.",3,((List) parseInfo.getTreeElement().getExpressionElementProperties()).size());
			}
			else {
				fail("Could not parse a valid parameter 3.");
			}

			parseInfo.setEndPos(0);
			if (expressionElement.parseElement("x+y,,x*z", ExpressionModelFactory.getDefaultExpressionModel(), parseInfo, ExpressionFactory.getCatalog(),functionParameters,1)) {
				fail("Could not parse an invalid parameter 4.");
			}
			else {
				//all rigth
			}
		}
		catch (ParsingException pe) {
			fail("Could not parse expression. " + pe);
		}
		catch (ModelException me) {
			fail("Could not get expression model");
		}
	}

}
