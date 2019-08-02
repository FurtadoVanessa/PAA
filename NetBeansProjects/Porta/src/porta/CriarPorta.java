package porta;
public class CriarPorta {
        public static void main(String[] args) {
        Porta p = new Porta();
        p.abre();
        p.fecha();
        p.pinta("Vermelha");
        p.pinta("Verde");
        p.pinta("Azul");
        p.pinta("Amarela");
        p.setDimensaoX(10);
        p.setDimensaoY(20);
        p.setDimensaoZ(30);
        if(p.estaAberta())
            System.out.println("Está aberta");
        else
            System.out.println("Está fechada");
        
        
        Porta p1 = new Porta(false, "branca", 10,10,10);
        Porta p2 = new Porta(false, "branca", 10,10,10);
        Porta p3 = new Porta(false, "branca", 10,10,10);
         
        Casa casa = new Casa("azul", p1, p2, p3);
         
        casa.getPorta1().abre();
        casa.getPorta1().fecha();
        casa.getPorta1().abre();
         
        System.out.println("O numero de portas abertas e" +casa.quantasPortasEstaoAbertas());
    }
}
