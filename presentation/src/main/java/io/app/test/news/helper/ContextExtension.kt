package io.app.test.news.helper

import android.content.Context
import android.net.Uri
import androidx.browser.customtabs.CustomTabColorSchemeParams
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import io.app.test.news.R

fun Context.openLink(url: String) {
    val builder = CustomTabsIntent.Builder()
    val customTabsIntent = builder.build()
    val tabColorSchemeParams = CustomTabColorSchemeParams.Builder()
        .setToolbarColor(ContextCompat.getColor(this, R.color.colorAccent)).build()
    builder.setDefaultColorSchemeParams(tabColorSchemeParams)
    customTabsIntent.launchUrl(this, Uri.parse(url))
}
