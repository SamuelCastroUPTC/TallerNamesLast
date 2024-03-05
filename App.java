
import src.co.edu.uptc.services.ServicesFile;

public class App {
    public static void main(String[] args)   {
        ServicesFile servicesFile= new ServicesFile(Integer.parseInt(args[0]), Integer.parseInt(args[1]), args[2], args[3]);
        try {
            servicesFile.setListName("data\\NamesAndGender.txt");
            servicesFile.setListLastName("data\\LastName.txt");
            servicesFile.writeCombined();
        } catch (Exception e) {
            System.out.println("Revisar datos");
        }
        
    }
}