package com.sym.program;

import java.util.List;

import com.sym.statement.IStatement;

public interface IBasicBlockNode extends IProgramNode {
	public List<IStatement> getStatements();
	public void addStatement(IStatement statement);
}
