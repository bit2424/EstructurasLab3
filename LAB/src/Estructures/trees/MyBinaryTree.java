package Estructures.trees;

import Estructures.trees.Binary_node;

public interface MyBinaryTree<V extends Comparable<V>> {
	
	public void add(V value, Binary_node<V> ref);
	public void delete(V value, Binary_node<V> ref);
	public Binary_node<V> search(V value, Binary_node<V> ref);
	public V[] inOrder();
}
