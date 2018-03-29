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

package fr.expression4j.config;

import java.io.StringWriter;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import fr.expression4J.config.BinaryOperator;
import fr.expression4J.config.CatalogDocument;
import fr.expression4J.config.Catalogs;
import fr.expression4J.config.ConfigurationDocument;
import fr.expression4J.config.ModelDocument;
import fr.expression4J.config.Models;
import fr.expression4J.config.OperatorDocument;
import fr.expression4J.config.OperatorManagerDocument;
import fr.expression4J.config.OperatorManagers;
import fr.expression4J.config.Operators;
import fr.expression4j.basic.ExpressionElement;
import fr.expression4j.basic.MathematicalElement;
import fr.expression4j.basic.Operator;
import fr.expression4j.basic.OperatorImpl;
import fr.expression4j.basic.OperatorManager;
import fr.expression4j.core.Catalog;
import fr.expression4j.core.Expression;
import fr.expression4j.core.ExpressionModel;
import fr.expression4j.factory.ExpressionFactory;
import fr.expression4j.factory.ExpressionModelFactory;
import fr.expression4j.factory.OperatorFactory;
import fr.expression4j.factory.OperatorManagerFactory;

public class TestConfiguration {

	public TestConfiguration() {
		super();
	}

	/**
	 * @param args
	 */
	public static void main2(String[] args) {
		try {
			ConfigurationDocument configDoc = ConfigurationDocument.Factory.newInstance();
			ConfigurationDocument.Configuration config = configDoc.addNewConfiguration();
			
			Operators operators = config.addNewOperators();
			OperatorManagers opManagers = config.addNewOperatorManagers();
			Models models = config.addNewModels();
			
			OperatorDocument.Operator operator = operators.addNewOperator();
			operator.setName("UnaryPlus");
			operator.setSymbol("+");
			operator.setUnary(true);

			operator = operators.addNewOperator();
			operator.setName("UnaryMinus");
			operator.setSymbol("-");
			operator.setUnary(true);

			operator = operators.addNewOperator();
			operator.setName("Plus");
			operator.setSymbol("+");
			operator.setUnary(false);

			operator = operators.addNewOperator();
			operator.setName("Minus");
			operator.setSymbol("-");
			operator.setUnary(false);

			operator = operators.addNewOperator();
			operator.setName("Factor");
			operator.setSymbol("*");
			operator.setUnary(false);

			operator = operators.addNewOperator();
			operator.setName("Divide");
			operator.setSymbol("/");
			operator.setUnary(false);

			operator = operators.addNewOperator();
			operator.setName("Exponent");
			operator.setSymbol("^");
			operator.setUnary(false);

			OperatorManagerDocument.OperatorManager opManager = opManagers.addNewOperatorManager();
			opManager.setName("default");
			opManager.addOperatorImplClass("fr.expression4j.basic.impl.Plus");
			opManager.addOperatorImplClass("fr.expression4j.basic.impl.Minus");
			opManager.addOperatorImplClass("fr.expression4j.basic.impl.Divide");
			opManager.addOperatorImplClass("fr.expression4j.basic.impl.Factor");
			
			opManager = opManagers.addNewOperatorManager();
			opManager.setName("myDef");
			opManager.addOperatorImplClass("fr.expression4j.basic.impl.Plus");
			opManager.addOperatorImplClass("fr.expression4j.basic.impl.Minus");
			opManager.addOperatorImplClass("fr.expression4j.basic.impl.Divide");
			opManager.addOperatorImplClass("fr.expression4j.basic.impl.Factor");
			
			ModelDocument.Model currentModel = models.addNewModel();
			currentModel.setName("default");
			currentModel.addUnaryOperatorClassName("Unaryplus");
			currentModel.addUnaryOperatorClassName("UnaryMinus");
			
			BinaryOperator binOp = currentModel.addNewBinaryOperator();
			binOp.addOperatorClassName("Plus");
			binOp.addOperatorClassName("Minus");
			binOp.setPriority(1);
			binOp = currentModel.addNewBinaryOperator();
			binOp.addOperatorClassName("Factor");
			binOp.addOperatorClassName("Divide");
			binOp.setPriority(2);
			binOp = currentModel.addNewBinaryOperator();
			binOp.addOperatorClassName("Exponent");
			binOp.setPriority(3);
			
			fr.expression4J.config.ExpressionElement expElem = currentModel.addNewExpressionElement();
			expElem.setExpressionElementClassName("fr.expression4j.basic.expElem1");
			expElem.setEvaluationOrder(1);
			expElem = currentModel.addNewExpressionElement();
			expElem.setExpressionElementClassName("fr.expression4j.basic.expElem2");
			expElem.setEvaluationOrder(2);
			
			
			StringWriter writer = new StringWriter();
			configDoc.save(writer);
			System.out.println(writer.toString());
		} catch (Exception e) {
			System.out.println("Error: " + e);
		}

	}

	public static void main(String[] args) {
		try {
			//ExpressionFactory.createExpression("f(x,y)=2*x+y-5");
			
			
			ConfigurationDocument configDoc = ConfigurationDocument.Factory.newInstance();
			ConfigurationDocument.Configuration config = configDoc.addNewConfiguration();
			
			Operators operators = config.addNewOperators();
			OperatorManagers opManagers = config.addNewOperatorManagers();
			Models models = config.addNewModels();
			Catalogs catalogs = config.addNewCatalogs();
			
			///////////////
			//add operators
			///////////////
			List operatorList = OperatorFactory.getOperatorList();
			Iterator iter = operatorList.iterator();
			while (iter.hasNext()) {
				String operatorName = (String) iter.next();
				Operator operator = OperatorFactory.getOperator(operatorName);
				OperatorDocument.Operator operatorXML = operators.addNewOperator();
				operatorXML.setName(operator.getName());
				operatorXML.setSymbol(operator.getSymbol());
				operatorXML.setUnary(operator.isUnary());
			}


			///////////////
			//add operator managers
			//with Operator impl
			///////////////
			List opManagerList = OperatorManagerFactory.getOperatorManagerList();
			iter = opManagerList.iterator();
			while (iter.hasNext()) {
				String opManagerName = (String) iter.next();
				OperatorManager opManager = OperatorManagerFactory.getOperatorManager(opManagerName);

				OperatorManagerDocument.OperatorManager opManagerXML = opManagers.addNewOperatorManager();
				opManagerXML.setName(opManager.getName());
				List opImplList = opManager.getOperatorImplList();
				Iterator iter2 = opImplList.iterator();
				while (iter2.hasNext()) {
					String currentOpImplName = (String) iter2.next();
					OperatorImpl currentOpImpl = (OperatorImpl) opManager.getOperatorImpl(currentOpImplName);
					
					opManagerXML.addOperatorImplClass(currentOpImpl.getClass().getName());
				}
			}
			
			///////////////
			//add expression models
			///////////////
			List expressionModelList = ExpressionModelFactory.getAllExpressionModel();
			iter = expressionModelList.iterator();
			while (iter.hasNext()) {
				String currentExpressionModelName = (String) iter.next();
				ExpressionModel currentExpressionModel = ExpressionModelFactory.getExpressionModel(currentExpressionModelName);

				ModelDocument.Model currentModelXML = models.addNewModel();
				currentModelXML.setName(currentExpressionModel.getName());

				// manage unary operators
				List unaryOperatorList = currentExpressionModel.getUnaryOperators();
				Iterator iter2 = unaryOperatorList.iterator();
				while (iter2.hasNext()) {
					Operator currentOperator = (Operator) iter2.next();
					currentModelXML.addUnaryOperatorClassName(currentOperator.getClass().getName());
				}
				
				//manage binary operators
				int maxBinaryOperatorLevel = currentExpressionModel.getMaxOperatorPriority();
				for (int i=1; i<=maxBinaryOperatorLevel; i++) {
					List currentLevelBinaryOperator = currentExpressionModel.getBinaryOperators(i);
					BinaryOperator binOp = currentModelXML.addNewBinaryOperator();
					binOp.setPriority(i);
					iter2 = currentLevelBinaryOperator.iterator();
					while (iter2.hasNext()) {
						Operator currentOperator = (Operator) iter2.next();
						binOp.addOperatorClassName(currentOperator.getClass().getName());
					}
				}
				
				//manage expression element
				int nbExpressionElementPriority = currentExpressionModel.getMaxExpressionElementPriority();
				for (int i=1; i<=nbExpressionElementPriority; i++) {
					ExpressionElement currentExpressionElement = currentExpressionModel.getExpressionElement(i);
					fr.expression4J.config.ExpressionElement expElemXML = currentModelXML.addNewExpressionElement();
					expElemXML.setEvaluationOrder(i);
					expElemXML.setExpressionElementClassName(currentExpressionElement.getClass().getName());
				}
				
			}
			
			///////////////
			// finally
			//add expression and constant in catalogs
			///////////////
			List catalogList = ExpressionFactory.getCatalogList();
			iter = catalogList.iterator();
			while (iter.hasNext()) {
				String currentCatalogName = (String) iter.next();
				Catalog currentCatalog = ExpressionFactory.getCatalog(currentCatalogName);
				
				CatalogDocument.Catalog catalogXML = catalogs.addNewCatalog();
				catalogXML.setName(currentCatalog.getName());
				
				// add expression
				List expressionList = currentCatalog.listExpression();
				Iterator iter2 = expressionList.iterator();
				while (iter2.hasNext()) {
					String currentExpressionName = (String) iter2.next();
					Expression currentExpression = currentCatalog.getExpression(currentExpressionName);
					
					fr.expression4J.config.Expression expressionXML = catalogXML.addNewExpression();
					expressionXML.setExpressionName(currentExpression.getName());
					expressionXML.setExpressionValue(currentExpression.toString());
					expressionXML.setExpressionModel(currentExpression.getExpressionModel().getName());
					expressionXML.setExpressionClass(currentExpression.getClass().getName());
				}
				
				//add constant
				List constantList = currentCatalog.listConstant();
				iter2 = constantList.iterator();
				while (iter2.hasNext()) {
					String currentConstantName = (String) iter2.next();
					MathematicalElement currentConstant = currentCatalog.getConstant(currentConstantName);
					fr.expression4J.config.Constant constantXML = catalogXML.addNewConstant();
					constantXML.setConstantName(currentConstantName);
					
					//set ptoperties
					Properties properties = currentConstant.getProperties();
					Enumeration enm = properties.keys();
					while (enm.hasMoreElements()) {
						String currentProperty = (String) enm.nextElement();
						String currentValue = properties.getProperty(currentProperty);
						fr.expression4J.config.Constant.ConstantProperty currentConstantProperty = constantXML.addNewConstantProperty();
						
						currentConstantProperty.setName(currentProperty);
						currentConstantProperty.setValue(currentValue);
						
					}
				}
			}

		
			StringWriter writer = new StringWriter();
			configDoc.save(writer);
			System.out.println(writer.toString());
		} catch (Exception e) {
			System.out.println("Error: " + e);
		}

	}
	
}
