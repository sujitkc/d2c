package com.iiitb.blocks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.iiitb.cfg.Accfg;
import com.sym.cfg.ICFEdge;
import com.sym.cfg.ICFG;
import com.sym.cfg.ICFGBasicBlockNode;
import com.sym.expression.MulExpression;
import com.sym.expression.Variable;
import com.sym.mycfg.CFEdge;
import com.sym.mycfg.CFG;
import com.sym.mycfg.CFGBasicBlockNode;
import com.sym.statement.Statement;


public class Gain extends Block {

	public static final Map<Integer, String> operator = new HashMap<Integer, String>();
	private Accfg accfg;

	private ICFG mCFG;
	private ICFGBasicBlockNode BlockNode;

	public Accfg getAccfg() {
		return accfg;
	}

	public void setAccfg(Accfg accfg) {
		this.accfg = accfg;
	}
	
	private Variable rhs,lhs,value1;
	
	public Variable getOutVar() {
		return rhs;
	}
	
	public void setBlockNode(ICFG mergedCFG,ArrayList<Variable> vars) throws Exception {
		lhs = vars.get(0);
		value1 = new Variable(getValue(), mergedCFG);
		rhs = new Variable(vars.get(0).getName() + " * " + value1.getName(),mergedCFG);
		BlockNode = new CFGBasicBlockNode(getName(), mergedCFG);
		MulExpression exp = new MulExpression(mergedCFG, lhs,value1);
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
		lhs = new Variable("input1",mCFG);
		value1 = new Variable(getValue(), mCFG);
		
		BlockNode = new CFGBasicBlockNode(getName(), mCFG);
		
		MulExpression exp = new MulExpression(mCFG, lhs,value1);
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
	
	@Override
	public void setInput(String input) {
		if(this.input1 == null || this.input1 == ""){
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
	
	private String input1;
	private String value;
	
	public String getInput1() {
		return input1;
	}

	public void setInput1(String input1) {
		this.input1 = input1;
	}
	
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	public String expression() {

		return (getName()+"= " + input1 + " Gain "+ value);
	}
	
	public Gain(String input1, String name) {

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

	public Gain(String blockName) {
		// TODO Auto-generated constructor stub
		
		super(blockName);
		Accfg accfgObj = new Accfg();
		accfgObj.setOutput(blockName);
		setAccfg(accfgObj);
		
	}

}