package com.iiitb.factory;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.NodeList;

import com.iiitb.blocks.Block;
import com.iiitb.inter.IHelper;

public class AbsHelper implements IHelper {
	@Override
	public void setAttr(NodeList attributes, int iter, Block block) {

		// Set FP based on value
		
		List<String> expr = new ArrayList<String>();
		expr.add(block.expression());
		block.getAccfg().setFp(expr);
//		System.out.println("ABS expr:" + expr);

	}

}
