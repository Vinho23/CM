package trabalhopratico.cm.edjd.ipca.ipcaloja;

public class Cliente {

    String idCliente;
    String nomeCliente;
    String generoCliente;

    public Cliente() {

    }

    public Cliente(String idCliente, String nomeCliente, String generoCliente) {
        this.idCliente = idCliente;
        this.nomeCliente = nomeCliente;
        this.generoCliente = generoCliente;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public String getGeneroCliente() {
        return generoCliente;
    }
}
