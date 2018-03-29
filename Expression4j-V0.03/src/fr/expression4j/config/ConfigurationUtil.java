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

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;

import fr.expression4J.config.BinaryOperator;
import fr.expression4J.config.CatalogDocument;
import fr.expression4J.config.Catalogs;
import fr.expression4J.config.ConfigurationDocument;
import fr.expression4J.config.Constant;
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
import fr.expression4j.basic.impl.RealImpl;
import fr.expression4j.core.Catalog;
import fr.expression4j.core.ConfigurationException;
import fr.expression4j.core.Expression;
import fr.expression4j.core.ExpressionModel;
import fr.expression4j.factory.ExpressionFactory;
import fr.expression4j.factory.ExpressionModelFactory;
import fr.expression4j.factory.OperatorFactory;
import fr.expression4j.factory.OperatorManagerFactory;

public final class ConfigurationUtil {

	static Logger log = Logger.getLogger(ConfigurationUtil.class);

	/**
	 * Load the default expression4j configuration.
	 *
	 */
	public static final void loadDefaultConfig() {
		try {
			InputStream defaultConfigStream = ConfigurationUtil.class.getClassLoader().getResourceAsStream("/fr/expression4j/config/DefaultConfig.xml");
			loadConfig(defaultConfigStream);
		}
		catch (ConfigurationException ce) {
			System.out.println("Could not load init parameters.");
		}
	}
	
	/**
	 * Load the specified expression4j configuration
	 * @param xmlConfigStream stream from with read data.
	 */
	public static final void loadConfig(InputStream xmlConfigStream) throws ConfigurationException {
		try {
			log.debug("In loadConfig");
			
			//parse xml configuration document
			log.debug("Parse xml from stream.");
			ConfigurationDocument configDoc = ConfigurationDocument.Factory.parse(xmlConfigStream);
			ConfigurationDocument.Configuration configuration = configDoc.getConfiguration();
			
			///////////////
			//add operators
			///////////////
			log.debug("Add operators");
			Operators operators = configuration.getOperators();
			OperatorDocument.Operator[] operatorList = operators.getOperatorArray();
			for (int i=0; i<operatorList.length; i++) {
				OperatorDocument.Operator currentOperator = operatorList[i];
				OperatorFactory.createOperator(currentOperator.getName(),currentOperator.getSymbol(),currentOperator.getUnary());
			}
			log.debug("Operator added");
			
			///////////////
			//add operator managers
			//with Operator impl
			///////////////
			log.debug("Add operator manager");
			OperatorManagers operatorManagers = configuration.getOperatorManagers();
			OperatorManagerDocument.OperatorManager[] operatorManagerList = operatorManagers.getOperatorManagerArray();
			for (int i=0; i<operatorManagerList.length; i++) {
				OperatorManagerDocument.OperatorManager currentOperatorManagerXML = operatorManagerList[i];
				String operatorManagerName = currentOperatorManagerXML.getName();
				
				OperatorManager currentOperatorManager = OperatorManagerFactory.createOperatorManager(operatorManagerName);
				String[] classNameList = currentOperatorManagerXML.getOperatorImplClassArray();
				for (int j=0; j<classNameList.length; j++) {
					String currentClassName = classNameList[j];
					Class currentClass = Class.forName(currentClassName);
					OperatorImpl currentOperatorImpl = (OperatorImpl) currentClass.newInstance();
					
					currentOperatorManager.addOperatorImpl(currentOperatorImpl);
				}
			}
			log.debug("Operator manager added.");
			
			///////////////
			//add expression models
			///////////////
			log.debug("Add expression models");
			Models models = configuration.getModels();
			ModelDocument.Model[] modelList = models.getModelArray();
			for (int i=0; i<modelList.length; i++) {
				ModelDocument.Model currentModelXML = modelList[i];
				String modelName = currentModelXML.getName();
				
				ExpressionModel currentExpressionModel = ExpressionModelFactory.createExpressionModel(modelName);
				
				//add unary operators
				log.debug("Add unary operators");
				String[] unaryOperatorList = currentModelXML.getUnaryOperatorClassNameArray();
				for (int j=0; j<unaryOperatorList.length; j++) {
					Operator currentUnaryOperator = OperatorFactory.getOperator(unaryOperatorList[j]);
					currentExpressionModel.addUnaryOperator(currentUnaryOperator);
				}
				log.debug("Unary operators added");
				
				//add binary operators
				log.debug("Add binary operators");
				BinaryOperator[] binaryOperatorList = currentModelXML.getBinaryOperatorArray();
				for (int j=0; j<binaryOperatorList.length; j++) {
					int currentPriority = binaryOperatorList[j].getPriority();
					String[] binaryClassOperatorList = binaryOperatorList[j].getOperatorClassNameArray();
					
					for (int k=0; k<binaryClassOperatorList.length; k++) {
						Operator currentBinaryOperator = OperatorFactory.getOperator(binaryClassOperatorList[k]);
						currentExpressionModel.addBinaryOperator(currentBinaryOperator,currentPriority);
					}
				}
				log.debug("Binary operators added.");
				
				//add expression models
				log.debug("Add expression models");
				fr.expression4J.config.ExpressionElement[] expressionElementList = currentModelXML.getExpressionElementArray();
				for (int j=0; j<expressionElementList.length; j++) {
					int currentExpressionElementIndex = expressionElementList[j].getEvaluationOrder();
					Class currentClass = Class.forName(expressionElementList[j].getExpressionElementClassName());
					ExpressionElement currentExpressionElement = (ExpressionElement) currentClass.newInstance();

					currentExpressionModel.addExpressionElement(currentExpressionElement,currentExpressionElementIndex);
				}
				log.debug("Expression models added.");
			}
			
			///////////////
			// finally
			//add expression and constant in catalogs
			///////////////
			log.debug("Add expression and constant in catalogs");
			Catalogs catalogs = configuration.getCatalogs();
			CatalogDocument.Catalog[] catalogList = catalogs.getCatalogArray();
			for (int i=0; i<catalogList.length; i++) {
				String currentCatalogName = catalogList[i].getName();
				
				Catalog currentCatalog = ExpressionFactory.createCatalog(currentCatalogName);

				//add constant to catalog
				Constant[] constantList = catalogList[i].getConstantArray();
				for (int j=0; j<constantList.length; j++) {
					String currentConstantName = constantList[j].getConstantName();
					Properties currentProperties = new Properties();
					
					Constant.ConstantProperty[] constantPropertyList = constantList[j].getConstantPropertyArray();
					for (int k=0; k<constantPropertyList.length; k++) {
						currentProperties.setProperty(constantPropertyList[k].getName(),constantPropertyList[k].getValue());
					}

					MathematicalElement currentConstant = new RealImpl(0);
					currentConstant.setProperties(currentProperties);
					
					currentCatalog.addConstant(currentConstantName,currentConstant);
				}

				//add expression to catalog
				fr.expression4J.config.Expression[] expressionList = catalogList[i].getExpressionArray();
				for (int j=0; j<expressionList.length; j++) {
					//String currentExpressionName = expressionList[j].getExpressionName();
					String currentExpressionClass = expressionList[j].getExpressionClass();
					String currentExpressionValue = expressionList[j].getExpressionValue();
					String currentExpressionModel = expressionList[j].getExpressionModel();
					
					if (currentExpressionValue.equals("Predefine")) {
						//it is a predefine expression like cos or sin
						Class currentClass = Class.forName(currentExpressionClass);
						Expression currentPredefineExpression = (Expression) currentClass.newInstance();
						currentCatalog.addExpression(currentPredefineExpression);
					}
					else {
						//it is a std expression like "f(g)=3*g+5"
						ExpressionModel model = ExpressionModelFactory.getExpressionModel(currentExpressionModel);
						ExpressionFactory.createExpression(currentExpressionValue,currentCatalog,model);
					}
				}				
			}
			log.debug("Catalogs added");
			
		} catch (Exception e) {
			throw new ConfigurationException("Could not load configuration.",e);
		}
	}
	
	/**
	 * write expression4j configuration
	 * @param xmlConfigStream stream where to write configuration.
	 */
	public static final void saveConfig(OutputStream xmlConfigStream) throws ConfigurationException {
		try {
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
					currentModelXML.addUnaryOperatorClassName(currentOperator.getName());
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
						binOp.addOperatorClassName(currentOperator.getName());
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
				
				//add constant
				List constantList = currentCatalog.listConstant();
				Iterator iter2 = constantList.iterator();
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
				
				// add expression
				List expressionList = currentCatalog.listExpression();
				iter2 = expressionList.iterator();
				while (iter2.hasNext()) {
					String currentExpressionName = (String) iter2.next();
					Expression currentExpression = currentCatalog.getExpression(currentExpressionName);
					
					fr.expression4J.config.Expression expressionXML = catalogXML.addNewExpression();
					expressionXML.setExpressionName(currentExpression.getName());
					expressionXML.setExpressionValue(currentExpression.toString());
					expressionXML.setExpressionClass(currentExpression.getClass().getName());
					expressionXML.setExpressionModel(currentExpression.getExpressionModel().getName());
				}
			}

		
			configDoc.save(xmlConfigStream);
		} catch (Exception e) {
			throw new ConfigurationException("Could not load configuration.",e);
		}
	}
	
}
