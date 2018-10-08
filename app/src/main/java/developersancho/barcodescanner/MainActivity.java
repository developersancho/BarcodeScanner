package developersancho.barcodescanner;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;

import com.google.android.gms.samples.vision.barcodereader.BarcodeCapture;
import com.google.android.gms.samples.vision.barcodereader.BarcodeGraphic;
import com.google.android.gms.vision.barcode.Barcode;

import java.util.List;

import xyz.belvi.mobilevisionbarcodescanner.BarcodeRetriever;

public class MainActivity extends AppCompatActivity implements BarcodeRetriever {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BarcodeCapture barcodeCapture = (BarcodeCapture) getSupportFragmentManager().findFragmentById(R.id.barcode);
        barcodeCapture.setRetrieval(this);
    }

    @Override
    public void onRetrieved(final Barcode barcode) {
        Log.d(MainActivity.this.getClass().getSimpleName(), "Barcode read: " + barcode.displayValue);
        /*runOnUiThread(new Runnable() {
            @Override
            public void run() {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Code Retrieved");
                builder.setMessage(barcode.displayValue);
                builder.show();
            }
        });*/
    }

    @Override
    public void onRetrievedMultiple(final Barcode closetToClick, final List<BarcodeGraphic> barcodeGraphics) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                String message = "Code selected : " + closetToClick.displayValue + "\n\nother " +
                        "codes in frame include : \n";
                for (int index = 0; index < barcodeGraphics.size(); index++) {
                    Barcode barcode = barcodeGraphics.get(index).getBarcode();
                    message += (index + 1) + ". " + barcode.displayValue + "\n";
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this)
                        .setTitle("code multiple retrieved")
                        .setMessage(message);
                builder.show();
            }
        });

    }

    @Override
    public void onBitmapScanned(SparseArray<Barcode> sparseArray) {

    }

    @Override
    public void onRetrievedFailed(String reason) {

    }

    @Override
    public void onPermissionRequestDenied() {

    }
}
