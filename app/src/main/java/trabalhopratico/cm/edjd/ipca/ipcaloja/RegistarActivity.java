package trabalhopratico.cm.edjd.ipca.ipcaloja;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistarActivity extends AppCompatActivity {

    EditText editTextNomeCliente;
    Button buttonRegistaCliente;
    Spinner spinnerCliente;

    DatabaseReference databaseClientes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registar);

        databaseClientes = FirebaseDatabase.getInstance().getReference("clientes");

        editTextNomeCliente = (EditText) findViewById(R.id.editTextNomeCliente);
        buttonRegistaCliente = (Button) findViewById(R.id.buttonRegistaCliente);
        spinnerCliente = (Spinner) findViewById(R.id.spinnerCliente);

        buttonRegistaCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCliente();
            }
        });
    }

    private void addCliente() {

        String nome = editTextNomeCliente.getText().toString().trim();
        String genero = spinnerCliente.getSelectedItem().toString();

        if (!TextUtils.isEmpty(nome)) {
            String id  = databaseClientes.push().getKey();

            Cliente cliente = new Cliente(id, nome, genero);

            databaseClientes.child(id).setValue(cliente);

            Toast.makeText(this, "Cliente adicionado", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "You should enter a name", Toast.LENGTH_LONG).show();
        }

    }
}
