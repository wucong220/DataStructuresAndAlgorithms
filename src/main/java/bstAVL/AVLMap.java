package bstAVL;

import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

import junit.framework.Assert;

public class AVLMap<K, V> implements Iterable<AVLEntry<K, V>> {
	private int size;
	private AVLEntry<K, V> root;
	private Comparator<K> comp;
	private LinkedList<AVLEntry<K, V>> stack = new LinkedList<>();

	@SuppressWarnings({ "unused", "unchecked" })
	private int compare(K a, K b) {
		if (comp != null) {
			return comp.compare(a, b);
		} else {
			Comparable<K> o = (Comparable<K>) a;
			return o.compareTo(b);
		}
	}

	public AVLMap(Comparator<K> comp) {
		super();
		this.comp = comp;
	}

	public AVLMap() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public V put(K key, V value) {
		if (root == null) {
			root = new AVLEntry<K, V>(key, value);
			size++;
		} else {
			AVLEntry<K, V> p = root;
			while (p != null) {
				stack.push(p);
				int compareResult = compare(key, p.key);
				if (compareResult == 0) {
					p.setValue(value);
					break;
				} else if (compareResult < 0) {
					if (p.left == null) {
						p.left = new AVLEntry<K, V>(key, value);
						size++;
						break;
					} else {
						p = p.left;
					}
				} else {
					if (p.right == null) {
						p.right = new AVLEntry<K, V>(key, value);
						size++;
						break;
					} else {
						p = p.right;
					}
				}
			}
		}
		fixAfterInsertion(key);
		return value;
	}

	@Override
	public Iterator<AVLEntry<K, V>> iterator() {
		// TODO Auto-generated method stub
		return new AVLIterator<>(root);
	}

	public AVLEntry<K, V> getEntry(K key) {
		AVLEntry<K, V> p = root;
		while (p != null) {
			int compareResult = compare(key, p.key);
			if (compareResult < 0) {
				p = p.left;
			} else if (compareResult > 0) {
				p = p.right;
			} else {
				return p;
			}
		}
		return null;
	}

	public boolean containsKey(K key) {
		return getEntry(key) != null;
	}

	public boolean containsValue(V value) {
		Iterator<AVLEntry<K, V>> it = iterator();
		while (it.hasNext()) {
			if (it.next().getValue().equals(value))
				return true;
		}
		return false;
	}

	public V get(K key) {
		AVLEntry<K, V> p = getEntry(key);
		return p == null ? null : p.getValue();
	}

	public AVLEntry<K, V> getFirstEntry(AVLEntry<K, V> p) {
		while (p.left != null)
			p = p.left;
		return p;
	}

	public AVLEntry<K, V> getLastEntry(AVLEntry<K, V> p) {
		while (p.right != null)
			p = p.right;
		return p;
	}

	public AVLEntry<K, V> deleteEntry(AVLEntry<K, V> p, K key) {
		if (p == null) {
			return null;
		} else {
			int compareResult = compare(key, p.key);
			if (compareResult == 0) {
				if (p.left == null && p.right == null) {
					p = null;
				} else if (p.left != null && p.right == null) {
					p = p.left;
				} else if (p.left == null && p.right != null) {
					p = p.right;
				} else {
					if ((size & 1) == 0) {
						AVLEntry<K, V> rightMin = getFirstEntry(p.right);
						p.key = rightMin.key;
						p.value = rightMin.value;
						AVLEntry<K, V> newRight = deleteEntry(p.right, rightMin.key);
						p.right = newRight;
					} else {
						AVLEntry<K, V> leftMax = getLastEntry(p.left);
						p.key = leftMax.key;
						p.value = leftMax.value;
						AVLEntry<K, V> newLeft = deleteEntry(p.left, leftMax.key);
						p.left = newLeft;
					}
				}
			} else if (compareResult < 0) {
				AVLEntry<K, V> newLeft = deleteEntry(p.left, key);
				p.left = newLeft;
			} else {
				AVLEntry<K, V> newRight = deleteEntry(p.right, key);
				p.right = newRight;
			}
			p=fixAfterDeletion(p);
			return p;
		}
	}
	public int getHeight(AVLEntry<K, V> p) {
		return p == null ? 0 : p.height;
	}

	private AVLEntry<K, V> rotateRight(AVLEntry<K, V> p) {
		AVLEntry<K, V> left = p.left;
		p.left = left.right;
		left.right = p;
		p.height = Math.max(getHeight(p.left), getHeight(p.right)) + 1;
		left.height = Math.max(getHeight(left.left), getHeight(left.right)) + 1;
		return left;
	}

	private AVLEntry<K, V> rotateLeft(AVLEntry<K, V> p) {
		AVLEntry<K, V> right = p.right;
		p.right = right.left;
		right.left = p;
		p.height = Math.max(getHeight(p.left), getHeight(p.right)) + 1;
		right.height = Math.max(getHeight(right.left), getHeight(right.right)) + 1;
		return right;
	}

	private AVLEntry<K, V> firstLeftThenRight(AVLEntry<K, V> p) {
		p.left = rotateLeft(p.left);
		p = rotateRight(p);
		return p;
	}

	private AVLEntry<K, V> firstRightThenLeft(AVLEntry<K, V> p) {
		p.right = rotateRight(p.right);
		p = rotateLeft(p);
		return p;
	}

	private void fixAfterInsertion(K key) {
		AVLEntry<K, V> p = root;
		while (!stack.isEmpty()) {
			p = stack.pop();
			int newHeight =  Math.max(getHeight(p.left), getHeight(p.right)) + 1;
			if(p.height>1&&newHeight==p.height){
				stack.clear();
				return;
			}
			p.height = Math.max(getHeight(p.left), getHeight(p.right)) + 1;
			int d = getHeight(p.left) - getHeight(p.right);
			if (Math.abs(d) <= 1) {
				continue;
			} else {
				if (d == 2) {
					// LL
					if (compare(key, p.left.key) < 0) {
						p = rotateRight(p);
					}
					// LR
					else {
						p = firstLeftThenRight(p);
					}
				} else {
					// RR
					if (compare(key, p.right.key) > 0) {
						p = rotateLeft(p);
					}
					// LR
					else {
						p = firstRightThenLeft(p);
					}
				}
				if (!stack.isEmpty()) {
					if (compare(key, stack.peek().key) < 0) {
						stack.peek().left = p;
					} else {
						stack.peek().right = p;
					}
				}
			}
		}
		root = p;
	}

	public void checkBalance() {
		postOrderCheckBalance(root);
	}

	private void postOrderCheckBalance(AVLEntry<K, V> p) {
		if (p != null) {
			postOrderCheckBalance(p.left);
			postOrderCheckBalance(p.right);
			Assert.assertTrue((getHeight(p.left)-getHeight(p.right))<=1);
		}
	}
	
	public V remove(K key) {
		AVLEntry<K, V> entry = getEntry(key);
		if (entry == null)
			return null;
		else {
			V oldValue = entry.getValue();
			root = deleteEntry(root, key);
			size--;
			return oldValue;
		}
	}

	private AVLEntry<K, V> fixAfterDeletion(AVLEntry<K, V> p){
		if(p==null){
			return null;
		}
		else{
			p.height = Math.max(getHeight(p.left),getHeight(p.right))+1;
			int d = getHeight(p.left)-getHeight(p.right);
			if(d==2){
				if(getHeight(p.left.left)-getHeight(p.left.right)>=0){
					p=rotateRight(p);
				}else{
					p=firstLeftThenRight(p);
				}
			}else if(d==-2){
				if(getHeight(p.right.left)-getHeight(p.right.right)<=0){
					p=rotateLeft(p);
				}else{
					p=firstRightThenLeft(p);
				}
			}
			return p;
		}
	}
	
	public void levelOrder() {
		Queue<AVLEntry<K, V>> queue = new LinkedList<>();
		queue.offer(root);
		int preCount = 1;
		int pCount = 0;
		while (!queue.isEmpty()) {
			AVLEntry<K, V> p = queue.poll();
			System.out.print(p);
			preCount--;
			if (p.left != null) {
				pCount++;
				queue.offer(p.left);
			}
			if (p.right != null) {
				pCount++;
				queue.offer(p.right);
			}
			if (preCount == 0) {
				preCount = pCount;
				pCount = 0;
				System.out.println();
			}
		}
	}
}
