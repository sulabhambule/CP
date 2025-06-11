#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

struct Node
{
    long long sum, prefix, suffix, maxSubarray;

    Node(long long value)
    {
        sum = prefix = suffix = maxSubarray = value;
    }

    Node()
    {
        sum = prefix = suffix = maxSubarray = 0;
    }

    void merge(const Node &left, const Node &right)
    {
        sum = left.sum + right.sum;
        prefix = max(left.prefix, left.sum + right.prefix);
        suffix = max(right.suffix, right.sum + left.suffix);
        maxSubarray = max({left.maxSubarray, right.maxSubarray, left.suffix + right.prefix});
    }
};

class SegTree
{
private:
    vector<Node> tree;
    vector<long long> arr;
    int n;

    void build(int start, int end, int index)
    {
        if (start == end)
        {
            tree[index] = Node(arr[start]);
            return;
        }
        int mid = (start + end) / 2;
        build(start, mid, 2 * index);
        build(mid + 1, end, 2 * index + 1);
        tree[index] = Node();
        tree[index].merge(tree[2 * index], tree[2 * index + 1]);
    }

    Node query(int start, int end, int index, int left, int right)
    {
        if (start > right || end < left)
            return Node();
        if (start >= left && end <= right)
            return tree[index];
        int mid = (start + end) / 2;
        Node l = query(start, mid, 2 * index, left, right);
        Node r = query(mid + 1, end, 2 * index + 1, left, right);
        Node res;
        res.merge(l, r);
        return res;
    }

public:
    SegTree(const vector<long long> &a)
    {
        arr = a;
        n = a.size();
        int size = 1;
        while (size < 2 * n)
            size <<= 1;
        tree.assign(size, Node());
        build(0, n - 1, 1);
    }

    Node makeQuery(int left, int right)
    {
        return query(0, n - 1, 1, left, right);
    }
};

int main()
{
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int n, m;
    cin >> n >> m;
    vector<long long> arr(n);
    for (int i = 0; i < n; ++i)
        cin >> arr[i];

    SegTree segTree(arr);

    for (int i = 0; i < m; ++i)
    {
        int a, b;
        cin >> a >> b;
        --a, --b; // 0-based indexing
        cout << max(0LL, segTree.makeQuery(a, b).maxSubarray) << '\n';
    }

    return 0;
}
