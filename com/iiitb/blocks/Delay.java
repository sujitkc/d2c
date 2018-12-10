package com.iiitb.blocks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.iiitb.cfg.Accfg;

public class Delay extends Block {

	public static final Map<Integer, String> signList = new HashMap<Integer, String>();
	private Accfg accfg;

	public Accfg getAccfg() {
		return accfg;
	}

	public void setAccfg(Accfg accfg) {
		this.accfg = accfg;
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
	
	private String input1;
	private String value;
	private String initial;
	
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
	
	public String getDelay() {
		return initial;
	}

	public void setDelay(String value) {
		this.initial = value;
	}

	public String expression() {

		return (getName()+"= " + input1 + " Delay "+ value /*" Initial Condition: "+ initial*/);
	}
	
public Delay(String input1,String name) throws Exception {
		
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
	
	public Delay(String blockName) throws Exception {
		// TODO Auto-generated constructor stub
		
		super(blockName);
		Accfg accfgObj = new Accfg();
		accfgObj.setOutput(blockName);
		setAccfg(accfgObj);
		
	}
}

	