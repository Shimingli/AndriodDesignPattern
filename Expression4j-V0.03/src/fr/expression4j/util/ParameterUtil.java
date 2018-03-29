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

package fr.expression4j.util;

import java.util.List;
import java.util.Vector;

public class ParameterUtil {
	private static List xParameters;
	
	static {
		xParameters = new Vector(1);
		xParameters.add("x");
	}

	/**
	 * Define a "x" parameter.
	 * Used by default function like cos(x).
	 * 
	 * @return parameter "x"
	 */
	public static final List generateXParameters() {
		return xParameters;
	}
}
