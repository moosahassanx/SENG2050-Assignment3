/*
    Assignment 3: responsibility.java
    Josh R(c3324541), Moosa H (c3324541), Keeylan H ()
    -----------------------------------------------------
    Purpose: this file will hold all the information that relates to the responsibilities.
*/
package userpackage;

public class responsibility {
    
    private String responsible;
    private String description;
    private String dateStarted;
    private String dateComplete;
    private String responseID;
    private boolean completed;

    public responsibility(){

    }

    public responsibility(String responsible, String description, String dateStarted, String dateComplete, boolean completed){
        this.responsible = responsible;
        this.description = description;
        this.dateStarted = dateStarted;
        this.dateComplete = dateComplete;
        this.completed = completed;
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

    public String getCompletion(){
        if(this.completed == true){
            String completion = "Yes";
            return completion;
        }
        String completion = "No";
        return completion;
    }

    public void setResponseID(String responseID){
        this.responseID = responseID;
    }

    public String getResponseID(){
        return responseID;
    }
}
