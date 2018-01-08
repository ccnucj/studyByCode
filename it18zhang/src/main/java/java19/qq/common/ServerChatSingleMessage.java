package java19.qq.common;

/**
 * 服务器端的私聊消息
 */
public class ServerChatSingleMessage extends Message<ChatSingleBean> {
	public ServerChatSingleMessage(ChatSingleBean csb) {
		this.setMessageContent(csb);
	}
}
