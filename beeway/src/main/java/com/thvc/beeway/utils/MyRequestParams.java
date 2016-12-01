package com.thvc.beeway.utils;




import com.lidroid.xutils.http.RequestParams;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;


/**
 * Created by Administrator on 2015/10/13.
 * 加密
 */
public class MyRequestParams extends RequestParams {
    private RequestParam param;
    private ArrayList<RequestParam> list;

    public String myRequestParams(RequestParams params) {
        RequestParams ss = params;
        java.util.List<org.apache.http.NameValuePair> getQueryStringParams = ss.getQueryStringParams();//拿到动态的所有参数
        list = new ArrayList<RequestParam>();//序列化集合对象
        list.add(new RequestParam("_deviceid", "123456"));
        list.add(new RequestParam("_model", android.os.Build.MODEL));
        list.add(new RequestParam("_system", "2"));
        list.add(new RequestParam("App_id", "api_app"));
        list.add(new RequestParam("App_key", "d3a9c17d9284f979"));

        for (int i = 0; i < getQueryStringParams.size(); i++) {//遍历动态参数，添加到对象里面
            String paramsname = getQueryStringParams.get(i).getName();
            String paramsvalue = getQueryStringParams.get(i).getValue();
            param = new RequestParam(paramsname, paramsvalue);
            list.add(param);
        }
        String strlist = "&";
        for (int j = 0; j < list.size(); j++) {//遍历集合进行拼接
            String paramsnames = list.get(j).getName();
            String paramsvalues = list.get(j).getValue();
            if (paramsnames.equals("")) {
            } else {
                strlist += (paramsnames + "=" + paramsvalues) + "&";//动态参数的拼接
            }
        }
        String keys = (strlist.substring(1, strlist.length())).toString().trim();//去前面&和后面&
        //截取前3个字符
        String strs = keys.substring(keys.indexOf("v"), keys.length());
        //MD5加密
        String _sign = getMD5(strs + "x-)a#2(^9" + "_de");
        String dlappkey = "";
        dlappkey=keys.replaceAll("App_key=d3a9c17d9284f979&","");
        String s = dlappkey.replace(" ","");
        //拼接完整的URL
        String url = s + "_sign" + "=" + _sign;
//        Log.e("url",url);
        return url;
    }

    /**
     * java语言md5加密
     *
     * @param info
     * @return
     */
    public String getMD5(String info) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(info.getBytes("UTF-8"));
            byte[] encryption = md5.digest();

            StringBuffer strBuf = new StringBuffer();
            for (int i = 0; i < encryption.length; i++) {
                if (Integer.toHexString(0xff & encryption[i]).length() == 1) {
                    strBuf.append("0").append(Integer.toHexString(0xff & encryption[i]));
                } else {
                    strBuf.append(Integer.toHexString(0xff & encryption[i]));
                }
            }
            return strBuf.toString();
        } catch (NoSuchAlgorithmException e) {
            return "";
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }
}
