package trabalhopratico.cm.edjd.ipca.ipcaloja;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RegistarActivity extends AppCompatActivity {

    EditText editTextNomeCliente;
    Button buttonRegistaCliente;
    Spinner spinnerCliente;
    EditText editTextEmail;
    EditText editTextPassword;

    DatabaseReference databaseClientes;

    ListView listViewClientes;

    List<Cliente> listaClientes;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registar);

        databaseClientes = FirebaseDatabase.getInstance().getReference("clientes");


        firebaseAuth = FirebaseAuth.getInstance();

        editTextNomeCliente = (EditText) findViewById(R.id.editTextNomeCliente);
        buttonRegistaCliente = (Button) findViewById(R.id.buttonRegistaCliente);
        spinnerCliente = (Spinner) findViewById(R.id.spinnerCliente);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);

        listViewClientes = (ListView) findViewById(R.id.listViewClientes);

        listaClientes = new ArrayList<>();
        buttonRegistaCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCliente();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseClientes.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) { // isto lê os valores no firebase database, vai buscar todos os valores no databaseClientes

                listaClientes.clear();

                for (DataSnapshot clienteSnapshot: dataSnapshot.getChildren()) {
                    Cliente cliente = clienteSnapshot.getValue(Cliente.class);

                    listaClientes.add(cliente);
                }

                ListaClientes adapter = new ListaClientes(RegistarActivity.this, listaClientes);
                listViewClientes.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void addCliente() {

        String nome = editTextNomeCliente.getText().toString().trim();
        String genero = spinnerCliente.getSelectedItem().toString();

        String user_email = editTextEmail.getText().toString().trim();
        String user_password = editTextPassword.getText().toString().trim();

        firebaseAuth.createUserWithEmailAndPassword(user_email, user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Toast.makeText(RegistarActivity.this, "Registo com sucesso", Toast.LENGTH_SHORT).show();
            }
        });

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
