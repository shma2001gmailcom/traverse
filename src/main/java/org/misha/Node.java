package org.misha;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.Predicate;

import static java.util.Collections.singletonList;

/**
 * author: misha
 * date: 4/21/18
 * time: 11:48 AM
 * a tree
 */
public final class Node<T> implements Iterable<Node<T>> {
    private final List<Node<T>> children;
    private final ReadWriteLock lock;
    private final AtomicInteger depth;
    private T data;
    private Node<T> parent;
    private volatile boolean isAttached;

    Node() {
        depth = new AtomicInteger(0);
        isAttached = false;
        children = new ArrayList<>();
        lock = new ReentrantReadWriteLock();
    }

    @Override
    public Iterator<Node<T>> iterator() {
        return children.iterator();
    }

    public List<Node<T>> brothers() {
        return parent != null ? parent.children : singletonList(this);
    }

    T getData() {
        try {
            lock.readLock().lock();
            return data;
        } finally {
            lock.readLock().unlock();
        }
    }

    void setData(final T t) {
        try {
            lock.writeLock().lock();
            data = t;
        } finally {
            lock.writeLock().unlock();
        }
    }

    private void makeChild(final Node<T> node) {
        children.add(node);
        node.parent = this;
        node.depth.set(1 + this.depth.get());
        node.isAttached = true;
    }

    @SafeVarargs
    final void attach(final Node<T>... nodes) {
        for (final Node<T> node : nodes) {
            node.checkNotAttached();
        }
        try {
            lock.writeLock().lock();
            for (final Node<T> node : nodes) {
                makeChild(node);
            }
        } finally {
            lock.writeLock().unlock();
        }
    }

    private void checkNotAttached() {
        if (isAttached) {
            throw new IllegalStateException("already has a parent" + this);
        }
    }

    Node<T> getParent() {
        return parent;
    }

    boolean isLeaf() {
        return children.isEmpty();
    }

    boolean isRoot() {
        return parent == null;
    }

    boolean hasChild(final Node<T> node) {
        return children.contains(node);
    }

    Set<Node<T>> childrenByCondition(final Predicate<Node<T>> predicate) {
        final Set<Node<T>> result = new HashSet<>();
        for (final Node<T> node : this) {
            if (predicate.test(node)) {
                result.add(node);
            }
        }
        return result;
    }

    int depth() {
        return depth.get();
    }

    @Override
    public String toString() {
        return data == null ? super.toString() : data.toString();
    }
}
