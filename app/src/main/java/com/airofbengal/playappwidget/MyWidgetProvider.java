package com.airofbengal.playappwidget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.text.Layout;
import android.util.Log;
import android.widget.Button;
import android.widget.RemoteViews;

import java.util.Random;

/**
 * Created by hp on 25-Mar-16.
 */
public class MyWidgetProvider extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        ComponentName thisWidget = new ComponentName(context,MyWidgetProvider.class);
        int[] allWidgetIds = appWidgetManager.getAppWidgetIds(thisWidget);


        for(int widgetId: allWidgetIds){
            Intent intent = new Intent(context,MainActivity.class);

            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,widgetId);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            // context.startActivity(intent);

            PendingIntent pendingIntent =
                    PendingIntent.getActivity(context,0,intent,0);

            RemoteViews remoteViews = new RemoteViews(context.getPackageName(),R.layout.widget_layout);

            remoteViews.setOnClickPendingIntent(R.id.bStart,pendingIntent);
            appWidgetManager.updateAppWidget(widgetId,remoteViews);
        }
    }
}
