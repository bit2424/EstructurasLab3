package Estructures.trees;


public class Binary_tree<V  extends Comparable<V>> implements MyBinaryTree<V> {
	Binary_node<V> root;

	public Binary_tree(Binary_node<V> root) {
		this.root = root;
	}

	public void add(V value){
		add(value,root);
	}

	@Override
	public void add(V value, Binary_node<V> ref) {
		
		if(root == null) {
			root = new Binary_node<V>(null, value);
		}else {
			
		if(ref.getValue().compareTo(value) <= 0) {
			if(ref.getLeft() == null) {
				ref.setLeft(new Binary_node<V>(ref, value));
			}else {
				add(value , ref.getLeft());
			}
				
		}else {
			if(ref.getRight() == null) {
				ref.setRight(new Binary_node<V>(ref, value));
			}else {
				add(value , ref.getRight());
			}
		}
			
		}
		
	}

	public void delete(V elem){
		delete(elem, root);
	}


	@Override
	public void delete(V elem,Binary_node<V> ref) {
		Binary_node<V> f_delete = search(elem, ref);
		if(f_delete != null){
			if(f_delete.getLeft() == null && f_delete.getRight() == null ) {  // Case 1: No childs
				if(f_delete.getParent() != null) {	
					if(f_delete.getParent().getLeft() != null && f_delete.getParent().getLeft().equals(f_delete)) {
						f_delete.getParent().setLeft(null);
					}else {
						f_delete.getParent().setRight(null);
					}
				}else {
					root = null;
				}
			}else if(f_delete.getLeft() == null || f_delete.getRight() == null ) {  // Case 2: 1 child
				
				Binary_node<V> replace = (f_delete.getLeft() == null)? f_delete.getRight():f_delete.getLeft();
				
				if(f_delete.getParent() != null) {
					if(f_delete.getParent().getLeft() != null && f_delete.getParent().getLeft().equals(f_delete)) {
						f_delete.getParent().setLeft(replace);
					}else {
						f_delete.getParent().setRight(replace);
					}
				}else {
					root = replace;
				}
				
			}else { // Case 3: 2 childs
				Binary_node<V> replace = f_delete.sucesor();
				V aux = f_delete.getValue();
				f_delete.setValue(replace.getValue());
				replace.setValue(aux);
				delete(aux, ref);
			}
			
		}
	}

	public Binary_node<V> search(V value){
		return search(value,root);
	}

	@Override
	public Binary_node<V> search(V value,Binary_node<V> ref) {
		if(ref != null) {
			if(ref.getValue().equals(value)) {
				return ref;
			}else if(ref.getValue().compareTo(value) > 0) {
				return search(value,ref.getRight());
			}else if(ref.getValue().compareTo(value) < 0){
				return search(value,ref.getLeft());
			}
		}
		return null;
	}

	@Override
	public V[] inOrder() {
		// TODO Auto-generated method stub
		return null;
	}
	
	                                       
	
	
	
}
