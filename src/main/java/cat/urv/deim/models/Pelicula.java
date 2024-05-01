package cat.urv.deim.models;

public class Pelicula implements Comparable<Pelicula> {
    private int idP;
    private String titol;
    private int any; 
    private double promig;
    private int compt;  

    public Pelicula(int idP, String titol, int any, double promig, int compt) {
        this.idP = idP;
        this.titol = titol;
        this.any = any;
        this.promig = promig;
        this.compt = compt;

    }

    public int getID() {
        return this.idP;
    }

    public String getTitol() {
        return this.titol;
    }

    public int getAny() {
        return this.any;
    }

    public double getPromig() {
        return this.promig;
    }

    public void setID(int idP) {
        this.idP = idP;
    }

    public void setTitol(String titol) {
        this.titol = titol;
    }
     
    public void setAny(int any) {
        this.any = any;
    }

    public void setPromig(double promig) {
        this.promig = promig;
    }

    public int getCompt() {
        return this.compt;
    }

    public void setCompt(int compt) {
        this.compt = compt;
    }

    // Comparem amb tres criteris, primer el promig, després el títol i per últim l'any
    @Override
    public int compareTo(Pelicula peliculaAComparar) {
        int comparacionPromig = Double.compare(peliculaAComparar.getPromig(), this.promig);
        if (comparacionPromig != 0) {
            return comparacionPromig; // Devolvemos la comparación por promig si son diferentes
        } else {
            // El promedio es igual, comparamos por título y año
            int comparacionTitulo = this.titol.compareTo(peliculaAComparar.getTitol());
            if (comparacionTitulo != 0) {
                return comparacionTitulo; // Devolvemos la comparación por título si son diferentes
            } else {
                // Los títulos son iguales, comparamos por año
                return Integer.compare(this.any, peliculaAComparar.getAny());
            }
        }
    }
} 