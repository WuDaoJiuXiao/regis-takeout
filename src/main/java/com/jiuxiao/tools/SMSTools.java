package com.jiuxiao.tools;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;

/**
 * 短信验证码工具类
 * @Author: 悟道九霄
 * @Date: 2022年08月10日 9:47
 * @Version: 1.0.0
 */
public class SMSTools {

    /**
     * @param signName     短信签名
     * @param templateCode 短信模板
     * @param phoneNumbers 要发送的手机号
     * @param param        其他参数
     * @return: void
     * @decription 发送短信验证码
     * @date 2022/8/10 10:00
     */
    public static void sendMsg(String signName, String templateCode, String phoneNumbers, String param) {
        final String regionId = "cn-hangzhou";
        final String accessKey = "";
        final String secret = "";

        DefaultProfile profile = DefaultProfile.getProfile(regionId, accessKey, secret);
        DefaultAcsClient client = new DefaultAcsClient(profile);
        SendSmsRequest request = new SendSmsRequest();

        request.setSysRegionId("cn-hangzhou");
        request.setPhoneNumbers(phoneNumbers);
        request.setSignName(signName);
        request.setTemplateCode(templateCode);
        request.setTemplateParam("{\"code\":\"" + param + "\"}");

        try {
            SendSmsResponse response = client.getAcsResponse(request);
            System.out.println("短信发行成功");
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }
}