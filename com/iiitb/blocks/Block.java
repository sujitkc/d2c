package com.iiitb.blocks;

import java.util.ArrayList;

import com.iiitb.cfg.Accfg;
import com.iiitb.inter.IBlock;

import com.sym.cfg.ICFG;
import com.sym.cfg.ICFGBasicBlockNode;
import com.sym.expression.Variable;

public abstract class Block implements IBlock {

	private String name;
	private String value;
	private String initial;
	private String port;
	private int sign;
	private Accfg accfg;
	private ICFG mCFG;
	private ICFGBasicBlockNode BlockNode;


	boolean inputSetFlag;

	public boolean isInputSetFlag() {
		return inputSetFlag;
	}

	public void setInputSetFlag(boolean inputSetFlag) {
		this.inputSetFlag = inputSetFlag;
	}
	
	public Accfg getAccfg() {
		return accfg;
	}

	public void setAccfg(Accfg accfg) {
		this.accfg = accfg;
	}

	public ICFG getcfg() throws Exception {
		return mCFG;
	}

	public void setcfg(ICFG Cfg) {
		this.mCFG = Cfg;
	}

	public int getSign() {
		return sign;
	}

	public void setSign(int sign) {
		this.sign = sign;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	public String getDelay() {
		return initial;
	}

	public void setDelay(String value) {
		this.initial = value;
	}
	public String getPort() {
		return port;
	}

	public void setPort(String value) {
		this.port = value;
	}
	public Block(String name) {
		// TODO Auto-generated constructor stub
		this.name = name;
	}

	public Block() {
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {

		return getName();

	}


	@Override
	public boolean equals(Object block) {

		if (block instanceof Block) {

			if (this.getName().equalsIgnoreCase(((Block) block).getName())) {

				return true;
			}

		}
		return false;
	}

	public void setVar() throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void setBlockNode(ICFG mergedCFG,ArrayList<Variable> vars) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	public Variable getOutVar(){
		return null;
	
	}
	
	public ICFGBasicBlockNode getBlockNode() {
		return this.BlockNode;
	}
	

}
