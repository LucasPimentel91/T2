package br.edu.ifba.inf008.interfaces;

import java.time.LocalDate;

import javafx.beans.property.StringProperty;

public interface ILoan {
    public IUser getUser();
    public IBook getBook();
    public LocalDate getDateLoan();
    public LocalDate getDateReturn(); 
    public int getStatus()
    public void setDateDelivery(LocalDate date);
    public void returned();
    public void late();
    //public void teste();
    public StringProperty userProperty();
    public StringProperty bookProperty();
    public StringProperty dateLoanProperty();
    public StringProperty dateReturnProperty();

}
