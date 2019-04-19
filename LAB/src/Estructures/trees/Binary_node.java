package Estructures.trees;

public class Binary_node<V  extends Comparable<V>> {
	V value;
	Binary_node<V> right;
	Binary_node<V> left;
	Binary_node<V> parent;
	
	public Binary_node(Binary_node<V> parent,V value) {
		right = null;
		left = null;
		this.value = value;
		this.parent = parent;
	}

	public V getValue() {
		return value;
	}

	public void setValue(V value) {
		this.value = value;
	}

	public Binary_node<V> getRight() {
		return right;
	}

	public void setRight(Binary_node<V> right) {
		this.right = right;
	}

	public Binary_node<V> getLeft() {
		return left;
	}

	public void setLeft(Binary_node<V> left) {
		this.left = left;
	}

	public Binary_node<V> getParent() {
		return parent;
	}

	public void setParent(Binary_node<V> parent) {
		this.parent = parent;
	}
	
	public Binary_node<V> sucesor(){
		
		return this.getRight().getRbottom();
	}

	private Binary_node<V> getRbottom() {
		Binary_node<V> ref = this;
		while(ref.left != null){
			ref = ref.getLeft();
		}

		return  ref;
	}
}
