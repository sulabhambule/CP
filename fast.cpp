#include <bits/stdc++.h>
using namespace std;

#define int long long
#define fast_io                  \
    ios::sync_with_stdio(false); \
    cin.tie(0);                  \
    cout.tie(0);

bool isP(vector<int> &a, int k, int mex)
{
    int n = a.size();
    int count = 0;
    vector<int> freq(mex, 0);
    int cm = 0;
    vector<bool> seen(mex, false);

    for (int i = 0; i < n; i++)
    {
        int val = a[i];
        if (val < mex && !seen[val])
        {
            seen[val] = true;
            cm++;
        }

        if (cm == mex)
        {
            count++;
            cm = 0;
            fill(seen.begin(), seen.end(), false);
        }
    }

    return count >= k;
}

void solve()
{
    int n, k;
    cin >> n >> k;
    vector<int> a(n);
    for (int &x : a)
        cin >> x;

    int low = 0, high = n + 1, ans = 0;

    while (low <= high)
    {
        int mid = (low + high) / 2;
        if (isP(a, k, mid))
        {
            ans = mid;
            low = mid + 1;
        }
        else
        {
            high = mid - 1;
        }
    }

    cout << ans << '\n';
}

int32_t main()
{
    fast_io;
    int t;
    cin >> t;
    while (t--)
        solve();
}
