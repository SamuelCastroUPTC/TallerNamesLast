package src.co.edu.uptc.services;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import src.co.edu.uptc.models.Name;

public class ServicesFile {
    private FileWriter fileWriter;
    private BufferedWriter bufferedWriter;
    private BufferedReader bufferedReader;
    private File file;
    private List<Name> listName;
    private List<String> listLastName;
    private String charsetName;
    private int names;
    private int lastNames;
    private String words;
    private String character;
    private List<String> combinedList;


    public ServicesFile(int names, int lastName,String words, String character){
        this.charsetName="UTF-8";
        listName= new ArrayList<Name>();
        this.names=names;
        this.lastNames=lastName;
        this.words=words;
        this.character=character;
        combinedList= new ArrayList<String>();
    }

    private void openFile(String path) throws Exception{
        file= new File(path);
        if (!file.exists()) {
            throw new Exception("El archivo no existe");
        }else{
            Reader reader = new InputStreamReader(new FileInputStream(file),this.charsetName);
            bufferedReader = new BufferedReader(reader);
        }
        
    }

    private List<String> readFile() throws Exception{
        String cont="";
        List<String> list = new ArrayList<String>();
        while ((cont=bufferedReader.readLine())!=null) {
            list.add(cont);
        }
        return list;
    }

    private void closedFile() throws Exception{
        if (bufferedReader!= null) {
            bufferedReader.close();
        }
    }

    private List<String> extractStrings(String path) throws Exception{
        this.openFile(path);
        List<String> list=this.readFile();
        this.closedFile();
        return list;
    }

    public void setListName(String path) throws Exception{
        for (String nameAndGender : extractStrings(path)) {
            String separate[]= separateNameAndGender(nameAndGender);
            Name name= new Name();
            name.setName(separate[0]);
            name.setGender(separate[1]);
            listName.add(name);
        }

    }

    public List<Name> getListName(){
        return listName;
    }

    private String[] separateNameAndGender(String line){
        String[] cont= line.split(" ");
        return cont;
    }

    public void setListLastName(String path) throws Exception{
        listLastName=extractStrings(path);
    }

    public List<String> getListLastName(){
        return listLastName;
    }

    private String[] possibleCombinations(int names, int lastName){
        int totalnameslast=(int) (((Math.pow(listName.size(), names))*((Math.pow(listLastName.size(), lastName))))-1);
        String[] totalPossible= new String[totalnameslast+1];
        for (int i = 0; i < totalPossible.length; i++) {
            totalPossible[i]="";
        }
        int stop=totalnameslast/listName.size();
        for (int i = 1; i <= names+lastName; i++) {
            int cont=0;
            if (i<=names && i>1) {
                stop=stop/listName.size();
            }
            if (i>names && i>0) {
                stop=stop/listLastName.size();
            }
            int contstop=0;
            for (int j = 0; j <=totalnameslast; j++) {
                if (contstop>stop) {
                    cont=cont+1;
                    contstop=0;
                }
                if(cont==listLastName.size() || cont==listName.size()){
                    cont=0;
                }
                if (i>names) {
                    totalPossible[j]=totalPossible[j]+listLastName.get(cont)+character;
                }else{ 
                    if (i>1) {
                        if (comparateGender(totalPossible[j],listName.get(cont))==true) {
                            totalPossible[j]=totalPossible[j]+listName.get(cont).getName()+character;
                        }else{
                            totalPossible[j]="0";
                        }
                    }else{
                        totalPossible[j]=totalPossible[j]+listName.get(cont).getName()+character;
                    }
                }
                contstop=contstop+1;
            }
            cont=0;
        }
        return totalPossible;
    }

    private boolean comparateGender(String name, Name name2){
        boolean comparate=false;
        String realName=comparateGetGender(name);
        for (Name name3 : listName) {
            if(name3.getName().compareTo(realName)==0){
                if (name3.getGender().compareTo(name2.getGender())==0) {
                    comparate=true;
                    
                }
            }
        }
        return comparate;
    }

    private String comparateGetGender(String name){
        for (int i = 0; i < name.length(); i++) {
            if (name.substring(i, i+1).compareTo(character)==0) {
                return name.substring(0, i);
            }
        }
        return "";
    }

    private void eraserZero(){
        String[] totalStrings=possibleCombinations(names, lastNames);
        for (int i = 0; i < totalStrings.length; i++) {
            if(totalStrings[i].substring(0, 1).compareTo("0")==0){

            }else{
                combinedList.add(totalStrings[i]);
            }
        }
    }

    private void changeWords(){
        eraserZero();
        String componente="";
        for (int i = 0; i < combinedList.size(); i++) {
            switch (words) {
                case "M":
                componente = combinedList.get(i).toUpperCase();
                combinedList.set(i, componente);
                    break;
                case "m":
                componente = combinedList.get(i).toLowerCase();
                combinedList.set(i, componente);
                    break;
                case "Mm":
                    componente=changeWordsMm(combinedList.get(i));
                    combinedList.set(i, componente);
                    break;
                default:
                    break;
            }
        }
    }

    private String changeWordsMm(String word){
        int contCharacter=1;
        for (int i = 0; i < word.length(); i++) {
            if (contCharacter==(names+lastNames)) {
                break;
            }
            if (i==0) {
                word.substring(0, 1).toUpperCase();
            }else{
            }if (word.substring(i, i+1).compareTo(character)==0) {
                word.substring(i+1, i+2).toUpperCase();
                contCharacter=contCharacter+1;
            }
        }
        return word;
    }

    private void readyList(){
        changeWords();
    }

    public void writeCombined() throws IOException{
        readyList();
        actuFile();
        createWrite();
        writeFileCombined();
        closeWrite();
    }

    private void actuFile(){
        file= new File(".\\combined.txt");
    }
    private void createWrite() throws IOException{
        fileWriter= new FileWriter(file);
        bufferedWriter= new BufferedWriter(fileWriter);
    }

    private void writeFileCombined() throws IOException{
        bufferedWriter.write(changeListCombinedToString());
    }

    private void closeWrite() throws IOException{
        bufferedWriter.close();
    }

    private String changeListCombinedToString(){
        String acumulateCombined="";
        for (String string : combinedList) {
            acumulateCombined=acumulateCombined+string+"\n";
        }
        return acumulateCombined;
    }

}