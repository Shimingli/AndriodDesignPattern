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
import fr.expression4j.basic.ParseInfo;
import fr.expression4j.basic.impl.ParenthesisExpressionElement;
import fr.expression4j.basic.impl.RealExpressionElement;
import fr.expression4j.basic.impl.RealImpl;
import fr.expression4j.basic.impl.RealSimpleExpressionElement;
import fr.expression4j.basic.impl.SignedExpressionExpressionElement;
import fr.expression4j.basic.impl.SimpleExpressionExpressionElement;
import fr.expression4j.basic.impl.UnaryOperatorExpressionElement;
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
public class TestExpressionElement2 extends TestCase {
	public static Test suite() {
		return new TestSuite(TestExpressionElement2.class);
	}


	public void testRealExpressionElement() {
		try {
			ExpressionElement expressionElement = new RealExpressionElement();
			assertEquals("Invalid expression element name.","RealExpressionElement",expressionElement.getName());
			
			ParseInfo parseInfo = new ParseInfo();

			parseInfo.setEndPos(0);
			if (expressionElement.parseElement("6",null, parseInfo, null,null,1)) {
				assertEquals("Invalid parse end position.",1,parseInfo.getEndPos());
				assertEquals("Invalid operator found.",6,parseInfo.getTreeElement().getValue().getRealValue(),0);
			}
			else {
				fail ("Cannot parse a valid real value (1)");
			}
			
			parseInfo.setEndPos(0);
			if (expressionElement.parseElement("6.2",null, parseInfo, null,null,1)) {
				assertEquals("Invalid parse end position.",3,parseInfo.getEndPos());
				assertEquals("Invalid operator found.",6.2,parseInfo.getTreeElement().getValue().getRealValue(),0);
			}
			else {
				fail ("Cannot parse a valid real value (1)");
			}

			parseInfo.setEndPos(0);
			if (expressionElement.parseElement("6e-2",null, parseInfo, null,null,1)) {
				assertEquals("Invalid parse end position.",4,parseInfo.getEndPos());
				assertEquals("Invalid operator found.",6e-2,parseInfo.getTreeElement().getValue().getRealValue(),0);
			}
			else {
				fail ("Cannot parse a valid real value (1)");
			}

			parseInfo.setEndPos(0);
			if (expressionElement.parseElement("6.2e-2",null, parseInfo, null,null,1)) {
				assertEquals("Invalid parse end position.",6,parseInfo.getEndPos());
				assertEquals("Invalid operator found.",6.2e-2,parseInfo.getTreeElement().getValue().getRealValue(),0);
			}
			else {
				fail ("Cannot parse a valid real value (1)");
			}

			parseInfo.setEndPos(0);
			if (expressionElement.parseElement("-6",null, parseInfo, null,null,1)) {
				fail ("Can parse a negative real (1)");
			}
			else {
				//all right
			}

			parseInfo.setEndPos(0);
			if (expressionElement.parseElement("-6.2",null, parseInfo, null,null,1)) {
				fail ("Can parse a negative real (2)");
			}
			else {
				//all right
			}

			parseInfo.setEndPos(0);
			if (expressionElement.parseElement("-6e-2",null, parseInfo, null,null,1)) {
				fail ("Can parse a negative real (3)");
			}
			else {
				//all right
			}

			parseInfo.setEndPos(0);
			if (expressionElement.parseElement("-6.2e-2",null, parseInfo, null,null,1)) {
				fail ("Can parse a negative real (3)");
			}
			else {
				//all right
			}

			parseInfo.setEndPos(0);
			if (expressionElement.parseElement("a6",null, parseInfo, null,null,1)) {
				fail("Can pase an invalid real !!");
			}
			else {
				//nothing to do
			}
		}
		catch (ParsingException pe) {
			fail("Could not parse expression. " + pe);
		}
	}


	public void testRealSimpleExpressionElement() {
		try {
			ExpressionElement expressionElement = new RealSimpleExpressionElement();
			assertEquals("Invalid expression element name.","RealSimpleExpressionElement",expressionElement.getName());
			
			ParseInfo parseInfo = new ParseInfo();

			parseInfo.setEndPos(0);
			if (expressionElement.parseElement("6",null, parseInfo, null,null,1)) {
				assertEquals("Invalid parse end position.",1,parseInfo.getEndPos());
				assertEquals("Invalid operator found.",6,parseInfo.getTreeElement().getValue().getRealValue(),0);
			}
			else {
				fail ("Cannot parse a valid real value (1)");
			}
			
			parseInfo.setEndPos(0);
			if (expressionElement.parseElement("6.2",null, parseInfo, null,null,1)) {
				assertEquals("Invalid parse end position.",3,parseInfo.getEndPos());
				assertEquals("Invalid operator found.",6.2,parseInfo.getTreeElement().getValue().getRealValue(),0);
			}
			else {
				fail ("Cannot parse a valid real value (2)");
			}

			parseInfo.setEndPos(0);
			if (expressionElement.parseElement("6e-2",null, parseInfo, null,null,1)) {
				assertEquals("Invalid parse end position.",1,parseInfo.getEndPos());
				assertEquals("Invalid operator found.",6,parseInfo.getTreeElement().getValue().getRealValue(),0);
			}
			else {
				fail ("Cannot parse a valid real value (3)");
			}

			parseInfo.setEndPos(0);
			if (expressionElement.parseElement("6.2e-2",null, parseInfo, null,null,1)) {
				assertEquals("Invalid parse end position.",3,parseInfo.getEndPos());
				assertEquals("Invalid operator found.",6.2,parseInfo.getTreeElement().getValue().getRealValue(),0);
			}
			else {
				fail ("Cannot parse a valid real value (4)");
			}

			parseInfo.setEndPos(0);
			if (expressionElement.parseElement("-6",null, parseInfo, null,null,1)) {
				fail ("Can parse a negative real (1)");
			}
			else {
				//all right
			}

			parseInfo.setEndPos(0);
			if (expressionElement.parseElement("-6.2",null, parseInfo, null,null,1)) {
				fail ("Can parse a negative real (2)");
			}
			else {
				//all right
			}

			parseInfo.setEndPos(0);
			if (expressionElement.parseElement("-6e-2",null, parseInfo, null,null,1)) {
				fail ("Can parse a negative real (3)");
			}
			else {
				//all right
			}

			parseInfo.setEndPos(0);
			if (expressionElement.parseElement("-6.2e-2",null, parseInfo, null,null,1)) {
				fail ("Can parse a negative real (4)");
			}
			else {
				//all right
			}

			parseInfo.setEndPos(0);
			if (expressionElement.parseElement("a6",null, parseInfo, null,null,1)) {
				fail("Can pase an invalid real !!");
			}
			else {
				//nothing to do
			}
		}
		catch (ParsingException pe) {
			fail("Could not parse expression. " + pe);
		}
	}

	public void testSignedExpressionExpressionElement() {
		try {
			ExpressionElement expressionElement = new SignedExpressionExpressionElement();
			assertEquals("Invalid expression element name.","SignedExpressionExpressionElement",expressionElement.getName());
			
			ParseInfo parseInfo = new ParseInfo();

			parseInfo.setEndPos(0);
			if (expressionElement.parseElement("-6",ExpressionModelFactory.getDefaultExpressionModel(), parseInfo, null,null,1)) {
				assertEquals("Invalid parse end position.",2,parseInfo.getEndPos());
				//assertEquals("Invalid operator found.",6,parseInfo.getTreeElement().getValue().getRealValue(),0);
			}
			else {
				fail ("Cannot parse a valid signed value (1)");
			}
			
			parseInfo.setEndPos(0);
			if (expressionElement.parseElement("-6.2",ExpressionModelFactory.getDefaultExpressionModel(), parseInfo, null,null,1)) {
				assertEquals("Invalid parse end position.",4,parseInfo.getEndPos());
				//assertEquals("Invalid operator found.",6.2,parseInfo.getTreeElement().getValue().getRealValue(),0);
			}
			else {
				fail ("Cannot parse a valid signed value (2)");
			}

			parseInfo.setEndPos(0);
			if (expressionElement.parseElement("-6e-2",ExpressionModelFactory.getDefaultExpressionModel(), parseInfo, null,null,1)) {
				assertEquals("Invalid parse end position.",5,parseInfo.getEndPos());
				//assertEquals("Invalid operator found.",6,parseInfo.getTreeElement().getValue().getRealValue(),0);
			}
			else {
				fail ("Cannot parse a valid real value (3)");
			}

			parseInfo.setEndPos(0);
			if (expressionElement.parseElement("-6.2e-2",ExpressionModelFactory.getDefaultExpressionModel(), parseInfo, null,null,1)) {
				assertEquals("Invalid parse end position.",7,parseInfo.getEndPos());
				//assertEquals("Invalid operator found.",6.2,parseInfo.getTreeElement().getValue().getRealValue(),0);
			}
			else {
				fail ("Cannot parse a valid signed value (4)");
			}

			
			parseInfo.setEndPos(0);
			if (expressionElement.parseElement("+6",ExpressionModelFactory.getDefaultExpressionModel(), parseInfo, null,null,1)) {
				assertEquals("Invalid parse end position.",2,parseInfo.getEndPos());
				//assertEquals("Invalid operator found.",6,parseInfo.getTreeElement().getValue().getRealValue(),0);
			}
			else {
				fail ("Cannot parse a valid signed value (5)");
			}
			
			parseInfo.setEndPos(0);
			if (expressionElement.parseElement("+6.2",ExpressionModelFactory.getDefaultExpressionModel(), parseInfo, null,null,1)) {
				assertEquals("Invalid parse end position.",4,parseInfo.getEndPos());
				//assertEquals("Invalid operator found.",6.2,parseInfo.getTreeElement().getValue().getRealValue(),0);
			}
			else {
				fail ("Cannot parse a valid signed value (6)");
			}

			parseInfo.setEndPos(0);
			if (expressionElement.parseElement("+6e-2",ExpressionModelFactory.getDefaultExpressionModel(), parseInfo, null,null,1)) {
				assertEquals("Invalid parse end position.",5,parseInfo.getEndPos());
				//assertEquals("Invalid operator found.",6,parseInfo.getTreeElement().getValue().getRealValue(),0);
			}
			else {
				fail ("Cannot parse a valid real value (7)");
			}

			parseInfo.setEndPos(0);
			if (expressionElement.parseElement("+6.2e-2",ExpressionModelFactory.getDefaultExpressionModel(), parseInfo, null,null,1)) {
				assertEquals("Invalid parse end position.",7,parseInfo.getEndPos());
				//assertEquals("Invalid operator found.",6.2,parseInfo.getTreeElement().getValue().getRealValue(),0);
			}
			else {
				fail ("Cannot parse a valid signed value (8)");
			}
			
		}
		catch (ParsingException pe) {
			fail("Could not parse expression. " + pe);
		}
		catch (ModelException me) {
			fail("Could not get default expression model");
		}
	}
	
	
	public void testUnaryOperatorExpressionElement() {
		try {
			ExpressionElement expressionElement = new UnaryOperatorExpressionElement();
			assertEquals("Invalid expression element name.","UnaryOperatorExpressionElement",expressionElement.getName());
			
			ExpressionModel model = ExpressionModelFactory.createExpressionModel("expressionModel");
			model.addUnaryOperator(OperatorFactory.createOperator("unary plus","+",true));
			model.addUnaryOperator(OperatorFactory.createOperator("unary minus","-",true));
			
			ParseInfo parseInfo = new ParseInfo();
			parseInfo.setEndPos(0);
			
			expressionElement.parseElement("+",model, parseInfo, null,null,1);
			assertEquals("Invalid parse end position.",1,parseInfo.getEndPos());
			assertEquals("Invalid operator found.","unary plus",parseInfo.getTreeElement().getStringElement());
		}
		catch (ModelException me) {
			fail("Could not add info into model. " + me);
		}
		catch (ParsingException pe) {
			fail("Could not parse expression. " + pe);
		}
	}

	public void testParenthesisExpressionElement() {
		try {
			ExpressionElement expressionElement = new ParenthesisExpressionElement();
			assertEquals("Invalid expression element name.","ParenthesisExpressionElement",expressionElement.getName());
			
			ParseInfo parseInfo = new ParseInfo();
			List functionParameters = new Vector();

			parseInfo.setEndPos(0);
			if (expressionElement.parseElement("(6)",ExpressionModelFactory.getDefaultExpressionModel(), parseInfo, ExpressionFactory.getCatalog(),functionParameters,1)) {
				assertEquals("Invalid parse end position.",3,parseInfo.getEndPos());
				//assertEquals("Invalid operator found.",6,parseInfo.getTreeElement().getValue().getRealValue(),0);
			}
			else {
				fail ("Cannot parse a valid parenthesis value (1)");
			}
			
			parseInfo.setEndPos(0);
			if (expressionElement.parseElement("((((6))))",ExpressionModelFactory.getDefaultExpressionModel(), parseInfo, ExpressionFactory.getCatalog(),functionParameters,1)) {
				assertEquals("Invalid parse end position.",9,parseInfo.getEndPos());
				//assertEquals("Invalid operator found.",6.2,parseInfo.getTreeElement().getValue().getRealValue(),0);
			}
			else {
				fail ("Cannot parse a valid parenthesis value (2)");
			}

			parseInfo.setEndPos(0);
			if (expressionElement.parseElement("(1+(2+(3+(6+5)+4)+3)+2)+4",ExpressionModelFactory.getDefaultExpressionModel(), parseInfo, ExpressionFactory.getCatalog(),functionParameters,1)) {
				assertEquals("Invalid parse end position.",23,parseInfo.getEndPos());
				//assertEquals("Invalid operator found.",6.2,parseInfo.getTreeElement().getValue().getRealValue(),0);
			}
			else {
				fail ("Cannot parse a valid parenthesis value (3)");
			}

			parseInfo.setEndPos(0);
			if (expressionElement.parseElement("(((6))))",ExpressionModelFactory.getDefaultExpressionModel(), parseInfo, ExpressionFactory.getCatalog(),functionParameters,1)) {
				assertEquals("Invalid parse end position.",7,parseInfo.getEndPos());
				//assertEquals("Invalid operator found.",6.2,parseInfo.getTreeElement().getValue().getRealValue(),0);
			}
			else {
				fail ("Cannot parse a valid parenthesis value (3)");
			}

			parseInfo.setEndPos(0);
			if (expressionElement.parseElement("(((6))",ExpressionModelFactory.getDefaultExpressionModel(), parseInfo, ExpressionFactory.getCatalog(),functionParameters,1)) {
				fail ("Can parse an invalid parenthesis value (5)");
			}
			else {
				//all right
			}
		}
		catch (ParsingException pe) {
			fail("Could not parse expression. " + pe);
		}
		catch (ModelException me) {
			fail("Could not get default expression model");
		}
	}
	
	public void testSimpleExpressionExpressionElement() {
		try {
			
			ExpressionElement expressionElement = new SimpleExpressionExpressionElement();
			assertEquals("Invalid expression element name.","SimpleExpressionExpressionElement",expressionElement.getName());
			
			ParseInfo parseInfo = new ParseInfo();
			List functionParameters = new Vector();

			parseInfo.setEndPos(0);
			if (expressionElement.parseElement("6",ExpressionModelFactory.getDefaultExpressionModel(), parseInfo, ExpressionFactory.getCatalog(),functionParameters,1)) {
				assertEquals("Invalid parse end position.",1,parseInfo.getEndPos());
				assertEquals("Invalid operator found.",6,parseInfo.getTreeElement().getValue().getRealValue(),0);
			}
			else {
				fail ("Cannot parse a valid simple expression value (1)");
			}

			parseInfo.setEndPos(0);
			if (expressionElement.parseElement("6.2e-5",ExpressionModelFactory.getDefaultExpressionModel(), parseInfo, ExpressionFactory.getCatalog(),functionParameters,1)) {
				assertEquals("Invalid parse end position.",6,parseInfo.getEndPos());
				assertEquals("Invalid operator found.",6.2e-5,parseInfo.getTreeElement().getValue().getRealValue(),0);
			}
			else {
				fail ("Cannot parse a valid simple expression value (1)");
			}
			
			parseInfo.setEndPos(0);
			if (expressionElement.parseElement("6i",ExpressionModelFactory.getDefaultExpressionModel(), parseInfo, ExpressionFactory.getCatalog(),functionParameters,1)) {
				assertEquals("Invalid parse end position.",2,parseInfo.getEndPos());
				assertEquals("Invalid operator found.",6,parseInfo.getTreeElement().getValue().getComplexValue(),0);
			}
			else {
				fail ("Cannot parse a valid simple expression value (1)");
			}

			parseInfo.setEndPos(0);
			if (expressionElement.parseElement("6.2e-5i",ExpressionModelFactory.getDefaultExpressionModel(), parseInfo, ExpressionFactory.getCatalog(),functionParameters,1)) {
				assertEquals("Invalid parse end position.",7,parseInfo.getEndPos());
				assertEquals("Invalid operator found.",6.2e-5,parseInfo.getTreeElement().getValue().getComplexValue(),0);
			}
			else {
				fail ("Cannot parse a valid simple expression value (1)");
			}

			parseInfo.setEndPos(0);
			Catalog catalog = ExpressionFactory.getCatalog();
			catalog.addConstant("const",new RealImpl(10.4));
			
			if (expressionElement.parseElement("const",ExpressionModelFactory.getDefaultExpressionModel(), parseInfo, ExpressionFactory.getCatalog(),functionParameters,1)) {
				assertEquals("Invalid parse end position.",5,parseInfo.getEndPos());
				//assertEquals("Invalid operator found.",6.2e-5,parseInfo.getTreeElement().getValue().getComplexValue(),0);
			}
			else {
				fail ("Cannot parse a valid simple expression value (1)");
			}

			parseInfo.setEndPos(0);
			functionParameters.add("foo");
			if (expressionElement.parseElement("foo",ExpressionModelFactory.getDefaultExpressionModel(), parseInfo, ExpressionFactory.getCatalog(),functionParameters,1)) {
				assertEquals("Invalid parse end position.",3,parseInfo.getEndPos());
				//assertEquals("Invalid operator found.",6.2e-5,parseInfo.getTreeElement().getValue().getComplexValue(),0);
			}
			else {
				fail ("Cannot parse a valid simple expression value (1)");
			}
		
			parseInfo.setEndPos(0);
			ExpressionFactory.createExpression("f(x)=3");
			if (expressionElement.parseElement("f(3)",ExpressionModelFactory.getDefaultExpressionModel(), parseInfo, ExpressionFactory.getCatalog(),functionParameters,1)) {
				assertEquals("Invalid parse end position.",4,parseInfo.getEndPos());
				//assertEquals("Invalid operator found.",6.2e-5,parseInfo.getTreeElement().getValue().getComplexValue(),0);
			}
			else {
				fail ("Cannot parse a valid simple expression value (1)");
			}

			parseInfo.setEndPos(0);
			if (expressionElement.parseElement("(f(3))",ExpressionModelFactory.getDefaultExpressionModel(), parseInfo, ExpressionFactory.getCatalog(),functionParameters,1)) {
				assertEquals("Invalid parse end position.",6,parseInfo.getEndPos());
				//assertEquals("Invalid operator found.",6.2e-5,parseInfo.getTreeElement().getValue().getComplexValue(),0);
			}
			else {
				fail ("Cannot parse a valid simple expression value (1)");
			}
		}
		catch (ParsingException pe) {
			fail("Could not parse expression. " + pe);
		}
		catch (ModelException me) {
			fail("Could not get default expression model");
		}
		catch (MathematicalException me2) {
			fail("Could not get complex value");
		}
	}
	
}
