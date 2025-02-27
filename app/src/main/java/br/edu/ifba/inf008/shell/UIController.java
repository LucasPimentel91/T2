package br.edu.ifba.inf008.shell;

import br.edu.ifba.inf008.interfaces.*;
import br.edu.ifba.inf008.shell.PluginController;

import java.time.LocalDate;
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
    private TableView<IBook> tableBook;
    private TableView<IUser> tableUser;
    private TableView<ILoan> tableLoan;
    private ObservableList<IBook> bookList = FXCollections.observableArrayList();
    private ObservableList<IUser> userList = FXCollections.observableArrayList();
    private ObservableList<ILoan> loanList = FXCollections.observableArrayList();
    private IBookController bookController = Core.getInstance().getBookController();
    private IUserController userController = Core.getInstance().getUserController();
    private ILoanController loanController = Core.getInstance().getLoanController();

    public UIController() {
    }

    @Override
    public void init() {
        uiController = this;
    }

    public static UIController getInstance() {
        return uiController;
    }

    public void start(Stage primaryStage) {
        primaryStage.setTitle("Sua Biblioteca");
        
        menuBar = new MenuBar();
        
        menuBar.getMenus().add(new Menu("Livro"));
        MenuItem menuItem = createMenuItem("Livro", "Cadastrar Livro");
        menuBar.getMenus().get(0).getItems().add(menuItem);
        MenuItem menuItem2 = createMenuItem("Usuário", "Cadastrar Usuário");
        menuBar.getMenus().get(0).getItems().add(menuItem2);
        MenuItem menuItem3 = createMenuItem("Empréstimo", "Fazer empréstimo");
        menuBar.getMenus().get(0).getItems().add(menuItem3);
        menuItem.setOnAction(e -> {
            openBookTab();
        });
        menuItem2.setOnAction(e -> {
            openUserTab();
        });
        menuItem3.setOnAction(e -> {
            openLoanTab();
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
        
            if (bookController.requestCreateBook(title, ISBN, author, genre, year)) {
                IBook book = bookController.createBook(title, ISBN, author, genre, year);
                bookList.add(book);  // Atualiza a ObservableList
                tableBook.refresh();  // Atualiza a exibição da tabela
                titleField.clear();
                isbnField.clear();
                authorField.clear();
                genreField.clear();
                yearField.clear();
                //bookController.teste(book);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Erro ao adicionar livro!", ButtonType.OK);
                alert.showAndWait();
            }
        });

        // Criando a Tabela
        tableBook = new TableView<>(bookList); // Inicializa com a lista

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

        Collections.addAll(tableBook.getColumns(), titleCol, isbnCol, authorCol, genreCol, yearCol);
        tableBook.setItems(bookList);


        // Criar o layout da aba
        VBox layout = new VBox(10, titleField, isbnField, authorField, genreField, yearField, saveButton, tableBook);
        layout.setPadding(new javafx.geometry.Insets(10));

        // Criar a aba no UIController
        createTab("Cadastro de Livros", layout);
    }

    public void openUserTab() {
        
        TextField nameField = new TextField();
        nameField.setPromptText("Nome");
        
        TextField emailField = new TextField();
        emailField.setPromptText("E-mail");
        
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Senha");

        // Botão para salvar
        Button saveButton = new Button("Salvar");
        saveButton.setOnAction(e -> {
            String name = nameField.getText();
            String email = emailField.getText();
            String password = passwordField.getText();
        
            if (userController.requestCreateUser(name, email, password)) {
                IUser user = userController.createUser(name, email, password);
                userList.add(user);  // Atualiza a ObservableList
                tableUser.refresh();  // Atualiza a exibição da tabela
                nameField.clear();
                emailField.clear();
                passwordField.clear();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Erro ao adicionar Usuário!", ButtonType.OK);
                alert.showAndWait();
            }
        });

        // Criando a Tabela
        tableUser = new TableView<>(userList); // Inicializa com a lista

        TableColumn<IUser, String> nameCol = new TableColumn<>("Nome");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<IUser, String> emailCol = new TableColumn<>("Email");
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));


        Collections.addAll(tableUser.getColumns(), nameCol, emailCol);
        tableUser.setItems(userList);


        // Criar o layout da aba
        VBox layout = new VBox(10, nameField, emailField, passwordField,  saveButton, tableUser);
        layout.setPadding(new javafx.geometry.Insets(10));

        // Criar a aba no UIController
        createTab("Cadastro de Usuários", layout);
    }

    public void openLoanTab() {
        Stage stage = new Stage();
        stage.setTitle("Gerar Empréstimo");

        ComboBox<IUser> userComboBox = new ComboBox<>(userList);
        userComboBox.setPromptText("Selecione um Usuário");

        ComboBox<IBook> bookComboBox = new ComboBox<>(bookList);
        bookComboBox.setPromptText("Selecione um Livro");
        
        DatePicker datePicker = new DatePicker(LocalDate.now());
        
        Button saveButton = new Button("Realizar Empréstimo");
        saveButton.setOnAction(e -> {
            IUser user = userComboBox.getValue();
            IBook book = bookComboBox.getValue();
            LocalDate dateLoan = datePicker.getValue();
            
            if (user == null || book == null || dateLoan == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Preencha todos os campos", ButtonType.OK);
                alert.showAndWait();
                return;
            }
            
            ILoan loan = loanController.setLoan(user, book, dateLoan);
            userController.getListBooks(user).add(book);
            bookController.isLoan(book);
            //loanController.getDate(loan);
            loanList.add(loan);
            //tableLoan.setItems(loanList);
            //tableLoan.refresh();
            if(loan == null){
                Alert alert = new Alert(Alert.AlertType.ERROR, "Erro no registro!!!", ButtonType.OK);
                alert.showAndWait();
                return;
            }
            Alert success = new Alert(Alert.AlertType.INFORMATION, "Empréstimo registrado com sucesso!", ButtonType.OK);
            success.showAndWait();
        });
        /* 
        tableLoan = new TableView<>(loanList);
        TableColumn<ILoan, String> userCol = new TableColumn<>("Usuário");
        userCol.setCellValueFactory(data -> data.getValue().userProperty());
        
        TableColumn<ILoan, String> bookCol = new TableColumn<>("Livro");
        bookCol.setCellValueFactory(data -> data.getValue().bookProperty());
        
        TableColumn<ILoan, String> dateLoanCol = new TableColumn<>("Data do Empréstimo");
        dateLoanCol.setCellValueFactory(data -> data.getValue().dateLoanProperty());
        
        TableColumn<ILoan, String> dateReturnCol = new TableColumn<>("Data do Retorno");
        dateReturnCol.setCellValueFactory(data -> data.getValue().dateReturnProperty());
        
        Collections.addAll(tableLoan.getColumns(), userCol, bookCol, dateLoanCol, dateReturnCol);
        */

        VBox layout = new VBox(10, userComboBox, bookComboBox, datePicker, saveButton);
        //VBox layout = new VBox(10, userComboBox, bookComboBox, datePicker, saveButton, tableLoan);
        Scene scene = new Scene(layout, 500, 400);
        stage.setScene(scene);
        stage.show();
    }

    public ObservableList<ILoan> getObListLoan(){
        return loanList;
    }
}

