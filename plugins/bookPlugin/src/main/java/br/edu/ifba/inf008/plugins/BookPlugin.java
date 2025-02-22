package br.edu.ifba.inf008.plugins;

import br.edu.ifba.inf008.interfaces.IPlugin;
import br.edu.ifba.inf008.interfaces.ICore;
import br.edu.ifba.inf008.interfaces.IUIController;

import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.Node;

public class BookPlugin implements IPlugin {

    private Book book;
    private TableView<Book> table;

    @Override
    public boolean init() {
        IUIController uiController = ICore.getInstance().getUIController();

        // Criar item de menu para abrir a tela de cadastro de livros
        MenuItem menuItem = uiController.createMenuItem("Plugins", "Cadastro de Livros");
        menuItem.setOnAction(e -> openBookTab(uiController));

        return true;
    }

    private void openBookTab(IUIController uiController) {
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

            if (book.createBook(title, ISBN, author, genre, year)) {
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
        table = new TableView<>();
        TableColumn<Book, String> titleCol = new TableColumn<>("Título");
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));

        TableColumn<Book, String> isbnCol = new TableColumn<>("ISBN");
        isbnCol.setCellValueFactory(new PropertyValueFactory<>("ISBN"));

        TableColumn<Book, String> authorCol = new TableColumn<>("Autor");
        authorCol.setCellValueFactory(new PropertyValueFactory<>("author"));

        TableColumn<Book, String> genreCol = new TableColumn<>("Gênero");
        genreCol.setCellValueFactory(new PropertyValueFactory<>("genre"));

        TableColumn<Book, String> yearCol = new TableColumn<>("Ano");
        yearCol.setCellValueFactory(new PropertyValueFactory<>("publicationYear"));

        table.getColumns().addAll(titleCol, isbnCol, authorCol, genreCol, yearCol);

        // Criar o layout da aba
        VBox layout = new VBox(10, titleField, isbnField, authorField, genreField, yearField, saveButton, table);
        layout.setPadding(new javafx.geometry.Insets(10));

        // Criar a aba no UIController
        uiController.createTab("Cadastro de Livros", layout);
    }
}