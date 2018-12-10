package com.iiitb.blocks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.iiitb.cfg.Accfg;
import com.sym.cfg.ICFEdge;
import com.sym.cfg.ICFG;
import com.sym.cfg.ICFGBasicBlockNode;
import com.sym.expression.EqualsExpression;
import com.sym.expression.Expression;
import com.sym.expression.GreaterThanEqualToExpression;
import com.sym.expression.GreaterThanExpression;
import com.sym.expression.LesserThanEqualToExpression;
import com.sym.expression.LesserThanExpression;
import com.sym.expression.NotExpression;
import com.sym.expression.Variable;
import com.sym.mycfg.CFEdge;
import com.sym.mycfg.CFG;
import com.sym.mycfg.CFGBasicBlockNode;
import com.sym.statement.Statement;

public class Relational extends Block {

	public static final Map<Integer, String> signList = new HashMap<Integer, String>();
	private Accfg accfg;

	private ICFG mCFG;
	private ICFGBasicBlockNode BlockNode;

	public Accfg getAccfg() {
		return accfg;
	}

	public void setAccfg(Accfg accfg) {
		this.accfg = accfg;
	}
	
	static {

		signList.put(1, "==");
		signList.put(2, "~=");
		signList.put(3, "<");
		signList.put(4, "<=");
		signList.put(5, ">=");
		signList.put(6, ">");

	}
	
private Variable rhs,lhs1,lhs2;
	
	public Variable getOutVar() {
		return rhs;
	}
	
	public void setBlockNode(ICFG mergedCFG,ArrayList<Variable> vars) throws Exception {
		lhs1 = vars.get(0);
		lhs2 = vars.get(1);
		rhs = new Variable(vars.get(0).getName() + " "+signList.get(sign)+" " + vars.get(1).getName(),mergedCFG);
		BlockNode = new CFGBasicBlockNode(getName(), mergedCFG);
		Expression exp = null;
		if(signList.get(sign)=="==") {
			exp = new EqualsExpression(mergedCFG,lhs1,lhs2);
		}
		if(signList.get(sign)=="~=") {
			exp = new EqualsExpression(mergedCFG,lhs1,lhs2);
			exp = new NotExpression(mergedCFG,exp);
		}
		if(signList.get(sign)=="<") {
			exp = new LesserThanExpression(mergedCFG,lhs1,lhs2);
		}
		if(signList.get(sign)=="<=") {
			exp = new LesserThanEqualToExpression(mergedCFG,lhs1,lhs2);
		}
		if(signList.get(sign)==">=") {
			exp = new GreaterThanEqualToExpression(mergedCFG,lhs1,lhs2);
		}
		if(signList.get(sign)==">") {
			exp = new GreaterThanExpression(mergedCFG,lhs1,lhs2);
		}

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
		
		Expression exp = null;
		if(signList.get(sign)=="==") {
			exp = new EqualsExpression(mCFG,lhs1,lhs2);
		}
		if(signList.get(sign)=="~=") {
			exp = new EqualsExpression(mCFG,lhs1,lhs2);
			exp = new NotExpression(mCFG,exp);
		}
		if(signList.get(sign)=="<") {
			exp = new LesserThanExpression(mCFG,lhs1,lhs2);
		}
		if(signList.get(sign)=="<=") {
			exp = new LesserThanEqualToExpression(mCFG,lhs1,lhs2);
		}
		if(signList.get(sign)==">=") {
			exp = new GreaterThanEqualToExpression(mCFG,lhs1,lhs2);
		}
		if(signList.get(sign)==">") {
			exp = new GreaterThanExpression(mCFG,lhs1,lhs2);
		}
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
	
	private String input1;
	private String input2;
	
	private int sign ;
	// private String name;

	/*
	 * public String getName() { return name; }
	 * 
	 * public void setName(String name) { this.name = name; }
	 */
	
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
	
	@Override
	public String expression() {

		return ("Relational Operator = "+input1 + " "+signList.get(sign)+ " " + input2);
	}
	
	public Relational(String input1, String input2, String name, int sign) {

		super(name);

		setInput1(input1);
		setInput2(input2);

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

	public Relational(String blockName) {
		// TODO Auto-generated constructor stub
		
		super(blockName);
		Accfg accfgObj = new Accfg();
		accfgObj.setOutput(blockName);
		setAccfg(accfgObj);
		
	}

}
	