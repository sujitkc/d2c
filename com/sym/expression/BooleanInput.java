package com.sym.expression;

import com.sym.program.IProgram;
import com.sym.visitors.IExprVisitor;

public class BooleanInput extends Expression {

	public BooleanInput(IProgram program) throws Exception {
		super(program, Type.BOOLEAN);
	}

	
	@Override
	public String toString() {
		return "BooleanInput";
	}

	@Override
	public void accept(IExprVisitor<?> visitor) {
	}

}
