package com.iiitb.factory;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.NodeList;

import com.iiitb.blocks.Block;
import com.iiitb.inter.IHelper;

public class DelayHelper implements IHelper {
	@Override
	public void setAttr(NodeList attributes, int iter, Block block) {

		// Value of constant block
		block.setValue(attributes.item(9).getTextContent());
		System.out.println("Value of Delay " + attributes.item(9).getTextContent());
		block.setDelay(attributes.item(11).getTextContent());
		System.out.println("Value of Delay " + attributes.item(11).getTextContent());
		// Set FP based on value
		List<String> expr = new ArrayList<String>();
		expr.add(block.expression());
		block.getAccfg().setFp(expr);

	}

}
