package model;

import Estructures.trees.AVLTree;
import Estructures.trees.RBTree;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

public class Market {

	private ArrayList<State> states;
	private char type;
	private String name;
	private AVLTree<Date,double[]> tree_Date_Currency;
	private RBTree<Date,double[]> tree_Date_Shares;
	private AVLTree<Double,Date> tree_Value_Currency;
	private RBTree<Double,Date> tree_Value_Shares;

	
	public Market(ArrayList<State> states, String name) {
		this.states = states;
		Collections.reverse(this.states);
		this.type = name.charAt(0);
		this.name = name;
		if(type == '#'){
			tree_Value_Currency = new AVLTree<>();
			tree_Date_Currency = new AVLTree<>();
			createCurrencyTree();
		}else{
			tree_Value_Shares = new RBTree<>();
			tree_Date_Shares = new RBTree<>();
			createSharesTree();
		}
	}

	private void createSharesTree() {
		for (int I = 0; I< states.size() ; I++){
			double[] ref = {states.get(I).getValue(),I};
			tree_Date_Shares.insert(states.get(I).getDate(),ref);
			tree_Value_Shares.insert(states.get(I).getValue(),states.get(I).getDate());
		}
	}

	private void createCurrencyTree() {
		for (int I = 0; I< states.size() ; I++){
			double [] ref = {states.get(I).getValue(),I};
			tree_Date_Currency.insert(states.get(I).getDate(), ref);
			tree_Value_Currency.insert(states.get(I).getValue(),states.get(I).getDate());
		}
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<State> getStates() {
		return states;
	}
	public void setStates(ArrayList<State> states) {
		this.states = states;
	}
	public char getType() {
		return type;
	}
	public void setType(char type) {
		this.type = type;
	}

	@Override
	public String toString() {
//		return "Market [states=" + states + ", type=" + type + ", name=" + name + "]";
		return states.size()+"";
	}

	public AVLTree<Date, double[]> getTree_Date_Currency() {
		return tree_Date_Currency;
	}

	public void setTree_Date_Currency(AVLTree<Date, double[]> tree_Date_Currency) {
		this.tree_Date_Currency = tree_Date_Currency;
	}

	public RBTree<Date, double[]> getTree_Date_Shares() {
		return tree_Date_Shares;
	}

	public void setTree_Date_Shares(RBTree<Date, double[]> tree_Date_Shares) {
		this.tree_Date_Shares = tree_Date_Shares;
	}

	public AVLTree<Double, Date> getTree_Value_Currency() {
		return tree_Value_Currency;
	}

	public void setTree_Value_Currency(AVLTree<Double, Date> tree_Value_Currency) {
		this.tree_Value_Currency = tree_Value_Currency;
	}

	public RBTree<Double, Date> getTree_Value_Shares() {
		return tree_Value_Shares;
	}

	public void setTree_Value_Shares(RBTree<Double, Date> tree_Value_Shares) {
		this.tree_Value_Shares = tree_Value_Shares;
	}
}
