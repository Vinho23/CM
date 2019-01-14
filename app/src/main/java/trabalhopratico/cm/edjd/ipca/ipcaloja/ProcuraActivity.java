package trabalhopratico.cm.edjd.ipca.ipcaloja;

import android.content.Intent;
import android.net.Uri;
import android.os.Debug;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;


public class ProcuraActivity extends AppCompatActivity {

    private static SeekBar seekBar;
    private static TextView textView;
    private static SeekBar seekBar2;
    private static TextView textView2;
    private static Button button;
    private static int a = 0;
    private static int b = 0;
    private static String site;
    private static Spinner spinner;
    private static String value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_procura);

        button = findViewById(R.id.searchButton);
        spinner = findViewById(R.id.spinenroo);
        value = getString(R.string.Solo);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(a != 0 && value == "Singular"){
                    site = "https://www.homeaway.pt/results/keywords:barcelos-distrito-de-braga-portugal/maxNightlyPrice/" + a +"?petIncluded=false&ssr=true&adultsCount=1";
                }else if(a != 0 && value != "Singular") {
                    site = "https://www.homeaway.pt/results/keywords:barcelos-distrito-de-braga-portugal?adultsCount=2&petIncluded=false";
                }
                else if(a == 0 && value != "Singular"){
                    site = "https://www.homeaway.pt/results/keywords:barcelos-distrito-de-braga-portugal?adultsCount=2&petIncluded=false";
                }else{
                    site = "https://www.homeaway.pt/results/keywords:barcelos-distrito-de-braga-portugal?adultsCount=1&petIncluded=false";
                }
                Uri webadress = Uri.parse(site); // Uri - universal resource identifier

                Intent gotoSite = new Intent(Intent.ACTION_VIEW, webadress);
                if (gotoSite.resolveActivity(getPackageManager()) != null) {
                    startActivity(gotoSite);
                }
            }

        });

        SeekBarPrice();
        SeekBarDistance();
    }

    public void SeekBarPrice(){
        seekBar = findViewById(R.id.seekBarPrice);
        textView = findViewById(R.id.textView);
        seekBar.setMax(1000);

        seekBar.setOnSeekBarChangeListener(

                new SeekBar.OnSeekBarChangeListener() {
                    int progressValue;
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        progressValue = progress;
                        textView.setText("" + progress + " / " + seekBar.getMax());
                        //Toast.makeText(ProcuraActivity.this, "SeekBar in Progress", Toast.LENGTH_LONG).show();
                        a = progressValue;
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                        //Toast.makeText(ProcuraActivity.this, "SeekBar in StartTracking", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        textView.setText(" " + progressValue + " / " + seekBar.getMax());
                        //Toast.makeText(ProcuraActivity.this, "SeekBar in StopTracking", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void SeekBarDistance(){
        seekBar2 = findViewById(R.id.seekBarDistance);
        textView2 = findViewById(R.id.textView2);

        seekBar2.setOnSeekBarChangeListener(

                new SeekBar.OnSeekBarChangeListener() {
                    int progressValue;
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        progressValue = progress;
                        seekBar2.setMax(10);
                        textView2.setText("" + progressValue + " / " + seekBar.getMax());
                        //Toast.makeText(ProcuraActivity.this, "SeekBar in Progress", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                        //Toast.makeText(ProcuraActivity.this, "SeekBar in StartTracking", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        textView2.setText(" " + progressValue + " / " + seekBar.getMax());
                        //Toast.makeText(ProcuraActivity.this, "SeekBar in StopTracking", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }
}
