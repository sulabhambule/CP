#include <bits/stdc++.h>
using namespace std;

#define int long long

class SegmentTree
{
private:
    int size;
    vector<int> tree;

    void build(vector<int> &arr, int node, int start, int end)
    {
        if (start == end)
        {
            tree[node] = arr[start];
        }
        else
        {
            int mid = (start + end) / 2;
            build(arr, 2 * node + 1, start, mid);
            build(arr, 2 * node + 2, mid + 1, end);
            tree[node] = (tree[2 * node + 1] ^ tree[2 * node + 2]);
        }
    }

    void update(int node, int start, int end, int index, int value)
    {
        if (start == end)
        {
            tree[node] = value;
        }
        else
        {
            int mid = (start + end) / 2;
            if (index <= mid)
            {
                update(2 * node + 1, start, mid, index, value);
            }
            else
            {
                update(2 * node + 2, mid + 1, end, index, value);
            }
            tree[node] = (tree[2 * node + 1] ^ tree[2 * node + 2]);
        }
    }

    int query(int node, int start, int end, int l, int r)
    {
        if (r < start || end < l)
            return 0;
        if (l <= start && end <= r)
            return tree[node];
        int mid = (start + end) / 2;
        int leftSum = query(2 * node + 1, start, mid, l, r);
        int rightSum = query(2 * node + 2, mid + 1, end, l, r);
        return (leftSum ^ rightSum);
    }

public:
    SegmentTree(vector<int> &arr)
    {
        size = arr.size();
        tree.resize(4 * size);
        build(arr, 0, 0, size - 1);
    }

    void update(int index, int value)
    {
        update(0, 0, size - 1, index, value);
    }

    int query(int l, int r)
    {
        return query(0, 0, size - 1, l, r);
    }
};

int32_t main()
{
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int n, q;
    cin >> n >> q;
    vector<int> arr(n);
    for (int i = 0; i < n; i++)
    {
        cin >> arr[i];
    }

    SegmentTree segTree(arr);

    while (q--)
    {

        int l, r;
        cin >> l >> r;
        cout << segTree.query(l - 1, r - 1) << '\n';
    }

    return 0;
}