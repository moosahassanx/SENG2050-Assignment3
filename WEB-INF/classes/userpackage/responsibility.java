/*
    Assignment 3: responsibility.java
    Josh R(c3324541), Moosa H (), Keeylan H ()
    -----------------------------------------------------
    Purpose: this file will hold all the information that relates to the responsibilities.
*/
package userpackage;

import javax.swing.JSpinner.DateEditor;

public class responsibility {
    
    private String responsible;
    private String description;
    private String dateStarted;
    private String dateComplete;
    private boolean completed;

    public responsibility(){

    }

    public responsibility(String responsible, String description, String dateStarted, String dateComplete){
        this.responsible = responsible;
        this.description = description;
        this.dateStarted = dateStarted;
        this.dateComplete = dateComplete;
        this.completed = false;
    }

    public String getResponsible(){
        return responsible;
    }
;
    public String getDescription(){
        return description;
    }

    public String getDateStarted(){
        return dateStarted;
    }

    public String getDateComplete(){
        return dateComplete;
    }





}
