package com.odev.yhq_getter;

import io.reactivex.Flowable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface JdService {

    @Headers({
            "Host: api.m.jd.com",
            "Cookie: __jdc=122270672; mba_muid=1540483397320591160689; shshshfp=e1fe31b1ea1c14a949e93a2e1fc6dcf4; shshshfpa=7c7ff8fb-4e5f-0226-66f1-789cd36ce324-1540483398; shshshfpb=14d6ce9f499d1479f949a60727c5877381393786c669488245bd1e9483; TrackerID=6_fapLB3FkoUTnAIkJnneJ5WacyFNpQgESKUYYmOtYW2kYAjg7GBg1u3TEL5i1NUN_wAkDfOnnfYSEi95HPif3Boy0r7LOR8UYGhB_JkLbp7wU-O6ncF8ufQMr3Rn34OGwI3XrSzQZyn4vgp8HMA2g; pt_key=AAFb0qaVADAy4ysk3FvPPHndG608Jq8yXxIJ4NJ-0xO_fsWYSR03yjARyIgHvJpbi1J2HwkPW6w; pt_pin=%E5%88%98%E5%A4%B4%E4%BA%BA123; pt_token=4fnxijit; pwdt_id=%E5%88%98%E5%A4%B4%E4%BA%BA123; autoOpenApp_downCloseDate_auto=1540531871212_21600000; __jda=122270672.1540483397320591160689.1540483397.1540531867.1540531914.7; __jdb=122270672.1.1540483397320591160689|7.1540531914; __jdv=122270672|pdappwakeupup_20170001|t_335139774|appshare|Wxfriends|1540531914175; mba_sid=15405318457125493192882303972.4; shshshsID=9063cdce4ed65805b5517a1d0cef8d71_5_1540531915168",
            "accept: application/json",
            "origin: https://pro.m.jd.com",
            "user-agent: jdapp;android;7.2.0;7.0;352562070161644-fcdbb366dba0;network/wifi;model/SM-G9280;addressid/138098159;osp/android;apv/7.2.0;osv/7.0;uid/352562070161644-fcdbb366dba0;pv/45.9;psn/352562070161644-fcdbb366dba0|56;psq/5;ref/com.jingdong.app.mall.home.JDHomeFragment;pap/JA2015_311210|7.2.0|ANDROID 7.0;usc/pdappwakeupup_20170001;ucp/t_335139774;umd/appshare;utr/Wxfriends;adk/;ads/;jdv/0|pdappwakeupup_20170001|t_335139774|appshare|Wxfriends|1540516023377|1540516026;partner/azgoogle001;apprpd/Home_Main;Mozilla/5.0 (Linux; Android 7.0; SM-G9280 Build/NRD90M; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/57.0.2987.132 MQQBrowser/6.2 TBS/044106 Mobile Safari/537.36",
            "content-type: application/x-www-form-urlencoded",
            "referer: https://pro.m.jd.com/mall/active/41aqaSvcZnV7XDwDXm1NtiHiqz7J/index.html?utm_source=pdappwakeupup_20170001&ShareTm=rChy88z654oe3N78RDkyVtbXv4u1BvFXsw2RbPGSSpOldf9IZcvqcSWYppbA84OiL98%2FtP6Wa8KXIcNHUQVrTb8Y5Vh4bI%2FPVO4%2BDl59jTFjRF4FKUnjXC%2F2yBEp3t6cOBS3CwTn%2BawfYgoYTP9cpSH6nnqeEGX3twznhtfG%2FBU%3D&ad_od=share&utm_source=androidapp&utm_medium=appshare&utm_campaign=t_335139774&utm_term=Wxfriends&from=singlemessage",
            "accept-language: zh-CN,zh-TW;q=0.8,en-US;q=0.6,de-DE;q=0.4",
            "x-requested-with: com.tencent.mm"
    })
    @POST("/client.action")
    Flowable<BaseResult> yhq(@Query("functionId") String newBabelAwardCollection, @Body RequestBody body);
}
