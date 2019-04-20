package Estructures;

import Estructures.linear_Structures.HashMap;
import Estructures.linear_Structures.Queue;
import Estructures.linear_Structures.Stack;
import Estructures.trees.Binary_node;
import Estructures.trees.Binary_tree;
import Estructures.trees.RB_Tree;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Pruebas_Estructuras {

	@Test
	void testQueue1() {
		Queue<Integer> Q = new Queue<>();
		Q.offer(3);
		Q.offer(4);

		Q.offer(5);
		Q.offer(77);
		if(Q.poll() == 3) {
			assertTrue(true);
		}else {
			fail("Not yet implemented");
		}
		if(Q.poll() == 4) {
			assertTrue(true);
		}else {
			fail("Not yet implemented");
		}
		if(Q.poll() == 5) {
			assertTrue(true);
		}else {
			fail("Not yet implemented");
		}
		if(Q.poll() == 77) {
			assertTrue(true);
		}else {
			fail("Not yet implemented");
		}
		if(Q.poll() == null) {
			assertTrue(true);
		}else {
			fail("LOL");
		}
	}
	
	@Test
	void testStack1() {
		Stack<Integer> S = new Stack<>();
		S.push(3);
		S.push(5);
		S.push(7);
		int a = S.pop();
		System.out.println(a + "S");
		if(a == 7) {
			assertTrue(true);
		}else {
			fail("Therr is no honor");
		}
		
		a = S.pop();
		
		if(a == 5) {
			assertTrue(true);
		}else {
			fail("Therr is no honor");
		}
		S.push(7);
		a = S.pop();
		
		if(a == 7) {
			assertTrue(true);
		}else {
			fail("Therr is no honor");
		}
	}
	
	@Test
	void TestHashMap1() {
		HashMap<Integer,String> HM = new HashMap<>();
		HM.put(2, "LOL");
		HM.put(4, "LOL2");
		HM.put(5, "LOL4");
		System.out.println(HM.get(2));
		if(HM.get(2) == "LOL") {
			assertTrue(true);
		}else {
			fail("Me la jugue");
		}
		if(HM.get(5) == "LOL4") {
			assertTrue(true);
		}else {
			fail("Me la jugue");
		}
	}
	
	Binary_tree<Integer> tree_t;
	
	void BSTscene() {
		tree_t = new Binary_tree<>(new Binary_node<Integer>(null, 5));
	}
	
	@Test
	void BST_Test() {
		BSTscene();
		tree_t.add(5);
		tree_t.add(15);
		tree_t.add(3);
		tree_t.search(3);
		tree_t.delete(3);
		tree_t.delete(15);
		tree_t.delete(5);
		tree_t.delete(5);
		tree_t.delete(5);
		tree_t.add(65);
		
		for (int i = 0; i < 500; i++) {
			tree_t.add((int)(Math.random()*1600));
		}
		
		for (int i = 0; i < 500; i++) {
			tree_t.delete((int)(Math.random()*1600));
		}
		
	}

	RB_Tree<Integer,Integer> RB_tree_t;
	private void RB_tree_scene() {
		RB_tree_t = new RB_Tree<>();
	}

	@Test
	void RB_tree_Test() {
		RB_tree_scene();
		RB_tree_t.add(1,5);
		RB_tree_t.add(1,15);
		RB_tree_t.add(1,3);
		RB_tree_t.add(1,6);
		RB_tree_t.add(1,7);
		RB_tree_t.add(1,8);
		RB_tree_t.add(1,9);
        RB_tree_t.add(1,10);
        RB_tree_t.add(1,11);
        RB_tree_t.add(2,11);
        RB_tree_t.add(3,11);
        RB_tree_t.add(1,12);
		RB_tree_t.search(3);
		RB_tree_t.delete(3);
		RB_tree_t.delete(15);
		RB_tree_t.delete(5);
		RB_tree_t.delete(5);
        RB_tree_t.delete(9);
        RB_tree_t.delete(7);
        RB_tree_t.delete(8);
        RB_tree_t.delete(10);
        RB_tree_t.delete(11);
        RB_tree_t.delete(12);
        RB_tree_t.delete(13);

		RB_tree_t.add(1,65);

		for (int i = 0; i < 2000; i++) {

            System.out.println(i+ "Mierda");
			RB_tree_t.add(1,(int)(Math.random()*1600));
		}

		for (int i = 0; i < 2000; i++) {
		    System.out.println(i);
			RB_tree_t.delete((int)(Math.random()*1600));
		}

	}



}
