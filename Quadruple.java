public class Quadruple {

    int haut, gauche, bas, droite;

    public Quadruple(int haut, int gauche, int bas, int droite) {
        this.haut = haut;
        this.gauche = gauche;
        this.bas = bas;
        this.droite = droite;
    }

    public void setHaut(int a) {
        this.haut = a;
    }

    public void setGauche(int a) {
        this.gauche = a;
    }

    public void setBas(int a) {
        this.bas = a;
    }

    public void setDroite(int a) {
        this.droite = a;
    }

    public int getHaut() {
        return this.haut;
    }

    public int getGauche() {
        return this.gauche;
    }

    public int getBas() {
        return this.bas;
    }

    public int getDroite() {
        return this.droite;
    }
}