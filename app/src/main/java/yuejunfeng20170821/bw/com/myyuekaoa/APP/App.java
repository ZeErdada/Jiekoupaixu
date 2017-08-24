package yuejunfeng20170821.bw.com.myyuekaoa.APP;

import android.app.Application;

import org.xutils.BuildConfig;
import org.xutils.x;

/**
 * Created by admin on 2017/8/23.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG);
    }
}
