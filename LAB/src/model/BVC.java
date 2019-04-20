package model;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

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
	public ArrayList<Market> getMarketShares() {
		return marketShares;
	}
	public ArrayList<Market> getMarketCurrencys() {
		return marketCurrencys;
	}
	public void createMarketTxt(String data) throws IOException {
		addMarket(receiver.createMarketTxt(data));
	}

}
