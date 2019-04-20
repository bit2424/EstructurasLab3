package Estructures.trees;

import javax.xml.bind.ValidationEvent;

public class RB_node<Key extends  Comparable<Key>, V>{


    RB_node<Key,V> parent;
    RB_node<Key,V> right;
    RB_node<Key,V> left;
    V value;
    Key key;

    boolean colorRed;

    public RB_node(Key key, V value, boolean colorRed, RB_node<Key,V> parent) {
        this.parent = parent;
        this.colorRed = colorRed;
        this.value =  value;
        this.key = key;
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

    public boolean isColorRed() {
        return colorRed;
    }

    public void setColorRed(boolean colorRed) {
        this.colorRed = colorRed;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public Key getKey() {
        return key;
    }

    public void setKey(Key k) {
        this.key = key;
    }

    public RB_node<Key, V> sucessor(){
        if(right.getKey() == null){
            return null;
        }
        return right.getMinimum();
    }

    public RB_node<Key,V> getMinimum() {
        if(left.getKey() == null){
            return this;
        }else{
            return left.getMinimum();
        }
    }
}
