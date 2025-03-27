#include <bits/stdc++.h>
using namespace std;

typedef long long ll;
#define MOD 1000000007
#define INF 1000000000

struct Node {
    ll val;
    Node() : val(0) {}
    Node(ll v) : val(v) {}
    void merge(const Node &l, const Node &r) {
        val = max(l.val, r.val);
    }
};

struct Update {
    ll x;
    Update(ll v) : x(v) {}
    void apply(Node &a) {
        a.val = x;
    }
};

class SegTree {
private:
    vector<Node> tree;
    vector<ll> arr;
    int n, s;

    void build(int start, int end, int index) {
        if (start == end) {
            tree[index] = Node(arr[start]);
            return;
        }
        int mid = (start + end) / 2;
        build(start, mid, 2 * index);
        build(mid + 1, end, 2 * index + 1);
        tree[index].merge(tree[2 * index], tree[2 * index + 1]);
    }

    void update(int start, int end, int index, int queryIndex, Update u) {
        if (start == end) {
            u.apply(tree[index]);
            return;
        }
        int mid = (start + end) / 2;
        if (mid >= queryIndex) update(start, mid, 2 * index, queryIndex, u);
        else update(mid + 1, end, 2 * index + 1, queryIndex, u);
        tree[index].merge(tree[2 * index], tree[2 * index + 1]);
    }

    Node query(int start, int end, int index, int left, int right) {
        if (start > right || end < left) return Node();
        if (start >= left && end <= right) return tree[index];
        int mid = (start + end) / 2;
        Node l = query(start, mid, 2 * index, left, right);
        Node r = query(mid + 1, end, 2 * index + 1, left, right);
        Node ans;
        ans.merge(l, r);
        return ans;
    }

public:
    SegTree(int a_len, vector<ll> &a) {
        arr = a;
        n = a_len;
        s = 4 * n;
        tree.resize(s);
        build(0, n - 1, 1);
    }

    void makeUpdate(int index, ll x) {
        update(0, n - 1, 1, index, Update(x));
    }

    Node makeQuery(int left, int right) {
        return query(0, n - 1, 1, left, right);
    }
};

void solve() {
    int n, m;
    cin >> n >> m;
    vector<ll> arr(n);
    for (int i = 0; i < n; i++) cin >> arr[i];
    vector<ll> rm(m);
    for (int i = 0; i < m; i++) cin >> rm[i];

    SegTree tree(n, arr);
    for (int i = 0; i < m; i++) {
        ll x = rm[i];
        int low = 0, high = n - 1, ans = -1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (tree.makeQuery(low, mid).val >= x) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        if (ans == -1) cout << 0 << " ";
        else {
            cout << (ans + 1) << " ";
            tree.makeUpdate(ans, tree.makeQuery(ans, ans).val - x);
            arr[ans] -= x;
        }
    }
    cout << "\n";
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    solve();
    return 0;
}
