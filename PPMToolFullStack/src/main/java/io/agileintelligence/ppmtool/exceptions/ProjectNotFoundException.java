package io.agileintelligence.ppmtool.exceptions;

public class ProjectNotFoundException  extends  RuntimeException{
    public ProjectNotFoundException(String message){
        super(message);
    }
}
