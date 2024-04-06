public class EjecutadorDeLimpieza extends Thread {
    public void run() {
        boolean a = true;
        while (a) {
            try {
                Thread.sleep(4);
                synchronized (Opcion2.memoriaReal) {
                    Opcion2.avisarGetSynchrinized();
                    Opcion2.limpiarReferencias();
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
