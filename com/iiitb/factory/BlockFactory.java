package com.iiitb.factory;

import org.w3c.dom.NodeList;

import com.iiitb.blocks.Abs;
import com.iiitb.blocks.Add;
import com.iiitb.blocks.Block;
import com.iiitb.blocks.Constant;
import com.iiitb.blocks.Delay;
import com.iiitb.blocks.Gain;
import com.iiitb.blocks.Logic;
import com.iiitb.blocks.MinMax;
import com.iiitb.blocks.Product;
import com.iiitb.blocks.Relational;
import com.iiitb.blocks.Sign;
import com.iiitb.blocks.Subsystem;
import com.iiitb.blocks.Sum;
import com.iiitb.cfg.Accfg;
import com.iiitb.constant.Constants;
import com.iiitb.utility.BlockFactoryUtility;

public class BlockFactory {

	/**
	 * @param blockName
	 *            - Based on blockName instance of a particular class is created
	 * @param attributes
	 *            - Passed as a parameter to utility method
	 * @return - Object with necessary attributes (e.g accfg) set
	 * @throws Exception 
	 */
	public static Block generateBlock(String blockName, NodeList attributes) throws Exception {

		Block block = null;
		if (blockName.startsWith(Constants.CONST)) {
			block = new Constant(blockName);
			BlockFactoryUtility.setBlockAttributes(Constants.VALUE, attributes,
					block);

		}

		if (blockName.startsWith(Constants.SUM)) {
			block = new Sum(blockName);

			BlockFactoryUtility.setBlockAttributes(Constants.INPUT, attributes,
					block);

		}
		
		if (blockName.startsWith(Constants.ADD)){
			block=new Add(blockName);
			
			BlockFactoryUtility.setBlockAttributes(Constants.INPUT, attributes,
					block);
		}
		
		if (blockName.startsWith(Constants.ABS)){
			block=new Abs(blockName);
			
			BlockFactoryUtility.setBlockAttributes(Constants.INPUT, attributes,
					block);
		}
		
		if (blockName.startsWith(Constants.SIGN)){
			block=new Sign(blockName);
			
			BlockFactoryUtility.setBlockAttributes(Constants.INPUT, attributes,
					block);
		}
		
		if (blockName.startsWith(Constants.PRODUCT)){
			block=new Product(blockName);
			
			BlockFactoryUtility.setBlockAttributes(Constants.INPUT, attributes,
					block);
		}
		
		if (blockName.contains(Constants.RELATIONAL)){
			block=new Relational(blockName);
			
			BlockFactoryUtility.setBlockAttributes(Constants.INPUT, attributes,
					block);
		}
		
		if (blockName.contains(Constants.DELAY)){
			block=new Delay(blockName);
			
			BlockFactoryUtility.setBlockAttributes(Constants.INPUT, attributes,
					block);
		}
		if (blockName.contains(Constants.LOGIC)){
			block=new Logic(blockName);
			
			BlockFactoryUtility.setBlockAttributes(Constants.INPUT, attributes,
					block);
		}
		if (blockName.contains(Constants.MINMAX)){
			block=new MinMax(blockName);
			
			BlockFactoryUtility.setBlockAttributes(Constants.INPUT, attributes,
					block);
		}
		if (blockName.contains(Constants.GAIN)){
			block=new Gain(blockName);
			
			BlockFactoryUtility.setBlockAttributes(Constants.INPUT, attributes,
					block);
		}
		return block;

	}

	
	/** Method caters to Subsystem block. The instance of Accfg to be passed is computed as part of
	 * underlying subsystem 
	**/
	
	public static Block generateBlock(String blockName, Accfg accfg) {
		//System.out.println("IT IS A SUBSYSTEM");
		Block block = null;
		if (blockName.startsWith(Constants.SUB_SYS)) {
			block = new Subsystem(accfg, blockName);

		}
		return block;
	}

}
