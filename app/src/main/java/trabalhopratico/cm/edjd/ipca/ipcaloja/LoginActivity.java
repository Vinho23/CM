package trabalhopratico.cm.edjd.ipca.ipcaloja;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {


    private EditText Nome;
    private EditText Password;
    private TextView Info;
    private Button Login;
    private int counter = 5;
    private Button btnLoginMain;

    private TextView textViewNovoRegisto;


    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Nome = (EditText) findViewById(R.id.editTextNome);
        Password = (EditText) findViewById(R.id.editTextPassword);
        Info = (TextView) findViewById(R.id.textViewLogin);
        Login = (Button) findViewById(R.id.btnLogin);

        btnLoginMain = (Button) findViewById(R.id.btnLoginMain) ;

        Info.setText("Número de tentativas restantes: 5");


        firebaseAuth = FirebaseAuth.getInstance();

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(Nome.getText().toString(), Password.getText().toString());
                //String
            }
        });

        textViewNovoRegisto = (TextView) findViewById(R.id.textViewNovoRegisto);

        textViewNovoRegisto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegistarActivity.class));
            }
        });
    }

    private void validate (String userName, String userPassword) {
        if((userName.equals("Admin")) && (userPassword.equals("1234"))) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }
        else{
            counter--;

            Info.setText("Número de tentativas restantes: " + String.valueOf(counter));

            if (counter == 0) {
                Login.setEnabled(false);
            }

        }
    }
}
