package com.iiitb.utility;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.iiitb.blocks.Block;
import com.iiitb.cfg.Accfg;
import com.iiitb.sort.TopologicalSort;

import com.sym.cfg.ICFEdge;
import com.sym.cfg.ICFG;
import com.sym.cfg.ICFGBasicBlockNode;
import com.sym.expression.Variable;
import com.sym.mycfg.CFEdge;
import com.sym.mycfg.CFG;
import com.sym.mycfg.CFGBasicBlockNode;
import com.sym.statement.Statement;

public class MergeCFG {

	// Perform topological sort . For now this is hardcoded
	public static ICFG merge(ArrayList<Block> blockList) throws Exception {
		
		List<Block> blockListToPass = new ArrayList<Block>();
		blockListToPass.addAll(blockList);
		
		
			//Topological sort 
		  
		
		  TopologicalSort ts = new TopologicalSort();
		  ArrayList<String> sortedList =
		  ts.sortGraph(FetchInputFromLine.adjacencyList);
		
		
		System.out.println("sorted List "+sortedList);
		System.out.println("Block List "+blockList);
		
		//Create a Merged CFG having a begin and a while
		ICFG mergedCFG = null;
		ICFGBasicBlockNode Begin = new CFGBasicBlockNode("Begin",null);
		ICFGBasicBlockNode While = new CFGBasicBlockNode("While",null);
		mergedCFG = new CFG(Begin, While);
		
		//If any initialization statements such as declaring constants in this case
		ICFGBasicBlockNode Init = new CFGBasicBlockNode("Init",null);

		List<Block> FinishedBlocks = new ArrayList<Block>();

		//This loop gets the constants and will be added to Init block 
		Iterator iter = blockList.iterator();
		while (iter.hasNext()) {
			Block block = (Block) iter.next();
			System.out.println("Test in merge2 "+block.getName());
				
				// Creating the Initialization block with all the constant declaration
			if(block.getName().contains("Constant")) {
				Variable x = new Variable(block.getName(),mergedCFG);
				Variable y = new Variable(block.getValue(),mergedCFG);
				Statement stmt = new Statement(mergedCFG,x,y);
				Init.addStatement(stmt);
				block.setBlockNode(mergedCFG,null);
				FinishedBlocks.add(block);
			}

		}

		
		Iterator BlockIter = blockListToPass.iterator();
		
//		System.out.println("INIT is  :" + FinishedBlocks);
		
		//Iterating through the blockslist and looking at the input for the block we assign the variables 
		
		while(BlockIter.hasNext()) {
			Block block1 = (Block) BlockIter.next();
			Iterator FinIter = FinishedBlocks.iterator();
			ArrayList<Variable> vars = new ArrayList<Variable>();
			int count=0;
			while(FinIter.hasNext()) {
				Block block2 = (Block) FinIter.next(); 
				if(block1.getAccfg().getInput().contains(block2.getName())) {
					System.out.println("Matched name for block : " + block1.getName()+" is "+ block2.getName());
					// get the output variables of the input block and add it to variables list
					vars.add(block2.getOutVar());
					count++;
				}
				if(count==2 || ((block1.getName().contains("Abs") || block1.getName().contains("Sign")) && count==1)) {
					// set our Block Node with these variables and assign it to the block
					System.out.println("  And its variables are : " + vars);
					block1.setBlockNode(mergedCFG,vars);
					FinishedBlocks.add(block1);
					break;
				}
			}
			
			
		}
		

		System.out.println("INIT is  :" + FinishedBlocks);
		
		Iterator FinIter = FinishedBlocks.iterator();
		
		ICFGBasicBlockNode prev = Begin;
		ICFGBasicBlockNode next = Init;
		
		//Iterating through the Finished Blocks apart from the constants because they were declared in th INIT block itself
		//The remaining blocks are linked in order of the finished blocks and added to merged CFG 
		
		while(FinIter.hasNext()) {
			Block block = (Block) FinIter.next(); 
			if(!block.getName().contains("Constant")) {
				
				mergedCFG.addBasicBlockNode(next);
				ICFEdge edge = new CFEdge( prev.getId() + " ---> " + next.getId(), mergedCFG,prev,next);
				mergedCFG.addEdge(edge);
				prev = next;
				next = block.getBlockNode();
		
			}
			if(!FinIter.hasNext()) {
				
				mergedCFG.addBasicBlockNode(next);
				ICFEdge edge1 = new CFEdge( prev.getId() + " ---> " + next.getId(), mergedCFG,prev,next);
				mergedCFG.addEdge(edge1);
				
				ICFEdge edge2 = new CFEdge( next.getId() + " ---> While", mergedCFG,next,While);
				mergedCFG.addEdge(edge2);
				
			}
			
		}
		
		//Finally the looping edge is added
		
		//Looping Edge
		ICFEdge WhileInit = new CFEdge("While ---> Init", mergedCFG, While, Init);
		mergedCFG.addEdge(WhileInit);
		
				
		// System.out.println("THE FINAL MERGED CFG IS : "+ mergedCFG);		
		return mergedCFG;

	}

}
