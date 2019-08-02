
package porta;

class Casa {
    private String cor;
    private Porta porta1;
    private Porta porta2;
    private Porta porta3;
    
    public Porta getPorta1(){
        return this.porta1;
    }
    public void setPorta1(Porta porta1){
        this.porta1 = porta1;
    }
    
    public Porta getPorta2(){
        return this.porta2;
    }
    public void setPorta2(Porta porta2){
        this.porta2 = porta2;
    }
    
    public Porta getPorta3(){
        return this.porta3;
    }
    public void setPorta3(Porta porta3){
        this.porta3 = porta3;
    }
    
    public String getCor(){
        return this.cor;
    }

    void pinta(String s){
        this.cor = s;
    }
    int quantasPortasEstaoAbertas(){
        int quantidadeDePortasAbertas = 0;
        if(this.porta1.estaAberta())
           quantidadeDePortasAbertas++;
        if(this.porta2.estaAberta())
           quantidadeDePortasAbertas++;
        if(this.porta3.estaAberta())
           quantidadeDePortasAbertas++;
       return quantidadeDePortasAbertas;
    }
    public Casa(){
        this.cor = "sem cor";
        this.porta1.pinta("porta sem cor");
        this.porta1.setDimensaoX(10);
        this.porta1.setDimensaoY(10);
        this.porta1.setDimensaoZ(10);
        this.porta1.fecha();
        this.porta2.pinta("porta sem cor");
        this.porta2.setDimensaoX(10);
        this.porta2.setDimensaoY(10);
        this.porta2.setDimensaoZ(10);
        this.porta2.fecha();
        this.porta3.pinta("porta sem cor");
        this.porta3.setDimensaoX(10);
        this.porta3.setDimensaoY(10);
        this.porta3.setDimensaoZ(10);
        this.porta3.fecha();
    }
    
    public Casa(String cor, Porta porta1, Porta porta2, Porta porta3){
        this.cor = cor;
        this.porta1 = porta1;
        this.porta2 = porta2;
        this.porta3 = porta3;
    }
}
