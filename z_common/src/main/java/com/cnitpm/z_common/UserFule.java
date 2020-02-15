package com.cnitpm.z_common;

import android.util.Base64;

import com.cnitpm.z_base.BaseActivity;
import com.cnitpm.z_common.Model.UserMessage;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Reader;

public class UserFule {
    /**登录信息写入**/
    public static void PutUser(UserMessage userMessage){
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos  = new ObjectOutputStream(baos);
            oos.writeObject(userMessage);//把对象写到流里
            String temp = new String(Base64.encode(baos.toByteArray(), Base64.DEFAULT));

            File file = new File(BaseActivity.getInstance().getExternalCacheDir().getPath()+"/", "user.txt");
            file.createNewFile(); // 创建文件

            byte bt[] = new byte[1024];
            bt = temp.getBytes();
            FileOutputStream in = new FileOutputStream(file);
            in.write(bt, 0, bt.length);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**登录信息删除**/
    public static void RemoveUser(){
        try {
            File file = new File(BaseActivity.getInstance().getExternalCacheDir().getPath()+"/", "user.txt");
            file.createNewFile(); // 创建文件

            byte bt[] = new byte[1024];
            bt = "ZW".getBytes();
            FileOutputStream in = new FileOutputStream(file);
            in.write(bt, 0, bt.length);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**文件读取**/
    public static UserMessage GetUser(){
        try {
            File file = new File(BaseActivity.getInstance().getExternalCacheDir().getPath()+"/user.txt");
            if(!file.exists()||file.isDirectory())
                throw new FileNotFoundException();
            BufferedReader br=new BufferedReader(new FileReader(file));
            String temp=null;
            StringBuffer sb=new StringBuffer();
            temp=br.readLine();
            while(temp!=null){
                sb.append(temp+" ");
                temp=br.readLine();
            }
            if (sb.toString().trim().equals("ZW")){
                return null;
            }else {
                ByteArrayInputStream bais =  new ByteArrayInputStream(Base64.decode(sb.toString().getBytes(), Base64.DEFAULT));
                ObjectInputStream ois = new ObjectInputStream(bais);
                UserMessage userMessage= (UserMessage) ois.readObject();
                return userMessage;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
