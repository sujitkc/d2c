package com.iiitb.blocks;

import java.util.ArrayList;

import com.iiitb.cfg.Accfg;

public class Subsystem extends Block {

	// Purposefully not overridden
	@Override
	public String expression() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	// Purposefully not overridden
	public ArrayList<String> getInput() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	// Purposefully not overridden
	public void setInput(String input) {
		// TODO Auto-generated method stub

	}

	public Subsystem(Accfg accfg, String name) {
		super(name);

		/*
		 * For eg : If output of inner subsystem is 'sum' and name of the
		 * current subsystem is 'subsystem' then add 'subsystem = sum' and then
		 * proceed with evaluation of current subsystem
		 */
		Accfg accfgLocal = new Accfg();
		accfgLocal.setFp(accfg.getFp());
		accfgLocal.getFp().add(name + "=" + accfg.getOutput());
		// There wont be an input to subsystem block
		accfgLocal.setInput(new ArrayList<String>());

		// Set output as block name
		accfgLocal.setOutput(getName());
		
		setAccfg(accfgLocal);
	}

}
