package com.iiitb.blocks;

import java.util.ArrayList;
import java.util.List;

import com.iiitb.cfg.Accfg;

import com.sym.cfg.ICFEdge;
import com.sym.cfg.ICFG;
import com.sym.cfg.ICFGBasicBlockNode;
import com.sym.expression.AddExpression;
import com.sym.expression.MulExpression;
import com.sym.expression.Variable;
import com.sym.mycfg.CFEdge;
import com.sym.mycfg.CFG;
import com.sym.mycfg.CFGBasicBlockNode;
import com.sym.statement.Statement;

public class Product extends Block {
	
	private Accfg accfg;

	private ICFG mCFG;
	private ICFGBasicBlockNode BlockNode;

	public Accfg getAccfg() {
		return accfg;
	}

	public void setAccfg(Accfg accfg) {
		this.accfg = accfg;
	}
	
	private Variable rhs,lhs1,lhs2;
	
	public Variable getOutVar() {
		return rhs;
	}
	
	public void setBlockNode(ICFG mergedCFG,ArrayList<Variable> vars) throws Exception {
		lhs1 = vars.get(0);
		lhs2 = vars.get(1);
		rhs = new Variable(vars.get(0).getName() + " * " + vars.get(1).getName(),mergedCFG);
		BlockNode = new CFGBasicBlockNode(getName(), mergedCFG);
		MulExpression exp = new MulExpression(mergedCFG, lhs1, lhs2);
		Statement stmt = new Statement(mergedCFG,rhs, exp);
		BlockNode.addStatement(stmt);
	}
	
	public ICFGBasicBlockNode getBlockNode() {
		return BlockNode;
	}
	
	public ICFG getcfg() throws Exception {
		
		ICFGBasicBlockNode B = new CFGBasicBlockNode(getName()+" begin",null);
		ICFGBasicBlockNode W = new CFGBasicBlockNode(getName()+" while",null);
		mCFG = new CFG(B, W);

		rhs = new Variable(getName(),mCFG);
		lhs1 = new Variable("input1",mCFG);
		lhs2 = new Variable("input2",mCFG);
		
		BlockNode = new CFGBasicBlockNode(getName(), mCFG);
		
		MulExpression exp = new MulExpression(mCFG, lhs1, lhs2);
		Statement stmt = new Statement(mCFG,rhs, exp);
		BlockNode.addStatement(stmt);
		mCFG.addBasicBlockNode(BlockNode);
		ICFEdge edge1 = new CFEdge( B.getId() + " ---> " + BlockNode.getId(), mCFG,B,BlockNode);
		mCFG.addEdge(edge1);
		ICFEdge edge2 = new CFEdge( BlockNode.getId() + " ---> " + W.getId(), mCFG,BlockNode,W);
		mCFG.addEdge(edge2);
		ICFEdge edge3 = new CFEdge( W.getId() + " ---> " + BlockNode.getId(), mCFG,W,BlockNode);
		mCFG.addEdge(edge3);

		return mCFG;
	}

	public void setcfg(ICFG Cfg) {
		this.mCFG = Cfg;
	}
	
	private String input1;
	private String input2;
	private int sign ;

	public int getSign() {
		return sign;
	}

	public void setSign(int sign) {
		this.sign = sign;
	}

	public String getInput1() {
		return input1;
	}

	public void setInput1(String input1) {
		this.input1 = input1;
	}

	public String getInput2() {
		return input2;
	}

	public void setInput2(String input2) {
		this.input2 = input2;
	}
	
	public Product(String input1, String input2, String name, int sign) throws Exception {
		
		super(name);

		setInput1(input1);
		setInput2(input2);
		setSign(sign);

		Accfg accfgObj = new Accfg();
		List<String> input = new ArrayList<String>();
		input.add(input1);
		input.add(input2);
		accfgObj.setInput(input);
		accfgObj.setOutput(getName());
		List<String> expr = new ArrayList<String>();
		expr.add(expression());
		accfgObj.setFp(expr);
		setAccfg(accfgObj);

	}
	
	public Product(String blockName) throws Exception {
		// TODO Auto-generated constructor stub
		
		super(blockName);
		Accfg accfgObj = new Accfg();
		accfgObj.setOutput(blockName);
		setAccfg(accfgObj);
	}
	
	@Override
	public void setInput(String input) {
		if(this.input1 == null || this.input1==""){
			setInput1(input);
			//System.out.println("Input1 is set");
		
		}
		else{
			setInput2(input);
			setInputSetFlag(true);
			//System.out.println("Input2 is set");
			}
	}

	@Override
	public ArrayList<String> getInput()
	{
		List<String> inputs = new ArrayList<String>();
		inputs.add(getInput1());
		inputs.add(getInput2());
		return (ArrayList<String>) inputs;
	}

	@Override
	public String expression() {

		return (getName()+"="+input1 + "*" + input2);
	}


}
