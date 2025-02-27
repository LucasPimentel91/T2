package br.edu.ifba.inf008.shell;

import java.time.LocalDate;

import br.edu.ifba.inf008.interfaces.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Loan implements ILoan {
    private IUser userInstance;
    private IBook bookInstance;
    private LocalDate loanDate;
    private LocalDate returnDate;
    
    
    public Loan(IUser userInstance, IBook bookInstance, LocalDate loanDate, LocalDate returnDate) {
        this.userInstance = userInstance;
        this.bookInstance = bookInstance;
        this.loanDate = loanDate;
        this.returnDate = returnDate;
    }

    public IUser getUser(){
        return userInstance;
    }

    public IBook getBook(){
        return bookInstance;
    }

    public LocalDate getDateLoan(){
        return loanDate;
    }

    public LocalDate getDateReturn(){
        return returnDate;
    }
    
    //public void teste(){ System.out.println(loanDate);}

    public StringProperty userProperty() {
        return new SimpleStringProperty(userInstance.getName());
    }

    public StringProperty bookProperty() {
        return new SimpleStringProperty(bookInstance.getTitle());
    }

    public StringProperty dateLoanProperty() {
        return new SimpleStringProperty(loanDate.toString());
    }

    public StringProperty dateReturnProperty() {
        return new SimpleStringProperty(returnDate.toString());
    }


}
