package johan.asling.ju15.javafx;

import java.io.File;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import johan.asling.ju15.beans.PersonBean;

/**
 * This class creates an application that manages people
 *
 * @version 1.1
 */
public class SpamWindow extends Application {

	TableView<PersonBean> table;
	private ObservableList<PersonBean> personData = FXCollections.observableArrayList();
	SortedList<PersonBean> sortedData = null;
	FilteredList<PersonBean> filteredData = null;
	String pathToData;
	
	/**
	 * Entry point of Application
	 * @param args	Not used
	 */
	public static void main(String[] args) {

		System.out.println("Launching JavaFX application.");

		// Start the JavaFX application by calling launch().
		launch(args);
	}

	// Override the init() method.
	/**
	 * This method loads the data needed for the application
	 */
	public void init() {
		System.out.println("Inside the init() method.");
		loadData();
		/*
		 * // read file Person2 person1 = new Person2("Pelle", "Olsson",
		 * "20040102-1234"); // person1.setFirstName("Pelle"); //
		 * person1.setLastName("Olsson"); // person.setAge(20);
		 * personData.add(person1); Person2 person2 = new Person2("Sven",
		 * "Svensson", "19901022-3456"); // person2.setFirstName("Sven"); //
		 * person2.setLastName("Svensson"); // person.setAge(20);
		 * personData.add(person2); Person2 person3 = new Person2("Anna",
		 * "Johansson", "19950810-2345"); // person3.setFirstName("Anna"); //
		 * person3.setLastName("Johansson"); // person.setAge(20);
		 * personData.add(person3); Person2 person4 = new Person2("Bengt",
		 * "Persson", "19600314-5678"); // person4.setFirstName("Bengt"); //
		 * person4.setLastName("Persson"); // person.setAge(20);
		 * personData.add(person4);
		 */
	}

	// Override the start() method.
	/**
	 * This method creates the layout of the application
	 */
	public void start(Stage myStage) {

		System.out.println("Inside the start() method.");

		table = new TableView<PersonBean>();
		table.setEditable(true);
		// Give the stage a title.
		myStage.setTitle("Person Spam App");
		filteredData = new FilteredList<>(personData, p -> true);
		sortedData = new SortedList<>(filteredData);
		sortedData.comparatorProperty().bind(table.comparatorProperty());

		table.setItems(sortedData);
		table.setPrefWidth(500);

		table.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
		
		/*
		TableColumn<PersonBean, String> nameCol = new TableColumn<PersonBean, String>("Full Name");
		nameCol.setCellValueFactory(new PropertyValueFactory("name"));
		nameCol.setCellFactory(TextFieldTableCell.<PersonBean> forTableColumn());
		nameCol.setOnEditCommit((CellEditEvent<PersonBean, String> t) -> {
			((PersonBean) t.getTableView().getItems().get(t.getTablePosition().getRow())).setName(t.getNewValue());
		});
		*/
	
		TableColumn<PersonBean, String> firstNameCol = new TableColumn<PersonBean, String>("First Name");
		firstNameCol.setCellValueFactory(new PropertyValueFactory("firstName"));
		firstNameCol.setCellFactory(TextFieldTableCell.<PersonBean> forTableColumn());
		firstNameCol.setOnEditCommit((CellEditEvent<PersonBean, String> t) -> {
			((PersonBean) t.getTableView().getItems().get(t.getTablePosition().getRow())).setFirstName(t.getNewValue());
		});

		TableColumn<PersonBean, String> lastNameCol = new TableColumn<PersonBean, String>("Last Name");
		lastNameCol.setCellValueFactory(new PropertyValueFactory("lastName"));
		lastNameCol.setCellFactory(TextFieldTableCell.<PersonBean> forTableColumn());
		lastNameCol.setOnEditCommit((CellEditEvent<PersonBean, String> t) -> {
			((PersonBean) t.getTableView().getItems().get(t.getTablePosition().getRow())).setLastName(t.getNewValue());
		});
		
		TableColumn<PersonBean, String> emailCol = new TableColumn<PersonBean, String>("Email");
		emailCol.setCellValueFactory(new PropertyValueFactory("email"));
		emailCol.setCellFactory(TextFieldTableCell.<PersonBean> forTableColumn());
		emailCol.setOnEditCommit((CellEditEvent<PersonBean, String> t) -> {
			((PersonBean) t.getTableView().getItems().get(t.getTablePosition().getRow())).setEmail(t.getNewValue());
		});
		
		TableColumn<PersonBean, String> petCol1 = new TableColumn<PersonBean, String>("First pet");
		petCol1.setCellValueFactory(new PropertyValueFactory("petAnimal1"));
		petCol1.setCellFactory(TextFieldTableCell.<PersonBean> forTableColumn());
		petCol1.setOnEditCommit((CellEditEvent<PersonBean, String> t) -> {
			((PersonBean) t.getTableView().getItems().get(t.getTablePosition().getRow())).setPet1(t.getNewValue());
		});
		
		TableColumn<PersonBean, String> petCol2 = new TableColumn<PersonBean, String>("Second pet");
		petCol2.setCellValueFactory(new PropertyValueFactory("petAnimal2"));
		petCol2.setCellFactory(TextFieldTableCell.<PersonBean> forTableColumn());
		petCol2.setOnEditCommit((CellEditEvent<PersonBean, String> t) -> {
			((PersonBean) t.getTableView().getItems().get(t.getTablePosition().getRow())).setPet2(t.getNewValue());
		});
		
		TableColumn<PersonBean, Integer> ageCol = new TableColumn<PersonBean, Integer>("Age");
		ageCol.setCellValueFactory(new PropertyValueFactory("age"));
		ageCol.setCellFactory(TextFieldTableCell.<PersonBean, Integer> forTableColumn(new IntegerStringConverter()));
		ageCol.setOnEditCommit((CellEditEvent<PersonBean, Integer> t) -> {
			((PersonBean) t.getTableView().getItems().get(t.getTablePosition().getRow())).setAge(t.getNewValue());
		});
		table.getColumns().setAll(firstNameCol, lastNameCol, emailCol, petCol1, petCol2, ageCol);
		
		Button allButton = new Button("All");
		allButton.setMinSize(100, 10);
		allButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				System.out.println("Show all!");
				filteredData.setPredicate(showAll());
			}
		});
		/*
		Button maleButton = new Button("Male");
		maleButton.setMinSize(100, 10);
		maleButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				System.out.println("Show male");
				//printIt();
				filteredData.setPredicate(isMale());

			}
		});
		Button femaleButton = new Button("Female");
		femaleButton.setMinSize(100, 10);
		femaleButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				System.out.println("Show female");
				filteredData.setPredicate(isFemale());
			}
		});
		*/

		Button over18Button = new Button("Over 18");
		over18Button.setMinSize(100, 10);
		over18Button.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				System.out.println("Above 18");
				//filteredData.setPredicate(over18());
				filteredData.setPredicate(new Predicate<PersonBean>() {
				    public boolean test(PersonBean p) {
				        return p.getAge() > 17;
				    }
				});
			}
		});

		Button addButton = new Button("Add row");
		addButton.setMinSize(100, 10);
		addButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				System.out.println("Add me!");
				filteredData.setPredicate(showAll());
				addRow();
			}
		});

		Button removeButton = new Button("Delete row");
		removeButton.setMinSize(100, 10);
		removeButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				System.out.println("delete me!");
				List items = new ArrayList(table.getSelectionModel().getSelectedItems());
				personData.removeAll(items);
				table.getSelectionModel().clearSelection();
			}
		});
		// Create a root node. In this case, a flow layout
		// is used, but several alternatives exist.
		VBox rootNode = new VBox();
		
		HBox hbox = new HBox();
		
		hbox.getChildren().addAll(allButton, over18Button, addButton, removeButton);

		rootNode.getChildren().addAll(table, hbox);
		// Create a scene.
		Scene myScene = new Scene(rootNode, 800, 800);
		
		//set stylesheet
		myScene.getStylesheets().add("style.css");
		
		// Set the scene on the stage.
		myStage.setScene(myScene);

		// Show the stage and its scene.
		myStage.show();
	}
/*
	private void printIt() {
		Iterator<Person2> it = personData.iterator();
		while (it.hasNext()) {
			System.out.println(it.next().getGender());
		}
	}
*/
	/** This method reads data from mydata.txt and initializes an ObservableList with the people read.
	 * 
	 */

	private void loadData() {
		try {
			//System.out.println(Paths.get("mydata.txt").toAbsolutePath());
			List<String> lines;
			File f = new File("src/johan/asling/ju15/utils/mydata.txt");
			if(f.exists() && !f.isDirectory()) { 
				lines = Files.readAllLines(Paths.get("src/johan/asling/ju15/utils/mydata.txt"));//, Charset.defaultCharset());
				pathToData = "src/johan/asling/ju15/utils/mydata.txt";
			}
			else{
				lines = Files.readAllLines(Paths.get("mydata.txt"));
				pathToData = "mydata.txt";
			}
			String data[] = new String[5];
			for (String line : lines) {
				data = line.split(",");
				if(!line.isEmpty()){
					PersonBean p = new PersonBean(data[0], data[1], data[2], data[3], data[4], new Integer(data[5]));
					personData.add(p);
				}
			}
			System.out.println("Loaded " + lines.size() + " rows of data.");
		} catch (Exception e) {
			System.out.println("There was a problem loading the file:" + e.getMessage());
			e.printStackTrace();
		}
		
	}
	
	/**
	 * This method saves the changes made to the people list
	 */
	private void writeIt() {
		try {
			PrintWriter writer = new PrintWriter(pathToData, "UTF-8");
			Iterator<PersonBean> it = personData.iterator();
			while (it.hasNext()) {
				PersonBean p = it.next();
				writer.println(p.getFirstName() + "," + p.getLastName() + "," + p.getEmail() + "," + p.getPet1() + "," +  p.getPet2() + "," + p.getAge());
			}
			writer.close();
		} catch (Exception e) {
			System.out.println("There was a problem saving the file:" + e.getMessage());
		}
	}

	/**
	 * This method adds a new person to the observable list.
	 */
	private void addRow() {
		System.out.println("new row.");
		personData.add(new PersonBean());
	}
	
	/** This method makes it so the table displays everything.
	 * 
	 * @return	Always returns true
	 */
	public static Predicate<PersonBean> showAll() {
		return p -> true;
	}
	/*
	/**This method makes it so the table only display males.
	 * 
	 * @return	True if male, false otherwise
	 */
	/*
	public static Predicate<PersonBean> isMale() {
		return p -> p.getGender().equalsIgnoreCase("Male");
	}

	/**This method makes it so the table only display females.
	 * 
	 * @return	True if female, false otherwise
	 */
	/*
	public static Predicate<PersonBean> isFemale() {
		return p -> p.getGender().equalsIgnoreCase("Female");
	}
	*/
	/**This method makes it so the table only display people over 18 year.
	 * 
	 * @return 	True if 18 or older, false otherwise
	 */
	public static Predicate<PersonBean> over18() {
		return p -> p.getAge() > 17;
	}

	// Override the stop() method.
	/**
	 * This method makes the appropriate shutdown saves.
	 */
	public void stop() {
		System.out.println("Inside the stop() method.");
		writeIt();
	}
}