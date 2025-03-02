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
    private IBookController bookController = Core.getInstance().getBookController();
    private IUserController userController = Core.getInstance().getUserController();
    private ILoanController loanController = Core.getInstance().getLoanController();
    private IIOController ioController = Core.getInstance().getIOController();

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
                //bookList.add(book);
                ioController.addBook(book);  // Atualiza a ObservableList
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
        tableBook = new TableView<>(ioController.getBookListObs()); // Inicializa com a lista

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
        tableBook.setItems(ioController.getBookListObs());


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
                //userList.add(user);  // Atualiza a ObservableList
                ioController.addUser(user);
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
        tableUser = new TableView<>(ioController.getUserListObs()); // Inicializa com a lista

        TableColumn<IUser, String> nameCol = new TableColumn<>("Nome");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<IUser, String> emailCol = new TableColumn<>("Email");
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));


        Collections.addAll(tableUser.getColumns(), nameCol, emailCol);
        tableUser.setItems(ioController.getUserListObs());


        // Criar o layout da aba
        VBox layout = new VBox(10, nameField, emailField, passwordField,  saveButton, tableUser);
        layout.setPadding(new javafx.geometry.Insets(10));

        // Criar a aba no UIController
        createTab("Cadastro de Usuários", layout);
    }

    public void openLoanTab() {
        Stage stage = new Stage();
        stage.setTitle("Gerar Empréstimo");
    
        ComboBox<IUser> userComboBox = new ComboBox<>(ioController.getUserListObs());
        userComboBox.setPromptText("Selecione um Usuário");
        
        //Proibir emprestimo de livro já emprestado. Pois todos são únicos.
        ListView<IBook> bookListView = new ListView<>(ioController.getBookListObs());
        bookListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE); // Permite múltipla seleção
        bookListView.setPrefHeight(150); // Ajuste de tamanho para melhor visualização
    
        DatePicker datePicker = new DatePicker(LocalDate.now());
    
        Button saveButton = new Button("Realizar Empréstimo");
        saveButton.setOnAction(e -> {
            IUser user = userComboBox.getValue();
            ObservableList<IBook> selectedBooks = bookListView.getSelectionModel().getSelectedItems();
            LocalDate dateLoan = datePicker.getValue();
    
            if (user == null || selectedBooks.isEmpty() || dateLoan == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Preencha todos os campos", ButtonType.OK);
                alert.showAndWait();
                return;
            }else if(selectedBooks.size() > 5){
                Alert alert = new Alert(Alert.AlertType.ERROR, "Só é permitido até cinco livros por empréstimo", ButtonType.OK);
                alert.showAndWait();
                return;
            }
    
            for (IBook book : selectedBooks) {
                ILoan loan = loanController.setLoan(user, book, dateLoan);
                if (loan == null) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Erro no registro de empréstimo para o livro: " + book.getTitle(), ButtonType.OK);
                    alert.showAndWait();
                    continue; // Pula para o próximo livro se houver erro
                }
    
                userController.getListBooks(user).add(book);
                bookController.isLoan(book);
                ioController.addLoan(loan);
            }
    
            Alert success = new Alert(Alert.AlertType.INFORMATION, "Empréstimo(s) registrado(s) com sucesso!", ButtonType.OK);
            success.showAndWait();
        });
    
        VBox layout = new VBox(10, userComboBox, bookListView, datePicker, saveButton);
        Scene scene = new Scene(layout, 500, 450);
        stage.setScene(scene);
        stage.show();
    }

    public void openLoanReturnTab() {
        //implementar tela de devolução

        /*
         Perguntar email do usuário;
         Carregar a lista de livros que tem esse usuario vinculado;
         Botão: Devolver esses livros?
         Yes - Retirar livros da lista pessoal do usuário, trocar stutus de emprestado dos livros, e adicionar uma coluna de data de entrega no livro no loan.
            se atrasado (data atual > data de retorno), gerar cálculo de multa e mostrar na tela.
        obs: tem que impactar nos relatórios de empréstimo e atrasado.
         */
    }

}

