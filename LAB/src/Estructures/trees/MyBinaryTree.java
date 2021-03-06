package Estructures.trees;

import Estructures.trees.NodeBinaryTree;

import java.util.ArrayList;

public interface MyBinaryTree<K extends Comparable<K>, V> {

	public void insert(K key, V value);

	public V delete(K key);

	public V delete (K key, V value);

	public V search(K key);

	public boolean isInTree(K key);

	public K getMin();

	public K getMax();

	public K getPredecessor(K key);

	public K getSuccessor(K key);

	public ArrayList<V> searchEqualTo(K key);

	public ArrayList<V> searchLowerOrEqualTo(K key);

	public ArrayList<V> searchLowerTo(K key);

	public ArrayList<V> searchBiggerOrEqualThan(K key);

	public ArrayList<V> searchBiggerThan(K key);
}
