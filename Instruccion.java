public class Instruccion {
    private String matriz;
    private Tupla ubicacionMatriz;
    private Tupla ubicacionMemoriaVirtual;
    private String operation;

    public Instruccion(String texto) {
        // M[0][0],2,4,R
        String[] parts = texto.split(",");
        String matrizIJ = parts[0];
        matriz = matrizIJ.substring(0, 1);
        String[] ij = matrizIJ.substring(2, matrizIJ.length() - 1).split("\\]\\[");
        int i = Integer.parseInt(ij[0]);
        int j = Integer.parseInt(ij[1]);
        ubicacionMatriz = new Tupla(i, j);
        int slot = Integer.parseInt(parts[1]);
        int intNumber = Integer.parseInt(parts[2]);
        ubicacionMemoriaVirtual = new Tupla(slot, intNumber);
        operation = parts[3];
    }

    // toString
    public String toString() {
        return matriz + "[" + ubicacionMatriz.getI() + "][" + ubicacionMatriz.getJ() + "],"
                + ubicacionMemoriaVirtual.getI() + "," + ubicacionMemoriaVirtual.getJ() + "," + operation;
    }

    public String getMatriz() {
        return matriz;
    }

    public Tupla getUbicacionMatriz() {
        return ubicacionMatriz;
    }

    public Tupla getUbicacionMemoriaVirtual() {
        return ubicacionMemoriaVirtual;
    }

    public String getOperation() {
        return operation;
    }

}