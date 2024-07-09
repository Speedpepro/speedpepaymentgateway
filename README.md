# SpeedPe Payment Gateway SDK

The SpeedPe Payment Gateway SDK allows merchants to integrate the SpeedPe payment gateway into their Android applications easily. This SDK provides a simple interface to initiate payments and handle responses.

## Installation

To use the SDK in your project, follow these steps:

1. **Add the JitPack repository to your root `build.gradle` file:**

```gradle
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```
2. **Add the dependency in your app-level build.gradle file**
```
dependencies {
    implementation 'com.github.yourusername:speedpepayment:1.0.0'
}
```
## Initialize the SDK
First, create an instance of the `SpeedPePaymentGateway` class by providing your public key and secret key:

```
SpeedPePaymentGateway paymentGateway = new SpeedPePaymentGateway(
    "your_public_key",
    "your_secret_key"
);
```
## Initiate a Payment
Use the `initiatePayment` method to start a payment. This method requires the customer's mobile number, amount, order ID, redirect URL, and a note. You also need to implement the `PaymentCallback` interface to handle the success and failure responses.

```
paymentGateway.initiatePayment(
    "customer_mobile",
    15.0,
    "order_id",
    "https://yourredirecturl.com",
    "Add Money",
    new SpeedPePaymentGateway.PaymentCallback() {
        @Override
        public void onSuccess(String response) {
            // Handle success
            Log.d("PaymentSuccess", response);
        }

        @Override
        public void onFailure(String error) {
            // Handle failure
            Log.d("PaymentFailure", error);
        }
    }
);

```
## Example
Here is a complete example of how to use the SDK in an `Activity`:

```
public class MainActivity extends AppCompatActivity {

    private SpeedPePaymentGateway paymentGateway;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String publicKey = "your_public_key";
        String secretKey = "your_secret_key";

        paymentGateway = new SpeedPePaymentGateway(publicKey, secretKey);

        initiatePayment();
    }

    private void initiatePayment() {
        paymentGateway.initiatePayment(
            "customer_mobile",
            15.0,
            "order_id",
            "https://yourredirecturl.com",
            "Add Money",
            new SpeedPePaymentGateway.PaymentCallback() {
                @Override
                public void onSuccess(String response) {
                    // Handle success
                    Log.d("PaymentSuccess", response);
                }

                @Override
                public void onFailure(String error) {
                    // Handle failure
                    Log.d("PaymentFailure", error);
                }
            }
        );
    }
}
```

## License
This SDK is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

