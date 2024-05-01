package cat.urv.deim.models;


public class Usuaris implements Comparable<Usuaris> {
    private int id_usuari;
    private double similitud; 

    public Usuaris (int id_usuari, double similitud) {
        this.id_usuari = id_usuari; 
        this.similitud = similitud;
    }

    public int getID() {
        return this.id_usuari; 
    }

    public void setID(int id_usuari) {
        this.id_usuari = id_usuari; 
    }

    public double getSimilitud() {
        return this.similitud; 
    }

    public void setSimilitud(double similitud) {
        this.similitud = similitud; 
    }

   @Override
    public int compareTo(Usuaris UsuariAComparar) {
        if (this.similitud > UsuariAComparar.getSimilitud()) {
            return -1; // La similitud de this es mayor, se coloca antes en la ordenación
        } else if (this.similitud < UsuariAComparar.getSimilitud()) {
            return 1; // La similitud de this es menor, se coloca después en la ordenación
        } else {
            return 0; // La similitud es igual, no hay preferencia en el orden
        }
    }

} 