package cat.urv.deim.models;

import cat.urv.deim.exceptions.ElementNoTrobat;

public class LlistaDoblementEncadenada<E extends Comparable<E>> {

    protected Node <E> ini, fi; //Node inicial i final
    protected int size; //Tamany de la llista

    //Mètode per insertar un element a la llista. 
    public void inserir(E e) {
        Node <E> nou = new Node <> (e); //Node nou
        Node <E> c = ini; //Node actual

        //Si la llista està buida, el nou element serà el primer i l'últim
        if (esBuida()){
            //El nou element serà el primer i l'últim
            ini=nou; 
            fi=nou; 
        } else{
            //Si l'element a inserir és menor que el primer element de la llista
            if (ini.getDada().compareTo(e) >= 0){ 
                nou.setNext(ini); //El node següent del nou node passa a ser el node inicial
                ini.setAnt(nou); //El node anterior del node inicial passa a ser el nou node
                ini = nou;       //El node inicial passa a ser el nou node
            } else {
                //Si l'element a inserir és major que el darrer element de la llista
                if (fi.getDada().compareTo(e) <= 0){
                    fi.setNext(nou); //El node següent del node final passa a ser el nou node
                    nou.setAnt(fi); //El node anterior del nou node passa a ser el node final
                    fi = nou; //El node final passa a ser el nou node
                } else {
                    //Mentre el node següent no sigui null i el node següent sigui menor que l'element a inserir
                    while (c.getNext() != null && c.getNext().getDada().compareTo(e) < 0){ 
                        c=c.getNext();
                    }
                    //Inserim l'element a la llista
                    nou.setAnt(c); //El node anterior del nou node passa a ser el node actual
                    nou.setNext(c.getNext()); //El node següent del nou node passa a ser el node següent del node actual
                    c.getNext().setAnt(nou); //El node anterior del node següent del node actual passa a ser el nou node
                    c.setNext(nou); // El node següent del node actual passa a ser el nou node
                }
            }
        }
        size ++; 
    }

    //Mètode per a esborrar un element de la llista
    public void esborrar(E e) throws ElementNoTrobat {
        Node <E> c = ini; 
        //Si l'element a esborrar és el primer element de la llista
        if (ini.getDada().equals(e)){ 
            ini=ini.getNext(); //El node inicial passa a ser el node següent
            //Si el node inicial no és null 
            if (ini != null){
                ini.setAnt(null); //El node anterior del node inicial passa a ser null
            }
        //Si l'element a esborrar és el darrer element de la llista
        }else if (fi.getDada().equals(e)){ 
            fi = fi.getAnt(); //El node final passa a ser el node anterior
            fi.setNext(null); //El node següent del node final passa a ser null
        }
        else{
            //Mentre el node actual no sigui null i el node actual sigui diferent de l'element a esborrar
            while (c != null && !c.getDada().equals(e)){
                c=c.getNext();
            }
            //Si el node actual és null, llavors l'element no està a la llista
            if (c==null){
                throw new ElementNoTrobat("L'element no està a la llista");
            }
            //Si el node actual és diferent de null, llavors l'element està a la llista
            c.getAnt().setNext(c.getNext()); //El node següent del node anterior del node actual passa a ser el node següent del node actual
            c.getNext().setAnt(c.getAnt()); //El node anterior del node següent del node actual passa a ser el node anterior del node actual
        }
        size--; 
    }

    //Mètode per a comprovar si un element està a la llista
    public boolean buscar(E e) {
       Node <E> c = ini; //Node actual
       //Mentre el node actual no sigui null i el node actual sigui menor que l'element a buscar
       while (c != null && c.getDada().compareTo(e) < 0){
            //Si el node actual és igual a l'element a buscar, llavors l'element està a la llista
            if (c.getDada().equals(e)){
                return true; 
            }    
            c=c.getNext();
        }
        return false; 
    }

    //Mètode per a comprovar si la llista té elements
    public boolean esBuida() {
        return ini == null;
    }


    //Mètode per a obtenir el nombre d'elements de la llista
    public int longitud() {
        int compt = 0; //Comptador
        Node <E> c = ini; //Node actual
        //Mentre el node actual no sigui null
        while (c != null){
            compt ++;
            c = c.getNext();
        }
        return compt;
    }

    //Metode per a obtenir un array amb tots els elements
    public Pelicula[] elements() {
        Pelicula [] array = new Pelicula[size]; //Array de Pelicules
        Node <E> c= ini;  //Node actual
        int i=0; 
        //Mentre el node actual no sigui null
        while (c!=null){
            array[i]=(Pelicula)c.getDada(); //Afegim l'element del node actual a l'array
            c=c.getNext();
            i++;
        }
        return array; 
    }

    //Funcio que busca quantes películes hi ha d'un any en concret
    public int comptarPeliculesAny (int any){
        Node <E> c=ini; //Node actual
        int compt =0;  
        //Mentre el node actual no sigui null
        while (c!=null){
            Pelicula p = (Pelicula) c.getDada(); //Passem el node actual a una variable de tipus Pelicula
            //Si l'any de la pelicula és igual a l'any que li passem per paràmetre, llavors incrementem el comptador
            if (p.getAny() == any){
                compt++;
            }
            c=c.getNext();
        }
        return compt;
    }

    //Funció que ens diu l'any en que va sortir una película
    public int buscarAnyPelicula (String titol) throws ElementNoTrobat{
        Node <E> c = ini; //Node actual
        int any = 0;  //Any de la pelicula
        while (c!=null){
            Pelicula p = (Pelicula) c.getDada(); //Passem el node actual a una variable de tipus Pelicula
            //Si el titol de la pelicula és igual al titol que li passem per paràmetre, llavors retornem l'any de la pelicula
            if (p.getTitol().equals(titol)){
                any=p.getAny();
                return any; 
            }
            c=c.getNext();
        }
        throw new ElementNoTrobat("No s'ha trobat la Pelicula");
    }

    //Funció que retorna la pelicula segons el titol
    public Pelicula buscarPelicula (String titol) throws ElementNoTrobat{
        Node <E> c = ini;  //Node actual
        Pelicula p = null; //Pelicula
        while (c!= null){
            p = (Pelicula) c.getDada(); //Passem el node actual a una variable de tipus Pelicula
            //Si el titol de la pelicula és igual al titol que li passem per paràmetre, llavors retornem la pelicula
            if (p.getTitol().equals(titol)){
                return p; //Retornem la pelicula
            }
            c=c.getNext();
        }
        //Si no troba la pelicula, llavors llança una excepció
        throw new ElementNoTrobat("No s'ha trobat la Pelicula"); 
    }

    //Retorna 1 element 
    public E buscarPelicula (int posicio) throws ElementNoTrobat{
        Node <E> c = ini; //Node actual
        int i = 0; //Comptador
        //Mentre el node actual no sigui null
        while (c!=null){
            //Si el comptador és igual a la posició que li passem per paràmetre, llavors retornem la pelicula
            if (i == posicio){
                return c.getDada(); //Retornem la pelicula
            }
            c=c.getNext();
            i++;
        }
        //Si no troba la pelicula, llavors llança una excepció
        throw new ElementNoTrobat("No s'ha trobat la Pelicula"); 
    }

    public boolean Mateixtitol (String titol) throws ElementNoTrobat{
        Node <E> c = ini;  //Node actual
        Pelicula p = null; //Pelicula
        while (c!= null){
            p = (Pelicula) c.getDada(); //Passem el node actual a una variable de tipus Pelicula
            //Si el titol de la pelicula és igual al titol que li passem per paràmetre, llavors retornem la pelicula
            if (p.getTitol().equals(titol)){
                return true; //Retornem la pelicula
            }
            c=c.getNext();
        }
        return false; 
    }

    public boolean MateixID (int id) throws ElementNoTrobat{
        Node <E> c = ini;  //Node actual
        Usuaris p = null; //Pelicula
        while (c!= null){
            p = (Usuaris) c.getDada(); //Passem el node actual a una variable de tipus Pelicula
            //Si el titol de la pelicula és igual al titol que li passem per paràmetre, llavors retornem la pelicula
            if (p.getID() == id){
                return true; //Retornem la pelicula
            }
            c=c.getNext();
        }
        return false; 
    }


}