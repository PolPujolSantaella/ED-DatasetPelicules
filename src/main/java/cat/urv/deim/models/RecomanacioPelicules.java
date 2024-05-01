package cat.urv.deim.models;

import java.util.List;

import java.util.Iterator;

import cat.urv.deim.exceptions.ElementNoTrobat;

public class RecomanacioPelicules {
    
    private MultiLlista<Integer, Integer> multilist;
    private HashMapIndirecte<Integer, Pelicula> pelicules;

    private final int MAX_RECOMANACIONS = 3;
    private final double MIN_SIMILITUD = 25.0;
    private final int MIN_VISTES = 6000; 
    private final double MIN_VALORACIO = 3.8; 

    public RecomanacioPelicules(MultiLlista<Integer, Integer> multilist, HashMapIndirecte<Integer, Pelicula> pelicules){
        this.multilist = multilist;
        this.pelicules = pelicules;
    }

    public LlistaPelicules RecomenarPelicules(int id_usuari) throws ElementNoTrobat{
        LlistaPelicules recomanacions = new LlistaPelicules();

        List<Integer> llistaPelicules = multilist.columna(id_usuari); 
        HashMapIndirecte <Integer, Usuaris> similituds = new HashMapIndirecte<>(300);

        for (Integer pelicula : llistaPelicules){
            List<Integer> UsuarisSimilars = multilist.fila(pelicula); 
            for (Integer usuari : UsuarisSimilars){
                if (usuari != id_usuari && !similituds.buscar(usuari)){
                        double similitudUsuari = calcularSimilitudUsuari(id_usuari, usuari);  
                        if (similitudUsuari >= MIN_SIMILITUD){
                            similituds.inserir(usuari,new Usuaris(usuari, similitudUsuari)); 
                        }
                }
            }
        }

        int US = similituds.longitud(); 
        LlistaDoblementEncadenada<Pelicula> promigs = new LlistaDoblementEncadenada<Pelicula>();
        HashMapIndirecte <Integer, Pelicula> peliculesValorades = new HashMapIndirecte<>(US); 
        Iterator<Usuaris> it = similituds.iterator();

        while (it.hasNext()){
            Usuaris u = it.next();   
            List<Integer> peliculesUsuari = multilist.columna(u.getID());
            for (Integer pelicula : peliculesUsuari){
                Pelicula p = pelicules.element(pelicula);
                if (!promigs.Mateixtitol(p.getTitol()) && !peliculesValorades.buscar(pelicula)){
                    List<Integer> UsuarisPelicules = multilist.fila(pelicula);
                    p = CalcularPromig(UsuarisPelicules, p);
                    if (p.getPromig() > MIN_VALORACIO){
                        promigs.inserir(p);
                    }
                }
                peliculesValorades.inserir(p.getID(), p);
            }
        }

        int i=0; 
        while (i<promigs.longitud() && recomanacions.longitud() < MAX_RECOMANACIONS){
            Pelicula recomana = promigs.buscarPelicula(i);
            recomanacions.inserir(recomana); 
            i++;
        }
        
        return recomanacions; 
    }

    public double calcularSimilitudUsuari(int id_usuari1, int id_usuari2) throws ElementNoTrobat {
        List<Integer> peliculesUsuari1 = multilist.columna(id_usuari1);
        List<Integer> peliculesUsuari2 = multilist.columna(id_usuari2);

        HashMapIndirecte <Integer, Boolean> Map = new HashMapIndirecte<Integer, Boolean>(100);
        for (Integer pelicula : peliculesUsuari2) {
            Map.inserir(pelicula, true);
        }

        double similitud = 0;
        for (Integer pelicula : peliculesUsuari1) {
            if (Map.buscar(pelicula)) {
                similitud++;
            }
        }

        double percent = ((similitud / peliculesUsuari1.size())*100); 

        return percent;
    }


    public Pelicula CalcularPromig (List<Integer> usuaris, Pelicula pelicula) throws ElementNoTrobat{
        int compt = usuaris.size();
        if (compt > MIN_VISTES){
            double suma=0;
            for (Integer usuari : usuaris){
                int val = multilist.getValoracio(pelicula.getID(), usuari);
                suma += val;  
            }
        
            double promig =  suma / (double) compt;

            pelicula.setPromig(promig);
            pelicula.setCompt(compt);
        }

        return pelicula;
    }
}