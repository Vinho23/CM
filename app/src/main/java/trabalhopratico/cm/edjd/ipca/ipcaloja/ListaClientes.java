package trabalhopratico.cm.edjd.ipca.ipcaloja;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ListaClientes extends ArrayAdapter<Cliente> {

    private Activity context;
    private List<Cliente> listaClientes;

    public ListaClientes(Activity context, List<Cliente> listaClientes) {

        super(context, R.layout.list_layout, listaClientes);
        this.context = context;
        this.listaClientes = listaClientes;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.list_layout, null, true);

        TextView textViewNome = (TextView) listViewItem.findViewById(R.id.textViewNome);
        TextView textViewGenero = (TextView) listViewItem.findViewById(R.id.textViewGenero);

        Cliente cliente = listaClientes.get(position);

        textViewNome.setText(cliente.getNomeCliente());
        textViewGenero.setText(cliente.getGeneroCliente());

        return listViewItem;
    }
}
