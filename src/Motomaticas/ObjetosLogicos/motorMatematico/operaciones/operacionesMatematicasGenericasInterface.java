package Motomaticas.ObjetosLogicos.motorMatematico.operaciones;

import Motomaticas.ObjetoMatematico;
public interface operacionesMatematicasGenericasInterface{
    public class llaveDerecha extends OperacionMatematica{

        public llaveDerecha() {
            super(true, "}", "llave derecha", "Establece la jerarquia de operaciones con encapsulamiento de llaves",
                    4);
            //TODO Auto-generated constructor stub
        }

        @Override
        public String toStringReducido(){
            return SimboloIdentificador;

        }
        @Override
        public ObjetoMatematico calcularOperacion(ObjetoMatematico... args) {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        protected String[] definirTipoDeOperandoscorrectos() {
            // TODO Auto-generated method stub
            return null;
        }

    }
    public class llaveIzquierda extends OperacionMatematica{

        public llaveIzquierda() {
            super(true, "{", "llave izquierda", "Establece la jerarquia de operaciones con encapsulamiento de llaves",4);
            //TODO Auto-generated constructor stub
        }
        @Override
        public String toStringReducido(){
            return SimboloIdentificador;

        }
        @Override
        protected String[] definirTipoDeOperandoscorrectos() {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public ObjetoMatematico calcularOperacion(ObjetoMatematico... args) {
            // TODO Auto-generated method stub
            return args[0];
        }

  

    }


}