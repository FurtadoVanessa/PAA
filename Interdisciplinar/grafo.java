

    public class Grafo {
        public class Vertice {
            int nome;
            List<Aresta> adjacente;

            Vertice(String nome) {
                this.nome = nome;
                this.adjacente = new ArrayList<Aresta>();
            }

            void addAdj(Aresta e) {
                adjacente.add(e);
            }
        }

        public class Aresta {
            Vertice origem;
            Vertice destino;

            Aresta(Vertice origem, Vertice destino) {
                this.origem = origem;
                this.destino = destino;
            }
        }

        List<Vertice> vertices;
        List<Aresta> arestas;

        public Grafo() {
            vertices = new ArrayList<Vertice>();
            arestas = new ArrayList<Aresta>();
        }

        Vertice addVertice(String nome) {
            Vertice v = new Vertice(nome);
            vertices.add(v);
            return v;
        }

        Aresta addAresta(Vertice origem, Vertice destino) {
            Aresta e = new Aresta(origem, destino);
            Aresta e2 = new Aresta(destino, origem);
            origem.addAdj(e);
            destino.addAdj(e2);
            arestas.add(e);
            return e;
        }