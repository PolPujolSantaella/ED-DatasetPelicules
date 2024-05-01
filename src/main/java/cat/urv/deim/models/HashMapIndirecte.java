package cat.urv.deim.models;

import java.util.Iterator;


import cat.urv.deim.exceptions.ElementNoTrobat;

public class HashMapIndirecte<A,B> {

    private static final float FactorCarregaMAX = 0.75f;
    private int mida;
    private int numElements;
    private Entry<A,B>[] taula;

    @SuppressWarnings("unchecked")

    public HashMapIndirecte(int mida) {
        taula = new Entry[mida];
        numElements = 0;
        this.mida = mida;
    }

    // Metode per insertar un element a la taula. Si existeix un element amb aquesta clau s'actualitza el valor
    public void inserir(A key, B value){
        if (factorCarrega() >= FactorCarregaMAX) {
            redimensionar();
        }
        int i = Math.abs(key.hashCode() % mida);
        Entry<A,B> entrada = taula[i]; 
        while (entrada != null) {
            if (entrada.getKey().equals(key)) {
                entrada.setValue(value);
                return;
            }
            entrada = entrada.getNext();
        }

        // Si no existeix l'element, l'afegim a la taula    
        entrada = new Entry<A,B>(key, value);
        entrada.setNext(taula[i]); 
        taula[i] = entrada; 
        numElements++;
    }

    @SuppressWarnings("unchecked")
    public void redimensionar(){
        int novaMida = mida * 2;
        Entry<A,B>[] novaTaula = new Entry[novaMida];

        for (int i=0; i<mida; i++){
            Entry<A,B> entrada = taula[i];
            while (entrada != null){
                int j = Math.abs(entrada.getKey().hashCode() % novaMida);
                Entry<A,B> seguent = entrada.getNext();
                entrada.setNext(novaTaula[j]);
                novaTaula[j] = entrada;
                entrada = seguent;
            }
        }
        taula = novaTaula;
        mida = novaMida;
    }

    // Metode per a esborrar un element de la taula de hash
    public void esborrar(A key) throws ElementNoTrobat{
        int i = Math.abs(key.hashCode() %mida); 
        Entry<A,B> anterior = null;
        Entry<A,B> entrada = taula[i];
        
        while (entrada != null){
            if (entrada.getKey().equals(key)){
                if (anterior == null){
                    taula[i] = entrada.getNext();
                } else {
                    anterior.setNext(entrada.getNext());
                }
                numElements--;
                return;
            }
            anterior = entrada;
            entrada = entrada.getNext();
        }
        throw new ElementNoTrobat("No existeix l'element");
    }

    // Metode per a comprovar si un element esta a la taula de hash
    public boolean buscar(A key){
        int i = Math.abs(key.hashCode() % mida);
        Entry<A,B> entrada = taula[i];
        while (entrada != null){
            if (entrada.getKey().equals(key)){
                return true;
            }
            entrada = entrada.getNext();
        }
        return false;
    }

    // Metode per a comprovar si la taula te elements
    public boolean esBuida(){
        return numElements == 0;
    }

    // Metode per a obtenir el nombre d'elements de la taula
    public int longitud(){
        return numElements;
    }

    //Metode per a poder iterar pels elements de la taula
    public Iterator<B> iterator(){
        return new HashMapIndirecteIterator<A,B>(taula, mida);
    }

    // Metode per a obtenir les claus de la taula
    public Object[] obtenirClaus(){
        Object[] claus = new Object[numElements];
        int i = 0;
        for (Entry<A,B> entrada : taula){
            while (entrada != null){
                claus[i] = entrada.getKey();
                i++;
                entrada = entrada.getNext();
            }
        }
        return claus;
    }

    public A obtenirClau(B value){
        for (Entry<A,B> entrada : taula){
            while (entrada != null){
                if (entrada.getValue().equals(value)){
                    return entrada.getKey();
                }
                entrada = entrada.getNext();
            }
        }
        return null;
    }

    // Metode per a obtenir un array amb tots els elements de K
    public B element(A key) throws ElementNoTrobat{
        int i = Math.abs(key.hashCode() % mida);
        Entry<A,B> entrada = taula[i];
        while (entrada != null){
            if (entrada.getKey().equals(key)){
                return entrada.getValue();
            }
            entrada = entrada.getNext();
        }
        //throw new ElementNoTrobat("No existeix l'element");
        return null;
    }

    // Metode per a saber el factor de carrega actual de la taula
    public float factorCarrega(){
        return (float) numElements / mida;
    } 

    // Metode per a saber la mida actual de la taula
    public int midaTaula(){
        return mida;
    } 
 
}