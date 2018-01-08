package java19.qq.common;

/**
 * 服务器端的群聊天消息
 */
public class ServerChatsMessage extends Message<String[]> {
	public ServerChatsMessage(String[] msg) {
		this.setMessageContent(msg);
	}
}
