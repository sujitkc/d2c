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

public class MergeAccfg {

	// Perform topological sort . For now this is hardcoded
	// FP is set here
	public static Accfg merge(ArrayList<Block> blockList) throws Exception {
		
		Accfg merged = null;
		List<String> fpList = new ArrayList<String>();
		
		/* Created a new list and copied all values from blockList.This is required since we remove blockList entries during iteration.
		 * We pass "blockListToPass" to  findInputOutputVariable method
		 */
		List<Block> blockListToPass = new ArrayList<Block>();
		blockListToPass.addAll(blockList);
		
		merged = new Accfg();
		
		
			//Topological sort 
		  
		
		  TopologicalSort ts = new TopologicalSort();
		  ArrayList<String> sortedList =
		  ts.sortGraph(FetchInputFromLine.adjacencyList);
		
		
		System.out.println("sorted List "+sortedList);
		System.out.println("Block List "+blockList);

		Iterator sortedIter = sortedList.iterator();
		
		String sortFp = "";

		while (sortedIter.hasNext()) {
			sortFp = (String) sortedIter.next();
			System.out.println("Test in merge1 "+sortFp);
			Iterator iter = blockList.iterator();
			while (iter.hasNext()) {
				Block block = (Block) iter.next();
				System.out.println("Test in merge2 "+block.getName());
				
				if (block.getName().equalsIgnoreCase(sortFp)) {
					fpList.addAll(block.getAccfg().getFp());
					iter.remove();
					break;
				}

			}

		}

		merged.setFp(fpList);

		// Call to set input and output
		merged = findInputOutputVariable(merged, (ArrayList<Block>)blockListToPass);

		// System.out.println(merged);
		return merged;

	}

	// Set input and output for merged ACCFG
	public static Accfg findInputOutputVariable(Accfg accfg,ArrayList<Block> blockList) throws Exception {

		List<String> input = new ArrayList<String>();
		List<String> output = new ArrayList<String>();


		Iterator blockListIter = blockList.iterator();
		while (blockListIter.hasNext()) {
			
		
			Block block = (Block) blockListIter.next();
			System.out.println("Block Name "+block.getName());
		
			input.addAll(block.getAccfg().getInput());
			System.out.println("Input "+block.getAccfg().getInput());
			
			output.add(block.getAccfg().getOutput());
			System.out.println("Output "+block.getAccfg().getOutput());
			

		}

		 System.out.println("Input Final "+input);
		 System.out.println("Output Final "+output);
		 
		 
		Iterator inputIter = input.iterator();
		String inputVar = "";
		String outputVar = "";
		while (inputIter.hasNext()) {
			inputVar = (String) inputIter.next();
			Iterator outputIter = output.iterator();

			while (outputIter.hasNext()) {

				outputVar = (String) outputIter.next();

				if (inputVar.equalsIgnoreCase(outputVar)) {
					inputIter.remove();
					outputIter.remove();
					break;
				}

				

			}

		}

		accfg.setInput(input);
		if(output.size()!=0)
		accfg.setOutput(output.get(0));

		return accfg;
	}

}
