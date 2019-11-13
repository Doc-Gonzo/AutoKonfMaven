package Matze.Controller;

import java.io.FileNotFoundException;

public class exc_fileNotFound extends FileNotFoundException {

    public exc_fileNotFound(){
        super();
    }

    public  exc_fileNotFound(String message){
        super(message);
    }

}
