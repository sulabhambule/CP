#include <bits/stdc++.h>
using namespace std;

struct Fenwick {
    int n;
    vector<int> bit;
    Fenwick(int n) : n(n), bit(n + 1, 0) {}

    void update(int idx, int val) {
        while (idx <= n) {
            bit[idx] += val;
            idx += idx & -idx;
        }
    }

    int prefix(int idx) {
        int sum = 0;
        while (idx > 0) {
            sum += bit[idx];
            idx -= idx & -idx;
        }
        return sum;
    }

    int query(int l, int r) {
        return prefix(r) - prefix(l - 1);
    }
};

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int n, q;
    cin >> n >> q;
    vector<long long> x(n + 1);
    for (int i = 1; i <= n; i++) cin >> x[i];

    vector<vector<pair<int, int>>> queries(n + 1);
    for (int i = 0; i < q; i++) {
        int a, b;
        cin >> a >> b;
        queries[a].push_back({b, i});
    }

    vector<int> ans(q);
    Fenwick ft(n);
    unordered_map<long long, int> last;

    for (int i = n; i >= 1; i--) {
        long long val = x[i];
        if (last.count(val)) {
            ft.update(last[val], -1); // remove previous occurrence
        }
        last[val] = i;
        ft.update(i, 1); // add current occurrence

        for (auto &qu : queries[i]) {
            int r = qu.first, idx = qu.second;
            ans[idx] = ft.query(i, r);
        }
    }

    for (int i = 0; i < q; i++) {
        cout << ans[i] << " ";
    }
    cout << "\n";

    return 0;
}
