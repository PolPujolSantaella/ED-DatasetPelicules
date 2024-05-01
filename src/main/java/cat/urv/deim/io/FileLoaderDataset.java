package cat.urv.deim.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import cat.urv.deim.exceptions.ElementNoTrobat;
import cat.urv.deim.exceptions.ElementRepetit;
import cat.urv.deim.models.MultiLlista;
import cat.urv.deim.models.Data;



public class FileLoaderDataset {

    public static void carregarDataset(String path, MultiLlista <Integer, Integer> multilist) throws ElementRepetit, ElementNoTrobat{
        BufferedReader br = null; 
        int id_pelicula = 0;
        try{
            File file = new File(path);
            br = new BufferedReader(new FileReader(file)); 
            String st; 
            while ((st = br.readLine()) != null){
                String[] info = st.split(","); 
                if (info.length != 3){
                    String[] idP = info[0].split(":");
                    id_pelicula = Integer.parseInt(idP[0]);
                } else {
                    int idU = Integer.parseInt(info[0]); 
                    int val = Integer.parseInt(info[1]); 
                    String[] data = info[2].split("-"); 
                    int any = Integer.parseInt(data[0]);
                    int mes = Integer.parseInt(data[1]);
                    int dia = Integer.parseInt(data[2]);
                    Data d = new Data(dia, mes, any);
                    multilist.inserir(id_pelicula, idU, val, d);
                }
            }
        } catch (FileNotFoundException ex) {

        } catch (IOException ex) {

        }
        if (br != null) {
            try {
                br.close();
            } catch (IOException ex) {

            }
        }
    }

}