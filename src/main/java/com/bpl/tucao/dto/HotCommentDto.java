package com.bpl.tucao.dto;

/**
 * @author cuiyongdai
 * @desc
 * @date 2017/8/10 0010.
 */
public class HotCommentDto {

    private Integer hotId;
    private Integer userId;
    private String nickName;
    private String content;

    public Integer getHotId() {
        return hotId;
    }

    public void setHotId(Integer hotId) {
        this.hotId = hotId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
