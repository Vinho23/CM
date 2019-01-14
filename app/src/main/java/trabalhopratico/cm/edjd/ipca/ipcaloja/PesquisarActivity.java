package trabalhopratico.cm.edjd.ipca.ipcaloja;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PesquisarActivity extends AppCompatActivity {

    private static EditText text;
    private static Button button;
    private static Button shareBtn;
    private static String site;
    private static String site1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisar);

        text = findViewById(R.id.editTextID);
        button = findViewById(R.id.pesquisarButton);
        shareBtn = findViewById(R.id.shareButton);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                site = "https://www.homeaway.pt/arrendamento-ferias/p" + text.getText();
                Uri webadress = Uri.parse(site); // Uri - universal resource identifier

                Intent gotoSite = new Intent(Intent.ACTION_VIEW, webadress);
                if (gotoSite.resolveActivity(getPackageManager()) != null) {
                    startActivity(gotoSite);
                }
            }
        });

        shareBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                site1 = "https://www.homeaway.pt/arrendamento-ferias/p" + text.getText();
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, site1);
                startActivity(intent);
            }
        });


    }
}
