package model;

import Estructures.trees.AVLNode;
import Estructures.trees.AVLTree;
import Estructures.trees.RBTree;
import myException.marketNotFoundException;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class BVC{

	private ArrayList<Market> marketShares;
	private ArrayList<Market> marketCurrencys;
	private Receiver receiver;

	public BVC() {
		marketCurrencys= new ArrayList<>();
		marketShares= new ArrayList<>();
		receiver = new Receiver();
	}

	public void addMarket(Market market) {
		if (market.getType() == '#')
			marketCurrencys.add(market);
		else
			marketShares.add(market);
	}
	public ArrayList<Market> getMarketShares() { return marketShares; }

	public ArrayList<Market> getMarketCurrencys() {
		return marketCurrencys;
	}

	public void createMarketTxt(String data) throws IOException {
		addMarket(receiver.createMarketTxt(data));
	}

	public double searchHighPrice(String name, Date Start , Date Finish){
		Market curr_market = null;

		int st = 0;
		int fin = 0;

		if (name.charAt(0) == '#') {
			for (int I = 0; I < marketCurrencys.size(); I++) {
				if(marketCurrencys.get(I).getName().equals(name)){
					curr_market = marketCurrencys.get(I);
					st = (int)curr_market.getTree_Date_Currency().search(Start)[1];
					fin = (int)curr_market.getTree_Date_Currency().search(Finish)[1];
				}
			}
		}else {
			for (int I = 0; I < marketShares.size(); I++) {
				if(marketShares.get(I).getName().equals(name)){
					curr_market = marketShares.get(I);
					st = (int)curr_market.getTree_Date_Shares().search(Start)[1];
					fin = (int)curr_market.getTree_Date_Shares().search(Finish)[1];
				}
			}
		}

		if(curr_market == null){
			try {
				throw new marketNotFoundException();
			} catch (marketNotFoundException e) {

			}
		}

		ArrayList<State> rangeStates = curr_market.getStates();

		double max_value = 0;

		for (int J =  fin ; J<=st ; J++){
			if(rangeStates.get(J).getValue() > max_value){
				max_value = rangeStates.get(J).getValue();
			}
		}

		return max_value;

	}

	public double searchLowPrice(String name, Date Start , Date Finish){
		Market curr_market = null;

		int st = 0;
		int fin = 0;

		if (name.charAt(0) == '#') {
			for (int I = 0; I < marketCurrencys.size(); I++) {
				if(marketCurrencys.get(I).getName().equals(name)){
					curr_market = marketCurrencys.get(I);
					st = (int)curr_market.getTree_Date_Currency().search(Start)[1];
					fin = (int)curr_market.getTree_Date_Currency().search(Finish)[1];
				}
			}
		}else {
			for (int I = 0; I < marketShares.size(); I++) {
				if(marketShares.get(I).getName().equals(name)){
					curr_market = marketShares.get(I);
					st = (int)curr_market.getTree_Date_Shares().search(Start)[1];
					fin = (int)curr_market.getTree_Date_Shares().search(Finish)[1];
				}
			}
		}

		if(curr_market == null){
			try {
				throw new marketNotFoundException();
			} catch (marketNotFoundException e) {

			}
		}

		ArrayList<State> rangeStates = curr_market.getStates();

		double min_value = rangeStates.get(fin).getValue();


		for (int J =  fin ; J<=st ; J++){
			if(rangeStates.get(J).getValue() < min_value){
				min_value = rangeStates.get(J).getValue();
			}
		}

		return min_value;

	}


	public boolean searchOverPriceName(String name, Date Start , Date Finish , double value){
		Market curr_market = null;

		if (name.charAt(0) == '#') {
			for (int I = 0; I < marketCurrencys.size(); I++) {
				if(marketCurrencys.get(I).getName().equals(name)){
					curr_market = marketCurrencys.get(I);
				}
			}
		}else {
			for (int I = 0; I < marketShares.size(); I++) {
				if(marketShares.get(I).getName().equals(name)){
					curr_market = marketShares.get(I);
				}
			}
		}

		ArrayList<Date> temp = curr_market.getTree_Value_Currency().searchBiggerThan(value);

		for (int I = 0;I<temp.size(); I++){
			if (temp.get(I).compareTo(Start)>=0 && temp.get(I).compareTo(Finish) <= 0){
				return true;
			}
		}

		return false;
	}


	public boolean searchOverPriceMarket(Market current, Date Start , Date Finish , double value){
		Market curr_market = current;

		ArrayList<Date> temp = curr_market.getTree_Value_Currency().searchBiggerThan(value);

		for (int I = 0;I<temp.size(); I++){
			if (temp.get(I).compareTo(Start)>=0 && temp.get(I).compareTo(Finish) <= 0){
				return true;
			}
		}

		return false;
	}

	public ArrayList<String> searchAllMarketsOverPrice(Date Start , Date Finish ,double value){
			ArrayList<String> overThePrice = new ArrayList<>();

			for (int I = 0; I < marketCurrencys.size(); I++) {
				if(searchOverPriceMarket(marketCurrencys.get(I),Start,Finish,value)){
					overThePrice.add(marketCurrencys.get(I).getName());
				}
			}

			for (int I = 0; I < marketShares.size(); I++) {
				if(searchOverPriceMarket(marketShares.get(I),Start,Finish,value)){
					overThePrice.add(marketShares.get(I).getName());
				}
			}

			return  overThePrice;
	}

	public String[] marketsMaximumGrowth(Date Start , Date Finish){

		AVLTree<Double,String> clasifier = new AVLTree<>();

		double max = Double.MIN_VALUE;

		for (int I = 0; I < marketCurrencys.size(); I++) {
			double key = (double)rangeMaximumGrowth(marketCurrencys.get(I),Start,Finish)[2];
			clasifier.insert( key, marketCurrencys.get(I).getName());
			if(key >max){
				max = key;
			}
		}

		for (int I = 0; I < marketShares.size(); I++) {
			double key = (double)rangeMaximumGrowth(marketShares.get(I),Start,Finish)[2];
			clasifier.insert(key, marketShares.get(I).getName());
			if(key > max){
				max = key;
			}
		}

		ArrayList<String> result = clasifier.searchLowerOrEqualTo(max);

		String names[] = {result.get(result.size()-1) , result.get(result.size()-2) , result.get(result.size()-3)};

		return names;
	}


	public Object[] rangeMaximumGrowth(String name, Date Start , Date Finish ){
		Market curr_market = null;

		int st = 0;
		int fin = 0;

		if (name.charAt(0) == '#') {
			for (int I = 0; I < marketCurrencys.size(); I++) {
				if(marketCurrencys.get(I).getName().equals(name)){
					curr_market = marketCurrencys.get(I);
					st = (int)curr_market.getTree_Date_Currency().search(Start)[1];
					fin = (int)curr_market.getTree_Date_Currency().search(Finish)[1];

				}
			}
		}else {
			for (int I = 0; I < marketShares.size(); I++) {
				if(marketShares.get(I).getName().equals(name)){
					curr_market = marketShares.get(I);
					st = (int)curr_market.getTree_Date_Shares().search(Start)[1];
					fin = (int)curr_market.getTree_Date_Shares().search(Finish)[1];

				}
			}
		}

		ArrayList<State> temp = curr_market.getStates();

		double slopes[] = new double[temp.size()];

		for (int I = st+1; I<=fin ; I++){
			slopes[I-1] = temp.get(I).getValue() - temp.get(I-1).getValue();
		}

		double result[] = FindMaximumSubarraySum(slopes,0,slopes.length-1);

		//En la posición 0 esta la fecha inicial en la  1 la fecha final y en la 2 el crecimiento total

		Object[] ref = {temp.get((int)result[0]).getDate() , temp.get((int)result[1]).getDate() , result[2]};

		return ref;
	}

	public Object[] rangeMaximumGrowth(Market curr, Date Start , Date Finish ){
		Market curr_market = curr;

		int st = (int)curr_market.getTree_Date_Currency().search(Start)[1];
		int fin = (int)curr_market.getTree_Date_Currency().search(Finish)[1];

		ArrayList<State> temp = curr_market.getStates();

		double slopes[] = new double[temp.size()];

		for (int I = st+1; I<=fin ; I++){
			slopes[I-1] = temp.get(I).getValue() - temp.get(I-1).getValue();
		}

		double result[] = FindMaximumSubarraySum(slopes,0,slopes.length-1);

		//En la posición 0 esta la fecha inicial en la  1 la fecha final y en la 2 el crecimiento total

		Object[] ref = {temp.get((int)result[0]).getDate() , temp.get((int)result[1]).getDate() , result[2]};

		return ref;
	}

	private double[] FindMaximumSubarraySum(double[] slopes, int start, int end) {
		double solution[] = {start,end, slopes[start]};
		if(start == end){
			return solution;
		}else{
			int mid = (int)Math.floor((end+start)/2);
			double[] solutionLeft = FindMaximumSubarraySum(slopes,start,mid);

			double[] solutionRight = FindMaximumSubarraySum(slopes,mid+1,end);

			double[] solutionCross = FindMaximumCrossSubarray(slopes,start,mid,end);

			if(solutionLeft[3] >= solutionRight[3] && solutionLeft[3] >= solutionCross[3] ){
				solution = solutionLeft;
			}else if(solutionLeft[3] <= solutionRight[3] && solutionRight[3] >= solutionCross[3]){
				solution = solutionRight;
			}else{
				solution = solutionCross;
			}

		}

		return solution;
	}

	private double[] FindMaximumCrossSubarray(double[] slopes, int start, int mid, int end) {
		double left_sum = Double.MIN_VALUE;
		double sum = 0;
		double max_left = 0;


		for(int I = mid; I>=end ; I--){
			sum += slopes[I];

			if(sum > left_sum){
				left_sum = sum;
				max_left = I;
			}

		}

		double right_sum = Double.MIN_VALUE;
		sum = 0;
		double max_right = 0;

		for(int I = mid; I<= start ; I++ ){
			sum += slopes[I];
			if(sum > right_sum ){
				right_sum = sum;
				max_right = I;
			}
		}

		double [] ref = {max_left,max_right, left_sum + right_sum};
		return ref;
	}



}
