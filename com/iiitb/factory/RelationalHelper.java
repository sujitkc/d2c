package com.iiitb.factory;
import org.w3c.dom.NodeList;

import com.iiitb.blocks.Block;
import com.iiitb.inter.IHelper;

public class RelationalHelper implements IHelper {

	@Override
	public void setAttr(NodeList attributes, int iter, Block block) {
		// TODO Auto-generated method stub
		if (attributes.item(iter).getTextContent().replaceAll("|", "")
				.contains("==")) {
			block.setSign(1);			
		}

		else if (attributes.item(iter).getTextContent().replaceAll("|", "")
				.contains("~=")) {
			block.setSign(2);
		}

		else if (attributes.item(iter).getTextContent()!="<=" && attributes.item(iter).getTextContent().replaceAll("|", "")
				.contains("<"))
		{

			block.setSign(3);
		}
		
		else if(attributes.item(iter).getTextContent().replaceAll("|", "")
				.contains("<="))
		{
			block.setSign(4);
		}
		else if(attributes.item(iter).getTextContent().replaceAll("|", "")
				.contains(">="))
		{
			block.setSign(5);
		}
		else if(attributes.item(iter).getTextContent().replaceAll("|", "")
				.contains(">"))
		{
			block.setSign(6);
		}
		else
		{
		}
		}
		}
