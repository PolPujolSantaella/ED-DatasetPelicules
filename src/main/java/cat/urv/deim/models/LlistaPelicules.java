package cat.urv.deim.models;

import cat.urv.deim.exceptions.ElementNoTrobat;

public class LlistaPelicules {
    private LlistaDoblementEncadenada<Pelicula> llista;
   
    public LlistaPelicules () {
        llista = new LlistaDoblementEncadenada<Pelicula>();
    }

    //Mètode per insertar un element a la llista. No importa la posició on s'afegeix l'element
    public void inserir(Pelicula e) {
       llista.inserir(e);
    }

    //Mètode per a esborrar un element de la llista
    public void esborrar(Pelicula e) throws ElementNoTrobat {
        llista.esborrar(e);
    }

    //Mètode per a comprovar si la llista té elements
    public boolean esBuida() {
        return llista.esBuida(); 
    }

    //Mètode per a obtenir el nombre d'elements de la llista
    public int longitud() {
       return llista.longitud(); 
    }

    //Funcio que busca quantes películes hi ha d'un any en concret
    public int comptarPeliculesAny(int any) { 
       return llista.comptarPeliculesAny(any); 
    }

    //Mètode per a comprovar si un element està a la llista
    public boolean buscar(Pelicula e){
        return llista.buscar(e); 
    }

    //Funció que ens diu l'any en que va sortir una película
    public int buscarAnyPelicula(String titol) throws ElementNoTrobat {
       return llista.buscarAnyPelicula(titol); 
    }

    /* 
    //Funció que retorna la pelicula segons el titol
    public Pelicula buscarPeliculaTitol(String titol) throws ElementNoTrobat {
       return llista.buscarPeliculaTitol(titol); 
    }
    */

    //Metode per a obtenir un array amb tots els elements
    public Pelicula[] elements() {
        return llista.elements(); 
    }

    public Pelicula[] llistaPelicules() {
        return llista.elements(); 
    }

    public Pelicula buscarPelicula (int posicio) throws ElementNoTrobat{
        return llista.buscarPelicula(posicio);
    }

    

    
}