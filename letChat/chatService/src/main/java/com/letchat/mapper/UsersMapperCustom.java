package com.letchat.mapper;

import com.letchat.pojo.Users;
import com.letchat.pojo.vo.FriendRequestVO;
import com.letchat.pojo.vo.MyFriendsVO;
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