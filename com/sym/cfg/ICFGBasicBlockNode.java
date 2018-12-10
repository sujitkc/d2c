package com.sym.cfg;

import com.sym.program.IBasicBlockNode;

public interface ICFGBasicBlockNode extends IBasicBlockNode, ICFGNode {
	public ICFEdge getOutgoingEdge();
	public ICFGNode getSuccessorNode();
	public ICFEdge setOutgoingEdge(ICFEdge edge);
	public ICFEdge deleteOutgoingEdge();
}
