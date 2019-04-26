package controller;
import java.io.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import model.BVC;
import model.Market;
import myException.ThreeMarketsInGraphException;
import myException.completeDataException;
import view.Main;

public class BVCcontroller implements Initializable {

    @FXML
    private AnchorPane addPane;
    @FXML
    private TextField fileAddress;
    @FXML
    private TextArea dataAreaText;
    @FXML
    private AnchorPane selecPane;
    @FXML
    private ComboBox<String> sharesCombo;
    @FXML
    private AnchorPane consulPane;
    @FXML
    private Label highLowLabel;
    @FXML
    private Label numberMarkets;
    @FXML
    private Label nameConsulPane;
    @FXML
    private Label priceLabel;
    @FXML
    private DatePicker dateStart;
    @FXML
    private DatePicker dateEnd;
    @FXML
    private TextField timeStart;
    @FXML
    private TextField timeEnd;
    @FXML
    private TextField nameMarketAddPane;
    @FXML
    private Label stateLabel;
    @FXML
    private AnchorPane intervalPane;
    @FXML
    private Label startDateLabel;
    @FXML
    private Label endDateLabel;
    @FXML
    private Label percentLabel;
    @FXML
    private AnchorPane filterPane;
    @FXML
    private TextField priceSearchTextField;
    @FXML
    private ListView<String> ListMarkets;
    @FXML
    private AnchorPane increasePane;
    @FXML
    private Label AnameIncreasePane;
    @FXML
    private Label BnameIncreasePane;
    @FXML
    private Label CnameIncreasePane;
    @FXML
    private Label typeMarketIncreasePane;
    @FXML
    private AnchorPane graphPane;
    @FXML
    private Label typeMarketGraphPane;
    @FXML
    private LineChart<String, Double> graph;
	@FXML
	private CategoryAxis xAxix;
	@FXML
	private NumberAxis yAxis;
    private int state;
    private int current;
    private ArrayList<String> listName;
    private ArrayList<String> listLink;
    
    @FXML
    void addGraph(ActionEvent event) {
    	try{
			if(graph.getData().size() == 3){
				throw new ThreeMarketsInGraphException();
			}
			selecPane.setVisible(true);
			graphPane.setVisible(false);
		}
    	catch(ThreeMarketsInGraphException e){
    		JOptionPane.showMessageDialog(null, e.getMessage());
		}
    }
    @FXML
    void addMarket(ActionEvent event) {
    	noVisiblePanes();
    	addPane.setVisible(true);
    	state =1;
    }
    @FXML
    void addMarketAddPane(ActionEvent event) throws IOException {
    	if(!nameMarketAddPane.getText().equals("")) {
    		if(dataAreaText.getText().equals("")&&fileAddress.getText().equals("")) {
        		try {
    				throw new completeDataException();
    			} catch (completeDataException e) {
    				JOptionPane.showMessageDialog(null,e.getMessage());
    			}
        	}else if(dataAreaText.getText().equals("")) {
        		listName.add(nameMarketAddPane.getText());
        		listLink.add(fileAddress.getText());
        	}else {
        		listName.add(nameMarketAddPane.getText());
        		listLink.add(saveData(dataAreaText.getText(), nameMarketAddPane.getText()));
        	}
    	}else {
    		try {
				throw new completeDataException();
			} catch (completeDataException e) {
				JOptionPane.showMessageDialog(null,e.getMessage());
			}
    	}
    	dataAreaText.setText("");
    	fileAddress.setText("");
    	nameMarketAddPane.setText("");
    	refreshMarkets();
    	noVisiblePanes();
    }
	@FXML
    void clean(ActionEvent event) {
		System.out.println("");
    }
    @FXML
    void deleteMarket(ActionEvent event) {
    	noVisiblePanes();
    	selecPane.setVisible(true);
    	state =3;
    	stateLabel.setText("Eliminar Mercado");
    	refreshMarkets();
    }
    @FXML
    void filterTypes(ActionEvent event) throws IOException {
    	noVisiblePanes();
    	filterPane.setVisible(true);
    	consulPane.setVisible(true);
    	state =6;
    	highLowLabel.setText("Filtro");
    	refreshMarkets();
    	createallMarkets();
    }
    @FXML
    void goGraph(ActionEvent event) {
    	goGraphs();
    }
    @FXML
    void intervalHigh(ActionEvent event) {
    	noVisiblePanes();
    	selecPane.setVisible(true);
    	state =7;
    	stateLabel.setText("Rango de mayor crecimiento");
    	highLowLabel.setText("Rango de mayor crecimiento");
    	refreshMarkets();
    }
    @FXML
    void nextSelecPane(ActionEvent event) throws IOException {
    	switch (state) {
		case 2: goSetMarket();
			break;
		case 3: goDeleteMarket();
		break;
		case 4: goConsulHigh();
		break;
		case 5: goConsulLow();
		break;
		case 7: goIntervalHigh();
		break;
		case 9:
		    goGraphs();
		    draw();
		    break;
		default:
			break;
		}
    }
	@FXML
    void searchAdd(ActionEvent event) {
    	 FileChooser fileChooser = new FileChooser();
         fileChooser.setTitle("Buscar Archivo de texto");
         fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All notes","*.txt"));
         File file = fileChooser.showOpenDialog(null);
         if(file!= null)
        	 fileAddress.setText(file.getPath());

    }
    @FXML
    void searchPriceHigh(ActionEvent event) {
    	noVisiblePanes();
    	selecPane.setVisible(true);
    	state =4;
    	stateLabel.setText("Consultar Precio Mas alto");
    	highLowLabel.setText("Consultar precio mas alto");
    	refreshMarkets();
    }
    @FXML
    void searchPriceLow(ActionEvent event) {
    	noVisiblePanes();
    	selecPane.setVisible(true);
    	state =5;
    	stateLabel.setText("Consultar Precio Mas bajo");
    	highLowLabel.setText("Consultar precio mas bajo");
    	refreshMarkets();
    }
    @FXML
    void setMarket(ActionEvent event) {
    	noVisiblePanes();
    	selecPane.setVisible(true);
    	state =2;
    	stateLabel.setText("Modificar mercado");
    	refreshMarkets();
    }
    @FXML
    void searchCosulPane(ActionEvent event) {
    	if(dateStart.getValue()!=null&&dateEnd.getValue()!=null
    			&& !timeStart.getText().equals("")&&!timeEnd.getText().equals("")) {
    		Date start = convertionDate(dateStart.getValue()+"", timeStart.getText());
        	Date end =  convertionDate(dateEnd.getValue()+"", timeEnd.getText());
        	double price=0;
        	Object[] data= new Object[3];
        	if(state ==4) {
        		price=Main.getReception().searchHighPrice(listName.get(current), start, end);
        	}
        	else if(state==5){
        		price=Main.getReception().searchLowPrice(listName.get(current), start, end);
        	}
        	else if(state==7) {
        		data=Main.getReception().rangeMaximumGrowth(listName.get(current), start, end);
        		SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        		startDateLabel.setText(formateador.format((Date)data[1]));
        		endDateLabel.setText(formateador.format((Date)data[0]));
        		percentLabel.setText(data[2]+"");
        	}
        	else if(state==6) {
        		if(!priceSearchTextField.getText().equals("")) {
        			ArrayList<String> a = Main.getReception().searchAllMarketsOverPrice(start, end, Double.parseDouble(priceSearchTextField.getText()));
        			ListMarkets.getItems().addAll(a);
        		}
        	}else {
        		String a[]= Main.getReception().marketsMaximumGrowth(start, end);
        		AnameIncreasePane.setText(a[0]);
        		BnameIncreasePane.setText(a[1]);
        		CnameIncreasePane.setText(a[2]);
        	}
        	nameConsulPane.setText(listName.get(current));
        	priceLabel.setText(price+"");
    	}else {
    		try {
				throw new completeDataException();
			} catch (completeDataException e) {
				JOptionPane.showMessageDialog(null,e.getMessage());
			}
    	}
    	
    	
    }
    @FXML
    void sharesAction(ActionEvent event) {
    	if(sharesCombo.getSelectionModel().getSelectedIndex()>-1) {
    		current = sharesCombo.getSelectionModel().getSelectedIndex();
    	}
    }
    @FXML
    void topGreaterGrowth(ActionEvent event) throws IOException {
    	highLowLabel.setText("Top de crecimiento de mercado");
    	refreshMarkets();
    	noVisiblePanes();
    	consulPane.setVisible(true);
    	increasePane.setVisible(true);
    	state =8;
    	createallMarkets();
    }
    private void noVisiblePanes() {
    	addPane.setVisible(false);
    	selecPane.setVisible(false);
    	consulPane.setVisible(false);
    	intervalPane.setVisible(false);
    	filterPane.setVisible(false);
    	graphPane.setVisible(false);
    	increasePane.setVisible(false);
    }
	private void refreshMarkets() {
		numberMarkets.setText("Cantidad de mercados: " + listName.size());
		listMarkets();
		refreshConsul();
		priceSearchTextField.setText("");
		AnameIncreasePane.setText("");
		BnameIncreasePane.setText("");
		CnameIncreasePane.setText("");
		nameConsulPane.setText("");
		startDateLabel.setText("");
		endDateLabel.setText("");
		priceLabel.setText("");
		percentLabel.setText("");
		ListMarkets.getItems().clear();
		Main.setReception(new BVC());
	}
	private void refreshConsul() {
		dateStart.setValue(null);
		dateEnd.setValue(null);
		timeStart.setText("");
		timeEnd.setText("");
	}
	private void listMarkets() {
		sharesCombo.getItems().clear();
		sharesCombo.getItems().addAll(listName);
	}
	private void goConsulLow() throws IOException {
		createMarket();
		noVisiblePanes();
		consulPane.setVisible(true);
	}
	private void goConsulHigh() throws IOException {
		createMarket();
		noVisiblePanes();
		consulPane.setVisible(true);
	}
	private void goDeleteMarket() {
		listName.remove(current);
		listLink.remove(current);
		noVisiblePanes();
		refreshMarkets();
		current=0;
	}
	private void goSetMarket() {
		goDeleteMarket();
		addPane.setVisible(true);
		current=0;
	}
	private void goGraphs() {
        noVisiblePanes();
        graphPane.setVisible(true);
        xAxix.setLabel("Fechas");
		ObservableList<String> categories = FXCollections.observableArrayList("Febrero 8 - 10", "Febrero 11 - 15", "Febrero 16 - 20", "Febrero 21 - 25", "Febrero 26 - 30",
			"Marzo 1 - 5", "Marzo 6 - 10", "Marzo 11 - 15", "Marzo 16 - 20");
        xAxix.setCategories(categories);
        yAxis.setLabel("Precios");
		yAxis.setAutoRanging(false);
		yAxis.setLowerBound(0);
		yAxis.setUpperBound(26000);
		yAxis.setTickUnit(100);
		state =9;
        refreshMarkets();
	}

	private void draw() throws IOException {
        XYChart.Series<String, Double> market1 = new XYChart.Series<>();
		market1.setName(listName.get(current));

		File file = new File(listLink.get(current));
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);

		String line = "";

		double sum = 0.0;
		double averagePrice = 0.0;
		int range = 0;
		int previousDay = 20;
		int count = 1;
		int initialDay = 0;
		int finalDay = 0;
		int previousMonth = 0;

		while((line = br.readLine()) != null){
			String[] array = line.split(",");
			String time = array[1];
			String[] array2 = time.split(" ");
			String date = array2[1];
			int month = Integer.parseInt(date.split("/")[1]);
			int day = Integer.parseInt(date.split("/")[0]);
			String price = array[2];
			price.trim();

			if(day != previousDay){
				count++;
			}
			if(count == 1){
				finalDay = day;
				previousMonth = month;
			}
			if(count < 6 ){
				sum += Double.parseDouble(price);
				range++;
				initialDay = previousDay;
				previousDay = day;
			}
			else{
				averagePrice = sum / range;
				market1.getData().add(new XYChart.Data<>(getMonthNameInSpanish(previousMonth) + " " + initialDay + " - " + finalDay, averagePrice));
				sum = 0;
				range = 0;
				count = 1;
				sum += Double.parseDouble(price);
				previousDay = day;
				range++;
			}
		}
		market1.getData().add(new XYChart.Data<>(getMonthNameInSpanish(previousMonth) + " " + initialDay + " - " + finalDay, averagePrice));
		br.close();
		fr.close();
		FXCollections.reverse(market1.getData());
		graph.getData().addAll(market1);
    }

    private String getMonthNameInSpanish(int month){
    	switch(month){
			case 2: return "Febrero";
			case 3: return "Marzo";
			default: return "";
		}
	}

	private void goIntervalHigh() throws IOException {
		createMarket();
		noVisiblePanes();
		consulPane.setVisible(true);
		intervalPane.setVisible(true);
	}

	private Date convertionDate(String string, String time) {
		int year = Integer.parseInt(string.split("-")[0])-1900; 
		int month = Integer.parseInt(string.split("-")[1])-1;
		int day = Integer.parseInt(string.split("-")[2]);
		int hrs= Integer.parseInt(time.split(":")[0]); 
		int mm= Integer.parseInt(time.split(":")[1]); 
		return new Date(year, month, day, hrs, mm);
	}



	private LocalDateTime convertionDate2(String date, String hour){
		int year = Integer.parseInt(date.split("/")[2]);
		int month = Integer.parseInt(date.split("/")[1]);
		int day = Integer.parseInt(date.split("/")[0]);
		int hr = Integer.parseInt(hour.split(":")[0]);
		int minutes = Integer.parseInt(hour.split(":")[1]);
		return LocalDateTime.of(year, month, day, hr, minutes);
	}

	private ArrayList<String> listDataSet(){
		String a[]= {"#US30","#USSPX500","BTCUSD","EURUSD","GBPCAD","USDJPY","WTI","XAUUSD"};
		ArrayList<String> n = new ArrayList<>(Arrays.asList(a));
		return n;
	}



	private ArrayList<String> linkDataSet(){
		String a1[]= {"./LAB/dataset/#US30 prices.txt","./LAB/dataset/#USSPX500 prices.txt","./LAB/dataset/BTCUSD prices.txt",
				"./LAB/dataset/EURUSD prices.txt","./LAB/dataset/GBPCAD prices.txt","./LAB/dataset/USDJPY prices.txt","./LAB/dataset/WTI prices.txt",
				"./LAB/dataset/XAUUSD prices.txt"};

		ArrayList<String> n = new ArrayList<>(Arrays.asList(a1));
		return n;
	}

	private ArrayList<String> linkDataSet2(){
		String a2[]= {"../LAB/dataset/#US30 prices.txt","../LAB/dataset/#USSPX500 prices.txt","../LAB/dataset/BTCUSD prices.txt",
				"../LAB/dataset/EURUSD prices.txt","../LAB/dataset/GBPCAD prices.txt","../LAB/dataset/USDJPY prices.txt","../LAB/dataset/WTI prices.txt",
				"../LAB/dataset/XAUUSD prices.txt"};

		ArrayList<String> n = new ArrayList<>(Arrays.asList(a2));
		return n;
	}

	private String saveData(String data, String name) throws IOException {
		String link="./LAB/dataset/"+name+" prices";
		File file =new File(link);
		BufferedWriter bw = new BufferedWriter(new FileWriter(file));
		bw.write(data);
		bw.close();
		return link;
	}
	private void createMarket() throws IOException {
		try {

			Main.getReception().createMarketTxt(listLink.get(current));
		}catch (Exception e){
			listLink = linkDataSet2();
			Main.getReception().createMarketTxt(listLink.get(current));

		}
	}
	private void createallMarkets() throws IOException {
    	try {
			for (int i = 0; i < listLink.size(); i++) {
				Main.getReception().createMarketTxt(listLink.get(i));
			}

		}catch (Exception e){
    		listLink = linkDataSet2();
			for (int i = 0; i < listLink.size(); i++) {
				Main.getReception().createMarketTxt(listLink.get(i));
			}
		}

	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		listName = listDataSet();
		listLink = linkDataSet();
		refreshMarkets();
	}
    
}
