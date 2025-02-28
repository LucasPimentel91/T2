package br.edu.ifba.inf008.shell;

import java.io.*;
import java.util.ArrayList;
import java.util.stream.Collectors;

import br.edu.ifba.inf008.interfaces.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class IOController implements IIOController, Serializable {
    private static final long serialVersionUID = 1L;
    private static final String FILE_PATH = "app/config/database.ser";

    private ArrayList<IBook> bookList = new ArrayList<>();
    private ArrayList<IUser> userList = new ArrayList<>();
    private ArrayList<ILoan> loanList = new ArrayList<>();

    private transient ObservableList<IBook> bookListObs = FXCollections.observableArrayList();
    private transient ObservableList<IUser> userListObs = FXCollections.observableArrayList();
    private transient ObservableList<ILoan> loanListObs = FXCollections.observableArrayList();
    private transient ObservableList<ILoan> loanLateListObs = FXCollections.observableArrayList(
        loanListObs.stream().filter(loan -> loan.getStatus() == 1).collect(Collectors.toList())
    );

    public IOController() {
        loadData();
        updateObservableLists();
    }

    public ArrayList<IUser> getListUser() {
        return userList;
    }

    public ArrayList<IBook> getListBook() {
        return bookList;
    }

    public ArrayList<ILoan> getListLoan() {
        return loanList;
    }

    public ObservableList<IBook> getBookListObs() {
        return bookListObs;
    }

    public ObservableList<IUser> getUserListObs() {
        return userListObs;
    }

    public ObservableList<ILoan> getLoanListObs() {
        return loanListObs;
    }
    public ObservableList<ILoan> getLoanLateListObs() {
        return loanLateListObs;
    }

    public void addBook(IBook book) {
        bookList.add(book);
        bookListObs.add(book);
        saveData();
    }

    public void addUser(IUser user) {
        userList.add(user);
        userListObs.add(user);
        saveData();
    }

    public void addLoan(ILoan loan) {
        loanList.add(loan);
        loanListObs.add(loan);
        saveData();
    }

    private void updateObservableLists() {
        bookListObs.setAll(bookList);
        userListObs.setAll(userList);
        loanListObs.setAll(loanList);
    }

    public void saveData() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            out.writeObject(bookList);
            out.writeObject(userList);
            out.writeObject(loanList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public void loadData() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return; // Se o arquivo não existe, não há nada para carregar
        }

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            bookList = (ArrayList<IBook>) in.readObject();
            userList = (ArrayList<IUser>) in.readObject();
            loanList = (ArrayList<ILoan>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
