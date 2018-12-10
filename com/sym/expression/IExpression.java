package com.sym.expression;

import com.sym.program.IProgram;
import com.sym.visitors.IAcceptor;

public interface IExpression extends IAcceptor {
	public IProgram getProgram();
	public void setProgram(IProgram program);
	public String getType();
	public String toString();
}
