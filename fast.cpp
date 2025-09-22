#include <bits/stdc++.h>
using namespace std;

#define int long long

const int MAX_VAL = 1e6;

void solve()
{
    int n;
    cin >> n;
    vector<int> a(n);
    vector<int> cnt(MAX_VAL + 1, 0);

    for (int i = 0; i < n; i++)
    {
        cin >> a[i];
        cnt[a[i]]++;
    }

    long long ans = 0;

    for (int i = 0; i < n; i++)
    {
        int val = a[i];

        // (cnt[val] - 1) * (cnt[val] - 2)
        ans += (cnt[val] - 1) * 1LL * (cnt[val] - 2);

        // multiples
        for (int b = 2; 1LL * val * b * b <= MAX_VAL; b++)
        {
            ans += cnt[val * b] * 1LL * cnt[val * b * b];
        }
    }

    cout << ans << "\n";
}

signed main()
{
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int t;
    cin >> t;
    while (t--)
    {
        solve();
    }
}
