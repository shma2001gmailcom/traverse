package org.misha.visitor;

import org.misha.Node;
import org.misha.visitor.collection.Stack;

/**
 * author: misha
 * date: 4/21/18
 * time: 7:18 PM
 * walk by tree on per-branch basis
 */
public class BranchVisitor<T> extends Visitor<T> {
    
    protected BranchVisitor() {
        super(new Stack<>());
    }
    
    @Override
    protected void doSomething(Node<T> node) {
        //to override
    }
}
