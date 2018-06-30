package com.sym.expression;

import com.sym.program.IProgram;
import com.sym.visitors.IExprVisitor;

public class True extends Expression {

	public True(IProgram program) throws Exception {
		super(program, Type.BOOLEAN);
	}

	@Override
	public String toString() {
		return "true";
	}

	@Override
	public void accept(IExprVisitor<?> visitor) {
	}
}
