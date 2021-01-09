package com.letchat.web.mapper;

import com.letchat.web.pojo.Users;
import com.letchat.web.pojo.vo.FriendRequestVO;
import com.letchat.web.pojo.vo.MyFriendsVO;
import com.letchat.utils.MyMapper;

import java.util.List;


/**
 * @author alice
 */
public interface UsersMapperCustom extends MyMapper<Users> {

    public List<FriendRequestVO> queryFriendRequestList(String acceptUserId);

    public List<MyFriendsVO> queryMyFriends(String userId);

    public void batchUpdateMsgSigned(List<String> msgIdList);

}
