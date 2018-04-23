package org.misha.visitor.collection;

import org.misha.Node;
import org.misha.visitor.TraverseCollection;

import java.util.LinkedList;

/**
 * author: misha
 * date: 4/21/18
 * time: 7:15 PM
 * push() adds first
 * pop() removes first and adds first's children
 */
public final class Stack<T> implements TraverseCollection<T> {
    private final LinkedList<Node<T>> list = new LinkedList<>();

    @Override
    public void push(final Node<T> node) {
        list.addFirst(node);
    }

    @Override
    public Node<T> pop() {
        final Node<T> result = list.pop();
        for (final Node<T> child : result) {
            list.addFirst(child);
        }
        return result;
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }
}
