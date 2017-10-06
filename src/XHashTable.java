public final class XHashTable {

	int size;
	Node[] buckets;
	int index;
	Node aNode8;
	Node aNode9;

	public XHashTable(final int int_0) {
		index = 0;
		size = int_0;
		buckets = new Node[int_0];

		for (int int_1 = 0; int_1 < int_0; int_1++) {
			final Node node_0 = buckets[int_1] = new Node();
			node_0.next = node_0;
			node_0.previous = node_0;
		}

	}

	public Node method515() {
		index = 0;
		return method516();
	}

	public Node get(final long long_0) {
		final Node node_0 = buckets[(int) (long_0 & size - 1)];

		for (aNode8 = node_0.next; node_0 != aNode8; aNode8 = aNode8.next) {
			if (aNode8.hash == long_0) {
				final Node node_1 = aNode8;
				aNode8 = aNode8.next;
				return node_1;
			}
		}

		aNode8 = null;
		return null;
	}

	public Node method516() {
		Node node_0;
		if ((index > 0) && (buckets[index - 1] != aNode9)) {
			node_0 = aNode9;
			aNode9 = node_0.next;
			return node_0;
		} else {
			do {
				if (index >= size) {
					return null;
				}

				node_0 = buckets[index++].next;
			} while (buckets[index - 1] == node_0);

			aNode9 = node_0.next;
			return node_0;
		}
	}

	void method517() {
		for (int int_0 = 0; int_0 < size; int_0++) {
			final Node node_0 = buckets[int_0];

			while (true) {
				final Node node_1 = node_0.next;
				if (node_1 == node_0) {
					break;
				}

				node_1.unlink();
			}
		}

		aNode8 = null;
		aNode9 = null;
	}

	public void put(final Node node_0, final long long_0) {
		if (node_0.previous != null) {
			node_0.unlink();
		}

		final Node node_1 = buckets[(int) (long_0 & size - 1)];
		node_0.previous = node_1.previous;
		node_0.next = node_1;
		node_0.previous.next = node_0;
		node_0.next.previous = node_0;
		node_0.hash = long_0;
	}

}
