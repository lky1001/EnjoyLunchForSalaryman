package com.soundlly.enjoylaunchforsalaryman.network.service;

import com.soundlly.enjoylaunchforsalaryman.LunchApplication;
import com.soundlly.enjoylaunchforsalaryman.BuildConfig;

import java.io.File;
import java.io.IOException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by soundllydev on 2016. 3. 31..
 */
public class ServiceBuilder {

    /** 캐시 50메가 **/
    private static final int DISK_CACHE_SIZE = 50 * 1024 * 1024;

    private static Retrofit.Builder retrofitBuilder = new Retrofit.Builder();

    private ServiceBuilder() {}

    public static <T> T getService(Class<T> serviceClass, String baseUrl) {
        OkHttpClient okHttpClient = getClient();

        okHttpClient.networkInterceptors().add(new Interceptor() {
            @Override
            public Response intercept(Interceptor.Chain chain) throws IOException {
                if(chain != null){
                    Request originalRequest = chain.request();

//                    if (accessToken != null) {
//                        String authorization = accessToken.getTokenType() + " " + accessToken.getAccessToken();
//
//                        Request modifiedRequest = originalRequest.newBuilder()
//                                .header("Authorization", authorization)
//                                .header("Accept", "application/vnd.vimeo.*+json; version=3.2")
//                                .build();
//                        Timber.d("Authorization : "+ authorization);
//
//                        return chain.proceed(modifiedRequest);
//                    } else {
//                        return chain.proceed(originalRequest);
//                    }
                }

                return null;
            }
        });

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        if (BuildConfig.DEBUG) {
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
        }        okHttpClient.interceptors().add(httpLoggingInterceptor); // Add only for debugging purposes

        retrofitBuilder.client(okHttpClient);
        retrofitBuilder.baseUrl(baseUrl);
        retrofitBuilder.addConverterFactory(JacksonConverterFactory.create());

        Retrofit retrofit = retrofitBuilder.build();
        return retrofit.create(serviceClass);
    }

    private static OkHttpClient getClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(10, TimeUnit.SECONDS);
        builder.readTimeout(10, TimeUnit.SECONDS);
        builder.writeTimeout(10, TimeUnit.SECONDS);

        // Install an HTTP cache in the application cache directory.
        try {
            File cacheDir = new File(LunchApplication.getCacheDirectory(), "http");
            Cache cache = new Cache(cacheDir, DISK_CACHE_SIZE);
            builder.cache(cache);
        } catch (Exception e) {
            e.printStackTrace();
        }
        builder.sslSocketFactory(createBadSslSocketFactory());

        OkHttpClient client = builder.build();

        return client;
    }

    private static SSLSocketFactory createBadSslSocketFactory() {
        try {
            // Construct SSLSocketFactory that accepts any cert.
            SSLContext context = SSLContext.getInstance("TLS");
            TrustManager permissive = new X509TrustManager() {
                @Override
                public void checkClientTrusted(X509Certificate[] chain, String authType)
                        throws CertificateException {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] chain, String authType)
                        throws CertificateException {
                }

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
            };
            context.init(null, new TrustManager[] { permissive }, null);
            return context.getSocketFactory();
        } catch (Exception e) {
            throw new AssertionError(e);
        }
    }
}
