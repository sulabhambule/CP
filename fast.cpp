    #include <bits/stdc++.h>
    using namespace std;
    #define int long long
    #define MOD 1000000007
    #define inf 1000000000

    void solve()
    {
        int n, k;
        long long x;
        cin >> n >> k >> x;
        vector<long long> a(n);

        for (int i = 0; i < n; i++)
        {
            cin >> a[i];
        }

        long long sum = accumulate(a.begin(), a.end(), 0LL);
        reverse(a.begin(), a.end());

        long long l = 1, h = n * k;
        long long ans = 0;

        while (l <= h)
        {
            int mid = (l + h) / 2;
            if ([&]
                {
                int nn = mid / n;
                int mn = mid % n;
                if (mn == 0) {
                    nn--;
                    mn = n;
                }
                long long remaining_x = x - (nn * sum);
                long long ss = 0;
                for (int i = 0; i < mn; i++) {
                    ss += a[i];
                    if (ss >= remaining_x) return true;
                }
                return ss >= remaining_x; }())
            {
                ans = mid;
                h = mid - 1;
            }
            else
            {
                l = mid + 1;
            }
        }

        if (ans == 0)
        {
            cout << 0 << '\n';
            return;
        }

        long long aa = (long long)n * k - ans + 1;
        cout << max(0LL, aa) << '\n';
    }

    int32_t main()
    {
        ios_base::sync_with_stdio(false);
        cin.tie(nullptr);

        int cp;
        cin >> cp;
        while (cp--)
        {
            solve();
        }

        return 0;
    }
