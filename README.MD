Java version 20.0.2.
Sistema Operativo Windows 11.
Sistema de Codificacion UTF-8.

El programa se encarga de hacer todas las combinaciones posibles entre cierto numero de nombres y apellidos. 

En la clase App:
-se crea un nuevo tipo ServiceFile en el cual estan todas las operaciones.
-Al crear el objeto se le pasa los parametros que requiere la aplicacion.
-En el try se les pasa la ubicacion de la lista de nombres y apellidos .
-Y se ejecuta finalmente el programa para crear un archivo en donde tendran las combinaciones.


En la clase Services File:
-Tiene una serie de aributos los cuales se van a utilizar en diferentes medotos de esta.
-public ServicesFile: Es el contructor que le da valores a los atributos
-private void openFile: Se encarga de crear un objeto de tipo File.
-private List<String> readFile(): Se encarga de leer lo que hay dentro de un documento.
-private void closedFile(): Cierra el objeto de tipo File.
-private List<String> extractStrings: Reune a los tres metodos antes utilizados.
-public void setListName: Se le pasa los valores que va a tomar la lista de nombres.
-public List<Name> getListName: Devuelve la lista de nombres.
-private String[] separateNameAndGender: Se encarga de separar los nombres del genero.
-public void setListLastName: Se le pasa los valores que va a tomar la lista de apellidos.
-public List<String> getListLastName: Devuelve la lista de apellidos.
-private String[] possibleCombinations: Se encarga mediante una formula realizar todas las combinaciones posibles.
-private boolean comparateGender: Compara el genero y devuelve cierto objeto de tipo boolean dependiendo las especificaciones.
-private String comparateGetGender: Obtiene el genero que se va a comparar.
-private void eraserZero: Se borran las combinaciones donde el genero no coincide.
-private void changeWords: Cambia la lista de combinaciondes dependiendo de que especifico el usuario.
-private String changeWordsMm: Cambia la palabra poniendo la primera en mayuscula.
-private void readyList: Metodo que obtiene la lista de combinaciones ya con las modificaciones del usuario.
-public void writeCombined: Metodo que escribe la lista de combinaciones en un archivo.
-private void actuFile: Se actualiza el objeto de tipo File.
-private void createWrite: Se les da un valor a los objetos que van a escribir en un archivo.
-private void writeFileCombined: Escribe la lista de combinaciones en un archivo.
-private void closeWrite: Cierra el archivo de Write.
-private String changeListCombinedToString: Se encarga de copilar todos los objetos de tipo string de la lista de combinaciones.

