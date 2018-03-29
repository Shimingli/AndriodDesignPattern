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

public class ExpressionElementUtil {

	/**
	 * check if the given position is in the range of the expression string
	 * @param position position to check
	 * @return <code>true</code> if position is in the string, <code>false</code> if position is outside the string.
	 */
	public static boolean checkPosition(String expression,int position) {
		int expressionLength = expression.length();
		if (position >= expressionLength || position < 0) {
			return false;
		}
		
		return true;
	}

}
