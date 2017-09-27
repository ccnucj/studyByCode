package java19.qq.common;

/**
 * 服务器端私聊bean
 */
public class ServerChatSingleBean extends ChatSingleBean {
    private byte[] chat;

    public ServerChatSingleBean(String userInfo, byte[] chat) {
        this.chat = chat;
        this.setUserInfo(userInfo);
    }

    public byte[] getChat() {
        return chat;
    }

    public void setChat(byte[] chat) {
        this.chat = chat;
    }
}
