package hu.szjanos.logika;

public class DHaromszog {

    double aOldal;
    double bOldal;
    double cOldal;

    private int sorSzam;



    public DHaromszog(String sor, int sorSzama) throws Exception{
        this.setSorSzam(sorSzama);

        String[] adatok = sor.replace(',','.').split(" ");
        this.setaOldal(Double.parseDouble(adatok[0]));
        this.setbOldal(Double.parseDouble(adatok[1]));
        this.setcOldal(Double.parseDouble(adatok[2]));
        this.EllNovekvoSorrend();
        this.EllMegszerkesztheto();
        this.EllDerekszogu();
    }

    private boolean EllDerekszogu() throws Exception {
        double atfogoosszeg = Math.pow(this.cOldal,2);
        double befogoosszeg = Math.pow(this.aOldal,2) + Math.pow(this.bOldal,2);
        boolean isDerekszogu = befogoosszeg == atfogoosszeg;

        if (!isDerekszogu) {
            throw new Exception(String.format("%d. sor: A haromszog nem derekszogu", this.getSorSzam())
            );


        }
        return befogoosszeg == atfogoosszeg;


    }

    private boolean EllMegszerkesztheto() throws Exception {
        boolean isMegserkestheto = this.aOldal+this.bOldal>this.cOldal;
        if (!isMegserkestheto){
            throw new Exception(String.format("%d. sor: A haromszoget nem lehet megszerkeztheti",this.getSorSzam())
            );
        }
        return this.aOldal+this.bOldal>this.cOldal;
    }

    private boolean EllNovekvoSorrend() throws Exception {
        boolean isNovekvo = this.aOldal <= this.bOldal && this.bOldal <= this.cOldal;
        if (!isNovekvo){
            throw new Exception(String.format("%d. sor: A haromszoget nem novekvo",this.getSorSzam())
            );
        }
        return this.aOldal <= this.bOldal && this.bOldal <= this.cOldal;
    }

    public double kerulet(){
        return this.aOldal + this.bOldal + this.cOldal;
    }

    public double terulet(){
        return this.aOldal * this.bOldal / 2;
    }


    public int getSorSzam() {
        return sorSzam;
    }

    public void setSorSzam(int sorSzam) {
        this.sorSzam = sorSzam;
    }

    public double getaOldal() {
        return this.aOldal;
    }

    public void setaOldal(double aOldal) throws Exception {
        if (aOldal > 0){
            this.aOldal = aOldal;
        }else{
            throw new Exception(String.format("%d. sor: Az a oldal nem lehet nulla vagz negativ", this.getSorSzam())
            );
        }
    }

    public double getbOldal() {
        return this.bOldal;
    }

    public void setbOldal(double bOldal) throws Exception {
        if (bOldal > 0){
            this.bOldal = bOldal;;
        }else{
            throw new Exception(String.format("%d . sor: A b oldal nem lehet nulla vagz negativ", this.getSorSzam())
            );
        }
    }



    public double getcOldal() {
        return this.cOldal;
    }

    public void setcOldal(double cOldal) throws Exception {
        if (cOldal > 0) {
            this.cOldal = cOldal;
            ;
        } else {
            throw new Exception(String.format("%d. sor: A c oldal nem lehet nulla vagz negativ", this.getSorSzam())
            );
        }

    }

    @Override
    public String toString() {

        return String.format(("%d. sor: a=%f b=%f c=%f"),
                this.getSorSzam(),
                this.getaOldal(),
                this.getbOldal(),
                this.getcOldal()
        );
    }
}
