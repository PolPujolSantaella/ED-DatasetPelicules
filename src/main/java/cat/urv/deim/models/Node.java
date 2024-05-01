package cat.urv.deim.models;



public class Node <E> {
    E pelicula;
    E usuari;  
    Node <E> next, ant; 
    private int valoracio; 
    private Data d;

    //Constructor quan no hi ha nodes

    public Node (E e){
        pelicula = e; 
        next = null; 
        ant = null; 
        valoracio = 0;
        d = new Data(0,0,0);
    }



    public Node <E> getNext(){
        return next; 
    }

    public Node <E> getAnt(){
        return ant; 
    }

    public E getDada(){
        return pelicula; 
    }

    public void setNext(Node <E> n){
        next = n; 
    }

    public void setAnt(Node <E> a){
        ant = a; 
    }

    public void setDada(E e){
        pelicula = e; 
    }

    public int getValoracio(){
        return valoracio; 
    }

    public void setValoracio(int v){
        valoracio = v; 
    }

    public Data getData(){
        return d; 
    }

    public void setData(Data d){
        this.d = d; 
    }

}