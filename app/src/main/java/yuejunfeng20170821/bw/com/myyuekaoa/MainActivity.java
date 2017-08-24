package yuejunfeng20170821.bw.com.myyuekaoa;

import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.sn.xlistviewlibrary.XListView;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import yuejunfeng20170821.bw.com.myyuekaoa.adapter.MyBaseAdapter;
import yuejunfeng20170821.bw.com.myyuekaoa.bean.ClassBean;

public class MainActivity extends AppCompatActivity {

    private XListView xlist_view1;
    private Button btn_di;
    private Button btn_gao;
    private String path = "http://api.fang.anjuke.com/m/android/1.3/shouye/recInfosV3/?city_id=14&lat=40.04652&lng=116.306033&api_key=androidkey&sig=9317e9634b5fbc16078ab07abb6661c5&macid=45cd2478331b184ff0e15f29aaa89e3e&app=a-ajk&_pid=11738&o=PE-TL10-user+4.4.2+HuaweiPE-TL10+CHNC00B260+ota-rel-keys%2Crelease-keys&from=mobile&m=Android-PE-TL10&cv=9.5.1&cid=14&i=864601026706713&v=4.4.2&pm=b61&uuid=1848c59c-185d-48d9-b0e9-782016041109&_chat_id=0&qtime=20160411091603";
    private List<ClassBean.ResultBean.RowsBean> list;
    private MyBaseAdapter adapter;
    private Handler handler =new Handler(){
        @Override
        public void handleMessage(Message msg) {
            adapter.notifyDataSetChanged();
        }
    };
    private File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        xlist_view1 = (XListView) findViewById(R.id.xlist_view1);
        btn_di = (Button) findViewById(R.id.btn_di);
        btn_gao = (Button) findViewById(R.id.btn_gao);
        //先得到文件位置目录
        String paths = Environment.getExternalStorageDirectory().getPath();
        file = new File(paths, "aa");
        if (!file.exists()) {
            try {
                file.createNewFile();
                System.out.println(file.getPath().toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        RequestParams params = new RequestParams(path);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                ClassBean classBean = gson.fromJson(result, ClassBean.class);
                list=classBean.result.rows;
                adapter = new MyBaseAdapter(MainActivity.this,list);
                xlist_view1.setAdapter(adapter);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
        btn_di.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Collections.sort(list);
                adapter.notifyDataSetChanged();
            }
        });
        btn_gao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Collections.reverse(list);
                adapter.notifyDataSetChanged();

            }
        });

    }
}
