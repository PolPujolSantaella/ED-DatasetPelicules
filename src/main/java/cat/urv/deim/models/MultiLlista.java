package cat.urv.deim.models;

import java.util.ArrayList;
import java.util.List;

import cat.urv.deim.exceptions.ElementRepetit;
import cat.urv.deim.exceptions.ElementNoTrobat;


public class MultiLlista<A,B> {

    private HashMapIndirecte<A,Node<B>> conjuntA;
    private HashMapIndirecte<B,Node<A>> conjuntB;

    public MultiLlista(HashMapIndirecte<A,Node<B>> conjuntA, HashMapIndirecte<B,Node<A>> conjuntB) {
        this.conjuntA = conjuntA;
        this.conjuntB = conjuntB;
    }
        
    public void inserir(A a, B b, int valoracio, Data data) throws ElementRepetit, ElementNoTrobat {
        
        if (existeix(a, b)){
            throw new ElementRepetit("Element repetit");
        } else {
            Node<B> nodeB = conjuntA.element(a);
    
            if (nodeB == null) {
                nodeB = new Node<B>(b);
                nodeB.setValoracio(valoracio);
                nodeB.setData(data);
                conjuntA.inserir(a, nodeB);
            } else {
                Node<B> newNodeB = new Node<B>(b);
                newNodeB.setValoracio(valoracio);
                newNodeB.setData(data);
                newNodeB.setNext(nodeB);
                nodeB = newNodeB;
                conjuntA.inserir(a, nodeB); 
            }

            Node<A> nodeA = conjuntB.element(b);
    
            if (nodeA == null) {
                nodeA = new Node<A>(a);
                nodeA.setValoracio(valoracio);
                nodeA.setData(data);
                conjuntB.inserir(b, nodeA);
            } else {
                Node<A> newNodeA = new Node<A>(a);
                newNodeA.setValoracio(valoracio);
                newNodeA.setData(data);
                newNodeA.setNext(nodeA);
                nodeA = newNodeA;
                conjuntB.inserir(b, nodeA);
            }
        }
    }
    
    public void esborrar(A a, B b) throws ElementNoTrobat {
        // Si la relació no existeix, llança una excepció
        if (!existeix(a, b)){
            throw new ElementNoTrobat("Element no trobat");
        } else {
            Node<B> nodeB = conjuntA.element(a); // Busca el node de B relacionat amb A
            Node<A> nodeA = conjuntB.element(b); // Busca el node de A relacionat amb B
    
            if (nodeB == null || nodeA == null) {
                throw new ElementNoTrobat("Element no trobat");
            }
    
            // Esborra la relació de A amb B
            Node<B> prevNodeB = null;
            while (nodeB != null) {
                if (nodeB.getDada().equals(b)) {
                    if (prevNodeB == null) {
                        conjuntA.inserir(a, nodeB.getNext());
                    } else {
                        prevNodeB.setNext(nodeB.getNext());
                    }
                    break;
                }
                prevNodeB = nodeB;
                nodeB = nodeB.getNext();
            }
    
            // Esborra la relació de B amb A
            Node<A> prevNodeA = null;
            while (nodeA != null) {
                if (nodeA.getDada().equals(a)) {
                    if (prevNodeA == null) {
                        conjuntB.inserir(b, nodeA.getNext());
                    } else {
                        prevNodeA.setNext(nodeA.getNext());
                    }
                    break;
                }
                prevNodeA = nodeA;
                nodeA = nodeA.getNext();
            }
        }
    }
    

    // Retorna la llista de B relacionats amb A
    public List<B> fila(A a) throws ElementNoTrobat {
        List <B> fila = new ArrayList <>();
        Node <B> nodeB = conjuntA.element(a); // Busca el node de B relacionat amb A
        Node <B> aux = nodeB; 
            while (aux != null){
                fila.add(aux.getDada());
                aux = aux.getNext();
            }
        return fila;
    }

    public List<A> columna(B b) throws ElementNoTrobat {
        List <A> columna = new ArrayList <>();
        Node <A> nodeA = conjuntB.element(b); // Busca el node de A relacionat amb B
        
        Node <A> aux = nodeA;
        while (aux != null){
            columna.add(aux.getDada());
            aux = aux.getNext();
        }
        return columna;
    }

    public boolean existeix(A a, B b) throws ElementNoTrobat{
        Node <B> nodeB = conjuntA.element(a); // Busca el node de B relacionat amb A
        Node <A> nodeA = conjuntB.element(b); // Busca el node de A relacionat amb B
        return nodeB != null && nodeB.getDada().equals(b) && nodeA != null && nodeA.getDada().equals(a); // Retorna si existeixen els dos nodes
    }

    //Mètode que retorna la valoració d'un element
    public int getValoracio(A a, B b) throws ElementNoTrobat{
        //Agafa la valoracio del usuari de la pelicula
        Node <B> nodeB = conjuntA.element(a); // Busca el node de B relacionat amb A
        Node <A> nodeA = conjuntB.element(b); // Busca el node de A relacionat amb B
        if (nodeB == null || nodeA == null){
            throw new ElementNoTrobat("Element no trobat");
        } else {
            Node <B> aux = nodeB;
            while (aux != null){
                if (aux.getDada().equals(b)){
                    return aux.getValoracio();
                }
                aux = aux.getNext();
            }
        }
        return 0; 
    }


    //Funcio que retorna el conjuntB
    public HashMapIndirecte<B,Node<A>> getConjuntB(){
        return conjuntB;
    }

}