package tech.rajnish.upiscanner;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;
import androidx.activity.result.ActivityResultLauncher;
public class ScannerActivity extends Activity {
 private boolean started = false;
 private final ActivityResultLauncher<ScanOptions> scanner = registerForActivityResult(new ScanContract(), result -> {
  if (result.getContents() == null) { finish(); return; }
  String raw = result.getContents().trim();
  Uri uri = Uri.parse(raw);
  if (!"upi".equalsIgnoreCase(uri.getScheme()) || !"pay".equalsIgnoreCase(uri.getHost())) {
   Toast.makeText(this, "Not a UPI payment QR", Toast.LENGTH_LONG).show(); finish(); return;
  }
  Intent payment = new Intent(Intent.ACTION_VIEW, uri);
  payment.addCategory(Intent.CATEGORY_DEFAULT);
  try { startActivity(Intent.createChooser(payment, "Pay using")); }
  catch (ActivityNotFoundException ex) { Toast.makeText(this, "No compatible UPI app found", Toast.LENGTH_LONG).show(); }
  finish();
 });
 @Override protected void onCreate(Bundle state) {
  super.onCreate(state);
  if (state == null) {
   ScanOptions options = new ScanOptions();
   options.setDesiredBarcodeFormats(ScanOptions.QR_CODE);
   options.setPrompt("");
   options.setBeepEnabled(false);
   options.setOrientationLocked(true);
   scanner.launch(options);
  }
 }
}