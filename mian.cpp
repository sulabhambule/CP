#include <bits/stdc++.h>
using namespace std;

// Author : Sulabh Ambule
#define ll long long
#define MOD 1000000007
#define endl '\n'

void Accepted() {
    int n, m;
    ll k;
    cin >> n >> m >> k;

    vector<ll> a(n), b(m);

    // Input the desired apartment sizes
    for (int i = 0; i < n; i++) {
        cin >> a[i];
    }

    // Input the actual apartment sizes
    for (int i = 0; i < m; i++) {
        cin >> b[i];
    }

    // Sort both arrays
    sort(a.begin(), a.end());
    sort(b.begin(), b.end());

    // Two-pointer approach
    int i = 0, j = 0, ans = 0;
    while (i < n && j < m) {
        ll des = a[i];
        ll size = b[j];

        if (size >= des - k && size <= des + k) {
            ans++;
            i++;
            j++;
        } else if (size < des - k) {
            j++;
        } else {
            i++;
        }
    }

    cout << ans << endl;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    // int t;
    // cin >> t;
    // while (t--) {
    Accepted();
    // }

    return 0;
}
