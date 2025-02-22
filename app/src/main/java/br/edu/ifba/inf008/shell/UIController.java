package br.edu.ifba.inf008.shell;

import br.edu.ifba.inf008.interfaces.*;
import br.edu.ifba.inf008.shell.PluginController;

import java.util.Collections;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.application.Platform;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;

public class UIController extends Application implements IUIController
{
    private ICore core;
    private MenuBar menuBar;
    private TabPane tabPane;
    private static UIController uiController;
    private TableView<IBook> table;
    private ObservableList<IBook> bookList = FXCollections.observableArrayList();

    public UIController() {
    }

    @Override
    public void init() {
        uiController = this;
    }

    public static UIController getInstance() {
        return uiController;
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Sua Biblioteca");
        
        menuBar = new MenuBar();
        
        menuBar.getMenus().add(new Menu("Livro"));
        MenuItem menuItem = createMenuItem("Livro", "Cadastro");
        menuBar.getMenus().get(0).getItems().add(menuItem);
        menuItem.setOnAction(e -> {
            openBookTab();
        });
        VBox vBox = new VBox(menuBar);

        tabPane = new TabPane();
        tabPane.setSide(Side.BOTTOM);

        vBox.getChildren().addAll(tabPane);

        Scene scene = new Scene(vBox, 960, 600);

        primaryStage.setScene(scene);
        primaryStage.show();

        Core.getInstance().getPluginController().init();
    }

    public MenuItem createMenuItem(String menuText, String menuItemText) {
        // Criar o menu caso ele nao exista
        Menu newMenu = null;
        for (Menu menu : menuBar.getMenus()) {
            if (menu.getText() == menuText) {
                newMenu = menu;
                break;
            }
        }
        if (newMenu == null) {
            newMenu = new Menu(menuText);
            menuBar.getMenus().add(newMenu);
        }

        // Criar o menu item neste menu
        MenuItem menuItem = new MenuItem(menuItemText);
        newMenu.getItems().add(menuItem);

        return menuItem;
    }

    public boolean createTab(String tabText, Node contents) {
        Tab tab = new Tab();
        tab.setText(tabText);
        tab.setContent(contents);
        tabPane.getTabs().add(tab);

        return true;
    }

    public void openBookTab() {
        

        // Criar campos de entrada
        TextField titleField = new TextField();
        titleField.setPromptText("Título");

        TextField isbnField = new TextField();
        isbnField.setPromptText("ISBN");

        TextField authorField = new TextField();
        authorField.setPromptText("Autor");

        TextField genreField = new TextField();
        genreField.setPromptText("Gênero");

        TextField yearField = new TextField();
        yearField.setPromptText("Ano de Publicação");

        // Botão para salvar
        Button saveButton = new Button("Salvar");
        saveButton.setOnAction(e -> {
            String title = titleField.getText();
            String ISBN = isbnField.getText();
            String author = authorField.getText();
            String genre = genreField.getText();
            String year = yearField.getText();
        
            var bookController = Core.getInstance().getBookController();
            if (bookController.requestCreateBook(title, ISBN, author, genre, year)) {
                IBook book = bookController.createBook(title, ISBN, author, genre, year);
                bookList.add(book);  // Atualiza a ObservableList
                table.refresh();  // Atualiza a exibição da tabela
                titleField.clear();
                isbnField.clear();
                authorField.clear();
                genreField.clear();
                yearField.clear();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Erro ao adicionar livro!", ButtonType.OK);
                alert.showAndWait();
            }
        });

        // Criando a Tabela
        table = new TableView<>(bookList); // Inicializa com a lista

        TableColumn<IBook, String> titleCol = new TableColumn<>("Título");
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));

        TableColumn<IBook, String> isbnCol = new TableColumn<>("ISBN");
        isbnCol.setCellValueFactory(new PropertyValueFactory<>("ISBN"));

        TableColumn<IBook, String> authorCol = new TableColumn<>("Autor");
        authorCol.setCellValueFactory(new PropertyValueFactory<>("author"));

        TableColumn<IBook, String> genreCol = new TableColumn<>("Gênero");
        genreCol.setCellValueFactory(new PropertyValueFactory<>("genre"));

        TableColumn<IBook, String> yearCol = new TableColumn<>("Ano");
        yearCol.setCellValueFactory(new PropertyValueFactory<>("year"));

        Collections.addAll(table.getColumns(), titleCol, isbnCol, authorCol, genreCol, yearCol);
        table.setItems(bookList);


        // Criar o layout da aba
        VBox layout = new VBox(10, titleField, isbnField, authorField, genreField, yearField, saveButton, table);
        layout.setPadding(new javafx.geometry.Insets(10));

        // Criar a aba no UIController
        createTab("Cadastro de Livros", layout);
    }
}

