package ir.tafreshiali.ayan_core_util.helper

import android.content.Context

fun Context.getApplicationForPishkhan(pishkhanApplicationName: Int): String =
    this.resources.getString(pishkhanApplicationName)

fun Context.getApplicationName(applicationName: Int): String =
    this.resources.getString(applicationName)


fun Context.getApplicationType(applicationType: Int): String =
    this.resources.getString(applicationType)

fun Context.getApplicationVersion(): String =
    this.packageManager
        .getPackageInfo(this.packageName, 0)
        .versionName

fun getApplicationCategory(applicationUniqueToken: String) =
    when (applicationUniqueToken.lowercase()) {
        "playstore" -> "playstore"
        "charkhoneh" -> "charkhoneh"
        "cafebazaar" -> "cafebazaar"
        "myket" -> "myket"
        "bulk" -> "bulk"
        else -> "socialmedia"
    }