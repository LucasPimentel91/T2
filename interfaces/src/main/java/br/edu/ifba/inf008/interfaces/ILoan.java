package br.edu.ifba.inf008.interfaces;

import java.time.LocalDate;

public interface ILoan {
    public IUser getUser();
    public IBook getBook();
    public LocalDate getDateLoan();

}
