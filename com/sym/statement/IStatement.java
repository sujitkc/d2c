package com.sym.statement;

import com.sym.program.IProgram;
import com.sym.expression.IExpression;
import com.sym.expression.IIdentifier;

public interface IStatement {
	public IIdentifier getLHS();
	public IExpression getRHS();
	public IProgram getProgram();
}
