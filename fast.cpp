#include <bits/stdc++.h>
using namespace std;

#define int long long

struct LazySimple
{
    int n;
    vector<int> st;
    vector<pair<int, int>> lazy; // {start term, common difference}

    void init(int _n)
    {
        n = _n;
        st.assign(4 * n, 0);
        lazy.assign(4 * n, {0, 0});
    }

    int combine(int a, int b)
    {
        return a + b;
    }

    void push(int start, int end, int node)
    {
        if (lazy[node].second != 0)
        {
            int len = end - start + 1;
            st[node] += len * (lazy[node].first - lazy[node].second) + lazy[node].second * len * (len + 1) / 2;

            if (start != end)
            {
                int mid = (start + end) / 2;
                int diff = (mid + 1) - start;

                lazy[2 * node + 1].first += lazy[node].first;
                lazy[2 * node + 2].first += lazy[node].first + diff * lazy[node].second;
                lazy[2 * node + 1].second += lazy[node].second;
                lazy[2 * node + 2].second += lazy[node].second;
            }
            lazy[node] = {0, 0};
        }
    }

    void build(int start, int end, int node, vector<int> &v)
    {
        if (start == end)
        {
            st[node] = v[start];
            return;
        }
        int mid = (start + end) / 2;
        build(start, mid, 2 * node + 1, v);
        build(mid + 1, end, 2 * node + 2, v);
        st[node] = combine(st[2 * node + 1], st[2 * node + 2]);
    }

    int query(int start, int end, int l, int r, int node)
    {
        push(start, end, node);
        if (start > r || end < l)
            return 0;
        if (start >= l && end <= r)
            return st[node];

        int mid = (start + end) / 2;
        int q1 = query(start, mid, l, r, 2 * node + 1);
        int q2 = query(mid + 1, end, l, r, 2 * node + 2);
        return combine(q1, q2);
    }

    void update(int start, int end, int node, int l, int r, int value)
    {
        push(start, end, node);
        if (start > r || end < l)
            return;
        if (start >= l && end <= r)
        {
            lazy[node] = {start - l + 1, 1};
            push(start, end, node);
            return;
        }
        int mid = (start + end) / 2;
        update(start, mid, 2 * node + 1, l, r, value);
        update(mid + 1, end, 2 * node + 2, l, r, value);
        st[node] = combine(st[2 * node + 1], st[2 * node + 2]);
    }

    void build(vector<int> &v)
    {
        build(0, n - 1, 0, v);
    }

    int query(int l, int r)
    {
        return query(0, n - 1, l, r, 0);
    }

    void update(int l, int r, int x)
    {
        update(0, n - 1, 0, l, r, x);
    }
};

int32_t main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n, q;
    cin >> n >> q;

    vector<int> t(n);
    for (int i = 0; i < n; i++)
    {
        cin >> t[i];
    }

    LazySimple tree;
    tree.init(n);
    tree.build(t);

    while (q--)
    {
        int type, a, b;
        cin >> type >> a >> b;
        a--, b--; // 0-indexed
        if (type == 1)
        {
            tree.update(a, b, 1); // Range Update
        }
        else
        {
            cout << tree.query(a, b) << '\n'; // Range Sum Query
        }
    }

    return 0;
}