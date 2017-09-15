package com.junlong.framecorelibrary.easypermissions.helper;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;

import com.junlong.framecorelibrary.easypermissions.RationaleDialogFragmentCompat;


/**
 * Implementation of {@link PermissionHelper} for Support Library host classes.
 */
public abstract class BaseSupportPermissionsHelper<T> extends PermissionHelper<T> {

    public BaseSupportPermissionsHelper(@NonNull T host) {
        super(host);
    }

    public abstract FragmentManager getSupportFragmentManager();

    @Override
    public void showRequestPermissionRationale(@NonNull String rationale,
                                               int positiveButton,
                                               int negativeButton,
                                               int requestCode,
                                               @NonNull String... perms) {
        RationaleDialogFragmentCompat
                .newInstance(positiveButton, negativeButton, rationale, requestCode, perms)
                .show(getSupportFragmentManager(), RationaleDialogFragmentCompat.TAG);
    }
}
