package java19.qq.common;

/**
 * 客户端群聊消息
 */
public class ClientChatsMessage extends Message<byte[]> {
	public ClientChatsMessage(byte[] raw) {
		this.setMessageContent(raw);
	}
}