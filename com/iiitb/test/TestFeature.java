package com.iiitb.test;

import org.w3c.dom.Document;

import com.iiitb.cfg.Accfg;
import com.iiitb.utility.MergeCFG;
import com.iiitb.utility.ParseXML;

import com.sym.cfg.ICFG;

public class TestFeature {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Document doc = ParseXML
					.initializeDocument("C:\\Users\\Rohith Yogi\\Desktop\\IMT2016072\\xmlfiles\\Working_Blocks_test.xml");

			/*
			 * 
			 * The currSubSystemNode represents the current subsystem node. For
			 * the first call since we are at the top most level we pass null to
			 * currSubSystemNode
			 */
			
			Accfg mergedAccfg = ParseXML.parseDocument(doc, doc.getDocumentElement());

			System.out.println("\n\n The final merged ACCFG is : \n\n"+mergedAccfg);
			
			ICFG FinalCFG = ParseXML.MergedCFG;
			
			System.out.println("\n\n The final merged CFG is : \n\n"+FinalCFG);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
