package com.iiitb.blocks;

import java.util.ArrayList;
import java.util.List;

import com.iiitb.cfg.Accfg;

import com.sym.cfg.ICFEdge;
import com.sym.cfg.ICFG;
import com.sym.cfg.ICFGBasicBlockNode;
import com.sym.expression.AddExpression;
import com.sym.expression.Variable;
import com.sym.mycfg.CFEdge;
import com.sym.mycfg.CFG;
import com.sym.mycfg.CFGBasicBlockNode;
import com.sym.statement.Statement;

/**
 * Constant block equivalent to Simulink block - Constant
 * 
 */
public class Constant extends Block {
	
	private String value;
	// private String name;
	
	private Accfg accfg;

	private ICFG mCFG;
	private ICFGBasicBlockNode BlockNode;
	private Variable value1;

	public Accfg getAccfg() {
		return accfg;
	}

	public void setAccfg(Accfg accfg) {
		this.accfg = accfg;
	}
	
	public Variable getOutVar(){
		return value1;
	}
	
	public void setBlockNode(ICFG mergedCFG,ArrayList<Variable> vars) throws Exception {
		
		Variable CONST = new Variable(getName(), mergedCFG);
		value1 = new Variable(getValue(), mergedCFG);
		BlockNode = new CFGBasicBlockNode(getName(), mergedCFG);
		Statement stmt = new Statement(mergedCFG,CONST, value1);
		BlockNode.addStatement(stmt);
		
	}
	
	public ICFGBasicBlockNode getBlockNode() {
		return BlockNode;
	}
	
	public ICFG getcfg() throws Exception {
		
		ICFGBasicBlockNode B = new CFGBasicBlockNode(getName()+" begin",null);
		ICFGBasicBlockNode W = new CFGBasicBlockNode(getName()+" while",null);
		mCFG = new CFG(B, W);

		BlockNode = new CFGBasicBlockNode(getName(), mCFG);
		
		Variable CONST = new Variable(getName(), mCFG);
		value1 = new Variable(getValue(), mCFG);
		Statement stmt = new Statement(mCFG,CONST, value1);
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
	
	public Variable getVar() {
		return value1;
	}
	
	@Override
	public void setVar() throws Exception {

		Variable CONST = new Variable(getName(), mCFG);
		value1 = new Variable(getValue(), mCFG);
		Statement stmt = new Statement(mCFG,CONST, value1);
		BlockNode.addStatement(stmt);
		mCFG.addBasicBlockNode(BlockNode);
	}
	/*
	 * public String getName() { return name; }
	 * 
	 * public void setName(String name) { this.name = name; }
	 */

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String expression() {

		return getName() + "=" + getValue();
	}
	
	public Constant(String name) throws Exception {

		super(name);
		Accfg accfgObj = new Accfg();
		accfgObj.setOutput(name);
		setAccfg(accfgObj);
	
	}

	public Constant(String value, String name) throws Exception {

		super(name);
		setValue(value);

		Accfg accfgObj = new Accfg();

		accfgObj.setOutput(getName());
		List<String> expr = new ArrayList<String>();
		expr.add(expression());
		accfgObj.setFp(expr);
		setAccfg(accfgObj);
		
	}

	@Override
	public ArrayList<String> getInput() {
		// TODO Auto-generated method stub
		
		//NO input for constant block. Returning null intentionally
		return null;
	}

	@Override
	public void setInput(String input) {
		// TODO Auto-generated method stub
		
		// Intentionally left blank
		// NO input for constant block
		
	}

}
