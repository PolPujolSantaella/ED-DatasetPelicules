package cat.urv.deim.models;

public class Entry<A,B> {
    A clau; 
    B valor;
    Entry<A,B> seguent;

    public Entry(A clau, B valor) {
        this.clau = clau;
        this.valor = valor;
        seguent = null;
    }
    
    public A getKey() {
        return clau;
    }

    public B getValue() {
        return valor;
    }

    public void setValue(B valor) {
        this.valor = valor;
    }

    public Entry<A,B> getNext() {
        return seguent;
    }

    public void setNext(Entry<A,B> seguent) {
        this.seguent = seguent;
    }

    public int size() {
        int size = 1;
        Entry<A,B> entry = seguent;
        while (entry != null) {
            size++;
            entry = entry.getNext();
        }
        return size;
    }

    public B getRelacio(){
        return valor;
    }


}