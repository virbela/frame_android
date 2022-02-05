package io.framevr.webchromeclient;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import android.webkit.PermissionRequest;
import android.webkit.WebChromeClient;
import android.os.Bundle;
import android.os.Handler;
import android.webkit.WebView;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    String url = "https://framevr.io/techtruth";

    WebView webView;
    ProgressBar progressBar;
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        webView = findViewById(R.id.web);
        swipeRefreshLayout = findViewById(R.id.swipe);

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

        //Refresh is nice
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                        webView.loadUrl(url);
                    }
                },  3000);
            }
        });
        swipeRefreshLayout.setColorSchemeColors(
                getResources().getColor(android.R.color.holo_blue_bright),
                getResources().getColor(android.R.color.holo_orange_dark),
                getResources().getColor(android.R.color.holo_green_dark),
                getResources().getColor(android.R.color.holo_red_dark)
        );
    }
}
