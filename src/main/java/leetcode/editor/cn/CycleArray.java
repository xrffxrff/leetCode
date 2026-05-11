package leetcode.editor.cn;

/**
 方案一：end 指向最后一个有效元素
 空数组: start=0, end=0, count=0  ❌ 歧义：end=0但没有元素
 1个元素: [A], start=0, end=0, count=1  ✓
 3个元素: [A,B,C], start=0, end=2, count=3  ✓

 方案二：end 指向下一个可插入位置（推荐）
 空数组: start=0, end=0, count=0  ✓ 清晰
 1个元素: [A], start=0, end=1, count=1  ✓
 3个元素: [A,B,C], start=0, end=3, count=3  ✓
 */

public class CycleArray<T> {
	private T[] arr;
	private int start;
	private int end;
	private int count;
	private int size;

	public CycleArray() {
		this(1);
	}

	public CycleArray(int size) {
		this.arr = (T[]) new Object[size];
		this.start = 0;
		this.end = 0;
		this.count = 0;
		this.size = size;
	}

	private void resize(int newSize) {
		T[] newArr = (T[]) new Object[newSize];
		for (int i = 0; i < count; i++) {
			newArr[i] = arr[(start+i)%size];
		}
		arr = newArr;
		start = 0;
		end = count;
		size = newSize;
	}

	public void addFirst(T t) {
		if (count == size) {
			resize(size*2);
		}
		start = (start-1+size) % size;
		arr[start] = t;
		count++;
	}

	public void addLast(T t) {
		if (count == size){
			resize(size*2);
		}
		arr[end] = t;
		end = (end+1) % size;
		count++;
	}

	public void add(int index, T t) {
		if (index < 0 || index > count) {
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + count);
		}
		if (count == size) {
			resize(size*2);
		}
		if (index == 0) {
			addFirst(t);
			return;
		}
		if (index == count) {
			addLast(t);
			return;
		}

		// 判断 index 靠近哪一端，选择移动较少的方向
		 if (index <= count/2) {
			 start = (start-1+size) % size;
			 for (int i = 0; i < index; i++) {
				 arr[(start+i)%size] = arr[(start+i+1)%size];
			 }
		 } else {
			 for (int i = count; i > index; i--) {
				 arr[(start+i)%size]  = arr[(start+i-1)%size];
			 }
		 }
		 arr[(start+index)%size] = t;
		 count ++;
	}

	public void removeFirst() {
		if (count == 0) {
			throw new IllegalStateException("Array is empty");
		}
		T t = arr[start];
		arr[start] = null;
		start = (start + 1) % size;
		count--;
		// 如果数组元素数量减少到原大小的四分之一，则减小数组大小为一半
		if (count > 0 && count == size / 4) {
			resize(size / 2);
		}
	}

	public void removeLast() {
		if (count == 0) {
			throw new IllegalStateException("Array is empty");
		}
		// 因为 end 是开区间，所以先左移，再赋值
		end = (end - 1 + size) % size;
		arr[end] = null;
		count--;
		// 如果数组元素数量减少到原大小的四分之一，则减小数组大小为一半
		if (count > 0 && count == size / 4) {
			resize(size / 2);
		}
	}

	public void remove(int index) {
		if (index < 0 || index>=count) {
			throw new IllegalStateException("Index out of range");
		}
		if (index == 0) {
			removeFirst();
			return;
		}
		if (index == count - 1) {
			removeLast();
			return;
		}

		// 判断 index 靠近哪一端，选择移动较少的方向
		if (index <= count/2) {
			for (int i = index; i > 0; i--) {
				arr[(start+i)%size] = arr[(start+i-1)%size];
			}
			arr[start] = null;
			start = (start +1) % size;
		} else {
			for(int i = index; i<count-1;i++) {
				arr[(start+i)%size] = arr[(start+i+1)%size];
			}
			// 因为 end 是开区间，所以先左移，再赋值
			end = (end-1+size) % size;
			arr[end] = null;
		}
		count--;
		if (count > 0 && count == size / 4) {
			resize(size / 2);
		}
	}

	public T getFirst(){
		if (isEmpty()) {
			throw new IllegalStateException("Array is empty");
		}
		return arr[start];
	}

	public T getLast(){
		if (isEmpty()) {
			throw new IllegalStateException("Array is empty");
		}
		return arr[(end-1+size)%size];
	}

	public T get(int index) {
		if (index < 0 || index>=count) {
			throw new IllegalStateException("Index out of range");
		}
		return arr[(start+index)%size];
	}

	public void set(int index, T t) {
		if (index < 0 || index>=count) {
			throw new IllegalStateException("Index out of range");
		}
		arr[(start+index)%size] = t;
	}

	public int size() {
		return count;
	}

	public boolean isEmpty() {
		return count == 0;
	}

	public void print() {
		for (int i = 0; i<count ;i++) {
			System.out.print(arr[(start+i)%size]);
			if (i < count-1) {
				System.out.print(", ");
			}
		}
		System.out.println();
	}

	public static void main(String[] args) {
		CycleArray<Integer> cycleArray = new CycleArray<>();
		cycleArray.addFirst(1);
		cycleArray.addFirst(2);
		cycleArray.addFirst(3);
		cycleArray.print();

		cycleArray.removeFirst();
		cycleArray.print();

	}
}
