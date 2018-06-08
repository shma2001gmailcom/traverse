package org.misha.visitor;

import org.misha.Node;
import org.misha.visitor.collection.Queue;

/**
 * author: misha
 * date: 4/21/18
 * time: 7:09 PM
 * walk by tree on per-layer (set of vertex of fixed depth) mode
 */
public class LayerVisitor<T> extends Visitor<T> {
    
    public LayerVisitor() {
        super(new Queue<>());
    }
    
    @Override
    protected void doSomething(Node<T> node) {
        //to override
    }
}
