package chenjie;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 合并含有K个元素的有序链表，并且作为一个有序链表的形式返回。分析并描述它的复杂度。
 *
 * @author: chenjie
 * @date: 2018/3/27
 */
public class L023_MergeKLists {

	public boolean valid(ListNode[] lists) {

		if (lists == null || lists.length == 0) {
			return false;
		}

		int count = 0;
		for (int i = 0; i < lists.length; i++) {
			if (lists[i] != null) {
				count++;
			}
		}
		if (count == 0) {
			return false;
		}
		return true;
	}

	//合并k个有序链表
	public ListNode mergeKLists(ListNode[] lists) {

		if (!valid(lists)) {
			return null;
		}

		ListNode head = null;

		//用PriorityQueue来实现一个堆，同时指定比较器
		PriorityQueue<ListNode> heap = new PriorityQueue<>(1, new Comparator<ListNode>() {
			@Override
			public int compare(ListNode o1, ListNode o2) {
				return o1.val - o2.val;
			}
		});

		for (int i = 0; i < lists.length; i++) {    //将所有链表头结点加入到堆中
			if (lists[i] != null) {
				heap.add(lists[i]);
			}
		}

		ListNode min = heap.poll();         //寻找头结点
		head = min;
		min = min.next;

		if (min != null)
			heap.add(min);
		ListNode last = head;              //ListNode保存已经排好序的链表的最后一个节点

		while (!heap.isEmpty()) {
			ListNode minNode = heap.poll();
			last.next = minNode;         //更新last
			last = last.next;
			minNode = minNode.next;
			if (minNode != null) {
				heap.add(minNode);
			}
		}

		return head;
	}
}
