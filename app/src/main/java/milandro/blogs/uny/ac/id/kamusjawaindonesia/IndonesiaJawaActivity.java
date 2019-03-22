package milandro.blogs.uny.ac.id.kamusjawaindonesia;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by milstrike on 23/02/2016.
 */
public class IndonesiaJawaActivity extends AppCompatActivity {

    private ListView listView;
    private TextView cariIndonesia;
    private RelativeLayout LayoutTampilCari, mainlayout;
    private TextView textjawa, textindonesia, textIndonesia2;
    private LinearLayout tutupLayoutTampil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indonesia);

        listView = (ListView) findViewById(R.id.listviewindonesia);
        cariIndonesia = (TextView) findViewById(R.id.searchindonesia);

        mainlayout = (RelativeLayout) findViewById(R.id.mainLayout);
        LayoutTampilCari = (RelativeLayout) findViewById(R.id.tampilArtiLayout);
        tutupLayoutTampil = (LinearLayout) findViewById(R.id.tutupArtiLayout);
        textjawa = (TextView) findViewById(R.id.tampilJawa);
        textindonesia = (TextView) findViewById(R.id.tampilIndonesia);
        textIndonesia2 = (TextView) findViewById(R.id.artiIndonesia);

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        List<String> daftarIndonesia = databaseAccess.getKamusIndonesia();
        databaseAccess.close();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, daftarIndonesia);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedFromList = (listView.getItemAtPosition(position).toString());
                getArti(selectedFromList);
            }
        });

        cariIndonesia.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                listChanger(cariIndonesia.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }


    public void listChanger(String keyword){
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        List<String> daftarIndonesia = databaseAccess.getSearchIndonesia(keyword);
        databaseAccess.close();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, daftarIndonesia);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedFromList = (listView.getItemAtPosition(position).toString());
                getArti(selectedFromList);
            }
        });
    }

    public void getArti(String indonesia){
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        String hasilArti = databaseAccess.getSelectedIndonesia(indonesia);
        String hasilArti2 = databaseAccess.getSelectedIndonesia2(indonesia);
        databaseAccess.close();

        textjawa.setText(indonesia);
        textindonesia.setText(hasilArti);
        textIndonesia2.setText(hasilArti2);

        tampilLayoutArti();

        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);

    }

    public void tampilLayoutArti(){
        mainlayout.setEnabled(false);
        LayoutTampilCari.setVisibility(View.VISIBLE);
    }

    public void dismisstampilLayoutArti(){
        LayoutTampilCari.setVisibility(View.GONE);
        mainlayout.setEnabled(true);
    }

    public void closeInAction(View V){
        dismisstampilLayoutArti();
    }

}
