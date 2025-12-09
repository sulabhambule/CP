#include <bits/stdc++.h>
using namespace std;

#define ll long long
#define all(x) (x).begin(), (x).end()
const int MOD = 1e9 + 7;
const int MOD2 = 998244353;
const int INF = 1e9;
const ll NEG = -1e18;

struct FASTIO
{
    FASTIO()
    {
        ios::sync_with_stdio(false);
        cin.tie(nullptr);
    }
} fastio;

// ---- Utility Methods ----

int msb(int num) { return 31 - __builtin_clz(num); }
int msbll(long long num) { return 63 - __builtin_clzll(num); }

ll modPow(ll b, ll e, ll mod)
{
    ll r = 1;
    b %= mod;
    while (e > 0)
    {
        if (e & 1)
            r = (r * b) % mod;
        b = (b * b) % mod;
        e >>= 1;
    }
    return r;
}

ll modInverse(ll a, ll mod) { return modPow(a, mod - 2, mod); }

vector<int> inputIntArr(int n)
{
    vector<int> a(n);
    for (int i = 0; i < n; i++)
        cin >> a[i];
    return a;
}

// --------------- SOLVE FUNCTION ----------------

void solve()
{
    int n;
    cin >> n;

    vector<int> a = inputIntArr(2 * n);
    vector<int> count(200005, 0);

    for (int x : a)
        count[x]++;

    sort(count.begin(), count.end());

    int ans = 0;
    int one = 0, two = 0;

    for (int i = 1; i < 200000; i++)
    {
        if (count[i] == 0)
            continue;

        if (count[i] % 2 == 1)
        { // odd frequency
            ans++;
            int pp = count[i] / 2;
            int qq = pp + 1;

            if (n - one >= qq && n - two >= qq)
            {
                if (one >= two)
                {
                    two += qq;
                    one += pp;
                }
                else
                {
                    one += qq;
                    two += pp;
                }
            }
            else if (n - one >= qq)
            {
                one += qq;
                two += pp;
            }
            else
            {
                two += qq;
                one += pp;
            }
        }
        else
        { // even frequency
            if ((count[i] / 2) % 2 == 1)
            {
                ans += 2;
                one += count[i] / 2;
                two += count[i] / 2;
            }
            else
            {
                int pp = count[i] / 2 - 1;
                int qq = count[i] / 2 + 1;

                if (n - one >= qq && n - two >= qq)
                {
                    if (one >= two)
                    {
                        two += qq;
                        one += pp;
                    }
                    else
                    {
                        one += qq;
                        two += pp;
                    }
                    ans += 2;
                }
                else if (n - one >= qq)
                {
                    ans += 2;
                    one += qq;
                    two += pp;
                }
                else if (n - two >= qq)
                {
                    ans += 2;
                    two += qq;
                    one += pp;
                }
                else
                {
                    one += count[i] / 2;
                    two += count[i] / 2;
                }
            }
        }
    }

    cout << ans << "\n";
}

// ------------------ MAIN -------------------

int main()
{
    int t;
    cin >> t;
    while (t--)
        solve();
    return 0;
}
