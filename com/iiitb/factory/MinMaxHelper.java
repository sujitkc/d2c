package com.iiitb.factory;
import org.w3c.dom.NodeList;

import com.iiitb.blocks.Block;
import com.iiitb.inter.IHelper;

public class MinMaxHelper implements IHelper {
	public void setAttr(NodeList attributes, int iter, Block block) {
		
		block.setValue(attributes.item(iter).getTextContent());
		System.out.println("Value  " + attributes.item(iter).getTextContent());
		// TODO Auto-generated method stub
		
		
		
		if (attributes.item(7).getTextContent().replaceAll("|", "")
				.contains("min") || attributes.item(9).getTextContent().replaceAll("|", "").contains("off")) {
			block.setSign(1);			
		}
		else if (attributes.item(iter).getTextContent().replaceAll("|", "")
				.contains("max")) {
			block.setSign(2);
		}
		
		
		if (attributes.item(13)!=null) {
			block.setPort(attributes.item(9).getTextContent());			
		}
		else {
			block.setPort(attributes.item(7).getTextContent());	
		}
	}
}