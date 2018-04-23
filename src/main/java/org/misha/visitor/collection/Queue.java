package org.misha.visitor.collection;

import org.misha.Node;
import org.misha.visitor.TraverseCollection;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * author: misha
 * date: 4/21/18
 * time: 7:15 PM
 * push(node) adds (as last) node with node's siblings
 * pop() removes first and makes above push for peak children
 */
public final class Queue<T> implements TraverseCollection<T> {
    private final LinkedList<Node<T>> list = new LinkedList<>();

    @Override
    public void push(final Node<T> node) {
        list.addAll(node.brothers());
    }

    @Override
    public Node<T> pop() {
        final Node<T> result = list.pop();
        final Iterator<Node<T>> iterator = result.iterator();
        if (iterator.hasNext()) {
            push(iterator.next());
        }
        return result;
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }
}
