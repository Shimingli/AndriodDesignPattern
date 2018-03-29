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

import fr.expression4j.basic.MathematicalElement;
import fr.expression4j.basic.impl.ComplexImpl;
import fr.expression4j.basic.impl.RealImpl;

public class NumberFactory {

	/**
	 * Create a real.
	 * @param value real value
	 * @return real as a number
	 */
	public static MathematicalElement createReal(double value) {
		return new RealImpl(value);
	}

	/**
	 * create a complex (a,bi) or a+bi
	 * @param realValue real complex value
	 * @param complexValue imaginary complex value
	 * @return the complex number created.
	 */
	public static MathematicalElement createComplex(double realValue, double complexValue) {
		return new ComplexImpl(realValue,complexValue);
	}
}
