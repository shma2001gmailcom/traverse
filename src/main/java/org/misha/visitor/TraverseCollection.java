package org.misha.visitor;

import org.misha.Node;

/**
 * author: misha
 * date: 4/21/18
 * time: 6:57 PM
 */
public interface TraverseCollection<T> {

    void push(Node<T> node);

    Node<T> pop();

    boolean isEmpty();
}
