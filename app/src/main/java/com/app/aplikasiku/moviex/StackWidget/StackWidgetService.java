package com.app.aplikasiku.moviex.StackWidget;

import android.content.Intent;
import android.widget.RemoteViewsService;

public class StackWidgetService extends RemoteViewsService {

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new FavoriteStackWidgetRemote(this.getApplicationContext(), intent);
    }
}
