package com.baidu.images.utils;

import com.baidu.aip.imagesearch.AipImageSearch;
import com.baidu.aip.util.Util;
import com.baidu.images.entity.AipImage;
import com.baidu.images.entity.Imag;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class AipImageUtils {

    /**
     * 该接口实现单张图片入库，入库时需要同步提交图片及可关联至本地图库的摘要信息
     * （具体变量为brief，具体可传入图片在本地标记id、图片url、图片名称等）；
     * 同时可提交分类维度信息（具体变量为tags，最多可传入2个tag），
     * 方便对图库中的图片进行管理、分类检索。
     *
     * 注：重复添加完全相同的图片会返回错误。
     * @param client
     */
    public static JSONObject saveImages(AipImageSearch client , Imag imag) {
        JSONObject res = null;
        try{

          if(null == imag) {
              throw new NullPointerException("空值");
          }

          if (null == imag.getBrief()){
              throw new RuntimeException("brief参数为必传");
          }
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();

        options.put("brief", imag.getBrief());

          if(null != imag.getTags()) {
              options.put("tags" , imag.getTags());
            ///  options.put("tags", "100,11");
         }


        if (null != imag.getLocalPath()) {
            // 参数为本地路径
            String image = imag.getLocalPath();

            boolean flag = options.isEmpty();
            res = client.similarAdd(image, options);

            if(!res.has("log_id")){
                // 参数为二进制数组
                byte[] file = Util.readFileByBytes(imag.getLocalPath());

                res = client.similarAdd(file, options);

            }
        }else{
            /// 网络图片可能存在网络的问题，所以放在最后
                res = client.similarAddUrl(imag.getUrl(), options);
        }
        return res;
    }catch (Exception e){
        e.printStackTrace();
    }
     return  null;
    }

    public static List<Imag> getListJSONObject(File file[]){

        List<Imag> list = new ArrayList<Imag>();
        for (int i = 0; i < file.length; i++) {
            Imag imag = new Imag();
            imag.setLocalPath(file[i].getPath());
            imag.setBrief("我最帅"+i);
            list.add(imag);
        }
        return list;
    }


    public static JSONObject searchImags(AipImageSearch client,Imag imag) {
        JSONObject res = null ;
        try{

         if(null == imag){
             throw  new NullPointerException("空值");
         }

        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();

         if(null != imag.getTags()) {
             /// options.put("tags", "100,11");
             options.put("tags", imag.getTags());
         }

         if(null != imag.getTag_logic()){
             options.put("tag_logic", imag.getTag_logic());
             /// options.put("tag_logic", "0");
         }

          if(null != imag.getPn()){
                options.put("pn", imag.getPn());;
                /// options.put("pn", "100");
          }

          if(null != imag.getRn()){
                options.put("pn", imag.getRn());
                /// options.put("pn", imag.getRn());
          }

         if(null != imag.getLocalPath()){
             // 参数为本地路径
             String image = imag.getLocalPath();
             res = client.similarSearch(image, options);




             if (!res.has("log_id")){
                 // 参数为二进制数组
                 byte[] file = Util.readFileByBytes(imag.getLocalPath());
                 res = client.similarSearch(file, options);

             }
         }else{
             // 相似图检索—检索, 图片参数为远程url图片
             res = client.similarSearchUrl(imag.getUrl(), options);

         }

         return res;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }



    /**
     * 测试
     */
    public static void main(String[] args)throws Exception{
        String brief = "";
        Double score = -1.0;
        JSONArray jsonArray = null;
        String str = "序号\t 本地图片地址\t\t\t\t\t\t\t\t 百度图片序号\t 相似度\n";
       File file = new File("C:\\Users\\Administrator\\Desktop\\test");
       List<Imag> list = getListJSONObject(file.listFiles());
        for (Imag imag : list) {

            JSONObject json = searchImags(AipImage.getInstance(),imag);

            if (json.has("result")){
                jsonArray = json.getJSONArray("result");
            }

            System.out.println(jsonArray);
            for (int i = 0; i < jsonArray.length(); i++) {
                if (jsonArray.getJSONObject(i).has("brief")){
                     brief = jsonArray.getJSONObject(i).getString("brief");
                }

                if (jsonArray.getJSONObject(i).has("score")){
                    score = jsonArray.getJSONObject(i).getDouble("score");
                }

                str+=i+"\t"+imag.getLocalPath()+"\t\t"+brief+"\t\t"+score+"\n";
            }



            Util.writeBytesToFileSystem(str.getBytes(),"D:\\output\\123.txt");

        }


    }
}
