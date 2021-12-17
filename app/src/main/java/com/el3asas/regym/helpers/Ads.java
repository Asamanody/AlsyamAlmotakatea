package com.el3asas.regym.helpers;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;

import com.google.android.ads.nativetemplates.TemplateView;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.nativead.NativeAd;

public class Ads {
    private NativeAd nativeAd;
    private final String TAG = "ADS";

    private AdLoader adLoader;
    private Runnable runnable;
    private Handler handler;
    private String plus = "";

    public void refreshAd(Context context, String name, TemplateView templateView, String ad_name) {
        plus = "++++++++++    " + name;
        adLoader = new AdLoader.Builder(context, ad_name)
                .forNativeAd(nativeAd -> {
                    templateView.setNativeAd(nativeAd);
                    templateView.setVisibility(View.VISIBLE);
                    this.nativeAd = nativeAd;
                    Log.d(TAG, "refreshAd:" + plus);
                })
                .withAdListener(new AdListener() {
                    @Override
                    public void onAdClosed() {
                        super.onAdClosed();
                        Log.d(TAG, "onAdClosed:" + plus);
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        super.onAdFailedToLoad(loadAdError);
                        ReloadAd();
                        Log.d(TAG, "onAdFailedToLoad:" + plus);
                    }

                    @Override
                    public void onAdOpened() {
                        super.onAdOpened();
                        Log.d(TAG, "onAdOpened:" + plus);
                    }

                    @Override
                    public void onAdLoaded() {
                        super.onAdLoaded();
                        ReloadAd();
                        Log.d(TAG, "onAdLoaded:" + plus);
                    }

                    @Override
                    public void onAdClicked() {
                        super.onAdClicked();
                        Log.d(TAG, "onAdClicked:" + plus);
                    }

                    @Override
                    public void onAdImpression() {
                        super.onAdImpression();
                        Log.d(TAG, "onAdImpression:" + plus);
                    }
                })
                .build();

        adLoader.loadAd(new AdRequest.Builder().build());
    }

    public void destroyAd() {
        Log.d(TAG, "destroyAd:" + plus);
        if (adLoader != null)
            adLoader = null;
        if (nativeAd != null)
            nativeAd.destroy();
        if (handler != null) {
            handler.removeCallbacks(runnable);
            handler = null;
        }
    }

    private void ReloadAd() {
        if (adLoader != null) {
            handler = new Handler();
            runnable = this::reloadAd;
            handler.postDelayed(runnable, 5000);
        }
    }

    public void reloadAd() {
        Log.d(TAG, "ReloadAd:" + plus);
        if (adLoader != null)
            adLoader.loadAd(new AdRequest.Builder().build());
    }
}
