package com.cnitpm.fire.Message;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
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
    @Autowired
    public int id=0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.messagecontent_layout);
        ARouter.getInstance().inject(this);
        MessageContent_Title=findViewById(R.id.MessageContent_Title);
        MessageContent_Time=findViewById(R.id.MessageContent_Time);
        MessageContent_Content=findViewById(R.id.MessageContent_Content);
        MainRequestServiceFactory.MymsgInfo(MessageContent_Title,getApplicationContext(),MessageContent_Time,MessageContent_Content,id);
    }
}
