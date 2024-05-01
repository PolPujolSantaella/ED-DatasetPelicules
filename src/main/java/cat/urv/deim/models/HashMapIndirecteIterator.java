package cat.urv.deim.models;

import java.util.Iterator;
import java.util.NoSuchElementException;


public class HashMapIndirecteIterator <A,B> implements Iterator<B>{
   private Entry<A,B>[] taula; 
   private int mida;
   private int pos; 
   private Entry<A,B> entrada;

   public HashMapIndirecteIterator(Entry<A,B>[] taula, int mida) {
       this.taula = taula;
       this.mida = mida;
       pos=-1; 
       entrada = null;
       seguentEntrada(); 
   }

   public boolean hasNext(){
        return entrada != null;
   }

   public B next(){
       if (!hasNext()){
            throw new NoSuchElementException();
       }
        B valor = entrada.getValue();
        seguentEntrada();
        return valor;
   }

    private void seguentEntrada(){
         if (entrada != null){
              entrada = entrada.getNext();
         }
         while (entrada == null && pos < mida-1){
            pos++; 
              entrada = taula[pos];
         }
    }

    public void remove(){
        throw new UnsupportedOperationException();
    }
}