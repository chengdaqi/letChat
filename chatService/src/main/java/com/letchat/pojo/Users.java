package com.letchat.pojo;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author alice
 */

@Data
@Builder
public class Users {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY,generator="Mysql")
    private String id;

    /**
     * 用户名，账号，慕信号
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 我的头像，如果没有默认给一张
     */
    @Column(name = "face_image")
    private String faceImage;

    @Column(name = "face_image_big")
    private String faceImageBig;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 新用户注册后默认后台生成二维码，并且上传到 fastdfs
     */
    private String qrcode;

    private String cid;

}
