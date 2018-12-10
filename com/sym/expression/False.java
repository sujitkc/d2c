package com.sym.expression;

import com.sym.program.IProgram;
import com.sym.visitors.IExprVisitor;

public class False extends Expression {

	public False(IProgram program) throws Exception {
		super(program, Type.BOOLEAN);
	}

	@Override
	public String toString() {
		return "false";
	}

	@Override
	public void accept(IExprVisitor<?> visitor) {
	}
}
