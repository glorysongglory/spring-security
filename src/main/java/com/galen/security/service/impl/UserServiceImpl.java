package com.galen.security.service.impl;

import com.galen.security.mapper.SysUserMapper;
import com.galen.security.model.SysUser;
import com.galen.security.service.UserService;
import com.galen.security.utils.IdUtil;
import com.galen.security.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public String createUser(SysUser sysUser) {
        if (null != sysUserMapper.selectByUsername(sysUser.getUsername())) {
            return "用户已经存在";
        }
        sysUser.setId(IdUtil.generateNumberId());
        sysUser.setPassword(MD5Util.encode(sysUser.getPassword()));
        sysUserMapper.insertSelective(sysUser);
        return "SUCCESS";
    }
}
