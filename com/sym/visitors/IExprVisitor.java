package com.sym.visitors;

import com.sym.expression.AddExpression;
import com.sym.expression.AndExpression;
import com.sym.expression.BooleanVariable;
import com.sym.expression.ConcreteConstant;
import com.sym.expression.DivExpression;
import com.sym.expression.EqualsExpression;
import com.sym.expression.False;
import com.sym.expression.GreaterThanEqualToExpression;
import com.sym.expression.GreaterThanExpression;
import com.sym.expression.IExpression;
import com.sym.expression.IIdentifier;
import com.sym.expression.Input;
import com.sym.expression.LesserThanEqualToExpression;
import com.sym.expression.LesserThanExpression;
import com.sym.expression.MulExpression;
import com.sym.expression.NotExpression;
import com.sym.expression.OrExpression;
import com.sym.expression.SubExpression;
import com.sym.expression.True;
import com.sym.expression.Variable;

public interface IExprVisitor<T> {
	void visit(Input exp);

	void visit(ConcreteConstant exp) throws Exception;

	void visit(False exp) throws Exception;

	void visit(GreaterThanExpression exp) throws Exception;

	void visit(AddExpression exp) throws Exception;

	void visit(SubExpression exp) throws Exception;

	void visit(MulExpression exp) throws Exception;

	void visit(DivExpression exp) throws Exception;

	void visit(LesserThanExpression exp) throws Exception;

	void visit(LesserThanEqualToExpression exp) throws Exception;

	void visit(GreaterThanEqualToExpression exp) throws Exception;

	void visit(AndExpression exp) throws Exception;

	void visit(OrExpression exp) throws Exception;

	void visit(True exp) throws Exception;

	void visit(Variable exp) throws Exception;

	void visit(BooleanVariable exp) throws Exception;

	void visit(NotExpression exp) throws Exception;

	void visit(EqualsExpression exp) throws Exception;

	void visit(IExpression exp) throws Exception;

	public T getValue();

}
