package com.example.fleegaclinic;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;
import com.example.fleegaclinic.R;

public class MainActivity extends AppCompatActivity {
    public WebView WV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WV = (WebView) findViewById(R.id.webView1);
        main_code("https://fleegaclinic.com/");
    }

    @SuppressLint("SetJavaScriptEnabled")
    public void main_code(final String link) {
        try {
            WebSettings webSettings = WV.getSettings();
            webSettings.setJavaScriptEnabled(true);
            webSettings.setLoadWithOverviewMode(true);
            webSettings.setAllowFileAccess(true);
            webSettings.setCacheMode(WebSettings.LOAD_DEFAULT); // Modern cache
            webSettings.setDomStorageEnabled(true); // HTML5 storage

            WV.setWebViewClient(new HelloWebViewClient());
            WV.loadUrl(link);
        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(), "Error!!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        if (WV.canGoBack()) {
            WV.goBack();
        } else {
            super.onBackPressed();
        }
    }

    private class HelloWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (url.contains("https://fleegaclinic.com/")) {
                view.loadUrl(url);
            } else {
                Uri urll = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, urll);
                view.getContext().startActivity(intent);
            }
            return true;
        }

        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            Toast.makeText(MainActivity.this, "error : " + errorCode, Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater =getMenuInflater();
        inflater.inflate(R.menu.item,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.video) {
            Intent a = new Intent(MainActivity.this, VideoCallActivity.class);
            startActivity(a);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

}