package com.iiitb.factory;
import org.w3c.dom.NodeList;

import com.iiitb.blocks.Block;
import com.iiitb.inter.IHelper;

public class LogicHelper implements IHelper {
	public void setAttr(NodeList attributes, int iter, Block block) {
		
		block.setValue(attributes.item(iter).getTextContent());
		System.out.println("Value  " + attributes.item(iter).getTextContent());
		// TODO Auto-generated method stub
		
		
		
		if (attributes.item(iter).getTextContent().replaceAll("|", "")
				.contains("AND")|| attributes.item(7).getTextContent().replaceAll("|", "")
				.contains("off")) {
			block.setSign(1);			
		}
		else if (attributes.item(iter).getTextContent().replaceAll("|", "")
				.contains("OR")) {
			block.setSign(2);
		}
		else if (attributes.item(iter).getTextContent().replaceAll("|", "")
				.contains("NAND")) {
			block.setSign(3);
		}
		else if (attributes.item(iter).getTextContent().replaceAll("|", "")
				.contains("NOR")) {
			block.setSign(4);
		}
		else if (attributes.item(iter).getTextContent().replaceAll("|", "")
				.contains("XOR")) {
			block.setSign(5);
		}
		else if (attributes.item(iter).getTextContent().replaceAll("|", "")
				.contains("NXOR")) {
			block.setSign(6);
		}
		else if (attributes.item(iter).getTextContent().replaceAll("|", "")
				.contains("NOT")) {
			block.setSign(7);
		}
	}
}