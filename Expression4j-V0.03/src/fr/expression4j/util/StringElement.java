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

import fr.expression4j.basic.Operator;

public class StringElement {
	private int type;
	private String value;
	private Operator operator;
	
	public StringElement(int type, String value,Operator operator) {
		this.type = type;
		this.value = value;
		this.operator = operator;
	}

	public int getType() {
		return type;
	}

	public String getValue() {
		return value;
	}
	
	public Operator getOperator() {
		return operator;
	}
	
}
