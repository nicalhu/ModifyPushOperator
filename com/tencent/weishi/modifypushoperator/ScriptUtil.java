package com.tencent.weishi.view;

import com.tencent.weishi.common.Config;
import com.tencent.weishi.util.*;
//import net.sf.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.*;
import java.util.List;

public class ScriptUtil {
    private Frame frame;
    private TextArea ta1;
    private TextArea ta2;
    private TextArea ta3;
    private Button jsonHandle;
    private Button videoHandle;
    private Button getOppositeJson;
    private Button jsonCompare;

    private Dialog jsonDialog;
    private Label jsonLabel;
    private Button jsonButton;

    private Dialog videoDialog;
    private Label videoLabel;
    private Button videoButton;

    private Dialog getOppositeJsonDialog;
    private Label getOppositeJsonLabel;
    private Button getOppositeJsonButton;

    private Dialog jsonCompareDialog;
    private Label jsonCompareLabel;
    private Button jsonCompareButton;

    public static void main(String[] args) {
        new ScriptUtil();
    }

    public ScriptUtil(){
        init();
    }

    public void init(){
        frame = new Frame("ScriptUtil");
        frame.setBounds(300,100,600,950 );
        frame.setLayout(new FlowLayout());

        ta1 = new TextArea(25,70);
        ta1.setForeground(Color.blue);
        ta1.append("文本显示区域：\r\n");

        ta2 = new TextArea(17,70);
        ta2.setForeground(Color.green);
        ta2.append("错误字段信息：\r\n");

        ta3 = new TextArea(8,70);
        ta3.setForeground(Color.red);
        ta3.append("不存在字段信息：\r\n");

        jsonHandle = new Button("处理json信息");
        videoHandle = new Button("处理video信息");
        getOppositeJson = new Button("获取反序列化json文件");
        jsonCompare = new Button("一键对比json");

        frame.add(ta1);
        frame.add(ta2);
        frame.add(ta3);
        frame.add(jsonHandle);
        frame.add(videoHandle);
        frame.add(getOppositeJson);
        frame.add(jsonCompare);

        jsonDialog = new Dialog(frame,"提示信息",true);
        jsonDialog.setBounds(400,200,400,100);
        jsonDialog.setLayout(new FlowLayout());
        jsonLabel = new Label();
        jsonButton = new Button("OK");
        jsonDialog.add(jsonLabel);
        jsonDialog.add(jsonButton);

        videoDialog = new Dialog(frame,"提示消息",true);
        videoDialog.setBounds(400,200,400,100);
        videoDialog.setLayout(new FlowLayout());
        videoLabel = new Label();
        videoButton = new Button("OK");
        videoDialog.add(videoLabel);
        videoDialog.add(videoButton);

        getOppositeJsonDialog = new Dialog(frame,"提示信息",true);
        getOppositeJsonDialog.setBounds(400,200,400,100);
        getOppositeJsonDialog.setLayout(new FlowLayout());
        getOppositeJsonLabel = new Label();
        getOppositeJsonButton = new Button("OK");
        getOppositeJsonDialog.add(getOppositeJsonLabel);
        getOppositeJsonDialog.add(getOppositeJsonButton);

        jsonCompareDialog = new Dialog(frame,"提示信息",true);
        jsonCompareDialog.setBounds(400,200,400,100);
        jsonCompareDialog.setLayout(new FlowLayout());
        jsonCompareLabel = new Label();
        jsonCompareButton = new Button("OK");
        jsonCompareDialog.add(jsonCompareLabel);
        jsonCompareDialog.add(jsonCompareButton);

        myEvent();
        frame.setVisible(true);
    }

    public void myEvent(){

        jsonCompareButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jsonCompareDialog.setVisible(false);
            }
        });

//        jsonCompare.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                for(int i=0;i<(Config.jsonpath.size())/2;i++){
//                    String JsonContext1 = new ReadUtil().ReadFile(Config.jsonpath.get(i));
//                    JsonContext1 = JsonContext1.replace("\\\"","'");
//                    jsonObject1 jsonObject1 = JSONObject.fromObject(JsonContext1);
//                    Map<String, Object> map1 =jsonObject1;
//
//                    String JsonContext2 = new ReadUtil().ReadFile(Config.jsonpath.get(i+14));
//                    JsonContext2 = JsonContext2.replace("\\\"","'");
//                    JSONObject jsonObject2 = JSONObject.fromObject(JsonContext2);
//                    Map<String, Object> map2 =jsonObject2;
//
//                    java.util.List<String> list1 = new ArrayList<>();
//                    //List<Object> listObject1 = new ArrayList<>();
//                    HashMap<String,Object> hashMap1 = new HashMap<>();
//                    List<String> list2 = new ArrayList<>();
//                    //List<Object> listObject2 = new ArrayList<>();
//                    HashMap<String,Object> hashMap2 = new HashMap<>();
//
//                    System.out.println(Config.jsonpath.get(i));
//
//                    Set set1 = map1.keySet();
//                    Iterator site1 = set1.iterator();
//                    while(site1.hasNext()){
//                        Object key = site1.next();
//                        Object value = map1.get(key);
//                        list1.add((String) key);
//                        //listObject1.add(value);
//                        hashMap1.put((String)key,value);
//                    }
//
//                    Set set2 = map2.keySet();
//                    Iterator site2 = set2.iterator();
//                    while(site2.hasNext()){
//                        Object key = site2.next();
//                        Object value = map2.get(key);
//                        list2.add((String) key);
//                        //listObject2.add(value);
//                        hashMap2.put((String)key,value);
//                    }
//
//                    for(int j=0;j<list2.size();j++){
//                        if(list2.contains(list1.get(j))){
//                            if(list2.get(j).equals(list1.get(j))){
//                                if(!hashMap1.get(list1.get(j)).equals(hashMap2.get(list2.get(j)))){
//                                    ta2.append(StringUtil.getLast(Config.jsonpath.get(i))+"中"+"【错误字段】序列化json中的字段"+list1.get(j)+"对应的值与反序列化json中该字段对应的值不符，序列化中该字段对应的值是"+hashMap1.get(list1.get(j))+",而反序列化中该字段对应的值是"+hashMap2.get(list2.get(j))+"\r\n");
//                                }
//                            }
//                        }else{
//                            ta3.append(StringUtil.getLast(Config.jsonpath.get(i))+"中"+"【不存在字段】序列化json中并不存在反序列化json中的"+list1.get(j)+"字段"+"\r\n");
//                        }
//                    }
//                }
//
//                jsonCompareLabel.setText("反序列化json对比完成！！！");
//                jsonCompareDialog.add(jsonLabel);
//                jsonCompareDialog.setVisible(true);
//            }
//        });

        getOppositeJsonButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getOppositeJsonDialog.setVisible(false);
            }
        });

        getOppositeJson.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String str = "adb pull /data/data/com.tencent.weishi/files/Drafts/v45/"+Config.personid;
                System.out.println(str);
                try{
                    File file = new File("F:\\restore");
                    if(!file.exists()){
                        file.mkdir();
                    }
                    Process pro = Runtime.getRuntime().exec(str + " "+file.getAbsolutePath());
                    Thread.sleep(500);
                    CopyDir.copyAllFile("F:\\restore","\\\\tencent.com\\tfs\\部门目录\\SNG社交网络事业群\\SNG社交网络质量部\\各中心信息\\QQ空间产品质量中心\\微视测试共享\\草稿箱\\draft\\restore");
                    Thread.sleep(500);
                    File filee = new File("\\\\tencent.com\\tfs\\部门目录\\SNG社交网络事业群\\SNG社交网络质量部\\各中心信息\\QQ空间产品质量中心\\微视测试共享\\草稿箱\\draft\\restore"+"\\"+Config.personid);
                    Config.jsonpath = GetDirPath.getDir(filee);
                    System.out.println(Config.jsonpath.size());
                }catch (Exception e2){
                    e2.printStackTrace();
                }

                getOppositeJsonLabel.setText("反序列化json获取成功");
                getOppositeJsonDialog.add(jsonLabel);
                getOppositeJsonDialog.setVisible(true);
            }
        });

        videoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                videoDialog.setVisible(false);
            }
        });

        videoHandle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String videoDirpath = "\\\\tencent.com\\tfs\\部门目录\\SNG社交网络事业群\\SNG社交网络质量部\\各中心信息\\QQ空间产品质量中心\\微视测试共享\\草稿箱\\draft\\rocky";
                File file = new File(videoDirpath);
                java.util.List<String> list = GetDirPath.getDirectory(file);
                if(list.size() >= 0 && list != null){
                    ta1.setText("");
                    for(int i=0;i<list.size();i++){
                        ta1.append(list.get(i)+"\r\n");
                    }
                }

                for(int i=0;i<list.size();i++) {
                    Runtime rt = Runtime.getRuntime(); //Runtime.getRuntime()返回当前应用程序的Runtime对象
                    Process ps = null;  //Process可以控制该子进程的执行或获取该子进程的信息。
                    
                    /*先给微视视频所在目录赋予权限*/
                    String newPath = "chmod 777 "+"/data/data/com.tencent.weishi/files/QZCamera/Video/"+Config.personid+"/Drafts";
                    
                    String newDoc = "adb push " + list.get(i) + " " + "/data/data/com.tencent.weishi/files/QZCamera/Video/"+Config.personid+"/Drafts";
                    try{
                        ps = rt.exec(newPath);
                        BufferedReader input = new BufferedReader(new InputStreamReader(ps.getInputStream()));
                        ps.getOutputStream().write(newDoc.getBytes());
                        ps.getOutputStream().flush();
                        input.close();
                        ps.destroy();
                        System.out.println("push成功");
                    }catch (Exception e1){
                        e1.printStackTrace();
                    }
                }

                StopAppUtil.StopApp();
                try{
                    Thread.sleep(500);
                }catch (InterruptedException e1){
                    e1.printStackTrace();
                }
                StartAppUtil.StartApp();
                System.out.println("执行1");
                EnterMySchema.enterMySchema();
                System.out.println("执行2");

                videoLabel.setText("video文件push成功");
                videoDialog.add(jsonLabel);
                videoDialog.setVisible(true);
            }
        });

        jsonButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jsonDialog.setVisible(false);
            }
        });

        jsonHandle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String jsonDirpath = "\\\\tencent.com\\tfs\\部门目录\\SNG社交网络事业群\\SNG社交网络质量部\\各中心信息\\QQ空间产品质量中心\\微视测试共享\\草稿箱\\draft\\jin";
                File file = new File(jsonDirpath);
                java.util.List<String> list = GetDirPath.getDir(file);
                if(list.size() >= 0 && list != null){
                    ta1.setText("");
                    for(int i=0;i<list.size();i++){
                        ta1.append(list.get(i)+"\r\n");
                    }
                }

//                for(int i=0;i<list.size();i++){
//                    String newDoc = "adb push "+list.get(i)+ " " +"/data/data/com.tencent.weishi/files/Drafts/v45/"+Config.personid;
//                    try{
//                        Process pro = Runtime.getRuntime().exec(newDoc);
//                        System.out.println("push成功");
//                    }catch (Exception e5){
//                        e5.printStackTrace();
//                    }
//                }
                
                for(int i=0;i<list.size();i++){
                	
                	/*先给微视json文件所在目录赋予权限*/
                	String newPath = "chmod 777 "+"/data/data/com.tencent.weishi/files/Drafts/v45/"+Config.personid; 
                	String newDoc = "adb push "+list.get(i)+ " " +"/data/data/com.tencent.weishi/files/Drafts/v45/"+Config.personid;
                	try{
                		Process pro = Runtime.getRuntime().exec(newPath);
                		BufferedReader input = new BufferedReader(new InputStreamReader(pro.getInputStream()));
                		pro.getOutputStream().write(newDoc.getBytes());
                		pro.getOutputStream().flush();
                		input.close();
                		pro.destroy();
                		System.out.println("push成功");
                	}catch(Exception e5){
                		e5.printStackTrace();
                	}
                	
                 }
                

                jsonLabel.setText("json文件push成功");
                jsonDialog.add(jsonLabel);
                jsonDialog.setVisible(true);
            }
        });

        frame.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
                Config.personid =JOptionPane.showInputDialog("请输入您的personid值:");
                System.out.println(Config.personid);
                while(Config.personid==null || Config.personid==""){
                    Config.personid =JOptionPane.showInputDialog("personid值不能为空，请重新输入您的personid值:");
                    break;
                }
            }

            @Override
            public void windowClosing(WindowEvent e) {
                Frame ff = (Frame)e.getSource();
                ff.dispose();
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });
    }
}