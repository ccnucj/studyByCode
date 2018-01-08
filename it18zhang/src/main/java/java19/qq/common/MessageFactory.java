package java19.qq.common;

import java19.qq.util.Util;

import java.io.InputStream;
import java.io.Serializable;
import java.util.List;


/**
 * 消息工厂
 */
public class MessageFactory {

	/**
	 * 组装客户端群聊消息
	 */
	public static byte[] popClientChatsMessage(String msg) {
		return popMessage(Message.CLIENT_TO_SERVER_CHATS, msg.getBytes());
	}

	/**
	 * 组装客户端私聊消息
	 * receiverUserInfo:接受者信息
	 * msg:聊天信息
	 */
	public static byte[] popClientChatSingleMessage(String receiverUserInfo, String msg) {
		//接受者信息数组
		byte[] recv = receiverUserInfo.getBytes();

		//消息数组
		byte[] chat = msg.getBytes();

		//组装报文
		byte[] pack = new byte[4 + 4 + recv.length + 4 + chat.length];

		//组装报文:1.消息类型
		System.arraycopy(Util.int2Bytes(Message.CLIENT_TO_SERVER_CHAT_SINGLE), 0, pack, 0, 4);

		//组装报文:2.接受者长度
		System.arraycopy(Util.int2Bytes(recv.length), 0, pack, 4, 4);

		//组装报文:3.接受者信息
		System.arraycopy(recv, 0, pack, 8, recv.length);

		//组装报文:4.消息长度
		System.arraycopy(Util.int2Bytes(chat.length), 0, pack, 8 + recv.length, 4);

		//组装报文:5.消息内容
		System.arraycopy(chat, 0, pack, 8 + recv.length + 4, chat.length);

		return pack;
	}

	/**
	 * 组装客户端发送的刷新好友列表消息
	 */
	public static byte[] popClientRefreshFriends() {
		return Util.int2Bytes(Message.CLIENT_TO_SERVER_REFRESH_FRIENDS);
	}

	/**
	 * 组装服务器端好友列表消息
	 */
	public static byte[] popServerFriends(List<String> list) {
		byte[] objArr = Util.serialize((Serializable) list);
		return popMessage(Message.SERVER_TO_CLIENT_FRIEND_LIST, objArr);
	}

	/**
	 * 组装服务器端转发的消息-ChatMessage
	 * --------------------
	 * |   |   |    |     |   4字节 -->消息类型
	 * --------------------
	 * |   |   |    |     |   4字节 -->userInfo长度
	 * --------------------
	 * |  ...........     |   n字节 -->userInfo内容
	 * --------------------
	 * |   |   |    |     |   4字节 -->消息长度
	 * --------------------
	 * |  ............    |   n字节 -->消息内容
	 * --------------------
	 */
	public static byte[] popServerChatsMessage(String userInfo, byte[] clientChatMsg) {
		//用户信息数组
		byte[] userInfoArr = userInfo.getBytes();

		//总消息长度
		byte[] allArr = new byte[4 + 4 + userInfoArr.length + 4 + clientChatMsg.length];

		//消息类型
		System.arraycopy(Util.int2Bytes(Message.SERVER_TO_CLIENT_CHATS), 0, allArr, 0, 4);

		//用户信息长度
		System.arraycopy(Util.int2Bytes(userInfoArr.length), 0, allArr, 4, 4);

		//用户信息内容
		System.arraycopy(userInfoArr, 0, allArr, 8, userInfoArr.length);

		//聊天内容长度
		System.arraycopy(Util.int2Bytes(clientChatMsg.length), 0, allArr, (4 + 4 + userInfoArr.length), 4);

		//聊天内容
		System.arraycopy(clientChatMsg, 0, allArr, 4 + 4 + userInfoArr.length + 4, clientChatMsg.length);

		return allArr;
	}

	/**
	 * 组装服务器私聊消息
	 * clientChatMsg:纯聊天内容
	 */
	public static byte[] popServerChatSingleMessage(String senderUserInfo, byte[] chatMsg) {
		//用户信息数组
		byte[] senderInfo = senderUserInfo.getBytes();

		//总消息长度
		byte[] pack = new byte[4 + 4 + senderInfo.length + 4 + chatMsg.length];

		//组装消息报文1.消息类型
		System.arraycopy(Util.int2Bytes(Message.SERVER_TO_CLIENT_CHAT_SINGLE), 0, pack, 0, 4);

		//组装消息报文2.发送方长度
		System.arraycopy(Util.int2Bytes(senderInfo.length), 0, pack, 4, 4);

		//组装消息报文3.发送方内容
		System.arraycopy(senderInfo, 0, pack, 8, senderInfo.length);

		//组装消息报文4.聊天内容长度
		System.arraycopy(Util.int2Bytes(chatMsg.length), 0, pack, (4 + 4 + senderInfo.length), 4);

		//组装消息报文4.聊天内容
		System.arraycopy(chatMsg, 0, pack, 4 + 4 + senderInfo.length + 4, chatMsg.length);

		return pack;
	}


	/**
	 * 组装一般性消息
	 */
	private static byte[] popMessage(int msgType, byte[] msgArr) {
		//消息长度
		byte[] msglenArr = Util.int2Bytes(msgArr.length);
		//消息类性
		byte[] typeArr = Util.int2Bytes(msgType);
		//总消息
		byte[] allArr = new byte[typeArr.length + msglenArr.length + msgArr.length];

		//复制消息类型
		System.arraycopy(typeArr, 0, allArr, 0, typeArr.length);
		//复制消息长度
		System.arraycopy(msglenArr, 0, allArr, typeArr.length, msglenArr.length);
		//复制消息内容
		System.arraycopy(msgArr, 0, allArr, (typeArr.length + msglenArr.length), msgArr.length);
		return allArr;
	}

	/**
	 * 从输入流中解析消息
	 */
	public static Message parseMessageFromInputStream(InputStream is) {
		try {
			//四个字节的缓冲区
			byte[] buf4 = new byte[4];
			byte[] typeArr = new byte[4];
			is.read(typeArr);
			//类型
			int type = Util.bytes2Int(typeArr);
			//判断消息类型
			switch (type) {
				/****************  服务器解析消息部分  **********************/
				//请求刷新好友
				case Message.CLIENT_TO_SERVER_REFRESH_FRIENDS:
					return new ClientRequestFreshFriendsMessage();

				//客户端群聊消息
				case Message.CLIENT_TO_SERVER_CHATS:
					//读取消息长度
					is.read(buf4);
					int msgLen = Util.bytes2Int(buf4);
					//消息内容
					byte[] msgBuf = new byte[msgLen];
					is.read(msgBuf);
					//客户端发送的聊天消息(纯消息内容)
					return new ClientChatsMessage(msgBuf);

				//客户端私聊消息
				case Message.CLIENT_TO_SERVER_CHAT_SINGLE:
					//接收方的长度
					is.read(buf4);
					int recvInfoLen = Util.bytes2Int(buf4);
					//接受内容
					byte[] recvBuf = new byte[recvInfoLen];
					is.read(recvBuf);
					String recvInfo = new String(recvBuf);
					//聊天信息
					is.read(buf4);
					int chatLen = Util.bytes2Int(buf4);
					byte[] chatBuf = new byte[chatLen];
					is.read(chatBuf);
					//服务器端私聊
					return new ClientChatSingleMessage(new ServerChatSingleBean(recvInfo, chatBuf));

				/****************  客户端解析消息部分  **********************/
				//服务器发送的客户端聊天信息
				case Message.SERVER_TO_CLIENT_CHATS:
					//读取userInfoLen
					is.read(buf4);
					//读取userInfo
					int userInfoLen = Util.bytes2Int(buf4);
					byte[] bufUserInfo = new byte[userInfoLen];
					is.read(bufUserInfo);
					String strUserInfo = new String(bufUserInfo);

					//读取chat的长度
					is.read(buf4);
					chatLen = Util.bytes2Int(buf4);

					//读取chat内容
					chatBuf = new byte[chatLen];
					is.read(chatBuf);
					String strChat = new String(chatBuf);
					//
					return new ServerChatsMessage(new String[]{strUserInfo, strChat});

				//服务器发送的客户端私聊信息
				case Message.SERVER_TO_CLIENT_CHAT_SINGLE:
					//读取userInfoLen
					is.read(buf4);
					//读取senderUserInfo
					int senderInfoLen = Util.bytes2Int(buf4);
					byte[] senderInfoBuf = new byte[senderInfoLen];
					is.read(senderInfoBuf);
					//senderInfo
					String senderInfo = new String(senderInfoBuf);

					//读取chat的长度
					is.read(buf4);
					chatLen = Util.bytes2Int(buf4);

					//读取chat内容
					chatBuf = new byte[chatLen];
					is.read(chatBuf);
					String chat = new String(chatBuf);
					//客户端解析到的消息
					return new ServerChatSingleMessage(new ClientChatSingleBean(senderInfo, chat));

				//服务器发送给客户端的好友列表
				case Message.SERVER_TO_CLIENT_FRIEND_LIST:
					//读取列表缓冲区长度
					is.read(buf4);
					int listLen = Util.bytes2Int(buf4);

					//读取好友列表内容
					byte[] friendBuf = new byte[listLen];
					is.read(friendBuf);

					//好友列表
					List<String> friendList = (List<String>) Util.deSerialize(friendBuf);
					return new ServerFriendsMessage(friendList);
			}
		} catch (Exception e) {
			//e.printStackTrace();
		}
		return null;
	}

}
