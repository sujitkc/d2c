package com.iiitb.factory;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.NodeList;

import com.iiitb.blocks.Block;
import com.iiitb.inter.IHelper;

public class GainHelper implements IHelper {
	@Override
	public void setAttr(NodeList attributes, int iter, Block block) {

		// Value of constant block
//		System.out.println(iter);
		block.setValue(attributes.item(5).getTextContent());
		System.out.println("Value of Gain " + attributes.item(5).getTextContent());
		// Set FP based on value
		List<String> expr = new ArrayList<String>();
		expr.add(block.expression());
		block.getAccfg().setFp(expr);

	}

}
