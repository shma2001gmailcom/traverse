package org.misha.visitor;

import org.misha.Node;
import org.misha.visitor.collection.Queue;

/**
 * author: misha
 * date: 4/21/18
 * time: 7:09 PM
 */
public class LayerVisitor<T> extends Visitor<T> {

    @Override
    protected void doSomething(Node<T> node) {
    }

    @Override
    TraverseCollection<T> getTraverseCollection() {
        return new Queue<>();
    }
}
