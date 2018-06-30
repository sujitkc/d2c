package com.iiitb.utility;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;

import com.iiitb.blocks.Abs;
import com.iiitb.blocks.Add;
import com.iiitb.blocks.Block;
import com.iiitb.blocks.Constant;
import com.iiitb.blocks.Product;
import com.iiitb.blocks.Sign;
import com.iiitb.blocks.Sum;
import com.iiitb.constant.Constants;
import com.iiitb.factory.AbsHelper;
import com.iiitb.factory.AddHelper;
import com.iiitb.factory.ConstHelper;
import com.iiitb.factory.DelayHelper;
import com.iiitb.factory.GainHelper;
import com.iiitb.factory.LogicHelper;
import com.iiitb.factory.MinMaxHelper;
import com.iiitb.factory.ProductHelper;
import com.iiitb.factory.RelationalHelper;
import com.iiitb.factory.SumHelper;
import com.iiitb.inter.IHelper;

public class BlockFactoryUtility {

	public static void setBlockAttributes(String attrToFetch,
			NodeList attributes, Block block) {
		IHelper helper = null;
		for (int iter = 0; iter < attributes.getLength(); iter++) {
			// Prevent #text tag
			if (attributes.item(iter).getNodeName() == Constants.PARA_TAG)

			{

				NamedNodeMap temp = attributes.item(iter).getAttributes();

				for (int tempIter = 0; tempIter < temp.getLength(); tempIter++) {
//
//					if (temp.item(tempIter).getNodeValue()
//							.equalsIgnoreCase(attrToFetch)) {

						if (block.getName().contains("Constant")) {
							helper = new ConstHelper();
							helper.setAttr(attributes,iter, block);
						}

						if (block.getName().contains("Sum")) {
							helper = new SumHelper();
							helper.setAttr(attributes, iter, block);
						}
						
						if (block.getName().contains("Add")) {
							helper = new AddHelper();
							helper.setAttr(attributes, iter, block);
						}
						
						if (block.getName().contains("Abs")) {
							helper = new AbsHelper();
							helper.setAttr(attributes, iter, block);
						}
						
						if (block.getName().contains("Sign")) {
							helper = new AbsHelper();
							helper.setAttr(attributes, iter, block);
						}
						
						if (block.getName().contains("Product")) {
							helper = new ProductHelper();
							helper.setAttr(attributes, iter, block);
						}
						if (block.getName().contains("Relational")) {
							helper = new RelationalHelper();
							helper.setAttr(attributes, iter, block);
						}
						if (block.getName().contains("Delay")) {
							helper = new DelayHelper();
							helper.setAttr(attributes, iter, block);
						}
						if (block.getName().contains("Logic")) {
							helper = new LogicHelper();
							helper.setAttr(attributes, iter, block);
						}
						if (block.getName().contains("MinMax")) {
							helper = new MinMaxHelper();
							helper.setAttr(attributes, iter, block);
						}
						if (block.getName().contains("Gain")) {
							helper = new GainHelper();
							helper.setAttr(attributes, iter, block);
						}
					}

//				}

			}

		}

	}

}
