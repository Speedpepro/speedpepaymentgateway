package pro.speedpe.speedpepayment;

import androidx.annotation.NonNull;

import okhttp3.*;

import java.io.IOException;

public class SpeedPePaymentGateway {

    private static final String BASE_URL = "https://speedpe.pro/apis/sandbox/v1/pay";
    private final String publicKey;
    private final String secretKey;

    public SpeedPePaymentGateway(String publicKey, String secretKey) {
        this.publicKey = publicKey;
        this.secretKey = secretKey;
    }

    public void initiatePayment(String customerMobile, double amount, String orderId, String redirectUrl, String note, PaymentCallback callback) {
        OkHttpClient client = new OkHttpClient();

        RequestBody formBody = new FormBody.Builder()
                .add("public_key", publicKey)
                .add("secret_key", secretKey)
                .add("customer_mobile", customerMobile)
                .add("amount", String.valueOf(amount))
                .add("order_id", orderId)
                .add("redirect_url", redirectUrl)
                .add("note", note)
                .build();

        Request request = new Request.Builder()
                .url(BASE_URL)
                .post(formBody)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                callback.onFailure(e.getMessage());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    String responseBody = response.body().string();
                    callback.onSuccess(responseBody);
                } else {
                    callback.onFailure(response.message());
                }
            }
        });
    }

    public interface PaymentCallback {
        void onSuccess(String response);

        void onFailure(String error);
    }
}

