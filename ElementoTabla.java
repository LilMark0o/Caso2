public class ElementoTabla {
    private int paginaApuntada;
    private boolean R;
    private boolean M;

    public ElementoTabla(int paginaApuntada) {
        this.paginaApuntada = paginaApuntada;
        this.R = false;
        this.M = false;
    }

    public int getpaginaApuntada() {
        return paginaApuntada;
    }

    public boolean getR() {
        return R;
    }

    public boolean getM() {
        return M;
    }

    public void setPaginaApuntada(int paginaApuntada) {
        this.paginaApuntada = paginaApuntada;
    }

    public void reset() {
        R = false;
        M = false;
    }

    public void modify() {
        M = true;
    }

    public void reference() {
        R = true;
    }

    public void deReference() {
        R = false;
    }

    public int getClassPage() {
        // TODO esto depende de como sean las clases
        if (M && R) {
            return 3;
        } else if (!M && R) {
            return 2;
        } else if (M && !R) {
            return 1;
        } else {
            return 0;
        }
    }
}
