package com.airofbengal.playappwidget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

import java.util.Random;

/**
 * Created by hp on 25-Mar-16.
 */
public class MyWidgetProvider extends AppWidgetProvider {
    private static final String ACTION_CLICK = "ACTION_CLICK";

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        ComponentName thisWidget = new ComponentName(context,MyWidgetProvider.class);
        int[] allWidgetIds = appWidgetManager.getAppWidgetIds(thisWidget);
        for(int widgetId: allWidgetIds){
            int number = new Random().nextInt(100);

            RemoteViews remoteViews = new RemoteViews(context.getPackageName(),R.layout.widget_layout);
            Log.w(this.getClass().getName(),String.valueOf(number));

            // register an onClickListener
            Intent intent = new Intent(context,MyWidgetProvider.class);

            intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS,appWidgetIds);

            PendingIntent pendingIntent =
                    PendingIntent.getBroadcast(context,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
            remoteViews.setOnClickPendingIntent(R.id.update,pendingIntent);
            appWidgetManager.updateAppWidget(widgetId,remoteViews);
        }

    }
}
