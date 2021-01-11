package com.six.service.impl;

import com.six.client.UserClient;
import com.six.common.BaseRes;
import com.six.dao.CommentMapper;
import com.six.pojo.Comment;
import com.six.pojo.User;
import com.six.pojo.vo.VoComment;
import com.six.service.CommentService;
import com.six.utils.CookieUtils;
import com.six.utils.JwtUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PreDestroy;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private UserClient userClient;
    @Autowired
    private CookieUtils cookieUtils;
    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public BaseRes findAllCommentByVid(Map map) {
        BaseRes baseRes = new BaseRes();
        Integer vid = (Integer) map.get("vid");
        List<Comment> commentList = commentMapper.findByVid(vid);
        List<VoComment> voCommentList = null;
        if (commentList == null) {
            baseRes.setCode(201);
            baseRes.setMsg("暂无评论");
            return baseRes;
        }
        for (Comment comment : commentList) {
            VoComment voComment = new VoComment();
            BeanUtils.copyProperties(comment, voComment);
            Integer uid = comment.getUid();
            Map userMap = new HashMap();
            userMap.put("uid", uid);
            BaseRes byUserId = userClient.findByUserId(userMap);
            if (byUserId == null) {
                baseRes.setCode(202);
                baseRes.setMsg("查无此人");
                return baseRes;
            }
            User user = (User) byUserId.getData();
            voComment.setUserName(user.getUsername());
            voComment.setUserPic(user.getPic());
            voCommentList.add(voComment);
        }
        baseRes.setCode(200);
        baseRes.setMsg("成功");
        baseRes.setData(voCommentList);
        return baseRes;
    }

    @Override
    public BaseRes addComment(Map map, HttpServletRequest request) {
        BaseRes baseRes = new BaseRes();
        Integer vid = (Integer) map.get("vid");
        String content = map.get("content").toString();
        Cookie[] cookies = request.getCookies();
        String token = cookieUtils.getToken(cookies);
        Map verify = jwtUtils.Verify(token);
        Integer userId = (Integer) verify.get("userId");
        if (userId == null) {
            baseRes.setCode(201);
            baseRes.setMsg("请登录");
            return baseRes;
        }
        Comment comment = new Comment();
        comment.setCommentTime(new Date());
        comment.setVid(vid);
        comment.setUid(userId);
        comment.setContent(content);
        comment.setCommentTime(new Date());
        commentMapper.addComment(comment);
        baseRes.setMsg("添加成功");
        baseRes.setCode(200);
        return baseRes;
    }

    @Override
    public BaseRes deleteComment(Integer cid) {
        BaseRes baseRes = new BaseRes();
        commentMapper.deleteComment(cid);
        baseRes.setMsg("删除成功");
        baseRes.setCode(200);
        return baseRes;
    }
}
