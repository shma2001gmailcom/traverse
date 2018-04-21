package org.misha.visitor.collection;

import org.misha.Node;
import org.misha.visitor.TraverseCollection;

import java.util.LinkedList;

/**
 * author: misha
 * date: 4/21/18
 * time: 7:15 PM
 */
public final class Queue<T> implements TraverseCollection<T> {
    private LinkedList<Node<T>> list = new LinkedList<>();

    @Override
    public void push(final Node<T> node) {
        list.addAll(node.brothers());
    }

    @Override
    public Node<T> pop() {
        final Node<T> result = list.pop();
        if (result.iterator().hasNext()) {
            push(result.iterator().next());
        }
        return result;
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }
}
