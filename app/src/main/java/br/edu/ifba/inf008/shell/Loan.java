package br.edu.ifba.inf008.shell;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

import br.edu.ifba.inf008.interfaces.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Loan implements ILoan, Serializable {
    private String id;
    private IUser userInstance;
    private IBook bookInstance;
    private int status;
    private LocalDate loanDate;
    private LocalDate returnDate;
    private LocalDate deliveryDate;
    
    
    public Loan(IUser userInstance, IBook bookInstance, LocalDate loanDate, LocalDate returnDate) {
        this.id = generateLoanId();
        this.userInstance = userInstance;
        this.bookInstance = bookInstance;
        this.loanDate = loanDate;
        this.returnDate = returnDate;
    }

    public String generateLoanId() {
        return UUID.randomUUID().toString();
    }

    public String getID(){
        return id;
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

    public LocalDate getDateDelivery(){
        return deliveryDate;
    }

    public int getStatus(){
        return status;
    }

    public void setDateDelivery(LocalDate date){
        this.deliveryDate = date;
    }

    public void returned(){
        this.status = 0;
    }

    public void late(){
        this.status = 1;
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
