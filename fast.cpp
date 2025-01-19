#include <bits/stdc++.h>
using namespace std;

using ll = long long;
#define int long long

#define endl '\n'
#define all(o) (o).begin(), (o).end()
#define allr(o) (o).rbegin(), (o).rend()
#define PB push_back
using state = pair<int, int>;
#define F first
#define S second
#define INF 1000000000LL
const int MOD = 1e9 + 7;
#define MP make_pair

void solve()
{
        int n;
        cin >> n;
        vector<int> v(n);

        v[0] = v[1] = v[n - 2] = 1;
        v[n - 1] = 2;

        for (int i = 2; i <= n - 3; i++)
   
    {
                v[i] = i;
           
    }

        for (int i = 0; i < n; i++)
   
    {
                cout << v[i] << " ";
           
    }
        cout << endl;
}

// Main function
int main()
{
        ios_base::sync_with_stdio(false);
        cin.tie(NULL);

        int t = 1;
        cin >> t;
        while (t--)
   
    {
                solve();
           
    }
}