package com.iiitb.factory;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.NodeList;

import com.iiitb.blocks.Block;
import com.iiitb.inter.IHelper;

public class ConstHelper implements IHelper {
	@Override
	public void setAttr(NodeList attributes, int iter, Block block) {
		String cons;
		// Value of constant block
		if(attributes.item(5)== null)
		{
			 cons= String.valueOf(1);
			block.setValue(cons);
		}
		else {
			 cons = attributes.item(5).getTextContent();
		block.setValue(cons);
		}
		System.out.println("Value of constant " + cons);

		// Set FP based on value
		List<String> expr = new ArrayList<String>();
		expr.add(block.expression());
		block.getAccfg().setFp(expr);

	}

}
