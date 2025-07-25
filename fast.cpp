#include <bits/stdc++.h>
using namespace std;

// Author : Sulabh Ambule

#define int long long
const int MOD = 1e9 + 7;
// const int MOD = 998244353;

void Accepted()
{
    string s;
    cin >> s;
    int n = s.length();
    int maxLength = 0;

    for (int len = 2; len <= n; len += 2)
    {
        for (int i = 0; i + len <= n; i++)
        {
            int start = i;
            int end = i + len - 1;
            int mid = (start + end) / 2;
            bool valid = true;

            for (int j = start; j <= mid; j++)
            {
                char c1 = s[j];
                char c2 = s[mid + 1 + (j - start)];
                if (c1 != '?' && c2 != '?' && c1 != c2)
                {
                    valid = false;
                    break;
                }
            }

            if (valid)
                maxLength = max(maxLength, len);
        }
    }

    cout << maxLength << '\n';
}

int32_t main()
{
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int t;
    cin >> t;
    while (t--)
    {
        Accepted();
    }

    return 0;
}
