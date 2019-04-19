package Estructures;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;

public class N_treeExample {

	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int dataLevel = 2;
		
		
	}

}


class N_tree<V>{
	N_tree<V> root;
	LinkedList<N_tree<V>> child;
	V value;
	
	public N_tree(N_tree<V> root) {
		this.root = root;
		child = new LinkedList<>();
	}
	
	public String inorder() {
		String out = "";
		if(!child.isEmpty()) {
			out += " " + String.valueOf(child.getFirst().value);
			out += " " + String.valueOf(root.value);
			boolean noGo = false;
			for (Iterator iterator = child.iterator(); iterator.hasNext();) {
				N_tree<V> n_tree = (N_tree<V>) iterator.next();
				if(noGo) {
					noGo = true;
				}else {
					out += n_tree.inorder();
				}
			}
			
		}else {
			out += " " + String.valueOf(root.value);
		}
		return out;
	}
}
