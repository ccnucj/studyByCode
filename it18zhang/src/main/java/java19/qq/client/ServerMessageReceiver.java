package java19.qq.client;


import java19.qq.common.*;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.List;

/**
 * 接受服务器消息
 */
public class ServerMessageReceiver extends Thread {
	private Socket sock;
	private InputStream in;
	private OutputStream out;
	private QQClientChatsUI ui;
	private MessageSender sender;

	public ServerMessageReceiver(Socket sock, QQClientChatsUI ui, MessageSender sender) {
		try {
			this.sock = sock;
			this.in = sock.getInputStream();
			this.out = sock.getOutputStream();
			this.ui = ui;
			this.sender = sender;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 *
	 */
	public void run() {
		try {
			//循环接受服务器发送来的消息
			while (true) {
				Message msg = MessageFactory.parseMessageFromInputStream(in);
				//好友列表
				if (msg.getClass() == ServerFriendsMessage.class) {
					List<String> list = ((ServerFriendsMessage) msg).getMessageContent();
					//刷新好友列表
					ui.refreshFriendList(list);
				}
				//好友群聊消息
				else if (msg.getClass() == ServerChatsMessage.class) {
					String[] ss = ((ServerChatsMessage) msg).getMessageContent();
					//更新聊天历史区
					ui.updateHistory(ss);
				}
				//好友私聊消息
				else if (msg.getClass() == ServerChatSingleMessage.class) {
					ServerChatSingleMessage scsm = ((ServerChatSingleMessage) msg);
					ClientChatSingleBean ccsb = (ClientChatSingleBean) scsm.getMessageContent();
					String senderInfo = ccsb.getUserInfo();
					String chat = ccsb.getChat();

					//从私聊窗口集合中提取指定的窗口
					QQClientChatSingleUI ui = QQClientChatsUI.chatSingleWindows.get(senderInfo);
					if (ui == null) {
						ui = new QQClientChatSingleUI(sender, senderInfo);
						QQClientChatsUI.chatSingleWindows.put(senderInfo, ui);
					}
					//更新聊天历史区
					ui.updateHistory(new String[]{senderInfo, chat});
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
