package br.edu.ifba.inf008.shell;

import java.time.LocalDate;

import br.edu.ifba.inf008.interfaces.*;

public class Loan implements ILoan {
    private int id;
    private IUser userInstance;
    private IBook bookInstance;
    private LocalDate loanDate;
    private String returnDate;
    
    
    public Loan(int id, IUser userInstance, IBook bookInstance, LocalDate loanDate, String returnDate) {
        this.id = id;
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


}
