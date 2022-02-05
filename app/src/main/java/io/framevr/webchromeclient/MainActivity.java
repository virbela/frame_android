package io.framevr.webchromeclient;

import androidx.appcompat.app.AppCompatActivity;
import android.webkit.PermissionRequest;
import android.webkit.WebChromeClient;
import android.os.Bundle;
import android.os.Handler;
import android.webkit.WebView;

public class MainActivity extends AppCompatActivity {

    String url = "https://framevr.io/techtruth";

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        webView = findViewById(R.id.web);

        //Assign a WebChromeClient
        webView.setWebChromeClient(new WebChromeClient() {
            //Important to request.grant in onPermisionsRequest
            @Override
            public void onPermissionRequest(final PermissionRequest request) {
                            request.grant(request.getResources());
            }
        });

        //Load webpage inside WebChromeClient
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);
    }
}
