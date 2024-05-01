
package cat.urv.deim.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import cat.urv.deim.models.HashMapIndirecte;
import cat.urv.deim.models.Pelicula;

public class FileLoader {
    public static void carregarFitxerPelicules(String path, HashMapIndirecte<Integer, Pelicula> hPelicules) {
        BufferedReader br = null;
        try {
            File file = new File(path);
            br = new BufferedReader(new FileReader(file));
            String st;
            while ((st = br.readLine()) != null) {
                String[] params = st.split("###");
                if (params.length != 3) {
                    continue;
                }
                int idP = Integer.parseInt(params[0]);
                String titol = params[2];
                int any = Integer.parseInt(params[1]);
                Pelicula pelicula = new Pelicula(idP, titol, any, 0, 0);
                hPelicules.inserir(pelicula.getID(), pelicula);
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