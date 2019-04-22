package controller;
import java.io.*;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

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
import model.Market;
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
    private Label nameIntervalPane;
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
    private int current=-1;
    private ArrayList<String> listName;
    private ArrayList<String> listLink;
    
    @FXML
    void addGraph(ActionEvent event) {
        selecPane.setVisible(true);
        graphPane.setVisible(false);

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
    void filterTypes(ActionEvent event) {
    	noVisiblePanes();
    	filterPane.setVisible(true);
    	state =6;
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
    void searchFilterPane(ActionEvent event) {

    }
    @FXML
    void searchPriceHigh(ActionEvent event) {
    	noVisiblePanes();
    	selecPane.setVisible(true);
    	state =4;
    	stateLabel.setText("Consultar Precio Mas alto");
    	highLowLabel.setText("alto");
    	refreshMarkets();
    }
    @FXML
    void searchPriceLow(ActionEvent event) {
    	noVisiblePanes();
    	selecPane.setVisible(true);
    	state =5;
    	stateLabel.setText("Consultar Precio Mas bajo");
    	highLowLabel.setText("bajo");
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
        	double price;
        	if(state ==4) {
        		price=Main.getReception().searchHighPrice(listName.get(current), start, end);
        	}
        	else {
        		price=Main.getReception().searchLowPrice(listName.get(current), start, end);
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
    void topGreaterGrowth(ActionEvent event) {
    	noVisiblePanes();
    	increasePane.setVisible(true);
    	state =8;
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
		numberMarkets.setText("Cantidad divisas: " +Main.getReception().getMarketCurrencys().size()
    			+ "           Cantidad acciones: "+ Main.getReception().getMarketShares().size());
		listMarkets();
		refreshConsul();
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
	}
	private void goSetMarket() {
		goDeleteMarket();
		addPane.setVisible(true);
	}
	private void goGraphs() {
        noVisiblePanes();
        graphPane.setVisible(true);
        xAxix.setLabel("Fechas");
        yAxis.setLabel("Precios");
        state =9;
        refreshMarkets();
	}

	private void draw() throws IOException {

        XYChart.Series<String, Double> market1 = new XYChart.Series<>();
		market1.setName(listName.get(current));

		File file = new File(listLink.get(current));
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);

		String line = br.readLine();
		String[] array = line.split(",");
		String time = array[1];
		String[] array2 = time.split(" ");
		String date = array2[1];
		String hour = array2[2];

		LocalDateTime marketDate = convertionDate2(date, hour);

		String price = array[2];
		price.trim();

		br.close();
		fr.close();

		market1.getData().add(new XYChart.Data<>(marketDate.getMonth().toString(), Double.parseDouble(price)));

        /*market1.getData().add(new XYChart.Data<>("Febrero", 5));
        market1.getData().add(new XYChart.Data<>("Marzo", 15));
        market1.getData().add(new XYChart.Data<>("Abril", 0));*/

		graph.getData().addAll(market1);
    }

	private void goIntervalHigh() {
		// TODO Auto-generated method stub
		
	}

	private Date convertionDate(String string, String time) {
		int year = Integer.parseInt(string.split("-")[0]);
		int month = Integer.parseInt(string.split("-")[1]);
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
		String a[]= {"../LAB/dataset/#US30 prices.txt","../LAB/dataset/#USSPX500 prices.txt","../LAB/dataset/BTCUSD prices.txt",
				"../LAB/dataset/EURUSD prices.txt","../LAB/dataset/GBPCAD prices.txt","../LAB/dataset/USDJPY prices.txt","../LAB/dataset/WTI prices.txt",
				"../LAB/dataset/XAUUSD prices.txt"};
		ArrayList<String> n = new ArrayList<>(Arrays.asList(a));
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
		Main.getReception().createMarketTxt(listLink.get(current));
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		listName = listDataSet();
		listLink = linkDataSet();
		refreshMarkets();
	}
    
}
