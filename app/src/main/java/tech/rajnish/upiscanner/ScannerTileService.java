package tech.rajnish.upiscanner;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.service.quicksettings.TileService;
public class ScannerTileService extends TileService {
 @Override public void onClick() {
  super.onClick();
  Intent intent = new Intent(this, ScannerActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
  if (Build.VERSION.SDK_INT >= 34) startActivityAndCollapse(PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE));
  else { startActivityAndCollapse(intent); }
 }
}