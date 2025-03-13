#include <bits/stdc++.h>
using namespace std;

#define int long long

struct Node
{
    long long val;

    Node() : val(0) {}

    Node(long long p1) : val(p1) {}

    void merge(const Node &l, const Node &r)
    {
        val = l.val + r.val;
    }
};

struct Update
{
    long long val;

    Update() : val(0) {}

    Update(long long val1) : val(val1) {}

    void apply(Node &node, int start, int end)
    {
        node.val += val * (end - start + 1);
    }

    void combine(const Update &newUpdate, int start, int end)
    {
        val += newUpdate.val;
    }
};

struct LazySGT
{
    vector<Node> tree;
    vector<Update> updates;
    vector<bool> lazy;
    int n;

    LazySGT(int n, vector<long long> &arr)
    {
        this->n = n;
        tree.resize(4 * n);
        updates.resize(4 * n);
        lazy.resize(4 * n, false);
        build(0, n - 1, 1, arr);
    }

    void build(int start, int end, int index, vector<long long> &arr)
    {
        if (start == end)
        {
            tree[index] = Node(arr[start]);
            return;
        }

        int mid = (start + end) / 2;
        build(start, mid, 2 * index, arr);
        build(mid + 1, end, 2 * index + 1, arr);
        tree[index].merge(tree[2 * index], tree[2 * index + 1]);
    }

    void pushdown(int index, int start, int end)
    {
        if (lazy[index])
        {
            int mid = (start + end) / 2;
            apply(2 * index, start, mid, updates[index]);
            apply(2 * index + 1, mid + 1, end, updates[index]);
            updates[index] = Update(0);
            lazy[index] = false;
        }
    }

    void apply(int index, int start, int end, Update &u)
    {
        if (start != end)
        {
            lazy[index] = true;
            updates[index].combine(u, start, end);
        }
        u.apply(tree[index], start, end);
    }

    void update(int start, int end, int index, int left, int right, Update u)
    {
        if (start > right || end < left)
        {
            return;
        }

        if (start >= left && end <= right)
        {
            apply(index, start, end, u);
            return;
        }

        pushdown(index, start, end);
        int mid = (start + end) / 2;
        update(start, mid, 2 * index, left, right, u);
        update(mid + 1, end, 2 * index + 1, left, right, u);
        tree[index].merge(tree[2 * index], tree[2 * index + 1]);
    }

    Node query(int start, int end, int index, int left, int right)
    {
        if (start > right || end < left)
        {
            return Node();
        }

        if (start >= left && end <= right)
        {
            pushdown(index, start, end);
            return tree[index];
        }

        pushdown(index, start, end);
        int mid = (start + end) / 2;
        Node l = query(start, mid, 2 * index, left, right);
        Node r = query(mid + 1, end, 2 * index + 1, left, right);

        Node ans;
        ans.merge(l, r);
        return ans;
    }

    void makeUpdate(int left, int right, long long val)
    {
        Update newUpdate(val);
        update(0, n - 1, 1, left, right, newUpdate);
    }

    long long makeQuery(int left, int right)
    {
        return query(0, n - 1, 1, left, right).val;
    }
};

int32_t main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    int n, q;
    cin >> n >> q;

    vector<long long> x(n);
    for (int i = 0; i < n; i++)
    {
        cin >> x[i];
    }

    LazySGT lazy(n, x);

    while (q--)
    {
        int type;
        cin >> type;
        if (type == 1)
        {
            int a, b;
            long long u;
            cin >> a >> b >> u;
            lazy.makeUpdate(a - 1, b - 1, u);
        }
        else
        {
            int k;
            cin >> k;
            cout << lazy.makeQuery(k - 1, k - 1) << "\n";
        }
    }

    return 0;
}