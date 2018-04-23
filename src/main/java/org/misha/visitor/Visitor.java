package org.misha.visitor;

import org.misha.Node;

/**
 * author: misha
 * date: 4/21/18
 * time: 7:07 PM
 */
public abstract class Visitor<T> {
    private final TraverseCollection<T> collection;
    
    Visitor(final TraverseCollection<T> collection) {
        this.collection = collection;
    }
    
    public void visit(Node<T> node) {
        collection.push(node);
        while (!collection.isEmpty()) {
            doSomething(collection.pop());
        }
    }

    abstract void doSomething(Node<T> node);
}
