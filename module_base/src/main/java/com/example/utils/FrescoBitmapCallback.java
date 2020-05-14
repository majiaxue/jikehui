package com.example.utils;

import android.net.Uri;

public interface FrescoBitmapCallback<T> {
    void onSuccess(Uri uri, T result);

    void onFailure(Uri uri, Throwable throwable);

    void onCancel(Uri uri);
}
