package milandro.blogs.uny.ac.id.kamusjawaindonesia;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

/**
 * Created by milstrike on 23/02/2016.
 */
public class TentangActivity extends AppCompatActivity {

    private Context context = this;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tentang);
    }

    public void giveRating(View V){
        Uri uri = Uri.parse("market://details?id=" + context.getPackageName());
        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
        // To count with Play market backstack, After pressing back button,
        // to taken back to our application, we need to add following flags to intent.
        goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET |
                Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        try {
            startActivity(goToMarket);
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://play.google.com/store/apps/details?id=" + context.getPackageName())));
        }
    }

    public void giveSuggest(View V){
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"dev.mil.system@gmail.com"});
        i.putExtra(Intent.EXTRA_SUBJECT, "Saran Paribasan Jawa");
        i.putExtra(Intent.EXTRA_TEXT   , "Saran: ");
        try {
            startActivity(Intent.createChooser(i, "Kirim Email: "));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(TentangActivity.this, "Tidak ada aplikasi email terinstall.", Toast.LENGTH_SHORT).show();
        }
    }

}
