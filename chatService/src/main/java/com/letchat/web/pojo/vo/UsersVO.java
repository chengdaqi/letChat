package com.letchat.web.pojo.vo;

import lombok.Builder;
import lombok.Data;

/**
 * @author alice
 */

@Data
@Builder
public class UsersVO {
    private String id;
    private String username;
    private String faceImage;
    private String faceImageBig;
    private String nickname;
    private String qrcode;
}
