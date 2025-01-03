#include <bits/stdc++.h>
using namespace std;

#define ll long long
#define f(i, a, b) for (int i = a; i <= b; i++)
#define ff(i, a, b) for (int i = a; i >= b; i--)
#define mod 1000000007

const int N = 1001;
vector<ll> vv(N, 1e5);

// ll solv(int ind,int opsrem,vector<ll> &c,vector<ll> &b,unordered_map<string,int> &dp){
//     int sz = b.size();
//     if(ind>=sz) return 0;

//     string ss = to_string(ind) + "-" + to_string(opsrem);

//     if(dp.count(ss)!=0) return dp[ss];

//     ll ans = 0;
//     ans = max(ans,solv(ind+1,opsrem,c,b,dp));
//     if(opsrem>=vv[b[ind]]) ans = max(ans,c[ind] + solv(ind+1,opsrem-vv[b[ind]],c,b,dp));

//     return dp[ss] = ans;
// }

void solve()
{

    ll n, k;
    cin >> n >> k;
    vector<ll> b(n), c(n);
    f(i, 0, n - 1) cin >> b[i];
    f(i, 0, n - 1) cin >> c[i];

    // max ops for any value -> 12
    // tot max ops req -> 12000
    // n*kmax = > 1000*12000 => 12*1e6

    // unordered_map<string,int> dp;
    ll mxopsreq = n * 12;
    ll finops = min(k, mxopsreq);
    // ll ans = solv(0,min(k,mxopsreq),c,b,dp);

    vector<ll> dp(finops + 2);

    ff(i, n - 1, 0)
    {
        vector<ll> ndp(finops + 2);
        f(j, 0, finops)
        {
            ndp[j] = dp[j];
            if (j >= vv[b[i]])
                ndp[j] = max(ndp[j], c[i] + dp[j - vv[b[i]]]);
        }
        dp = ndp;
    }

    cout << dp[finops] << endl;
}

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    vv[1] = 0;
    for (int i = 1; i <= 1e3; i++)
    {
        for (int j = 1; j <= i; j++)
        {
            int nxt = i + floor((double)i / (double)j);
            if (nxt > 1e3)
                continue;
            else
                vv[nxt] = min(vv[nxt], 1 + vv[i]);
        }
    }

    int T = 1;
    cin >> T;
    while (T--)
    {
        solve();
    }
    return 0;
}