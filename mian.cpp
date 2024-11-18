#include <bits/stdc++.h>
using namespace std;

#ifndef ONLINE_JUDGE
// #include "debug.h"
#endif
#define endl "\n"
#define int long long
#define ff first
#define ss second
#define all(x) (x).begin(), (x).end()
typedef long long ll;
typedef unsigned long long ull;
typedef long double lld;

void solve()
{
    int n, m, L;
    cin >> n >> m >> L;

    vector<pair<int, int>> hh;

    for (int i = 0; i < n; ++i)
    {
        int x, y;
        cin >> x >> y;
        hh.push_back({x, y});
    }

    sort(all(hh));

    vector<vector<int>> mnn(n);

    int ptr = 0;
    for (int i = 0; i < m; ++i)
    {
        int x, y;
        cin >> x >> y;

        if (ptr == n)
            continue;

        if (x < hh[ptr].ff)
            mnn[ptr].push_back(y);
        else
        {
            ptr++;
            if (ptr == n)
                continue;
            mnn[ptr].push_back(y);
        }
    }

    int energy = 1;
    int ans = 0;
    for (auto &vec : mnn)
    {
        sort(all(vec), greater<int>());
        for (int i = 0; i < vec.size(); ++i)
        {
            if (i)
                vec[i] += vec[i - 1];
        }
    }

    for (int i = 0; i < n; ++i)
    {
        int req = hh[i].ss - hh[i].ff + 2;

        if (req <= energy)
            continue;
        else
            req -= energy;

        if (mnn[i].empty())
        {
            cout << -1 << endl;
            return;
        }

        if (mnn[i][mnn[i].size() - 1] < req)
        {
            cout << -1 << endl;
            return;
        }
        int idx = lower_bound(all(mnn[i]), req) - mnn[i].begin();

        if (idx == mnn[i].size())
        {
            cout << -1 << endl;
            return;
        }

        energy += mnn[i][idx];
        ans += idx + 1;
    }

    cout << ans << endl;
}

signed main()
{
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);

    int t;
    cin >> t;

    while (t--)
        solve();

    return 0;
}