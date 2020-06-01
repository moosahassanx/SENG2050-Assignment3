package userpackage;

public class File {
    
    private String userUploaded;
    private String fileName;
    private String description;

    public File(){

    }

    public File(String userUploaded, String fileName, String description){
        this.userUploaded = userUploaded;
        this.fileName = fileName;
        this.description = description;
    }

    public String getUserUploaded(){
        return userUploaded;
    }

    public String getFileName(){
        return fileName;
    }

    public String getDescription(){
        return description;
    }

    public void setUserUploaded(String userUploaded){
        this.userUploaded = userUploaded;
    }

    public void setFileName(String fileName){
        this.fileName = fileName;
    }

    public void setDescription(String description){
        this.description = description;
    }

}