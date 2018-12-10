package com.sym.visitors;

public interface IAcceptor {
	public void accept(IExprVisitor<?> visitor) throws Exception;
}
