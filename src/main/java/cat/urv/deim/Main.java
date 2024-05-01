package cat.urv.deim;


import cat.urv.deim.exceptions.ElementNoTrobat;
import cat.urv.deim.exceptions.ElementRepetit;
import cat.urv.deim.io.FileLoader;
import cat.urv.deim.io.FileLoaderDataset;
import cat.urv.deim.models.HashMapIndirecte;
import cat.urv.deim.models.LlistaPelicules;
import cat.urv.deim.models.MultiLlista;
import cat.urv.deim.models.Pelicula;
import cat.urv.deim.models.RecomanacioPelicules;
import cat.urv.deim.models.Usuaris;

public class Main {

    public static <A, B> void main(String[] args) throws ElementRepetit, ElementNoTrobat {
        HashMapIndirecte<Integer, Pelicula> hPelicules = new HashMapIndirecte<>(17771);
        HashMapIndirecte <Integer, Pelicula> hPeliculesMult = new HashMapIndirecte<>(17771);
        HashMapIndirecte<Integer, Usuaris> hUsuaris = new HashMapIndirecte<>(10000);
        FileLoader.carregarFitxerPelicules("movies.txt", hPelicules);
        MultiLlista<Integer, Integer> multilist = null; 
        multilist = new MultiLlista(hPeliculesMult, hUsuaris);
        FileLoaderDataset.carregarDataset("dataset.txt", multilist);


        RecomanacioPelicules r = new RecomanacioPelicules(multilist, hPelicules);  
        int id_usuari=2304801;
        LlistaPelicules recomanacions = r.RecomenarPelicules(id_usuari); 
        
        System.out.println("Recomanacions per a l'usuari "+id_usuari+":");
        System.out.println(" ");

        int compt=1; 
        if (recomanacions.esBuida()){
            System.out.println("No hi ha usuaris similars");
        } else{
            for (int i=0; i<recomanacions.longitud(); i++){
                System.out.println("Recomanació "+compt+" :");
                System.out.println(recomanacions.buscarPelicula(i).getID()+": "+recomanacions.buscarPelicula(i).getTitol());
                System.out.println("L'han vist "+ recomanacions.buscarPelicula(i).getCompt()+" persones");
                System.out.println("La valoració mitjana és de "+ String.format("%.2f", recomanacions.buscarPelicula(i).getPromig()));
                System.out.println(" ");
                compt++;
            }
        }
    
    
    }
}
