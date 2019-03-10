package com.example.omarf.miscontactos.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.ShareCompat;

/**
 * Created by Omarfer.
 */

public class IntentUtils {

    private Context context;

    public IntentUtils(Context context) {
        this.context = context;
    }

    public void intentWEB(String url) {
        Intent web = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        context.startActivity(web);
    }

    public void intentCall(String telefono) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse(telefono));
        context.startActivity(intent);

    }

    public void intentEmail(String email, String subject, String body, String title) {
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + email));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        emailIntent.putExtra(Intent.EXTRA_TEXT, body);
        //emailIntent.putExtra(Intent.EXTRA_HTML_TEXT, body); //If you are using HTML in your body text
        //intent.putExtra(Intent.EXTRA_EMAIL, addresses); // String[] addresses -- for Android 4.3
        context.startActivity(Intent.createChooser(emailIntent, title));

    }


}
