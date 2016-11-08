package com.zecovery.android.ptrac.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.zecovery.android.ptrac.R;

public class FragmentInfo extends Fragment {

    private OnFragmentInteractionListener listener;
    private WebView mWebView;
    private static final String URL_PERRY_GATTY = "http://www.defensoresdemascotas.cl/";
    private ProgressBar mProgressBar;
    private Context context;


    public FragmentInfo() {
    }

    public static FragmentInfo newInstance() {
        return new FragmentInfo();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        context = getActivity().getApplicationContext();

        View rootView = inflater.inflate(R.layout.fragment_info, container, false);

        mWebView = (WebView) rootView.findViewById(R.id.webView);
        mProgressBar = (ProgressBar) rootView.findViewById(R.id.progressBar);

        initWebView(URL_PERRY_GATTY, true);
        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            listener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;


    }

    public interface OnFragmentInteractionListener {
    }

    private void initWebView(final String url, boolean js) {

        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(js);
        mProgressBar.setIndeterminate(true);
        mWebView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                mProgressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                Toast.makeText(context, "some error!!!!!", Toast.LENGTH_SHORT).show();
            }
        });
        mWebView.loadUrl(url);
    }
}
