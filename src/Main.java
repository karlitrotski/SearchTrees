package T2;

class Main {

  static public void main(String[] args) {
    /* Mi super stopwatch. */
    Cronometro sw = new Cronometro();
    /* Son 11 pruebas. */
    int total_tests = 11;
    /* El tamaño del universo de llaves. */
    int t_universo = (int)Math.pow(2, 22);
    for(int n_test = 0; n_test < total_tests; n_test++) {
      /* El tamaño del conjunto de llaves. */
      int n_llaves = (int)Math.pow(2, 10 + n_test);
      System.out.printf("Comenzando test con %d llaves\n", n_llaves);
      /* Poblamos el conjunto de llaves. */
      int[] llaves = new int[n_llaves];
      for(int i = 0; i < llaves.length; i++) {
        llaves[i] = (int)Math.floor(Math.random() * t_universo);
      }
      /* Insertamos las n llaves en representantes de cada tipo de árbol. */
      System.out.println("1. Inserción");
      System.out.print("BSTree\t");
      sw.iniciar();
      BSTree bst = new BSTree();
      insertarLlaves(bst, llaves);
      sw.detener();
      System.out.printf("%f\n", sw.tiempo());
      System.out.print("RedBlackTree\t");
      sw.iniciar();
      RedBlackTree rbt = new RedBlackTree();
      insertarLlaves(rbt, llaves);
      sw.detener();
      System.out.printf("%f\n", sw.tiempo());
      System.out.print("VEBTree\t");
      sw.iniciar();
      VEBTree vebt = new VEBTree();
      insertarLlaves(vebt, llaves);
      sw.detener();
      System.out.printf("%f\n", sw.tiempo());
      System.out.print("SplayTree\t");
      sw.iniciar();
      SplayTree splayt = new SplayTree();
      insertarLlaves(splayt, llaves);
      sw.detener();
      System.out.printf("%f\n", sw.tiempo());
      /* Era fácil hasta que llegó.. el óptimo. */
      System.out.print("OptTree\t");
      sw.iniciar();
      OptTree optt = new OptTree(t_universo);
      sw.detener();
      System.out.printf("%f\n", sw.tiempo());
      System.out.println("2. Búsqueda aleatoria");
      /* Escogemos la secuencia de búsqueda. */
      int[] secuencia_busqueda = new int[100 * n_llaves];
      for(int i = 0; i < secuencia_busqueda.length; i++) {
        secuencia_busqueda[i] = llaves[(int)Math.floor(Math.random() * n_llaves)];
      }
      /* Buscamos la secuencia en cada árbol. */
      System.out.print("BSTree\t");
      sw.iniciar();
      buscarLlaves(bst, secuencia_busqueda);
      sw.detener();
      System.out.printf("%f\n", sw.tiempo());
      System.out.print("RedBlackree\t");
      sw.iniciar();
      buscarLlaves(rbt, secuencia_busqueda);
      sw.detener();
      System.out.printf("%f\n", sw.tiempo());
      System.out.print("VEBTree\t");
      sw.iniciar();
      buscarLlaves(vebt, secuencia_busqueda);
      sw.detener();
      System.out.printf("%f\n", sw.tiempo());
      System.out.print("SplayTree\t");
      sw.iniciar();
      buscarLlaves(splayt, secuencia_busqueda);
      sw.detener();
      System.out.printf("%f\n", sw.tiempo());
      System.out.print("OptTree\t");
      sw.iniciar();
      buscarLlaves(optt, secuencia_busqueda);
      sw.detener();
      System.out.printf("%f\n", sw.tiempo());
      /* La constante variable para las probabilidades. */
      double[] constantes = {1.2, 1.5, 2.0};
      System.out.println("3. Búsqueda con probabilidades c/i^a");
      for(int i = 0; i < constantes.length; i++) {
        System.out.printf("3.%d. a=%f", i + 1, constantes[i]);
        /* Calculo la constante de normalización. */
        double suma_probabilidades_no_normalizadas = 0;
        double[] probabilidad_no_normalizada = new double[n_llaves];
        for(int j = 0; j < n_llaves; j++) {
          probabilidad_no_normalizada[j] = 1 / Math.pow(j + 1, constantes[i]);
          /* La suma de las probabilidades sin normalizar. */
          suma_probabilidades_no_normalizadas += probabilidad_no_normalizada[j];
        }
        double c = 1 / suma_probabilidades_no_normalizadas;
        System.out.printf(", c=%f\n", c);
        /* Confeccionamos la secuencia de búsqueda probabilística. */
        secuencia_busqueda = new int[100 * n_llaves];
        for(int j = 0; j < secuencia_busqueda.length; j++) {
          /* Tengo que hacer un sorteo en cada iteración. */
          double sorteo = Math.random();
          /* La probabilidad acumulada. */
          double probabilidad_acumulada = 0;
          for(int k = 0; k < n_llaves; k++) {
            /* La probabilidad de que el k-ésimo elemento sea buscado. */
            double probabilidad = c * probabilidad_no_normalizada[k];
            probabilidad_acumulada += probabilidad;
            /* Este elemento gana el sorteo. */
            if(probabilidad_acumulada >= sorteo) {
              secuencia_busqueda[j] = llaves[k];
              break;
            }
          }
        }
        /* Buscamos la secuencia en cada árbol. */
        System.out.print("BSTree\t");
        sw.iniciar();
        buscarLlaves(bst, secuencia_busqueda);
        sw.detener();
        System.out.printf("%f\n", sw.tiempo());
        System.out.print("RedBlackree\t");
        sw.iniciar();
        buscarLlaves(rbt, secuencia_busqueda);
        sw.detener();
        System.out.printf("%f\n", sw.tiempo());
        System.out.print("VEBTree\t");
        sw.iniciar();
        buscarLlaves(vebt, secuencia_busqueda);
        sw.detener();
        System.out.printf("%f\n", sw.tiempo());
        System.out.print("SplayTree\t");
        sw.iniciar();
        buscarLlaves(splayt, secuencia_busqueda);
        sw.detener();
        System.out.printf("%f\n", sw.tiempo());
        System.out.print("OptTree\t");
        sw.iniciar();
        buscarLlaves(optt, secuencia_busqueda);
        sw.detener();
        System.out.printf("%f\n", sw.tiempo());
      }
      System.out.println("4. Búsqueda con probabilidades c/a^i");     
      for(int i = 0; i < constantes.length; i++) {
        System.out.printf("4.%d. a=%f", i + 1, constantes[i]);
        /* Calculo la constante de normalización. */
        double[] probabilidad_no_normalizada = new double[n_llaves];
        double suma_probabilidades_no_normalizadas = 0;
        for(int j = 0; j < n_llaves; j++) {
          probabilidad_no_normalizada[j] = 1 / Math.pow(constantes[i], j + 1);
          /* La suma de las probabilidades sin normalizar. */
          suma_probabilidades_no_normalizadas += probabilidad_no_normalizada[j];
        }
        double c = 1 / suma_probabilidades_no_normalizadas;
        System.out.printf(", c=%f\n", c);
        /* Confeccionamos la secuencia de búsqueda probabilística. */
        secuencia_busqueda = new int[100 * n_llaves];
        for(int j = 0; j < secuencia_busqueda.length; j++) {
          /* Tengo que hacer un sorteo en cada iteración. */
          double sorteo = Math.random();
          /* La probabilidad acumulada. */
          double probabilidad_acumulada = 0;
          for(int k = 0; k < n_llaves; k++) {
            /* La probabilidad de que el k-ésimo elemento sea buscado. */
            double probabilidad = c * probabilidad_no_normalizada[k];
            probabilidad_acumulada += probabilidad;
            /* Este elemento gana el sorteo. */
            if(probabilidad_acumulada >= sorteo) {
              secuencia_busqueda[j] = llaves[k];
              break;
            }
          }
        }
        /* Buscamos la secuencia en cada árbol. */
        System.out.print("BSTree\t");
        sw.iniciar();
        buscarLlaves(bst, secuencia_busqueda);
        sw.detener();
        System.out.printf("%f\n", sw.tiempo());
        System.out.print("RedBlackree\t");
        sw.iniciar();
        buscarLlaves(rbt, secuencia_busqueda);
        sw.detener();
        System.out.printf("%f\n", sw.tiempo());
        System.out.print("VEBTree\t");
        sw.iniciar();
        buscarLlaves(vebt, secuencia_busqueda);
        sw.detener();
        System.out.printf("%f\n", sw.tiempo());
        System.out.print("SplayTree\t");
        sw.iniciar();
        buscarLlaves(splayt, secuencia_busqueda);
        sw.detener();
        System.out.printf("%f\n", sw.tiempo());
        System.out.print("OptTree\t");
        sw.iniciar();
        buscarLlaves(optt, secuencia_busqueda);
        sw.detener();
        System.out.printf("%f\n", sw.tiempo());
      }
      System.out.printf("Concluido test con %d llaves\n\n", n_llaves);
    }
  }
  
  static private void insertarLlaves(SearchTree arbol, int[] llaves) {
    for(int i = 0; i < llaves.length; i++) {
      arbol.insertar(llaves[i]);
    }
  }
  
  static private void buscarLlaves(SearchTree arbol, int[] llaves) {
    /* Al árbol óptimo hay que pasarle la secuencia de búsqueda. */
    if(arbol instanceof OptTree) {
      ((OptTree)(arbol)).buscar(llaves);
    }
    else {
      for(int i = 0; i < llaves.length; i++) {
        arbol.buscar(llaves[i]);
      }
    }
  }
}