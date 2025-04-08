package es.ies.puerto.controller.abstractas;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public abstract class AbstractController {

    private Properties propertiesIdioma;

    public void setPropertiesIdioma(Properties properties) {
        propertiesIdioma = properties;
    }

    public Properties getPropertiesIdioma() {
        return propertiesIdioma;
    }


    public Properties loadIdioma(String nombreFichero, String idioma) {
        Properties properties = new Properties();
        
        if (nombreFichero == null || idioma == null) {
            return properties;
        }
        
        String path = "src/main/resources/" + nombreFichero+"-"+idioma+".properties";

        File file = new File(path);

        if (!file.exists() || !file.isFile()) {
            System.out.println("Path:"+file.getAbsolutePath());
            return properties;
        }
        
        try {
            FileInputStream input = new FileInputStream(path);
            InputStreamReader isr = new InputStreamReader(input, "UTF-8");
            properties.load(isr);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return properties;
    }

    public void savePropertiesUsuario(String nombre, String contrasenia,String email) {   
        String path = "src/main/resources/usuario.txt";
        File file = new File(path);
        if (file.exists()) {
            file.delete();
        }try {
        file.createNewFile();       
          try (BufferedWriter writer = new BufferedWriter(new FileWriter(file,true))) {
            writer.write(nombre);
              writer.newLine();
              writer.write(contrasenia);
              writer.newLine();
              writer.write(email);
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<String> loadPropertiesUsuario() throws FileNotFoundException{
        List<String>arrayList=new ArrayList<>();
        String path = "src/main/resources/usuario.txt";
        File file = new File(path);
       BufferedReader reader=new BufferedReader(new FileReader(file));
      try {
        String line;
        while ((line=reader.readLine())!=null) {
            arrayList.add(line);
        }
        
    } catch (IOException e) {
        e.printStackTrace();
    }
        return arrayList;
    }
}
