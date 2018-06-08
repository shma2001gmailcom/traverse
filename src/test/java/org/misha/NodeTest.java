package org.misha;

import org.junit.Test;
import org.misha.visitor.BranchVisitor;
import org.misha.visitor.LayerVisitor;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * author: misha
 * date: 4/21/18
 * time: 12:47 PM
 */
public class NodeTest {
    private static final String NODE_0 = "node0";
    private static final String NODE_1 = "node1";
    private Node<String> root;
    private Node<String> node0;
    private Node<String> node1;
    private Node<String> node00;
    private Node<String> node10;

    public NodeTest() {
        root = new Node<>();
        root.setData("root");
        node0 = new Node<>();
        node1 = new Node<>();
        node00 = new Node<>();
        Node<String> node01 = new Node<>();
        node10 = new Node<>();
        Node<String> node11 = new Node<>();
        Node<String> node12 = new Node<>();
        node0.setData(NODE_0);
        node1.setData(NODE_1);
        node00.setData("node00");
        node01.setData("node01");
        node10.setData("node10");
        node11.setData("node11");
        node12.setData("node12");
        root.attach(node0, node1);
        node0.attach(node00, node01);
        node1.attach(node10, node11, node12);
    }

    @Test
    public void iterator() {
        final Set<String> childData = new HashSet<>();
        for (final Node<String> node : root) {
            childData.add(node.getData());
        }
        assertTrue(childData.contains(NODE_0) && childData.contains(NODE_1));
    }

    @Test
    public void getData() {
        assertEquals("root", root.getData());
    }

    @Test
    public void attach() {
        assertTrue(root.hasChild(node0) && root.hasChild(node1));
    }

    @Test
    public void getParent() {
        assertTrue(node0.getParent() == root && node1.getParent() == root && root.getParent() == null);
    }

    @Test
    public void isLeaf() {
        assertTrue(node00.isLeaf() && node10.isLeaf() && !root.isLeaf());
    }

    @Test
    public void isRoot() {
        assertTrue(root.isRoot() && !node0.isRoot());
    }

    @Test
    public void depth() {
        assertTrue(root.depth() == 0 && node0.depth() == 1 && node1.depth() == 1);
    }

    @Test
    public void nodesByCondition() {
        assertTrue(root.childrenByCondition(node -> node.getData().equals(NODE_0)).contains(node0));
        assertFalse(root.childrenByCondition(node -> node.getData().equals(NODE_1)).contains(node0));
        assertTrue(root.childrenByCondition(node -> node.depth() > 1).isEmpty());
    }

    @Test
    public void traverseByLayers() {
        class LayerTraveler extends LayerVisitor<String> {
            private final Set<Node<String>> selected = new HashSet<>();

            @Override
            protected void doSomething(final Node<String> node) {
                selected.addAll(node.childrenByCondition(n -> n.depth() > 1 && n.getData().endsWith("0")));
            }
        }
        final LayerTraveler layerTraveler = new LayerTraveler();
        layerTraveler.visit(root);
        final Set<Node<String>> filteredByBranch = layerTraveler.selected;
        assertTrue(filteredByBranch.size() == 2 && filteredByBranch.contains(node00));
    }

    @Test
    public void traverseByBranches() {
        class BranchTraveler extends BranchVisitor<String> {
            private final Set<Node<String>> selected = new HashSet<>();

            @Override
            protected void doSomething(final Node<String> node) {
                selected.addAll(node.childrenByCondition(n -> n.depth() > 1 && n.getData().endsWith("0")));
            }
        }
        final BranchTraveler branchTraveler = new BranchTraveler();
        branchTraveler.visit(root);
        final Set<Node<String>> filteredByLayer = branchTraveler.selected;
        assertTrue(filteredByLayer.size() == 2 && filteredByLayer.contains(node10));
    }
}