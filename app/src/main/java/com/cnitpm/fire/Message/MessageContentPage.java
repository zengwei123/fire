package com.cnitpm.fire.Message;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.cnitpm.fire.Net.MainRequestServiceFactory;
import com.cnitpm.fire.R;

/**会员消息内容**/
@Route(path = "/Main/MessageContentPage")
public class MessageContentPage extends AppCompatActivity {
    private TextView MessageContent_Title;
    private TextView MessageContent_Time;
    private TextView MessageContent_Content;

    private TextView Include_Title_Text;
    private ImageView Include_Title_Close;
    @Autowired
    public int id=0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.messagecontent_layout);
        if (getSupportActionBar() != null)
            getSupportActionBar().hide();
        ARouter.getInstance().inject(this);
        Include_Title_Text=findViewById(R.id.Include_Title_Text);
        Include_Title_Text.setText("会员消息");
        Include_Title_Close=findViewById(R.id.Include_Title_Close);
        Include_Title_Close.setOnClickListener(v -> finish());
        MessageContent_Title=findViewById(R.id.MessageContent_Title);
        MessageContent_Time=findViewById(R.id.MessageContent_Time);
        MessageContent_Content=findViewById(R.id.MessageContent_Content);
        MainRequestServiceFactory.MymsgInfo(MessageContent_Title,getApplicationContext(),MessageContent_Time,MessageContent_Content,id);
    }
}
