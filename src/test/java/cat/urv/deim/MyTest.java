package cat.urv.deim;

import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.jupiter.api.Test;

import cat.urv.deim.models.HashMapIndirecte;
import cat.urv.deim.models.Pelicula;
import cat.urv.deim.models.RecomanacioPelicules;
import cat.urv.deim.models.Data;

import cat.urv.deim.exceptions.ElementNoTrobat;
import cat.urv.deim.exceptions.ElementRepetit;
import cat.urv.deim.models.Usuaris;
import cat.urv.deim.io.FileLoaderDataset;
import cat.urv.deim.io.FileLoader;
import cat.urv.deim.models.MultiLlista;
import org.junit.jupiter.api.BeforeEach;





public class MyTest {

    private HashMapIndirecte<Integer, Pelicula> hPelicules;
    private HashMapIndirecte<Integer, Pelicula> hPeliculesMult;
    private HashMapIndirecte<Integer, Usuaris> hUsuaris;
    private MultiLlista<Integer, Integer> multilist;

    @BeforeEach
    public void setUp() throws ElementRepetit, ElementNoTrobat {
        hPelicules = new HashMapIndirecte<>(17771);
        hPeliculesMult = new HashMapIndirecte<>(17771);
        hUsuaris = new HashMapIndirecte<>(10000);
        FileLoader.carregarFitxerPelicules("movies.txt", hPelicules);
        multilist = new MultiLlista(hPeliculesMult, hUsuaris);
        FileLoaderDataset.carregarDataset("dataset.txt", multilist);
    }
 

    @Test
    public void PeliculesTotals() throws ElementRepetit, ElementNoTrobat {
        assertEquals(17762, hPelicules.longitud());
    }
     
    @Test
    public void UsuarisTotals() throws ElementRepetit, ElementNoTrobat {
        assertEquals(109994, hUsuaris.longitud());
    }
    
    @Test
    public void AfegirPeliculaRecomanacio() throws ElementRepetit, ElementNoTrobat {
        RecomanacioPelicules r = new RecomanacioPelicules(multilist, hPelicules);  
        var recomanacions = r.RecomenarPelicules(2218492); 
    }

    @Test
    public void RecomanacioNoHiHaPelicules() throws ElementNoTrobat, ElementRepetit{
        Data d1 = new Data(2019, 12, 12);
        
        multilist.inserir(1112, 8888, 5, d1);
        multilist.esborrar(1112, 8888);

        //RecomanacionsPeliculas r = new RecomanacionsPeliculas(multilist); 
       // var recomanacions = r.recomanarPeliculas(8888, hPelicules);  
    }


}
