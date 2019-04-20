package model;

import myException.marketNotFoundException;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class BVC implements Serializable{

	
	private static final long serialVersionUID = 1L;
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

	public void createMarketString(String data) {
		addMarket(receiver.createMarketString(data));
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


		for (int J =  st ; J<=fin ; J++){
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

		double max_value = 0;


		for (int J =  st ; J<=fin ; J++){
			if(rangeStates.get(J).getValue() > max_value){
				max_value = rangeStates.get(J).getValue();
			}
		}

		return max_value;

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



	public void rangeMaximumGrowth(){

	}

	public void marketsMaximumGrowth(){

	}

}
