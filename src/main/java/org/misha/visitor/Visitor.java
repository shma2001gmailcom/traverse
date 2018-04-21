package org.misha.visitor;

import org.misha.Node;

/**
 * author: misha
 * date: 4/21/18
 * time: 7:07 PM
 */
public abstract class Visitor<T> {

    public void visit(Node<T> node) {
        final TraverseCollection<T> collection = getTraverseCollection();
        collection.push(node);
        while (!collection.isEmpty()) {
            doSomething(collection.pop());
        }
    }

    abstract void doSomething(Node<T> node);

    abstract TraverseCollection<T> getTraverseCollection();
}
