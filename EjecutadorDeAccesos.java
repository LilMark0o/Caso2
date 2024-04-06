public class EjecutadorDeAccesos extends Thread {
    public void run() {
        boolean a = true;
        while (a) {
            try {
                Thread.sleep(1);

                synchronized (Opcion2.memoriaReal) {
                    Opcion2.avisarGetSynchrinized();
                    Instruccion orden = Opcion2.ordenAccesos.poll();
                    int pagina = orden.getUbicacionMemoriaVirtual().getI();
                    int index = Opcion2.buscarEnMemoriaReal(pagina);
                    if (index == -1) {
                        Opcion2.fallosDePagina++;
                        Opcion2.reemplazarEnMemoriaReal(pagina, orden.getOperation());
                    } else {
                        Opcion2.aciertosDePagina++;
                        Opcion2.memoriaReal[index].reference();
                        if (orden.getOperation() == "W") {
                            Opcion2.memoriaReal[index].modify();
                        }
                    }
                    if (Opcion2.ordenAccesos.isEmpty()) {
                        a = false;
                    }
                    Opcion2.avisarReleaseSynchrinized();
                }
            } catch (Exception e) {
                System.out.println("Error");
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
