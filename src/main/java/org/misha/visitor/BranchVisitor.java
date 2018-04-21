package org.misha.visitor;

import org.misha.Node;
import org.misha.visitor.collection.Stack;

/**
 * author: misha
 * date: 4/21/18
 * time: 7:18 PM
 */
public class BranchVisitor<T> extends Visitor<T> {

    @Override
    protected void doSomething(Node<T> node) {
    }

    @Override
    TraverseCollection<T> getTraverseCollection() {
        return new Stack<>();
    }
}
