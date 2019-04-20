package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class Receiver implements Serializable{

	private static final long serialVersionUID = 1L;

	public Receiver() {
		// TODO Auto-generated constructor stub
	}
	
	public Market createMarketString(String data) {
		String dataSplit[] = data.split("\n");
		String name = dataSplit[0].split(",")[0];
    	ArrayList<State> states = new ArrayList<State>();
		for (int i = 0; i < dataSplit.length&&!dataSplit[i].equals(""); i++) {
			states.add(createState(dataSplit[i]));
		}
		return new Market(states, name);
	}
	public Market createMarketTxt(String address) throws IOException {
		File file = new File(address);
    	FileReader fr = new FileReader(file);
    	BufferedReader br = new BufferedReader(fr);
    	String line = br.readLine();
    	String name = line.split(",")[0];
    	ArrayList<State> states = new ArrayList<State>();
    	while(line!=null){
    	states.add(createState(line));
    	line = br.readLine();
    	}
		return new Market(states, name);
	}

	private State createState(String line) {
		String m[] = line.split(",");
		double value = Double.parseDouble(m[2].trim());
		return new State(m[1], value);
	}

}
