package com.iiitb.blocks;

import java.util.ArrayList;
import java.util.List;

import com.iiitb.cfg.Accfg;

import com.sym.cfg.ICFEdge;
import com.sym.cfg.ICFG;
import com.sym.cfg.ICFGBasicBlockNode;
import com.sym.expression.Variable;
import com.sym.mycfg.CFEdge;
import com.sym.mycfg.CFG;
import com.sym.mycfg.CFGBasicBlockNode;
import com.sym.statement.Statement;

public class Sign extends Block {
	
	private Accfg accfg;
	private ICFG mCFG;
	private ICFGBasicBlockNode BlockNode;

	public Accfg getAccfg() {
		return accfg;
	}

	public void setAccfg(Accfg accfg) {
		this.accfg = accfg;
	}
	
	private Variable lhs,rhs;
	
	public Variable getOutVar() {
		return rhs;
	}
	
	public void setBlockNode(ICFG mergedCFG,ArrayList<Variable> vars) throws Exception {
		lhs = vars.get(0);
		rhs = new Variable("Sign( " + vars.get(0).getName() + " )",mergedCFG);
		BlockNode = new CFGBasicBlockNode(getName(), mergedCFG);
		Statement stmt = new Statement(mergedCFG, lhs, rhs);
		BlockNode.addStatement(stmt);
	}
	
	public ICFGBasicBlockNode getBlockNode() {
		return BlockNode;
	}
	
	
	public ICFG getcfg() throws Exception {
		
		ICFGBasicBlockNode B = new CFGBasicBlockNode(getName()+" begin",null);
		ICFGBasicBlockNode W = new CFGBasicBlockNode(getName()+" while",null);
		mCFG = new CFG(B, W);
		
		lhs = new Variable(getName(),mCFG);
		rhs = new Variable("|input|",mCFG);
		
		BlockNode = new CFGBasicBlockNode(getName(), mCFG);
		Statement stmt = new Statement(mCFG, lhs, rhs);
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

	public String getInput1() {
		return input1;
	}

	public void setInput1(String input1) {
		this.input1 = input1;
	}
	
	public Sign(String input1,String name) throws Exception {
		
		super(name);

		setInput1(input1);

		Accfg accfgObj = new Accfg();
		List<String> input = new ArrayList<String>();
		input.add(input1);
		accfgObj.setInput(input);
		accfgObj.setOutput(getName());
		List<String> expr = new ArrayList<String>();
		expr.add(expression());
		accfgObj.setFp(expr);
		setAccfg(accfgObj);

	}
	
	public Sign(String blockName) throws Exception {
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
			setInputSetFlag(true);
		
		}
	}

	@Override
	public ArrayList<String> getInput()
	{
		List<String> inputs = new ArrayList<String>();
		inputs.add(getInput1());
		return (ArrayList<String>) inputs;
	}

	@Override
	public String expression() {

		return (getName()+"="+ "Sign(" + input1 + ")");
	}


}
