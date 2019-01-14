package trabalhopratico.cm.edjd.ipca.ipcaloja;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    public String site;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Attempt to launch an activity outside our own app -> go to website we want
        Button quartosBtn = findViewById(R.id.quartosBtn);
        quartosBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                site = "https://www.homeaway.pt/results/keywords:barcelos-distrito-de-braga-portugal?adultsCount=1&petIncluded=false";
                Uri webadress = Uri.parse(site); // Uri - universal resource identifier

                Intent gotoSite = new Intent(Intent.ACTION_VIEW, webadress);
                if (gotoSite.resolveActivity(getPackageManager()) != null) {
                    startActivity(gotoSite);
                }
            }
        });
    }


    public void onProcuraActivity(View view) {
        Intent intent = new Intent(this, ProcuraActivity.class);
        startActivity(intent);
    }

    public void onPesquisarActivity(View view){
        Intent intent = new Intent(this, PesquisarActivity.class);
        startActivity(intent);
    }

    public void onRegistarActivity(View view) {
        Intent intent = new Intent(this, RegistarActivity.class);
        startActivity(intent);
    }
}