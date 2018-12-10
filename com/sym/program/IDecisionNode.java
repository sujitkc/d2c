package com.sym.program;

import com.sym.expression.IExpression;

public interface IDecisionNode extends IProgramNode {
	public IExpression getCondition();
}
