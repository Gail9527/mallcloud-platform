package cn.mallcloud.oauth2.server.service;

import cn.mallcloud.common.constants.CommonConstant;
import cn.mallcloud.common.vo.UserVO;
import cn.mallcloud.oauth2.server.feign.UserService;
import cn.mallcloud.oauth2.server.userdetails.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 用户信息获取
 *
 * @author zscat
 * @date 2018/7/24 17:06
 */
@Service("userDetailService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    /**
     * 通过 Username 加载用户详情
     *
     * @param username 用户名
     * @return UserDetails
     * @throws UsernameNotFoundException 用户没找到
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 查询用户信息,包含角色列表
        UserVO userVo = userService.findUserByUsername(username);
        if (userVo == null) {
            throw new UsernameNotFoundException("用户名/密码错误");
        }
        if (CommonConstant.DEL_FLAG.equals(userVo.getDelFlag())) {
            throw new DisabledException("用户: " + username + " 不可用");
        }
        return new UserDetailsImpl(userVo);
    }

}
