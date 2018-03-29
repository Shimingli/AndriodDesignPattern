package fr.expression4j.basic.impl;

import java.util.Properties;

import fr.expression4j.basic.MathematicalElement;
import fr.expression4j.basic.MathematicalException;

public class RealImpl implements MathematicalElement {

	protected double realValue;
	protected Properties properties; 
	
	public RealImpl(double value) {
		this.realValue = value;
		properties = new Properties();
		properties.setProperty(MathematicalElement.REAL_VALUE,String.valueOf(value));
	}
	
	public double getComplexValue() throws MathematicalException {
		throw new MathematicalException("Real number has no complex value.");
	}

	public double getRealValue() {
		return realValue;
	}

	public String toString() {
		return "" + realValue;
	}

	public int getType() {
		return 1;
	}
	
	public void setRealValue(double value) {
		this.realValue = value;
		properties.setProperty(MathematicalElement.REAL_VALUE,String.valueOf(value));
	}

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
		String stringValue = properties.getProperty(MathematicalElement.REAL_VALUE);
		if (stringValue != null) {
			realValue = Double.parseDouble(stringValue);
		}
		else {
			realValue = 0;
		}
	}

	public Object getValue() {
		return Double.valueOf(realValue);
	}
	
}
