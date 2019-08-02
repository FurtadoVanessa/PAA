package porta;
class Porta {

    private boolean aberta;
    private String cor;
    private double dimensaoX;
    private double dimensaoY;
    private double dimensaoZ;
      
    public void setDimensaoZ(double dimensaoZ){
         this.dimensaoZ = dimensaoZ;
     }
    public void setDimensaoY(double dimensaoY){
         this.dimensaoY = dimensaoY;
     }
    public void setDimensaoX(double dimensaoX){
         this.dimensaoX = dimensaoX;
     }
    public double getDimensaoX(){
         return this.dimensaoX;
     }
    public double getDimensaoY(){
         return this.dimensaoY;
     }
    public double getDimensaoZ(){
         return this.dimensaoZ;
     }
    public String getCor(){
        return this.cor;
    }
    void pinta(String s){
        this.cor = s;
    }
    boolean estaAberta(){
        return this.aberta;
    }
    void abre(){
        this.aberta = true;
    }
    void fecha(){
        this.aberta = false;
    }

public Porta(boolean aberta, String cor, double dimensaoX, double dimensaoY, double dimensaoZ){
    this.aberta = aberta;
    this.cor = cor;
    this.dimensaoX = dimensaoX;
    this.dimensaoY = dimensaoY;
    this.dimensaoZ = dimensaoZ;
}

public Porta(){
    this.aberta = false;
    this.cor = "sem cor";
    this.dimensaoX = 10;
    this.dimensaoY = 20;
    this.dimensaoZ = 30;
}

}

