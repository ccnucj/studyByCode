package java19.qq.common;

/**
 * 客户端私聊bean
 */
public class ClientChatSingleBean extends ChatSingleBean {
    private String chat;

    public ClientChatSingleBean(String userInfo, String chat) {
        this.chat = chat;
        this.setUserInfo(userInfo);
    }

    public String getChat() {
        return chat;
    }

    public void setChat(String chat) {
        this.chat = chat;
    }
}
