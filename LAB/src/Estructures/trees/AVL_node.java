package Estructures.trees;

public class AVL_node<Key extends  Comparable<Key>, V> {
    RB_node<Key,V> parent;
    RB_node<Key,V> right;
    RB_node<Key,V> left;
    int height;

    public AVL_node(RB_node<Key, V> parent, int height) {
        this.parent = parent;
        this.height = height;
        right = null;
        left = null;
    }

    public RB_node<Key, V> getParent() {
        return parent;
    }

    public void setParent(RB_node<Key, V> parent) {
        this.parent = parent;
    }

    public RB_node<Key, V> getRight() {
        return right;
    }

    public void setRight(RB_node<Key, V> right) {
        this.right = right;
    }

    public RB_node<Key, V> getLeft() {
        return left;
    }

    public void setLeft(RB_node<Key, V> left) {
        this.left = left;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
