package com.iiitb.utility;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.iiitb.blocks.Block;
import com.iiitb.cfg.Accfg;
import com.iiitb.constant.Constants;
import com.iiitb.factory.BlockFactory;
import com.sym.cfg.ICFG;


public class ParseXML {

	public static int countSubSystem = 0;

	public static Map<String, Accfg> subSystemMap = new HashMap<String, Accfg>();
	
	public static ICFG MergedCFG = null; 

	// Method returns instance of xml document that can be further parsed
	public static Document initializeDocument(String filePath) {
		Document doc = null;

		try {

			// Parse XML

			File fXmlFile = new File(filePath);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

			doc = dBuilder.parse(fXmlFile);
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("SAXException in "
					+ ParseXML.class.getName());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("IOException in "
					+ ParseXML.class.getName());
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("ParserConfigurationException in "
					+ ParseXML.class.getName());
		}

		return doc;
	}

	// Parse based on Document object. Can be called recursively based on
	// subsystem
	public static Accfg parseDocument(Document doc, Node currSubSystemNode) {

		List<Block> blockList = new ArrayList<Block>();

		try {

			// Parse XML

			// NodeList blockChildNodesOfSystemNode = null;
			// NodeList lineChildNodesOfSystemNode = null;

			ArrayList<Node> blockChildNodesOfSystemNodeList = new ArrayList<Node>();
			ArrayList<Node> lineChildNodesOfSystemNodeList = new ArrayList<Node>();

			NodeList tempForProcessing = null;

			if (currSubSystemNode.getNodeName().equalsIgnoreCase(
					Constants.MODEL_INFO)) {
				tempForProcessing = currSubSystemNode.getChildNodes();
				for (int tempForProcessingIter = 0; tempForProcessingIter < tempForProcessing
						.getLength(); tempForProcessingIter++) {
					if (tempForProcessing.item(tempForProcessingIter)
							.getNodeName().equals(Constants.MODEL)) {

						tempForProcessing = tempForProcessing.item(
								tempForProcessingIter).getChildNodes();
						break;

					}

				}

			}

			else
				tempForProcessing = currSubSystemNode.getChildNodes();

			NodeList tempSubsystemSystemChildren = null;
			/*
			 * System.out.println(currSubSystemNode.getNodeName());
			 * System.out.println(tempForProcessing.item(0));
			 * System.out.println(tempForProcessing.item(1));
			 * System.out.println(tempForProcessing.item(2));
			 */

			for (int tempForProcessingIter = 0; tempForProcessingIter < tempForProcessing
					.getLength(); tempForProcessingIter++) {

				if (tempForProcessing.item(tempForProcessingIter).getNodeName()
						.equals(Constants.SYSTEM)) {
					tempSubsystemSystemChildren = tempForProcessing.item(
							tempForProcessingIter).getChildNodes();
					//System.out.println("Block INFO: " + tempForProcessing.item(tempForProcessingIter).getAttributes());
					System.out.println("Length "
							+ tempSubsystemSystemChildren.getLength());
				}

				else
					continue;
			}

			for (int tempForProcessingIter = 0; tempForProcessingIter < tempSubsystemSystemChildren
					.getLength(); tempForProcessingIter++) {
				
				//System.out.println("Block INFO: " + tempSubsystemSystemChildren.item(tempForProcessingIter) );

				if (tempSubsystemSystemChildren.item(tempForProcessingIter)
						.getNodeName().equalsIgnoreCase(Constants.BLOCK)) {

					blockChildNodesOfSystemNodeList
							.add(tempSubsystemSystemChildren
									.item(tempForProcessingIter));

				}

				if (tempSubsystemSystemChildren.item(tempForProcessingIter)
						.getNodeName().equalsIgnoreCase(Constants.LINE)) {

					lineChildNodesOfSystemNodeList
							.add(tempSubsystemSystemChildren
									.item(tempForProcessingIter));

				}
	

			}

			System.out
					.println("Block Child " + blockChildNodesOfSystemNodeList);
			System.out.println("Line Child " + lineChildNodesOfSystemNodeList);

			for (int nodeIter = 0; nodeIter < blockChildNodesOfSystemNodeList
					.size(); nodeIter++) {

				NamedNodeMap temp = blockChildNodesOfSystemNodeList.get(
						nodeIter).getAttributes();
				String blockName = "";
				Node Btype = null;
				Node blockType = null;
				for (int tempIter = 0; tempIter < temp.getLength(); tempIter++) {
					if (temp.item(tempIter).getNodeName()
							.equalsIgnoreCase(Constants.NAME)) {
						blockType = blockChildNodesOfSystemNodeList
								.get(nodeIter);
						// block type will be "block"
						// block name will be (for e.g) "constant"
						blockName = temp.item(tempIter).getNodeValue();
						//Btype = temp.item(tempIter);
						System.out.println("Block Name is : " + blockName);
						//System.out.println("Block Type is : " + Btype);
					}
				}

				if (blockName != "" && blockType != null) {

					if (blockName.equalsIgnoreCase("Atomic Subsystem")) {
						System.out.println("Going Through");

						countSubSystem++;

						Accfg accfg = parseDocument(doc, blockType);
						Block block = BlockFactory.generateBlock(blockName,
								accfg);
						if (block != null)
							blockList.add(block);
						System.out.println(accfg);

					}

					else {
						NodeList attr = blockType.getChildNodes();

						// Simulink blocks to java library blocks

						Block block = BlockFactory.generateBlock(blockName,
								attr);
						if (block != null)
							blockList.add(block);
					}
				}

			}

			// Setting input to block object's ACCFG based on line tags

			for (int nodeIter = 0; nodeIter < lineChildNodesOfSystemNodeList
					.size(); nodeIter++) {

//				System.out.println("Node Iter Value " + nodeIter);

				// test can be used for any testing purpose

				Block test = FetchInputFromLine.parseLine(
						(ArrayList<Block>) blockList,
						lineChildNodesOfSystemNodeList.get(nodeIter)
								.getChildNodes());

			}

			// FetchInputFromLine.adjacencyList.clear();

			// BlockList has all blocks with ACCFG set
			// Merge based on topological sort and Display
			
			MergedCFG = MergeCFG.merge((ArrayList<Block>) blockList);
			
			Accfg retAccfg = MergeAccfg.merge((ArrayList<Block>) blockList);
			
			

			/*
			 * Since we call parseDocument method in a recursive fashion for
			 * each subsystem , we clear the "adjacencyList" List. The
			 * "adjacencyList" List corresponds to individual subsystem
			 */
			FetchInputFromLine.adjacencyList.clear();

			subSystemMap.put("SubSystem_" + String.valueOf(countSubSystem),
					retAccfg);
			return retAccfg;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			// throw new RuntimeException("Generic Exception in "
			// + ParseXML.class.getName());
			e.printStackTrace();
		}
		return null;

	}
}
