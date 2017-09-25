package java19.qq.common;

/**
 * 客户端私聊消息
 */
public class ClientChatSingleMessage extends Message<ChatSingleBean> {
    public ClientChatSingleMessage(ChatSingleBean csb) {
        this.setMessageContent(csb);
    }
}